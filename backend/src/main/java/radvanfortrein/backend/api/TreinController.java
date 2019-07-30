package radvanfortrein.backend.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import radvanfortrein.backend.model.Trein;
import radvanfortrein.backend.service.TreinService;

@RestController
@RequestMapping (path = "api/treinen")
public class TreinController {

	@Autowired
	TreinService treinService;

	@PostMapping
	public ResponseEntity<Trein> apiCreate(@RequestBody Trein trein) {
		if (!trein.getNaam().equals("")) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(treinService.save(trein), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Iterable<Trein>> apiGetAll() {
		return new ResponseEntity<>(treinService.findAll(), HttpStatus.OK);
	}

	@GetMapping (path = "{naam}")
	public ResponseEntity<Optional<Trein>> apiGetById(@PathVariable String naam) {
		Optional<Trein> trein = treinService.findById(naam);
		return new ResponseEntity<>(trein, trein.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@DeleteMapping (path = "{naam}")
	public ResponseEntity<Trein> apiDeleteById(@PathVariable String naam) {
		if (!treinService.findById(naam).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			treinService.deleteById(naam);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

}