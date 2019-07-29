package radvanfortrein.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import radvanfortrein.backend.model.Station;

@Repository
public interface StationRepository extends CrudRepository<Station, String> {

}
