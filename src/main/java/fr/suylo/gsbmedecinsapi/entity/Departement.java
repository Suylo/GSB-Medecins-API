package fr.suylo.gsbmedecinsapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Departement {
    @Id
    private Long id;
    private String nom;
    @ManyToOne
    private Pays pays;
    @OneToMany(mappedBy = "departement")
    private Set<Medecin> medecins;


    public Departement(Long id, String nom, Pays pays, Set<Medecin> medecins) {
        this.id = id;
        this.nom = nom;
        this.pays = pays;
        this.medecins = medecins;
    }

    public Departement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @JsonBackReference
    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    @JsonManagedReference
    public Set<Medecin> getMedecins() {
        return medecins;
    }

    public void setMedecins(Set<Medecin> medecins) {
        this.medecins = medecins;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
