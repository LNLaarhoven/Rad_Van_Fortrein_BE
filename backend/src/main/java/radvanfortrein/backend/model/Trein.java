package radvanfortrein.backend.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Trein")
public class Trein {
	
	@Id
	private String naam;
	
	private String origin;
	private String[] geplandeAankomsten;
	private String[] werkelijkeAankomsten;

	@ManyToOne
	private Station station;

	// CONSTRUCTORS
	public Trein () {
		
	}
	
	public Trein(String naam, String origin, String[] geplandeAankomsten,
			String[] werkelijkeAankomsten) {
		this.naam = naam;
		this.origin = origin;
		this.geplandeAankomsten = geplandeAankomsten;
		this.werkelijkeAankomsten = werkelijkeAankomsten;
		this.station = new Station("Duivendrecht");
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
	
//	public void setSingleGeplandeAankomsten(String[] geplandeAankomsten, int i) {
//		this.geplandeAankomsten.set(i,geplandeAankomsten);
//	}

	public String[] getWerkelijkeAankomsten() {
		return werkelijkeAankomsten;
	}

	public void setWerkelijkeAankomsten(String[] werkelijkeAankomsten) {
		this.werkelijkeAankomsten = werkelijkeAankomsten;
	}
	
	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
	
//	public void setSingleWerkelijkeAankomsten(int i, String geplandeAankomsten) {
//		this.geplandeAankomsten.set(i,geplandeAankomsten);
//	}
	
	public boolean getTeLaat(String station) {

				return LocalDateTime.parse(geplandeAankomsten[0]).isBefore(LocalDateTime.parse(werkelijkeAankomsten[0]));

	}
}
