package radvanfortrein.backend.schedule;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Departures {
	private String direction;
	private String name;
	private boolean cancelled;
	private LocalDateTime plannedDateTime;
	private LocalDateTime actualDateTime;

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public String getPlannedDateTime() {
		return plannedDateTime.toString();
	}

	public void setPlannedDateTime(String plannedDateTime) {
		this.plannedDateTime = LocalDateTime.parse(plannedDateTime.substring(0, plannedDateTime.indexOf('+')));
	}

	public String getActualDateTime() {
		return actualDateTime.toString();
	}

	public void setActualDateTime(String actualDateTime) {
		this.actualDateTime = LocalDateTime.parse(actualDateTime.substring(0, actualDateTime.indexOf('+')));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
