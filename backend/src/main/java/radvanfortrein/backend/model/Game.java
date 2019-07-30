package radvanfortrein.backend.model;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "Games")
public class Game extends Observable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	private Trein trein;
	
	@OneToOne
	private Station station;
	
	@OneToMany
	@JsonIgnoreProperties("game")
	private Set<Inzet> inzetten = new HashSet<>();

	public Game() {
	}
	
	public Game(Trein trein, Station station) {
		this.trein = trein;
		this.station = station;
	}
	
	public long getId() {
		return this.id;
	}

	public Trein getTrein() {
		return trein;
	}

	public void setTrein(Trein trein) {
		this.trein = trein;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
	
	public Set<Inzet> getInzetten() {
		return inzetten;
	}

	public void setInzetten(Set<Inzet> inzetten) {
		this.inzetten = inzetten;
	}
	
	public boolean getResult(Trein trein ) {
		boolean result = false;
		for(Inzet i : inzetten){
			result = (trein.isTeLaat() == i.isTeLaat());
		}
		return result;
	}

	public boolean addInzet(Inzet inzet) {
		if (inzetten == null)
			inzetten = new HashSet<>();
		return inzetten.add(inzet);
		
	}
}
