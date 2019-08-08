package radvanfortrein.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Station")
public class Station {

	@Id
	private String code;
	private String naam;
	private String[] treinen;

	public Station() {

	}

	public Station(String naam) {
		this.naam = naam;
	}
	
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String[] getTreinen() {
		return treinen;
	}

	public void setTreinen(String[] treinen) {
		this.treinen = treinen;
	}
}
