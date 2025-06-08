package net.engineeringdigest.journalApp.journalApp.controller;


import net.engineeringdigest.journalApp.journalApp.entity.Journals;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/_journal")
public class JournalController {

    Map<ObjectId, Journals> journalEntry = new HashMap<>();

    @GetMapping("/getAll")
    public List<Journals> getAll() {
        return new ArrayList<>(journalEntry.values());
    }

    @GetMapping("/{myId}")
    public Journals getById(@PathVariable Long myId){
        return journalEntry.get(myId);
    }

    @PostMapping("/addJournal")
    public void addJournal(@RequestBody Journals myEntry){
        journalEntry.put(myEntry.getId(), myEntry);
    }

    @DeleteMapping("/{myId}")
    public void deleteJournal(@PathVariable Long myId){
        journalEntry.remove(myId);
    }

    @PutMapping("/{myId}")
    public void updateJournal(@PathVariable ObjectId myId, @RequestBody Journals myEntry){
        journalEntry.put(myId, myEntry);
    }
}
