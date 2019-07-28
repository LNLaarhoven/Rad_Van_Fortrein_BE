package radvanfortrein.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import radvanfortrein.backend.model.Station;
import radvanfortrein.backend.repository.StationRepository;

@Service
@Transactional
public class StationService {
	
	@Autowired
	StationRepository stationRepository;
	
	public Station save(Station station) {
		return this.stationRepository.save(station);
	}
	
	public Optional<Station> findById(String code) {
		return this.stationRepository.findById(code);
	}
	
	public Iterable<Station> findAll() {
		return this.stationRepository.findAll();
	}
	
	public void deleteById(String code) {
		this.stationRepository.deleteById(code);
	}

}
