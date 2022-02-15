package fr.suylo.gsbmedecinsapi.controller;

import fr.suylo.gsbmedecinsapi.entity.User;
import fr.suylo.gsbmedecinsapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> findAll(){
        return userService.findAllUsers();
    }

    @GetMapping("/{nom}")
    public ResponseEntity<List<User>> getUserByNom(@RequestParam String nom){
        return new ResponseEntity<>(userService.findByNom(nom), HttpStatus.OK);
    }
}
