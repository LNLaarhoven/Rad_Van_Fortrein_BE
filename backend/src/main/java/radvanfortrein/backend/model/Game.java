package radvanfortrein.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "Games")
public class Game {
	
	static final int RESULTAAT_NIET_BEKEND = 0;
	static final int RESULTAAT_TREIN_TE_LAAT = 1;
	static final int RESULTAAT_TREIN_OP_TIJD = 2;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String trein;
	
	private String station;
	
	@OneToMany //(cascade={CascadeType.MERGE})
	@JsonIgnoreProperties(value = {"game"}, allowSetters = true)
	private Set<Inzet> inzetten = new HashSet<>();
	
	private int resultaat;

	public Game() {
	}
	
	public Game(Trein trein, Station station) {
		this.trein = trein.getNaam();
		this.station = station.getCode();
		this.resultaat = Game.RESULTAAT_NIET_BEKEND;
	}
	
	public long getId() {
		return this.id;
	}

	public String getTrein() {
		return trein;
	}

	public void setTrein(String trein) {
		this.trein = trein;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}
	
	public Set<Inzet> getInzetten() {
		return inzetten;
	}

	public void setInzetten(Set<Inzet> inzetten) {
		this.inzetten = inzetten;
	}
	
	public int getResultaat() {
		return resultaat;
	}

	public void setResultaat(int resultaat) {
		this.resultaat = resultaat;
	}
	
	public boolean addInzet(Inzet inzet) {
		return this.inzetten.add(inzet);
	}
}
