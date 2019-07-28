package radvanfortrein.backend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Trein")
public class Trein {

	@Id
	private String naam;

	private ArrayList<LocalDateTime> geplandeAankomsten;
	private ArrayList<LocalDateTime> werkelijkeAankomsten;
	private ArrayList<Station> stations;

	public Trein() {
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
}
