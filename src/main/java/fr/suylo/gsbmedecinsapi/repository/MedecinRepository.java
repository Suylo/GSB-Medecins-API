package fr.suylo.gsbmedecinsapi.repository;

import fr.suylo.gsbmedecinsapi.entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {

    /* Chercher un nom et prénom  */
    List<Medecin> findByNomAndPrenom(String prenom, String nom);
    List<Medecin> findBySpe(String spe);
}
