package radvanfortrein.backend.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import radvanfortrein.backend.model.Station;
import radvanfortrein.backend.service.StationService;

@RestController
@RequestMapping (
		path = "api/stations",
		consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
		produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
		)
public class StationController {

	@Autowired
	StationService stationService;

	@PostMapping
	public ResponseEntity<Station> apiCreate(@RequestBody Station station) {
		if (!station.getNaam().equals("")) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(stationService.save(station), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Iterable<Station>> apiGetAll() {
		return new ResponseEntity<>(stationService.findAll(), HttpStatus.OK);
	}

	@GetMapping (path = "{code}")
	public ResponseEntity<Optional<Station>> apiGetById(@PathVariable String code) {
		Optional<Station> station = stationService.findById(code);
		return new ResponseEntity<>(station, station.isPresent() ? HttpStatus.OK :  HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(path = "{code}")
	public ResponseEntity<Station> apiUpdate(
			@PathVariable("code") String code,
			@RequestBody Station station) {
		if (station == null || station.getCode() != code) {
			return new ResponseEntity<>(
					HttpStatus.BAD_REQUEST);
		}
		
		Optional<Station> oldStation = stationService.findById(station.getCode());
		if (!oldStation.isPresent()) {
			return new ResponseEntity<>(
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(
				stationService.save(station),
				HttpStatus.OK);
				
	}

	@DeleteMapping (path = "{code}")
	public ResponseEntity<Station> apiDeleteById(@PathVariable String code) {
		if (!stationService.findById(code).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			stationService.deleteById(code);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}