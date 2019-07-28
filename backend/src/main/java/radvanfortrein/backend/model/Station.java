package radvanfortrein.backend.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Station")
public class Station {

	@Id
	private String code;

	private String naam;
	private double longitude, latitude;

	private ArrayList<Trein> treinen;

	public Station() {
	}

	public Station(String code, String naam, double longitude, double latitude) {
		this.code = code;
		this.naam = naam;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public String getCode() {
		return this.code;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public ArrayList<Trein> getTreinen() {
		return treinen;
	}

	public void setTreinen(ArrayList<Trein> treinen) {
		this.treinen = treinen;
	}

}
