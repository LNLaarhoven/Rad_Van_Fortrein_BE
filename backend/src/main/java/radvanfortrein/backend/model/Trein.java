package radvanfortrein.backend.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Trein")
public class Trein {

	@Id
	private String naam;

	private String direction;
	private String[] geplandeAankomsten;
	private String[] werkelijkeAankomsten;

	// CONSTRUCTORS
	public Trein() {

	}

	public Trein(String naam, String direction, String[] geplandeAankomsten, String[] werkelijkeAankomsten) {
		this.naam = naam;
		this.direction = direction;
		this.geplandeAankomsten = geplandeAankomsten;
		this.werkelijkeAankomsten = werkelijkeAankomsten;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
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
		LocalDateTime aankomst = LocalDateTime.parse(geplandeAankomsten[0]);
		LocalDateTime werkelijkeAankomst = LocalDateTime.parse(werkelijkeAankomsten[0]);
		return (aankomst.isBefore(werkelijkeAankomst) && Math.abs(ChronoUnit.MINUTES.between(aankomst, werkelijkeAankomst)) > 0);
	}
}
