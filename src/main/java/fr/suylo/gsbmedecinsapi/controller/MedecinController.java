package fr.suylo.gsbmedecinsapi.controller;

import fr.suylo.gsbmedecinsapi.entity.Medecin;
import fr.suylo.gsbmedecinsapi.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}

