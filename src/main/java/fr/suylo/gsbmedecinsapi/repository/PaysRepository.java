package fr.suylo.gsbmedecinsapi.repository;

import fr.suylo.gsbmedecinsapi.entity.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {

}
