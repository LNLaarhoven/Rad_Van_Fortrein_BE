package radvanfortrein.backend.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import radvanfortrein.backend.model.Game;
import radvanfortrein.backend.model.Inzet;
import radvanfortrein.backend.model.Speler;
import radvanfortrein.backend.model.Station;
import radvanfortrein.backend.model.Trein;
import radvanfortrein.backend.repository.GameRepository;

@Service
@Transactional
public class GameService {
	
	@Autowired
	GameRepository gameRepository;
	@Autowired 
	InzetService inzetService;
	@Autowired
	SpelerService spelerService;
	@Autowired
	StationService stationService;
	@Autowired
	TreinService treinService;

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
	
	public Optional<Game> findByTrein(String trein) {
		return this.gameRepository.findByTrein(trein);
	}
	
	@PostConstruct 
	void initGameDatabase() {
		if (gameRepository.count()!=0) return;
		
		Game game = gameRepository.save(new Game());
		
		Speler gameSpeler = spelerService.save(new Speler("Barry"));
		
		Station gameStation = stationService.save(new Station("Amsterdam Centraal", "ASD"));
	}
}
