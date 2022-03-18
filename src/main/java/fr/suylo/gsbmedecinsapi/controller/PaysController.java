package fr.suylo.gsbmedecinsapi.controller;

import fr.suylo.gsbmedecinsapi.entity.Departement;
import fr.suylo.gsbmedecinsapi.entity.Medecin;
import fr.suylo.gsbmedecinsapi.entity.Pays;
import fr.suylo.gsbmedecinsapi.service.DepartementService;
import fr.suylo.gsbmedecinsapi.service.MedecinService;
import fr.suylo.gsbmedecinsapi.service.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/v1/pays")
public class PaysController {

    private final PaysService paysService;
    private final DepartementService departementService;
    private final MedecinService medecinService;


    @Autowired
    public PaysController(PaysService paysService, DepartementService departementService, MedecinService medecinService) {
        this.paysService = paysService;
        this.departementService = departementService;
        this.medecinService = medecinService;
    }

    @GetMapping
    public List<Pays> findAll() {
        return paysService.findAllPays();
    }

    @GetMapping("/{id}")
    public Pays get(@PathVariable("id") Long id){
        Optional<Pays> paysOptional = paysService.findPaysById(id);
        if(paysOptional.isPresent()){
            return paysOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/departements")
    public List<Departement> getDepartements(@PathVariable("id") Long id){
        Optional<Pays> paysOptional = paysService.findPaysById(id);
        if (paysOptional.isPresent()){
            return departementService.findDepartementByPaysId(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/departements/medecins")
    public Set<Medecin> getMedecinFromPays(@PathVariable("id") Long id){
        Optional<Pays> paysOptional = paysService.findPaysById(id);
        List<Departement> departementOptional = departementService.findDepartementByPaysId(id);

        if (paysOptional.isPresent() && !departementOptional.isEmpty()){
            List<List> medecinsParDepartements = new ArrayList<>();
            Set<Medecin> tousLesMedecins = new HashSet<>();

            for (Departement d : departementOptional) {
                if(!medecinService.findByDepartementId(d.getId()).isEmpty()){
                    medecinsParDepartements.add(medecinService.findByDepartementId(d.getId()));
                    for (List list : medecinsParDepartements) {
                        for (Object med : list) {
                            tousLesMedecins.add((Medecin) med);
                        }
                    }
                }
            }
            return tousLesMedecins;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nom")
    public ResponseEntity<List<Pays>> getPaysByNom(@RequestParam String nom){
        return new ResponseEntity<>(paysService.findPaysByNom(nom), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePays(@PathVariable Long id){
        get(id);
        paysService.deletePaysById(id);
    }

    @PostMapping("/create")
    public void createNewPays(@RequestBody Pays newPays) {
        paysService.save(newPays);
    }

    @PutMapping("/edit/{id}")
    Pays replacePays(@RequestBody Pays newPays, @PathVariable Long id) {

        return paysService.findPaysById(id)
                .map(pays -> {
                    pays.setNom(newPays.getNom());
                    pays.setDepartements(newPays.getDepartements());
                    return paysService.save(pays);
                })
                .orElseGet(() -> {
                    return paysService.save(newPays);
                });
    }
}
