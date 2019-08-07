package radvanfortrein.backend.model;

import java.util.ArrayList;
import java.util.Arrays;
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
@Table(name = "Speler")
public class Speler {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String naam;
	
	private int totaalPunten = 50;
	
//	@JsonIgnoreProperties("speler")
//	private Set<Inzet> inzetten = new HashSet<>();
	private String[] inzetten;

	public Speler() {}
	
	public Speler(String naam) {
		this.naam = naam;
	}
	
	public long getId() {
		return this.id;
	}
	
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String[] getInzetten() {
		return inzetten;
	}

	public void setInzetten(String[] inzetten) {
		this.inzetten = inzetten;
	}

	public int getTotaalPunten() {
		return totaalPunten;
	}

	public void setTotaalPunten(int totaalPunten) {
		this.totaalPunten = totaalPunten;
	}
	
	public boolean addInzet(Inzet inzet) {
		ArrayList<String> strings = new ArrayList<>(Arrays.asList(this.inzetten));
		strings.add("" + inzet.getId());
		this.inzetten = strings.toArray(new String[strings.size()]);
		return true;
	}
}
