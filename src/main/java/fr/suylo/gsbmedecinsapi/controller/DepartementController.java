package fr.suylo.gsbmedecinsapi.controller;

import fr.suylo.gsbmedecinsapi.entity.Departement;
import fr.suylo.gsbmedecinsapi.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departements")
public class DepartementController {

    private final DepartementService departementService;

    @Autowired
    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    @GetMapping
    public List<Departement> findAll() {
        return departementService.findAll();
    }

    @GetMapping("/{id}")
    public Departement get(@PathVariable("id") Long id){
        if (departementService.findDepartementById(id).isPresent()){
            return departementService.findDepartementById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
