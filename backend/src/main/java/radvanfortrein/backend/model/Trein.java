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
	
	private String origin;
	private LocalDateTime[] geplandeAankomsten;
	private LocalDateTime[] werkelijkeAankomsten;
	private Station[] stations;

	// CONSTRUCTORS
	public Trein () {
		
	}
	
	public Trein(String naam, String origin, LocalDateTime[] geplandeAankomsten,
			LocalDateTime[] werkelijkeAankomsten, Station[] station) {
		this.naam = naam;
		this.origin = origin;
		this.geplandeAankomsten = geplandeAankomsten;
		this.werkelijkeAankomsten = werkelijkeAankomsten;
		this.stations = station;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public LocalDateTime[] getGeplandeAankomsten() {
		return geplandeAankomsten;
	}

	public void setGeplandeAankomsten(LocalDateTime[] geplandeAankomsten) {
		this.geplandeAankomsten = geplandeAankomsten;
	}
	
//	public void setSingleGeplandeAankomsten(LocalDateTime[] geplandeAankomsten, int i) {
//		this.geplandeAankomsten.set(i,geplandeAankomsten);
//	}

	public LocalDateTime[] getWerkelijkeAankomsten() {
		return werkelijkeAankomsten;
	}

	public void setWerkelijkeAankomsten(LocalDateTime[] werkelijkeAankomsten) {
		this.werkelijkeAankomsten = werkelijkeAankomsten;
	}
	
//	public void setSingleWerkelijkeAankomsten(int i, LocalDateTime geplandeAankomsten) {
//		this.geplandeAankomsten.set(i,geplandeAankomsten);
//	}

	public Station[] getStations() {
		return stations;
	}

	public void setStations(Station[] stations) {
		this.stations = stations;
	}
	
	public boolean getTeLaat(String station) {
		for(int i = 0; i < stations.length; i++) {
			if(stations[i].getNaam().equals(station)) {
				return geplandeAankomsten[i].isBefore(werkelijkeAankomsten[i]);
			}
		}
		return false;
	}
}
