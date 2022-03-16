package fr.suylo.gsbmedecinsapi.repository;

import fr.suylo.gsbmedecinsapi.entity.Departement;
import fr.suylo.gsbmedecinsapi.entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {

    /* Chercher un nom et prénom  */
    List<Medecin> findByNomOrPrenom(String prenom, String nom);
    List<Medecin> findByNom(String nom);
    List<Medecin> findBySpe(String spe);
    List<Medecin> findBySpeContaining(String spe);
    List<Medecin> findByNomContainingOrPrenomContaining(String nomLike, String prenomLike);
    void deleteById(Long id);
    List<Medecin> findByDepartement(Departement departement);
}
