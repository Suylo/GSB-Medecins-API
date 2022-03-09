package fr.suylo.gsbmedecinsapi.service;

import fr.suylo.gsbmedecinsapi.entity.Departement;
import fr.suylo.gsbmedecinsapi.repository.DepartementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementService {

    private final DepartementRepository departementRepository;

    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public List<Departement> findAll(){
        return departementRepository.findAll();
    }

    public Optional<Departement> findDepartementById(Long id){
        return departementRepository.findById(id);
    }

    public List<Departement> findDepartementsByNom(String nom){
        return departementRepository.findByNom(nom);
    }

    public void deleteDepartement(Long id){
        departementRepository.deleteById(id);
    }
}
