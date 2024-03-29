package radvanfortrein.backend.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import radvanfortrein.backend.model.Trein;
import radvanfortrein.backend.service.TreinService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/treinen", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
public class TreinController {

	@Autowired
	TreinService treinService;

	@PostMapping
	public ResponseEntity<Trein> apiCreate(@RequestBody Trein trein) {
		Optional<Trein> inDataBaseTrein = this.treinService.findById(trein.getNaam());
		if (inDataBaseTrein.isPresent()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(treinService.save(trein), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Iterable<Trein>> apiGetAll() {
		Iterable<Trein> trein = treinService.findAll();
		ArrayList<Trein> treinenNaNu = new ArrayList<>();
		for (Trein t : trein) {
			if (LocalDateTime.now().isBefore(LocalDateTime.parse(t.getGeplandeAankomsten()[0]))) {
				treinenNaNu.add(t);
			}
		}
		return new ResponseEntity<>(treinenNaNu, HttpStatus.OK);
	}

	@GetMapping(path = "getAll")
	public ResponseEntity<Iterable<Trein>> apiGetTrueAll() {
		ArrayList<Trein> trein = (ArrayList<Trein>) treinService.findAll();
		return new ResponseEntity<>(trein, HttpStatus.OK);
	}

	@GetMapping(path = "{naam}")
	public ResponseEntity<Optional<Trein>> apiGetById(@PathVariable String naam) {
		Optional<Trein> trein = treinService.findById(naam);
		return new ResponseEntity<>(trein, trein.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@GetMapping(path = "{naam}/teLaat")
	public ResponseEntity<Boolean> apiGetTeLaat(@PathVariable String naam) {
		Optional<Trein> trein = treinService.findById(naam);
		return new ResponseEntity<>(trein.get().getTeLaat(), trein.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@PutMapping(path = "{naam}")
	public ResponseEntity<Trein> apiUpdate(@PathVariable("naam") String naam, @RequestBody Trein trein) {
		if (trein == null || !trein.getNaam().equals(naam)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Optional<Trein> oldTrein = treinService.findById(trein.getNaam());
		if (!oldTrein.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(treinService.save(trein), HttpStatus.OK);

	}

	@DeleteMapping(path = "{naam}")
	public ResponseEntity<Trein> apiDeleteById(@PathVariable String naam) {
		if (!treinService.findById(naam).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			treinService.deleteById(naam);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

}