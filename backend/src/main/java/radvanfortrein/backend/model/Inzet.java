package radvanfortrein.backend.model;

import java.util.Observable;
import java.util.Observer;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Inzet")
public class Inzet implements Observer{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JsonIgnoreProperties("inzetten")
	private Speler speler;
	@ManyToOne
	@JsonIgnoreProperties("inzetten")
	private Game game;
	
	private int inzetBedrag;
	
	private boolean isTeLaat;
	
	public boolean isTeLaat() {
		return isTeLaat;
	}

	public void setTeLaat(boolean isTeLaat) {
		this.isTeLaat = isTeLaat;
	}

	public int getInzetBedrag() {
		return inzetBedrag;
	}

	public void setInzetBedrag(int inzetBedrag) {
		this.inzetBedrag = inzetBedrag;
	}

	public Inzet() {
//		this( new Speler(), new Game(), 0, false);
	}
	
	public Inzet(Game game, int inzetBedrag, boolean isTeLaat) {
		this(new Speler(), game, inzetBedrag,  isTeLaat);
	}
	
	
	public Inzet(Speler speler, Game game, int inzetBedrag, boolean isTeLaat){
		this.speler = speler;
		this.game = game;
		this.setInzetBedrag(inzetBedrag);
		this.isTeLaat = isTeLaat;
		
		speler.addInzet(this);
		game.addInzet(this);
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
