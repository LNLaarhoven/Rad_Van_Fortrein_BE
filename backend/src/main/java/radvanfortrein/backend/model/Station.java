package radvanfortrein.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Station")
public class Station {

	@Id
	private String naam;

	private String code;
	private Trein[] treinen;

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

	public Trein[] getTreinen() {
		return treinen;
	}

	public void setTreinen(Trein[] treinen) {
		this.treinen = treinen;
	}
}
