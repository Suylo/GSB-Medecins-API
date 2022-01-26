package fr.suylo.gsbmedecinsapi.service;

import fr.suylo.gsbmedecinsapi.entity.Medecin;
import fr.suylo.gsbmedecinsapi.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedecinService {

    private final MedecinRepository medecinRepository;

    @Autowired
    public MedecinService(MedecinRepository medecinRepository) {
        this.medecinRepository = medecinRepository;
    }

    public List<Medecin> findAllMedecins() {
        return medecinRepository.findAll();
    }

    public Optional<Medecin> findMedecinById(Long id) {
        return medecinRepository.findById(id);
    }

    public List<Medecin> findByNomAndPrenom(String nom, String prenom){
        return medecinRepository.findByNomAndPrenom(nom, prenom);
    }

    public List<Medecin> findBySpe(String spe) {
        return medecinRepository.findBySpe(spe);
    }
}
