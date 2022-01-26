package fr.suylo.gsbmedecinsapi.repository;

import fr.suylo.gsbmedecinsapi.entity.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {
}
