package radvanfortrein.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import radvanfortrein.backend.model.Trein;

@Repository
public interface TreinRepository extends CrudRepository<Trein, String>{

}
