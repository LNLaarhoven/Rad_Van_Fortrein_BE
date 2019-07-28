package radvanfortrein.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import radvanfortrein.backend.model.Game;
import radvanfortrein.backend.repository.GameRepository;

@Service
@Transactional
public class GameService {
	
	@Autowired
	GameRepository gameRepository;

	public Game save(Game game) {
		return this.gameRepository.save(game);
	}
	
	public Optional<Game> findById(long id) {
		return this.gameRepository.findById(id);
	}
	
	public Iterable<Game> findAll() {
		return this.gameRepository.findAll();
	}
	
	public void deleteById(long id) {
		this.gameRepository.deleteById(id);
	}
}
