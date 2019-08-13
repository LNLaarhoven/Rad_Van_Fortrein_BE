package radvanfortrein.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Inzet")
public class Inzet{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JsonIgnoreProperties(value = {"inzetten"}, allowSetters = true)
	private Speler speler;
	
	@ManyToOne
	@JsonIgnoreProperties(value = {"inzetten"}, allowSetters = true)
	private Game game;
	
	private int inzetBedrag;
	
	private boolean inzetTeLaat;
	
	private int teWinnenBedrag;

	public Inzet() {
	}
	
	public Inzet(Game game, int inzetBedrag, boolean isTeLaat) {
		this(new Speler(), game, inzetBedrag,  isTeLaat);
	}
	
	public Inzet(Speler speler, Game game, int inzetBedrag, boolean isTeLaat){
		this.speler = speler;
		this.game = game;
		this.setInzetBedrag(inzetBedrag);
		this.inzetTeLaat = isTeLaat;
		
		speler.addInzet(this);
		game.addInzet(this);
	}
	
	public long getId() {
		return this.id;
	}

	public boolean isInzetTeLaat() {
		return inzetTeLaat;
	}

	public void setInzetTeLaat(boolean isTeLaat) {
		this.inzetTeLaat = isTeLaat;
	}

	public int getInzetBedrag() {
		return inzetBedrag;
	}

	public void setInzetBedrag(int inzetBedrag) {
		this.inzetBedrag = inzetBedrag;
	}
	
	public Speler getSpeler() {
		return speler;
	}


	public void setSpeler(Speler speler) {
		this.speler = speler;
	}


	public Game getGame() {
		return this.game;
	}


	public void setGame(Game game) {
		this.game = game;
	}
	
	public int getTeWinnenBedrag() {
		return teWinnenBedrag;
	}

	public void setTeWinnenBedrag(int teWinnenBedrag) {
		this.teWinnenBedrag = teWinnenBedrag;
	}

}
