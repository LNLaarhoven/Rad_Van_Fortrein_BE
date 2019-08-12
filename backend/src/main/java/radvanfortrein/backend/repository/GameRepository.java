package radvanfortrein.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import radvanfortrein.backend.model.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Long>{
	
	Optional<Game> findByTrein(String trein);

}
