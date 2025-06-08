package net.engineeringdigest.journalApp.journalApp.controller;


import net.engineeringdigest.journalApp.journalApp.entity.User;
import net.engineeringdigest.journalApp.journalApp.services.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("health-check")
    public String healthCheck(){
        return "Hello!!!!!!!!";
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> add(@RequestBody User user){
        userEntryService.saveNewEntry(user);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }
}
