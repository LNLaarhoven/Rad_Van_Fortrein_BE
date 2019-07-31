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
	
	@PostConstruct 
	void initGameDatabase() {
		/*if (gameRepository.count()!=0) return;
		
		Trein gameTrein = treinService.save(new Trein("eerste trein"));
		Trein gameTrein2 = treinService.save(new Trein("treede trein"));
		
		Station gameStation = stationService.save(new Station("ZL", "Zwolle"));
		Station gameStation2 = stationService.save(new Station("BGN", "Bergen Op Zoom"));
		
		Game game = gameRepository.save(new Game(gameTrein, gameStation));
		Game game2 = gameRepository.save(new Game(gameTrein2, gameStation2));
		
		Speler gameSpeler = spelerService.save(new Speler("Frits"));
		Speler gameSpeler2 = spelerService.save(new Speler("Piet"));
		Speler gameSpeler3 = spelerService.save(new Speler("Kees"));
		Speler gameSpeler4 = spelerService.save(new Speler("Jan"));
		
		Inzet gameInzet = inzetService.save(new Inzet(gameSpeler, game, 5, false));
		Inzet gameInzet2 = inzetService.save(new Inzet(gameSpeler, game2, 10, false));
		Inzet gameInzet3 = inzetService.save(new Inzet(gameSpeler2, game, 5, true));
		Inzet gameInzet4 = inzetService.save(new Inzet(gameSpeler2, game2, 5, true));
		Inzet gameInzet5 = inzetService.save(new Inzet(gameSpeler3, game, 5, false));
		Inzet gameInzet6 = inzetService.save(new Inzet(gameSpeler3, game2, 10, false));
		Inzet gameInzet7 = inzetService.save(new Inzet(gameSpeler4, game, 5, true));
		Inzet gameInzet8 = inzetService.save(new Inzet(gameSpeler4, game2, 5, true));
		
		spelerService.save(gameSpeler);
		spelerService.save(gameSpeler2);
		spelerService.save(gameSpeler3);
		spelerService.save(gameSpeler4);
		gameRepository.save(game);
		gameRepository.save(game2);*/
	}
}
