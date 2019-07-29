package radvanfortrein.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import radvanfortrein.backend.model.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Long>{

}
