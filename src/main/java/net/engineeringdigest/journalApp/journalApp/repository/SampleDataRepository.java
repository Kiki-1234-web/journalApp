package net.engineeringdigest.journalApp.journalApp.repository;

import net.engineeringdigest.journalApp.journalApp.entity.SampleData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleDataRepository extends MongoRepository<net.engineeringdigest.journalApp.journalApp.entity.SampleData, String> {
}
