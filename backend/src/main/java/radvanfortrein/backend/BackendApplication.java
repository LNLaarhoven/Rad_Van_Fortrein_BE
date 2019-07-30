package radvanfortrein.backend;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import radvanfortrein.backend.model.Game;
import radvanfortrein.backend.model.Inzet;
import radvanfortrein.backend.model.Speler;
import radvanfortrein.backend.model.Station;
import radvanfortrein.backend.model.Trein;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
//		Scanner scanner = new Scanner(System.in);
//
//		Game game = new Game();
//		Station gameStation = game.getStation();
//		Trein gameTrein = game.getTrein();
//		gameStation.getTreinen().add(gameTrein);
//		gameTrein.getStations().add(gameStation);
//
//		System.out.println("Komt de trein om " + gameTrein.getGeplandeAankomsten().get(0).getHour() + ":"
//				+ gameTrein.getGeplandeAankomsten().get(0).getMinute() + " op station " + gameStation.getNaam()
//				+ "? (y/n)");
//
//		String input = scanner.nextLine();
//		Inzet inzet;
//
//		if (input.equals("y")) {
//			System.out.println("Hoeveel wil je inzetten?");
//			int inzetBedrag = Integer.parseInt(scanner.nextLine());
//			inzet = new Inzet(game, inzetBedrag, false);
//			game.getInzetten().add(inzet);
//			inzet.getSpeler().getInzetten().add(inzet);
//		} else {
//			System.out.println("Hoeveel wil je inzetten?");
//			int inzetBedrag = Integer.parseInt(scanner.nextLine());
//			inzet = new Inzet(game, inzetBedrag, true);
//			game.getInzetten().add(inzet);
//			inzet.getSpeler().getInzetten().add(inzet);
//		}
//		System.out.println("breakpoint");
//		//System.out.println(game.getResult(gameTrein));
//			gameTrein.compareTime(gameTrein.getGeplandeAankomsten().get(0), gameTrein.getWerkelijkeAankomsten().get(0));
//
//		if (game.getResult(gameTrein)) {
//			System.out.println("Je hebt goed gegokt!");
//			for (Inzet i : game.getInzetten()) {
//				//Je wint dubbele inzet
//				inzet.getSpeler().setTotaalPunten((i.getInzetBedrag() * 2) + inzet.getSpeler().getTotaalPunten());
//				System.out.println("Je hebt nu " + inzet.getSpeler().getTotaalPunten() + " punten");
//			}
//		} else {
//			System.out.println("Je hebt niet goed gegokt.");
//			System.out.println("Je hebt nogsteeds " + inzet.getSpeler().getTotaalPunten() + " punten");
//		}

		

	}

}
