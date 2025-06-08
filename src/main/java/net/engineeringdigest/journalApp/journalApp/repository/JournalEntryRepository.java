package net.engineeringdigest.journalApp.journalApp.repository;


import net.engineeringdigest.journalApp.journalApp.entity.Journals;
import net.engineeringdigest.journalApp.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface JournalEntryRepository extends MongoRepository<Journals, ObjectId> {
}
