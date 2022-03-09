package fr.suylo.gsbmedecinsapi.controller;

import fr.suylo.gsbmedecinsapi.entity.Medecin;
import fr.suylo.gsbmedecinsapi.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medecins")
public class MedecinController {

    private final MedecinService medecinService;

    @Autowired
    public MedecinController(MedecinService medecinService) {
        this.medecinService = medecinService;
    }

    @GetMapping()
    public List<Medecin> findAll() {
        return medecinService.findAllMedecins();
    }

    @GetMapping("/{id}")
    public Medecin get(@PathVariable("id") Long id) {
        if(medecinService.findMedecinById(id).isPresent()){
            return medecinService.findMedecinById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nom")
    public ResponseEntity<List<Medecin>> getMedecinByNom(@RequestParam String nom){
        return new ResponseEntity<>(medecinService.findByNom(nom), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Medecin>> getMedecinByNomContaining(@RequestParam String nom, @RequestParam String prenom){
        return new ResponseEntity<>(medecinService.findByNomContainingOrPrenomContaining(nom, prenom), HttpStatus.OK);
    }

    @GetMapping("/specialite")
    public ResponseEntity<List<Medecin>> getMedecinBySpe(@RequestParam String spe){
        return new ResponseEntity<>(medecinService.findBySpeContaining(spe), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteMedecin(@PathVariable Long id) {
    get(id);
    medecinService.deleteMedecin(id);
    }
}

