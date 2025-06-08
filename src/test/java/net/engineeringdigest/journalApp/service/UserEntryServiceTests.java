package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.journalApp.JournalApplication;
import net.engineeringdigest.journalApp.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = JournalApplication.class)
@ActiveProfiles("dev")
public class UserEntryServiceTests {

    @Autowired
    UserEntryRepository userEntryRepository;


//    @BeforeAll, @BeforeEach, @AfterAll, @AfterEach

    @ParameterizedTest
//    @CsvSource({
//            "ram",
//            "kiki"
//    })
    @ValueSource(strings = {
            "ram",
            "kiki"
    })
    public void testFindByUserName(String name) {
        assertNotNull(userEntryRepository.findByUserName(name), "Failed for the name "+name);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,4,8"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
}

