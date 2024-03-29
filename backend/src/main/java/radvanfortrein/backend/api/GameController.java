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

import radvanfortrein.backend.model.Game;
import radvanfortrein.backend.service.GameService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping (
		path = "api/games",
		produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
		)
public class GameController {

	@Autowired
	GameService gameService;

	@PostMapping
	public ResponseEntity<Game> apiCreate(@RequestBody Game game) {
		if (game.getId() != 0) {
			return new ResponseEntity<> (HttpStatus.CONFLICT);
		}
		return new ResponseEntity<> (gameService.save(game), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Iterable<Game>> apiGetAll() {
		return new ResponseEntity <> (gameService.findAll(), HttpStatus.OK);
	}

	@GetMapping (path = "{id}")
	public ResponseEntity<Optional<Game>> apiGetById(@PathVariable long id) {
		Optional<Game> game = gameService.findById(id);
		return new ResponseEntity<>(game, game.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@GetMapping (path = "trein/{treinNaam}")
	public ResponseEntity<Optional<Game>> apiGetByTrein(@PathVariable String treinNaam) {
		Optional<Game> game = this.gameService.findByTrein(treinNaam);
		return new ResponseEntity<>(game, game.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	
	@PutMapping(path = "{id}")
	public ResponseEntity<Game> apiUpdate(
			@PathVariable("id") long id,
			@RequestBody Game game) {
		if (game == null || game.getId() != id) {
			return new ResponseEntity<>(
					HttpStatus.BAD_REQUEST);
		}
		
		Optional<Game> oldGame = gameService.findById(game.getId());
		if (!oldGame.isPresent()) {
			return new ResponseEntity<>(
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(
				gameService.save(game),
				HttpStatus.OK);		
	}
	
	@PutMapping(path = "{id}/Resultaat")
	public ResponseEntity<Integer> apiUpdateResultaat(
			@PathVariable("id") long id,
			@RequestBody Integer resultaat) {
		if (resultaat < 0 || resultaat > 2) {
			return new ResponseEntity<>(
					HttpStatus.BAD_REQUEST);
		}
		
		Optional<Game> oldGame = gameService.findById(id);
		if (!oldGame.isPresent()) {
			return new ResponseEntity<>(
					HttpStatus.NOT_FOUND);
		}
		Game newGame = oldGame.get();
		newGame.setResultaat(resultaat);
		gameService.save(newGame);
		return new ResponseEntity<>(				
				resultaat,
				HttpStatus.OK);		
	}

	@DeleteMapping (path = "{id}")
	public ResponseEntity<Game> deleteById(@PathVariable long id) {
		if (!gameService.findById(id).isPresent()) {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		} else {
			gameService.deleteById(id);
			return new ResponseEntity<> (HttpStatus.OK);
		}
	}

}
