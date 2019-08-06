package radvanfortrein.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Station")
public class Station {

	@Id
	private String naam;

	private String code;
	
	@OneToMany
	private Set<Trein> treinen;

	public Station() {
		this.treinen = new HashSet<>();
	}

	public Station(String naam) {
		this.naam = naam;
		this.treinen = new HashSet<>();
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

	public Set<Trein> getTreinen() {
		return treinen;
	}

	public void setTreinen(HashSet<Trein> treinen) {
		this.treinen = treinen;
	}
}
