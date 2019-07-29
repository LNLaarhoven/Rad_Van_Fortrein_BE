package radvanfortrein.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import radvanfortrein.backend.model.Speler;

@Repository
public interface SpelerRepository extends CrudRepository<Speler, Long>{

}
