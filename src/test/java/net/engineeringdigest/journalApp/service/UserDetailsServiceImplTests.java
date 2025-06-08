//package net.engineeringdigest.journalApp.service;
//
//import net.engineeringdigest.journalApp.journalApp.repository.UserEntryRepository;
//import net.engineeringdigest.journalApp.journalApp.services.UserDetailsServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//
//import static org.mockito.Mockito.when;
//
//public class UserDetailsServiceImplTests {
//
//    @InjectMocks
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Mock
//    private UserEntryRepository userEntryRepository;
//
//    @BeforeEach
//    void setUp(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void loadByUserNameTest(){
//        when(userEntryRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().username("ram").password("rajfgds").roles(new ArrayList<>()).build());
//        UserDetails userDetails = userDetailsService.loadUserByUsername("ram");
//        Assertions.assertNotNull(userDetails);
//    }
//}
