package fr.suylo.gsbmedecinsapi.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Pattern(regexp = "^[A-Za-z\\sàâäéèêëîïôöùûüçÀÂÄÉÈÊËÎÏÔÖÙÛÜÇ]{2,20}$", message = "Le nom doit contenir entre 2 et 20 caractères, uniquement des lettres ou des espaces")
    private String nom;

    @Pattern(regexp = "^[a-zA-Z\\sàâäéèêëîïôöùûüçÀÂÄÉÈÊËÎÏÔÖÙÛÜÇ]{2,20}$", message = "Le prénom ne doit contenir que des lettres et des espaces")
    @NotNull(message = "Le prénom ne peut pas être vide")
    private String prenom;

    @Pattern(regexp = "^[a-zA-Z0-9\\sàâäéèêëîïôöùûüçÀÂÄÉÈÊËÎÏÔÖÙÛÜÇ,]{10,50}$", message = "Uniquement des lettres, chiffres et espaces")
    @NotNull(message = "L'adresse ne peut pas être vide")
    private String adresse;

    @Pattern(regexp = "^[0-9]{1,14}$", message = "Seulement des chiffres entre 1 et 14 caractères")
    @NotNull(message = "Le téléphone ne peut pas être vide")
    private String tel;

    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Uniquement des lettres et des espaces sont autorisés.")
    private String spe;
    /*
        Un Département à plusieurs médecins
     */
    @ManyToOne
    private Departement departement;

    public Medecin(Long id, String nom, String prenom, String adresse, String tel, String spe, Departement departement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.spe = spe;
        this.departement = departement;
    }

    public Medecin() {
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSpe() {
        return spe;
    }

    public void setSpe(String spe) {
        this.spe = spe;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

}
