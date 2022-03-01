package fr.suylo.gsbmedecinsapi.controller;

import fr.suylo.gsbmedecinsapi.entity.Departement;
import fr.suylo.gsbmedecinsapi.entity.Pays;
import fr.suylo.gsbmedecinsapi.service.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pays")
public class PaysController {

    private final PaysService paysService;

    @Autowired
    public PaysController(PaysService paysService) {
        this.paysService = paysService;
    }

    @GetMapping
    public List<Pays> findAll() {
        return paysService.findAllPays();
    }

    @GetMapping("/{id}")
    public Pays get(@PathVariable("id") Long id){
        if(paysService.findPaysById(id).isPresent()){
            return paysService.findPaysById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nom")
    public ResponseEntity<List<Pays>> getPaysByNom(@RequestParam String nom){
        return new ResponseEntity<>(paysService.findPaysByNom(nom), HttpStatus.OK);
    }
}
