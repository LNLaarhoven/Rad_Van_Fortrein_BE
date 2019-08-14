package radvanfortrein.backend.schedule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Payload {

	private Departures[] departures;

	public Departures[] getDepartures() {
		return departures;
	}

	public void setDepartures(Departures[] departures) {
		this.departures = departures;
	}
}
