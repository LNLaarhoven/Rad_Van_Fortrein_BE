package radvanfortrein.backend.ConsoleMessages;

import java.time.LocalDateTime;

import radvanfortrein.backend.model.*;
import radvanfortrein.backend.schedule.*;

public class DebugMessages {
	// GIVES BACK A STATUS TO THE CONSOLE INFORMING IT THAT A TRAIN TIME HAS BEEN
	// UPDATED
	public void systemOutUpdate(Trein trein, Arrivals arrivals) {
		System.out.print("Update Trein: ");
		System.out.print("Origin: " + arrivals.getOrigin());
		System.out.print(" previous ActualDateTime: " + trein.getWerkelijkeAankomsten()[0]);
		System.out.println(" new ActualDateTime: " + arrivals.getActualDateTime());
		systemOutTreinTeLaat(arrivals);
	}

	// GIVES BACK A STATUS TO THE CONSOLE INFORMING IT THAT A NEW TRAIN HAS BEEN
	// MADE
	public void systemOutNieuwTrein(Arrivals arrivals) {
		System.out.print("Nieuwe Trein: ");
		System.out.print("Origin: " + arrivals.getOrigin());
		System.out.print(" Name: " + arrivals.getName());
		System.out.print(" PlannedDateTime: " + arrivals.getPlannedDateTime());
		System.out.println(" ActualDateTime: " + arrivals.getActualDateTime());
		systemOutTreinTeLaat(arrivals);
	}

	// GIVES BACK A STATUS TO THE CONSOLE INFORMING IT THAT A TRAIN IS LATER THAN
	// SCEDULED
	public void systemOutTreinTeLaat(Arrivals arrivals) {
		if (LocalDateTime.parse(arrivals.getPlannedDateTime())
				.isBefore(LocalDateTime.parse(arrivals.getActualDateTime()))) {
			System.out.println("TE LAAT! Dat gaat niet goed!");
		}
	}
}
