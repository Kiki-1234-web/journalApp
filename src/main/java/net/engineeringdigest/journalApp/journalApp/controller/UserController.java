package net.engineeringdigest.journalApp.journalApp.controller;

import net.engineeringdigest.journalApp.journalApp.entity.User;
import net.engineeringdigest.journalApp.journalApp.repository.UserEntryRepository;
import net.engineeringdigest.journalApp.journalApp.services.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    UserEntryRepository userEntryRepository;


    @GetMapping("/{userName}")
    public ResponseEntity<String> getUserByUserName(@PathVariable String userName){
        if(userEntryService.getEntry(userName)!=null) {
            return new ResponseEntity<>(String.valueOf(userEntryService.getEntry(userName)), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User dummy = userEntryService.findByUserName(userName);
        if(dummy!=null){
            dummy.setPassword(user.getPassword());
            dummy.setName(user.getName());
            userEntryService.saveUser(dummy);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body("User info updated!");
    }

    @DeleteMapping
    public ResponseEntity<String> delete(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User dummy = userEntryService.findByUserName(userName);
        if(dummy!=null) {
            userEntryRepository.deleteByUserName(userName);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
