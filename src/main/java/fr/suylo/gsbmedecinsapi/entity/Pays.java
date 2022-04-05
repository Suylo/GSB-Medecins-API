package fr.suylo.gsbmedecinsapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z\\sàâäéèêëîïôöùûüçÀÂÄÉÈÊËÎÏÔÖÙÛÜÇ-]{3,40}$", message = "Uniquement des lettres, (-) et espaces")
    @NotNull(message = "Le nom du pays est obligatoire")
    private String nom;
    /*
        Un pays a plusieurs département
     */
    @OneToMany(mappedBy = "pays", cascade = CascadeType.REMOVE)
    private Set<Departement> departements;

    public Pays(Long id, String nom, Set<Departement> departements) {
        this.id = id;
        this.nom = nom;
        this.departements = departements;
    }

    public Pays() {
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
    public Set<Departement> getDepartements() {
        return departements;
    }

    public void setDepartements(Set<Departement> departements) {
        this.departements = departements;
    }

    @Override
    public String toString() {
        return "Pays{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
