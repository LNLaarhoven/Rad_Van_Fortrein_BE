package radvanfortrein.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import radvanfortrein.backend.model.Inzet;

@Repository
public interface InzetRepository extends CrudRepository<Inzet, Long>{

}
