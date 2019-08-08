package radvanfortrein.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import radvanfortrein.backend.model.Trein;
import radvanfortrein.backend.repository.TreinRepository;

@Service
@Transactional
public class TreinService {

	@Autowired
	TreinRepository treinRepository;
	
	public Trein save(Trein trein) {
		return this.treinRepository.save(trein);
	}
	
	public Optional<Trein> findById(String naam) {
		return this.treinRepository.findById(naam);
	}
	
	public Iterable<Trein> findAll() {
		return this.treinRepository.findAll();
	}
	
	public void deleteById(String naam) {
		this.treinRepository.deleteById(naam);
	}
	
	public Iterable<Trein> findByOrigin(String origin) {
		return this.treinRepository.findByOrigin(origin);
	}
}
