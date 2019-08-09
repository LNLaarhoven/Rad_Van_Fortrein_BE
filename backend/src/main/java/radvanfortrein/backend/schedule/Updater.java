package radvanfortrein.backend.schedule;

import radvanfortrein.backend.model.*;
import radvanfortrein.backend.repository.TreinRepository;
import radvanfortrein.backend.service.StationService;
import radvanfortrein.backend.service.TreinService;
import radvanfortrein.backend.ConsoleMessages.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.TimerTask;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class Updater extends TimerTask {

	long singleGameID = 1;
	String myStation = "ASD"; // THE NAME OF THE STATION AS NS KNOWS IT
	Station station = new Station(myStation); // MAKES A OBJECT OF THE STATION
	int maxJourneys = 50; // LISTS THE MAX AMOUNT OF JOURNEYS THE API MAY PULL
	String databaseTreinenUrl = "http://localhost:8080/api/treinen"; // ADDRESS OF OWN DATABASE
	String databaseGameUrl = "http://localhost:8080/api/games"; // ADDRESS OF OWN DATABASE
	String databaseSpelerUrl = "http://localhost:8080/api/spelers"; // ADDRESS OF OWN DATABASE
	String databaseInzetUrl = "http://localhost:8080/api/inzetten";
	DebugMessages c = new DebugMessages();

	boolean LIVE = true; // IF TRUE, IT WILL TRY TO CONNECT WITH THE DATABASE ELSE IT WILL SKIP THE
							// DATABASE COMMUNICATION FUNCTIONS

	// RUNS THIS FUNCTION WITH A SCEDULED TIMERTASK, ACTIVATION EVERY *** MILLIS
	public void run() {
		if (true) {
			updateAlleTreinen(LocalDateTime.now().toString(), myStation);
		}
	}

	// HANDLES ALL THE FUNCTIONS NEEDED TO GET TRAIN DATA AND SEND IT TO OUR OWN
	// DATABASE
	void updateAlleTreinen(String time, String station) {
		Trein[] trein = new Trein[0];
		// haalt alle treinen van de komende 2 uur van het aangegeven station
		Arrivals[] arrivals = StationTreinen(time, station);

//		Iterable<Trein> treinen = this.treinService.findAll();
		Iterable<Trein> treinen = ontvangTreinen(databaseTreinenUrl);
		for (Trein treinElement : treinen) {
			trein = ArrayUtils.add(trein, treinElement);
		}

//		trein = this.station.getTreinen();
		trein = handelUpdatesEnNieuweTreinen(arrivals, trein);

		String[] nieuweTreinen = new String[0];
		for (Trein treinElement : trein) {
			nieuweTreinen = ArrayUtils.add(nieuweTreinen, treinElement.getNaam());
		}
		this.station.setTreinen(nieuweTreinen);
	}

	// CONTROLS ALL THE TRAINS IT FOUND FROM THE NS API AND SHOWS IF THEY ARE NEW TO
	// THE PROGRAM AND WEITHER THEIR TIMES HAVE BEEN UPDATED
	private Trein[] handelUpdatesEnNieuweTreinen(Arrivals[] arrivals, Trein[] trein) {
		boolean updates = false;
		if (trein == null) {
			trein = new Trein[] { addNewTrain(arrivals[0]) };
			updates = true;
			c.systemOutNieuwTrein(arrivals[0]);
		}
		for (int i = 0; i < arrivals.length; i++) {

			boolean nieuweTrein = true;
			for (int j = 0; j < trein.length; j++) {
				if (arrivals[i].getName().equals(trein[j].getNaam())
						&& arrivals[i].getOrigin().equals(trein[j].getOrigin())) {
					/*
					 * Als een trein dezelfde origine heeft EN dezelfde naam, dan weet je zeker dat
					 * je het over dezelfde trein hebt
					 */
					/* check if the actualDateTime still matches, else update */
					nieuweTrein = false; /* Een bekende trein gevonden */
					if (LocalDateTime.parse(trein[j].getWerkelijkeAankomsten()[0])
							.compareTo(LocalDateTime.parse(arrivals[i].getActualDateTime())) > 0) {
						/*
						 * vergelijkt of de actuele tijd nog overeen komt met die van het object Trein
						 */
						updates = true;
						/* De tijd is verandert dus dit moet doorgegeven worden */
						c.systemOutUpdate(trein[j], arrivals[i]);
						/* Bericht voor de console dat een trein geupdate gaat worden. */
						trein[j].setWerkelijkeAankomsten(new String[] { arrivals[i].getActualDateTime() });
						/* Trein oject gaat veranderen met de nieuwe tijden er in. */
						verzenden(trein[j], databaseTreinenUrl + "/" + trein[j].getNaam(), HttpMethod.PUT);
						/*
						 * Het aangepaste trein object wordt verzonden naar de database met een PUT
						 * functie zodat deze weet dat het niet om een nieuwe trein gaat.
						 */
					}
				}
			}
			if (nieuweTrein) {
				/*
				 * In de vorige code is nieuwe trein niet vals gemaakt dus is er een nieuwe
				 * trein gevonden die niet overeen kwam met bekende data
				 */
				/* nieuwe trein */
				updates = true;
				/* Database gaat geupdate worden dus updates moet true zijn. */
				c.systemOutNieuwTrein(arrivals[i]);
				/* Communiceerd via de console dat er een nieuwe trein gemaakt gaat worden. */
				trein = ArrayUtils.addAll(trein, addNewTrain(arrivals[i]));
				/* Voegd een niewe trein toe aan de huidige array. */
			}
		}
		if (!updates) {
			System.out.println("No new updates");
		}

		for (int i = 0; i < trein.length; i++) {
			controlIfTimeIsPassed(trein[i]);
		}

		System.out.println("------");
		return trein;
	}

	// MAKES A NEW TRAIN OBJECT, CAN BE USED TO ADD TO A ARRAYLIST
	private Trein addNewTrain(Arrivals arrival) {
		String[] arrivalPlannedDateTime = new String[] { arrival.getPlannedDateTime() };
		String[] arrivalActualDateTime = new String[] { arrival.getActualDateTime() };
		Trein trein = new Trein(arrival.getName(), arrival.getOrigin(), arrivalPlannedDateTime, arrivalActualDateTime);
		// mag verbinden met de database (kan op false gezet worden als je wilt debuggen
		// zonder de database)
		try { // kijkt of een trein gepost kan worden
			verzenden(trein, databaseTreinenUrl, HttpMethod.POST);
			System.out.println("Trein " + trein.getNaam() + " wordt aangemaakt in de database.");
		} catch (HttpClientErrorException e) { // krijgt deze een error omdat de trein in de database al bestaat?
			// Dan probeert hij deze in de database te updaten ipv. opnieuw aan te maken
			try {
				verzenden(trein, databaseTreinenUrl + "/" + trein.getNaam(), HttpMethod.PUT);
				System.out.println(
						"Trein " + trein.getNaam() + " zat al in de database. Wordt nu geupdate ipv aangemaakt.");
			} catch (HttpClientErrorException n) {
				// lukt dit niet dan is er waarschijnlijk iets mis met de verbinding tussen de
				// database en app
				System.out.println("Trein " + trein.getNaam()
						+ " kon niet aangemaakt worden, er werd een poging gedaan om te updaten maar dit lukte ook niet!");
			}
		}
		return trein;
	}

	private void verzenden(Object object, String url, HttpMethod method) {
		if (LIVE) {
			HttpEntity<Object> entity = new HttpEntity<Object>(object);
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.exchange(url, method, entity, Object.class);
		}
	}

	// GETS ALL THE TRAINS IN THE COMING 2 HOURS FROM THE NS API (REQUIRES OBJECTS
	// TO FIT THE INCOMING DATA)
	private Arrivals[] StationTreinen(String time, String station) {
		try {
			// HTTP GET REQUIREMENTS
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", "application/json");
			headers.set("Ocp-Apim-Subscription-Key", "d5ae16043d844abc9ad1db166ae5145f");
			HttpEntity<String> entity = new HttpEntity<>(headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<ThisStation> response = restTemplate
					.exchange(
							"https://gateway.apiportal.ns.nl/public-reisinformatie/api/v2/arrivals?" + time
									+ "&maxJourneys=" + maxJourneys + "&lang=nl&station=" + station,
							HttpMethod.GET, entity, ThisStation.class);
			return response.getBody().getPayload().getArrivals();
		} catch (HttpServerErrorException e) {
			System.out.println(
					"EXCEPTION!: Het was niet mogelijk om de data van de NS binnen te halen. Controleer of je een goeie verbinding hebt(404). Het kan ook aan de API van de NS liggen (503).");
			return new Arrivals[0];
		}

	}

	private void controlIfTimeIsPassed(Trein trein) {
		int passedTimeLimit = 2;
		LocalDateTime nu = LocalDateTime.now();
		LocalDateTime treinTijd = LocalDateTime.parse(trein.getGeplandeAankomsten()[0]);
		if (treinTijd.isBefore(nu)) {
			// De trein zijn geplande tijd is verstreken

			// Als de de tijd tussen geplande tijd en nu minder is dan passedTimeLimit
			// minuten dan mag hij dit versturen naar de database
			if (ChronoUnit.MINUTES.between(treinTijd, nu) < passedTimeLimit) {
				Integer teLaat;
				if (trein.getTeLaat()) {
					teLaat = 1;
				} else {
					teLaat = 2;
				}
				System.out.println(
						"De aankomst tijd van " + trein.getNaam() + " is verstreken teLaat=" + trein.getTeLaat());
				verzenden(teLaat, databaseGameUrl + "/" + singleGameID + "/Resultaat", HttpMethod.PUT);
				verdeelInzet(trein.getTeLaat());
			}
		}
	}

	private void verdeelInzet(boolean teLaat) {
		if (LIVE) {
			// HTTP GET REQUIREMENTS
			HttpHeaders headers = new HttpHeaders();
			HttpEntity<String> entity = new HttpEntity<>(headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Game> response = restTemplate.exchange(databaseGameUrl + "/" + singleGameID, HttpMethod.GET,
					entity, Game.class);
			Game currentGame = response.getBody();
			ArrayList<Integer> optijdPool = new ArrayList<>();
			ArrayList<Integer> telaatPool = new ArrayList<>();
			int[] poolTotaal = new int[2];
			long[] inzetIds = currentGame.getInzetten();
			ArrayList<Inzet> inzetten = (ArrayList<Inzet>) ontvangInzetten(databaseInzetUrl);

			// Het wordt duidelijk hoeveel er wordt ingezet voor en tegen de aankomst van de
			// trein.
			for (int i = 0; i < inzetten.size(); i++) {
				System.out.println(i);
				if (inzetten.get(i).isInzetTeLaat()) {
					telaatPool.add(inzetten.get(i).getInzetBedrag());
				} else {
					optijdPool.add(inzetten.get(i).getInzetBedrag());
				}
			}

			// Voeg alle punten samen tot 1 geheel.
			for (int i = 0; i < telaatPool.size(); i++) {
				poolTotaal[0] += telaatPool.get(i);
			}
			for (int i = 0; i < optijdPool.size(); i++) {
				poolTotaal[1] += optijdPool.get(i);
			}

			// Totaal aantal punten is duidelijk nu kan de winst verdeeld worden over de
			// spelers.
			for (int i = 0; i < inzetten.size(); i++) {
				int punten = 0;
				boolean In = inzetten.get(i).isInzetTeLaat();
				if (In == teLaat) {
					// krijg het percentage van jouw inzet in de pool van de andere pool
					if (poolTotaal[b(In)] > 0 && inzetten.get(i).getInzetBedrag() > 0) {
						punten = (int) ((double) poolTotaal[b(!In)]
								* (double) (inzetten.get(i).getInzetBedrag() / (double) (poolTotaal[b(In)])));
					}
				} else {
					if (poolTotaal[b(!In)] > 0) {
						punten = -inzetten.get(i).getInzetBedrag();
					}
				}
				System.out.println("Speler " + inzetten.get(i).getSpeler() + " krijgt " + punten + " punten.");
				verzenden(inzetten.get(i).getSpeler(),
						databaseSpelerUrl + "/" + inzetten.get(i).getSpeler() + "/" + punten, HttpMethod.PUT);
			}
		}
	}

	private int b(boolean bool) {
		// pak aan java
		return bool ? 1 : 0;
	}

	private Iterable<Inzet> ontvangInzetten(String url) {
			// HTTP GET REQUIREMENTS
			HttpHeaders headers = new HttpHeaders();
			HttpEntity<String> entity = new HttpEntity<>(headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Iterable<Inzet>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<Iterable<Inzet>>() {
					});
			return response.getBody();
	}

	private Iterable<Trein> ontvangTreinen(String url) {
			// HTTP GET REQUIREMENTS
			HttpHeaders headers = new HttpHeaders();
			HttpEntity<String> entity = new HttpEntity<>(headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Iterable<Trein>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<Iterable<Trein>>() {
					});
			return response.getBody();
	}
}