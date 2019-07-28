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
	private boolean teLaat;
	
	@ManyToOne
	private Speler speler;
	@ManyToOne
	private Game game;
	
	public Inzet() {}
	
	public boolean isTeLaat() {
		return teLaat;
	}
	
	public long getId() {
		return this.id;
	}

	public void setTeLaat(boolean teLaat) {
		this.teLaat = teLaat;
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
