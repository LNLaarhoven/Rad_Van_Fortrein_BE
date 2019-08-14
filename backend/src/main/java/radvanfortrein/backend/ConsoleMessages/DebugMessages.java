package radvanfortrein.backend.ConsoleMessages;

import java.time.LocalDateTime;

import radvanfortrein.backend.model.*;
import radvanfortrein.backend.schedule.*;

public class DebugMessages {
	// GIVES BACK A STATUS TO THE CONSOLE INFORMING IT THAT A TRAIN TIME HAS BEEN
	// UPDATED
	public void systemOutUpdate(Trein trein, Departures departures) {
		System.out.print("Update Trein: ");
		System.out.print("Direction: " + departures.getDirection());
		System.out.print(" previous ActualDateTime: " + trein.getWerkelijkeAankomsten()[0]);
		System.out.println(" new ActualDateTime: " + departures.getActualDateTime());
		systemOutTreinTeLaat(departures);
	}

	// GIVES BACK A STATUS TO THE CONSOLE INFORMING IT THAT A NEW TRAIN HAS BEEN
	// MADE
	public void systemOutNieuwTrein(Departures departures) {
		System.out.print("Nieuwe Trein: ");
		System.out.print("Direction: " + departures.getDirection());
		System.out.print(" Name: " + departures.getName());
		System.out.print(" PlannedDateTime: " + departures.getPlannedDateTime());
		System.out.println(" ActualDateTime: " + departures.getActualDateTime());
		systemOutTreinTeLaat(departures);
	}

	// GIVES BACK A STATUS TO THE CONSOLE INFORMING IT THAT A TRAIN IS LATER THAN
	// SCEDULED
	public void systemOutTreinTeLaat(Departures departures) {
		if (LocalDateTime.parse(departures.getPlannedDateTime())
				.isBefore(LocalDateTime.parse(departures.getActualDateTime()))) {
			System.out.println("TE LAAT! Dat gaat niet goed!");
		}
	}
}
