package radvanfortrein.backend.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Trein")
public class Trein {

	@Id
	private String naam;

	private String origin;
	private String[] geplandeAankomsten;
	private String[] werkelijkeAankomsten;

	// CONSTRUCTORS
	public Trein() {

	}

	public Trein(String naam, String origin, String[] geplandeAankomsten, String[] werkelijkeAankomsten) {
		this.naam = naam;
		this.origin = origin;
		this.geplandeAankomsten = geplandeAankomsten;
		this.werkelijkeAankomsten = werkelijkeAankomsten;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String[] getGeplandeAankomsten() {
		return geplandeAankomsten;
	}

	public void setGeplandeAankomsten(String[] geplandeAankomsten) {
		this.geplandeAankomsten = geplandeAankomsten;
	}

	public String[] getWerkelijkeAankomsten() {
		return werkelijkeAankomsten;
	}

	public void setWerkelijkeAankomsten(String[] werkelijkeAankomsten) {
		this.werkelijkeAankomsten = werkelijkeAankomsten;
	}

	public boolean getTeLaat() {
		return LocalDateTime.parse(geplandeAankomsten[0]).isBefore(LocalDateTime.parse(werkelijkeAankomsten[0]));
	}
}
