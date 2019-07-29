package radvanfortrein.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import radvanfortrein.backend.model.Speler;
import radvanfortrein.backend.repository.SpelerRepository;

@Service
@Transactional
public class SpelerService {
	
	@Autowired
	SpelerRepository spelerRepository;
	
	public Speler save(Speler speler) {
		return this.spelerRepository.save(speler);
	}
	
	public Optional<Speler> findById(long id) {
		return this.spelerRepository.findById(id);
	}
	
	public Iterable<Speler> findAll() {
		return this.spelerRepository.findAll();
	}
	
	public void deleteById(long id) {
		this.spelerRepository.deleteById(id);
	}
}
