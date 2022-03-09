package fr.suylo.gsbmedecinsapi.service;

import fr.suylo.gsbmedecinsapi.entity.Medecin;
import fr.suylo.gsbmedecinsapi.entity.Pays;
import fr.suylo.gsbmedecinsapi.repository.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaysService {
    private final PaysRepository paysRepository;

    @Autowired
    public PaysService(PaysRepository paysRepository) {
        this.paysRepository = paysRepository;
    }

    public List<Pays> findAllPays(){
        return paysRepository.findAll();
    }

    public Optional<Pays> findPaysById(Long id){
        return paysRepository.findById(id);
    }

    public List<Pays> findPaysByNom(String nom) {
        return paysRepository.findByNom(nom);
    }

    public void deletePaysById(Long id) {
        paysRepository.deleteById(id);
    }

    public Pays save(Pays newPays) {
        return paysRepository.save(newPays);
    }
}
