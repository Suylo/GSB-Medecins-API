package fr.suylo.gsbmedecinsapi.controller;

import fr.suylo.gsbmedecinsapi.entity.Departement;
import fr.suylo.gsbmedecinsapi.entity.Medecin;
import fr.suylo.gsbmedecinsapi.entity.Pays;
import fr.suylo.gsbmedecinsapi.service.DepartementService;
import fr.suylo.gsbmedecinsapi.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/departements")
public class DepartementController {

    private final DepartementService departementService;
    private final MedecinService medecinService;

    @Autowired
    public DepartementController(DepartementService departementService, MedecinService medecinService) {
        this.departementService = departementService;
        this.medecinService = medecinService;
    }

    @GetMapping
    public List<Departement> findAll() {
        return departementService.findAll();
    }

    @GetMapping("/{id}")
    public Departement get(@PathVariable("id") Long id){
        Optional<Departement> departementOptional = departementService.findDepartementById(id);
        if (departementOptional.isPresent()){
            return departementOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/medecins")
    public List<Medecin> getMedecins(@PathVariable("id") Long id){
        Optional<Departement> departementOptional = departementService.findDepartementById(id);
        if (departementOptional.isPresent()){
            return medecinService.findByDepartement(departementOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nom")
    public ResponseEntity<List<Departement>> getDepartementByNom(@RequestParam String nom){
        return new ResponseEntity<>(departementService.findDepartementsByNom(nom), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteMedecin(@PathVariable Long id){

        get(id);
        departementService.deleteDepartement(id);
    }

    @PostMapping("/create")
    public void createNewDepartement(@RequestBody Departement newDepartement) {
        departementService.save(newDepartement);
    }

    @PutMapping("/edit/{id}")
    Departement replaceDepartement(@RequestBody Departement newDepartement, @PathVariable Long id) {

        return departementService.findDepartementById(id)
                .map(departement -> {
                    departement.setNom(newDepartement.getNom());
                    departement.setPays(newDepartement.getPays());
                    return departementService.save(departement);
                })
                .orElseGet(() -> {
                    return departementService.save(newDepartement);
                });
    }
}
