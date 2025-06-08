package net.engineeringdigest.journalApp.journalApp.controller;

import net.engineeringdigest.journalApp.journalApp.entity.Journals;
import net.engineeringdigest.journalApp.journalApp.entity.User;
import net.engineeringdigest.journalApp.journalApp.services.JournalEntryService;
import net.engineeringdigest.journalApp.journalApp.services.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserEntryService userEntryService;

    // ✅ Get all journals for a specific user
    @GetMapping("/getJournal/{id}")
    public ResponseEntity<?> getAllByUserName(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userEntryService.findByUserName(userName);
        List<Journals>collect = user.getJournals().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()) {
            Optional<Journals>journalEntry = journalEntryService.getById(id);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // ✅ Get all journals in the system
    @GetMapping
    public ResponseEntity<List<Journals>> getAll() {
        return ResponseEntity.ok(journalEntryService.getEntries());
    }

    // ✅ Add a journal for a specific user
    @PostMapping("/addJournal")
    public ResponseEntity<String> addJournal(@RequestBody Journals journal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean success = journalEntryService.saveEntry(journal, userName);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Journal created");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    // ✅ Update a journal entry by ID
    @PutMapping("/{journalId}")
    public ResponseEntity<String> updateJournal(@PathVariable ObjectId journalId,
                                                @RequestBody Journals updatedJournal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userEntryService.findByUserName(userName);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        boolean updated = journalEntryService.update(journalId, updatedJournal);
        if (updated) {
            return ResponseEntity.ok("Journal updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Journal not found");
        }
    }

    // ✅ Delete a journal entry for a specific user
    @DeleteMapping("/{journalId}")
    public ResponseEntity<String> deleteJournal(@PathVariable ObjectId journalId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean deleted = journalEntryService.deleteEntry(journalId, userName);
        if (deleted) {
            return ResponseEntity.ok("Journal deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Journal not found");
        }
    }
}
