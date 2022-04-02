package fr.suylo.gsbmedecinsapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9\\sàâäéèêëîïôöùûüçÀÂÄÉÈÊËÎÏÔÖÙÛÜÇ-]{3,40}$", message = "Uniquement des lettres, (-) et espaces")
    @NotNull(message = "Le nom du département est obligatoire")
    private String nom;
    /*
        Un pays possède plusieurs départements
     */
    @ManyToOne
    private Pays pays;
    /*
        Un médecin possède un seul département
     */
    @OneToMany(mappedBy = "departement", cascade = CascadeType.REMOVE)
    private Set<Medecin> medecins;


    public Departement(Long id, String nom, Pays pays, Set<Medecin> medecins) {
        this.id = id;
        this.nom = nom;
        this.pays = pays;
        this.medecins = medecins;
    }

    public Departement(String nom, Pays pays){
        this.nom = nom;
        this.pays = pays;
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

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    @JsonBackReference
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
