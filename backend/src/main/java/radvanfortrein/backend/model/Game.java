package radvanfortrein.backend.model;

import java.util.ArrayList;
import java.util.Arrays;
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

import org.apache.commons.lang3.ArrayUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "Games")
public class Game extends Observable {
	
	static final int RESULTAAT_NIET_BEKEND = 0;
	static final int RESULTAAT_TREIN_TE_LAAT = 1;
	static final int RESULTAAT_TREIN_OP_TIJD = 2;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String trein;
	
	private String station;
	
//	@OneToMany
//	@JsonIgnoreProperties("game")
	private long[] inzetten;
	
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
	
	public long[] getInzetten() {
		return inzetten;
	}

	public void setInzetten(long[] inzetten) {
		this.inzetten = inzetten;
	}
	
	public int getResultaat() {
		//this.getTreinResultaat(this.trein);
		return resultaat;
	}

	public void setResultaat(int resultaat) {
		this.resultaat = resultaat;
	}
	
	/*public void getTreinResultaat(Trein trein) {
		if (trein.isTeLaat()) {
			this.resultaat = Game.RESULTAAT_TREIN_TE_LAAT;
		} else {
			this.resultaat = Game.RESULTAAT_TREIN_OP_TIJD;
		}
	}*/

	public boolean addInzet(Inzet inzet) {
		this.inzetten = ArrayUtils.add(this.inzetten, inzet.getId());
		return true;
		
	}
}
