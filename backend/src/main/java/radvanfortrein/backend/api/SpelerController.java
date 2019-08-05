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

import radvanfortrein.backend.model.Speler;
import radvanfortrein.backend.service.SpelerService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(
		path = "api/spelers",
//		consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
		produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
		)
public class SpelerController {

	@Autowired
	SpelerService spelerService;

	@PostMapping
	public ResponseEntity<Speler> apiCreate(@RequestBody Speler speler) {
		if (speler.getId() != 0) {
			return new ResponseEntity<> (HttpStatus.CONFLICT);
		}
		return new ResponseEntity<> (spelerService.save(speler), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Iterable<Speler>> apiGetAll() {
		return new ResponseEntity<>(spelerService.findAll(), HttpStatus.OK);
	}

	@GetMapping (path = "{id}")
	public ResponseEntity<Optional<Speler>> apiGetById(@PathVariable long id) {
		Optional<Speler> speler = spelerService.findById(id);
		return new ResponseEntity<>(speler, speler.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(path = "{id}")
	public ResponseEntity<Speler> apiUpdate(
			@PathVariable("id") long id,
			@RequestBody Speler speler) {
		if (speler == null || speler.getId() != id) {
			return new ResponseEntity<>(
					HttpStatus.BAD_REQUEST);
		}
		
		Optional<Speler> oldSpeler = spelerService.findById(speler.getId());
		if (!oldSpeler.isPresent()) {
			return new ResponseEntity<>(
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(
				spelerService.save(speler),
				HttpStatus.OK);
				
	}

	@DeleteMapping (path = "{id}")
	public ResponseEntity<Speler> apiDeleteById(@PathVariable long id) {
		if (!spelerService.findById(id).isPresent()) {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		} else {
			spelerService.deleteById(id);
			return new ResponseEntity<> (HttpStatus.OK);
		}
	}
}