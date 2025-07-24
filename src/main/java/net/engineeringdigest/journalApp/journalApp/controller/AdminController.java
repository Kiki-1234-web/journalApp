package net.engineeringdigest.journalApp.journalApp.controller;

import net.engineeringdigest.journalApp.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.journalApp.entity.User;
import net.engineeringdigest.journalApp.journalApp.services.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    AppCache appCache;

    @GetMapping("/allUsers")
    public ResponseEntity<?> getUsers(){
        if(userEntryService.getEntries()!=null) {
            return ResponseEntity.ok(userEntryService.getEntries());
        }else{
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createAdmin")
    public ResponseEntity<String> add(@RequestBody User user){
        userEntryService.saveAdmin(user);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @GetMapping("/clearAppCache")
    public void clearCache(){
        appCache.init();
    }
}
