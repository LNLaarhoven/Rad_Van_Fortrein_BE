package radvanfortrein.backend.model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Trein")
public class Trein {

	@Id
	private String naam;

	// @TODO verander misschien object types
	private ArrayList<LocalDateTime> geplandeAankomsten;
	private ArrayList<LocalDateTime> werkelijkeAankomsten;
	private ArrayList<Station> stations;

	private boolean isTeLaat;

	public Trein() {
		this.naam = "test trein";
		this.geplandeAankomsten = new ArrayList<>();
		this.werkelijkeAankomsten = new ArrayList<>();
		this.stations = new ArrayList<>();
		this.geplandeAankomsten.add(LocalDateTime.now());
		this.werkelijkeAankomsten.add(this.addRandomTime());
	}

	public boolean isTeLaat() {
		return isTeLaat;
	}

	public void setTeLaat() {
		int geplandMin = this.geplandeAankomsten.get(0).getMinute();
		int werkelijkMin = this.werkelijkeAankomsten.get(0).getMinute();

		System.out.println("De geplande tijd is: " + geplandMin);
		System.out.println("De werkelijke tijd is: " + werkelijkMin);

		isTeLaat = !(geplandMin == werkelijkMin);

	}

	public String getNaam() {
		return this.naam;
	}

	public ArrayList<LocalDateTime> getGeplandeAankomsten() {
		return geplandeAankomsten;
	}

	public void setGeplandeAankomsten(ArrayList<LocalDateTime> geplandeAankomsten) {
		this.geplandeAankomsten = geplandeAankomsten;
	}

	public ArrayList<LocalDateTime> getWerkelijkeAankomsten() {
		return werkelijkeAankomsten;
	}

	public void setWerkelijkeAankomsten(ArrayList<LocalDateTime> werkelijkeAankomsten) {
		this.werkelijkeAankomsten = werkelijkeAankomsten;
	}

	public ArrayList<Station> getStations() {
		return stations;
	}

	public void setStations(ArrayList<Station> stations) {
		this.stations = stations;
	}

	public LocalDateTime createRandomTime() {
		Random random = new Random();
		int millisInDay = 24 * 60 * 60;

		return LocalDateTime.now().plusSeconds(random.nextInt(millisInDay));
	}

	public LocalDateTime addRandomTime() {
		Random random = new Random();
		int chance = random.nextInt(10);
		if (chance == 0) {
			return LocalDateTime.now().plusMinutes(5);
		} else {
			return LocalDateTime.now();
		}
	}

	public boolean compareTime(LocalDateTime geplandeTijd, LocalDateTime werkelijkeTijd) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String gepland = formatter.format(geplandeTijd);
		String werkelijk = formatter.format(werkelijkeTijd);
		
		isTeLaat = !(gepland.equals(werkelijk));
//		System.out.println(gepland);
//		System.out.println(werkelijk);
		
//		System.out.println(isTeLaat);

		return isTeLaat;

	}

}
