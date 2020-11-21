package main.controllers;

import lombok.extern.log4j.Log4j2;
import main.api.request.UserRequest;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("api/")
public class ApiGeneralController
{
    private final UserService userService;

    @Autowired
    public ApiGeneralController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("list")
    public ResponseEntity getUserList()
    {
        log.info("message");
        return userService.getAllUser();
    }

    @GetMapping("{id}")
    public ResponseEntity getUser(@PathVariable("id") int id)
    {
        log.info("message");
        return userService.getUserById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable("id") int id)
    {
        log.info("message");
        return new ResponseEntity(userService.deleteUserById(id), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity addUser(@RequestBody UserRequest userRequest)
    {
        log.info("message");
        return userService.addUser(userRequest);
    }

    @PutMapping("{id}")
    public ResponseEntity editUser(@PathVariable("id") int id,
                                   @RequestBody UserRequest userRequest)
    {
        log.info("message");
        return userService.editUser(id, userRequest);
    }
}