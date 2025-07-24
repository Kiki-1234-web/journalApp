package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.journalApp.JournalApplication;
import net.engineeringdigest.journalApp.journalApp.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JournalApplication.class)
public class UserRepositoryImplTests {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Disabled("tested")
    @Test
    void testSaveNewUser() {
        Assertions.assertNotNull(userRepository.getUserForSA());
    }
}