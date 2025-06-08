package net.engineeringdigest.journalApp.journalApp.repository;

import net.engineeringdigest.journalApp.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepository extends MongoRepository<User, ObjectId> {

    public User findByUserName(String userName);
    public User deleteByUserName(String userName);
}