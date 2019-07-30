package radvanfortrein.backend.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import radvanfortrein.backend.model.Game;
import radvanfortrein.backend.model.Inzet;
import radvanfortrein.backend.service.InzetService;

@RestController
@RequestMapping("api/inzetten")
public class InzetController {

	@Autowired
	InzetService inzetService;

	@PostMapping
	public ResponseEntity<Inzet> apiCreate(@RequestBody Inzet inzet) {
		if (inzet.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
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
