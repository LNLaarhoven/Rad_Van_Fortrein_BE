package radvanfortrein.backend.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import radvanfortrein.backend.model.Inzet;
import radvanfortrein.backend.model.Speler;
import radvanfortrein.backend.service.GameService;
import radvanfortrein.backend.service.InzetService;
import radvanfortrein.backend.service.SpelerService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(
		path = "api/inzetten",
//		consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
		produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
		)
public class InzetController {

	@Autowired
	InzetService inzetService;
	
	@Autowired
	SpelerService spelerService;
	
	@Autowired
	GameService gameService;

	@PostMapping
	public ResponseEntity<Inzet> apiCreate(@RequestBody Inzet inzet) {
//		Optional<Speler> mogelijkeSpeler = this.spelerService.findById(1);
//		System.out.println("Speler id is: " + inzet.getSpeler().getId());
		Speler speler = inzet.getSpeler();
//		if (mogelijkeSpeler.isPresent()) {
//			speler = mogelijkeSpeler.get();
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
		if (inzet.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		if (speler == null || inzet.getInzetBedrag() > speler.getTotaalPunten() || inzet.getInzetBedrag() < 1) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			/* needs testing */
			speler.setTotaalPunten(speler.getTotaalPunten() - inzet.getInzetBedrag());
//			spelerService.save(speler);
		}
		inzet.getGame().addInzet(inzet);
		inzet.getSpeler().addInzet(inzet);
		this.inzetService.save(inzet);
		this.gameService.save(inzet.getGame());
		this.spelerService.save(inzet.getSpeler());
		return new ResponseEntity<> (inzetService.save(inzet), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Iterable<Inzet>> apiGetAll() {
		return new ResponseEntity<>(inzetService.findAll(), HttpStatus.OK);
	}

	@GetMapping (path = "{id}")
	public ResponseEntity<Optional<Inzet>> apiGetById(@PathVariable long id) {
		Optional<Inzet> inzet = inzetService.findById(id);
		return new ResponseEntity<>(inzet, inzet.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(path = "{id}")
	public ResponseEntity<Inzet> apiUpdate(
			@PathVariable("id") long id,
			@RequestBody Inzet inzet) {
		if (inzet == null || inzet.getId() != id) {
			return new ResponseEntity<>(
					HttpStatus.BAD_REQUEST);
		}
		
		Optional<Inzet> oldInzet = inzetService.findById(inzet.getId());
		if (!oldInzet.isPresent()) {
			return new ResponseEntity<>(
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(
				inzetService.save(inzet),
				HttpStatus.OK);
				
	}

	@DeleteMapping (path = "{id}")
	public ResponseEntity<Inzet> apiDeleteById(@PathVariable long id) {
		if (!inzetService.findById(id).isPresent()) {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		} else {
			inzetService.deleteById(id);
			return new ResponseEntity<> (HttpStatus.OK);
		}
	}
}
