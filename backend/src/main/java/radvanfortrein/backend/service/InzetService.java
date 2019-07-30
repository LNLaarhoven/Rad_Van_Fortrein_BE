package radvanfortrein.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import radvanfortrein.backend.model.Inzet;
import radvanfortrein.backend.repository.InzetRepository;

@Service
@Transactional
public class InzetService {

	@Autowired
	InzetRepository inzetRepository;
	
	
	public Inzet save(Inzet inzet) {
		return this.inzetRepository.save(inzet);
	}
	
	public Optional<Inzet> findById(long id) {
		return this.inzetRepository.findById(id);
	}
	
	public Iterable<Inzet> findAll() {
		return this.inzetRepository.findAll();
	}
	
	public void deleteById(long id) {
		this.inzetRepository.deleteById(id);
	}
}
