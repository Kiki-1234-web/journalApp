package net.engineeringdigest.journalApp.journalApp.services;

import net.engineeringdigest.journalApp.journalApp.entity.Journals;
import net.engineeringdigest.journalApp.journalApp.entity.User;
import net.engineeringdigest.journalApp.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntry;

    @Autowired
    private UserEntryService userService;

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    /**
     * Save a journal entry and associate it with a user.
     */
    @Transactional
    public boolean saveEntry(Journals journalEntryObj, String userName) {
        try {
            User user = userService.findByUserName(userName);
            if (user == null) return false;

            Journals saved = journalEntry.save(journalEntryObj);
            user.getJournals().add(saved);
            userService.saveUser(user);
            return true;
        } catch (Exception e) {
            logger.info("error "+ e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Get all journal entries from the DB.
     */
    public List<Journals> getEntries() {
        return journalEntry.findAll();
    }

    /**
     * Get a journal entry by ID.
     */
    public Optional<Journals> getById(ObjectId id) {
        return journalEntry.findById(id);
    }

    /**
     * Delete a journal entry by ID and remove it from the associated user.
     */
    @Transactional
    public boolean deleteEntry(ObjectId id, String userName) {
        Optional<Journals> optionalJournal = journalEntry.findById(id);
        User user = userService.findByUserName(userName);

        if (optionalJournal.isPresent() && user != null) {
            Journals journalToDelete = optionalJournal.get();

            // Remove journal from user's list
            user.getJournals().removeIf(journal -> journal.getId().equals(journalToDelete.getId()));
            userService.saveUser(user);
            logger.info("logging in delete entry");
            // Delete from journal collection
            journalEntry.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Update an existing journal entry by ID.
     */
    @Transactional
    public boolean update(ObjectId id, Journals updatedEntry) {
        Optional<Journals> optionalJournal = journalEntry.findById(id);

        if (optionalJournal.isPresent()) {
            Journals existing = optionalJournal.get();
            existing.setTitle(updatedEntry.getTitle());
            existing.setContent(updatedEntry.getContent());
            journalEntry.save(existing);
            return true;
        }
        return false;
    }
}
