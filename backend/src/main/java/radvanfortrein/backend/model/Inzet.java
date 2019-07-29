package radvanfortrein.backend.model;

import java.util.Observable;
import java.util.Observer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Inzet")
public class Inzet implements Observer{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Speler speler;
	@ManyToOne
	private Game game;
	
	private int inzetBedrag;
	
	private boolean isTeLaat;
	
	public boolean isTeLaat() {
		return isTeLaat;
	}

	public void setTeLaat(boolean isTeLaat) {
		this.isTeLaat = isTeLaat;
	}

	public Inzet() {
		this(0, new Speler(), new Game(), 0, false);
	}
	
	public Inzet(Game game, int inzetBedrag, boolean isTeLaat) {
		this(0, new Speler(), game, inzetBedrag,  isTeLaat);
	}
	
	public Inzet(long id, Speler speler, Game game, int inzetBedrag, boolean isTeLaat){
		this.id = id;
		this.speler = speler;
		this.game = game;
		this.inzetBedrag = inzetBedrag;
		this.isTeLaat = isTeLaat;
	}
	
	public long getId() {
		return this.id;
	}


	public Speler getSpeler() {
		return speler;
	}


	public void setSpeler(Speler speler) {
		this.speler = speler;
	}


	public Game getGame() {
		return game;
	}


	public void setGame(Game game) {
		this.game = game;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
