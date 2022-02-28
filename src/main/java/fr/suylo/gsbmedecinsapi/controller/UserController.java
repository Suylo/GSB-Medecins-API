package fr.suylo.gsbmedecinsapi.controller;

import fr.suylo.gsbmedecinsapi.entity.User;
import fr.suylo.gsbmedecinsapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/{id}")
    public User get(@PathVariable("id") Long id){
        if(userService.findUserById(id).isPresent()){
            return userService.findUserById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<User> getUserByLogin(@RequestParam String login){
        return new ResponseEntity<User>(userService.findUserByLogin(login), HttpStatus.OK);
    }
}
