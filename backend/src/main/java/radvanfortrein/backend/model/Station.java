package radvanfortrein.backend.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Station")
public class Station {

	@Id
	private String code;

	private String naam;

	private ArrayList<Trein> treinen; //@TODO verander misschien object type

	public Station() {
	}

	public Station(String code, String naam) {
		this.code = code;
		this.naam = naam;
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

	public ArrayList<Trein> getTreinen() {
		return treinen;
	}

	public void setTreinen(ArrayList<Trein> treinen) {
		this.treinen = treinen;
	}

}
