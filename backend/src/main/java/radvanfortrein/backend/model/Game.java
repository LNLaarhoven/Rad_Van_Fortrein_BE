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


@Entity
@Table(name = "Games")
public class Game extends Observable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String resultaat;
	
	@OneToOne
	private Trein trein;
	
	@OneToOne
	private Station station;
	
	@OneToMany
	private Set<Inzet> inzetten = new HashSet<>();
	
	public Game() {
		this(new Trein(), new Station());
	}
	
	public Game(Trein trein) {
		this(trein, new Station());
	}
	
	public Game(Station station) {
		this(new Trein(), station);
	}
	
	public Game(Trein trein, Station station) {
		this.resultaat = "Nog niet bekend";
		this.trein = trein;
		this.station = station;
	}
	
	public long getId() {
		return this.id;
	}

	public String getResultaat() {
		return resultaat;
	}

	public void setResultaat(String resultaat) {
		this.resultaat = resultaat;
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

}
