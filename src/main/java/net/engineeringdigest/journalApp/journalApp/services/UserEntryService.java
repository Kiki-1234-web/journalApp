package net.engineeringdigest.journalApp.journalApp.services;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.journalApp.entity.Journals;
import net.engineeringdigest.journalApp.journalApp.entity.User;
import net.engineeringdigest.journalApp.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntry;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewEntry(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userEntry.save(user);
            log.info("added new user");
            log.error("added new user");
            log.warn("added new user");
            log.debug("added new user");
            log.trace("added new user");
        } catch (Exception e) {
            log.error("error in saving new user {}", user.getUserName(),e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userEntry.save(user);
    }

    public void saveUser(User user){
        userEntry.save(user);
    }

    public List<User> getEntries(){
        return userEntry.findAll();
    }

    public Optional<User> getById(ObjectId id) {
        return userEntry.findById(id);
    }

    public User getEntry(String userName){
        return userEntry.findByUserName(userName);
    }
    public void deleteById(ObjectId id){
        userEntry.deleteById(id);
    }

    public boolean update(User user, String userName){
        User dummy = userEntry.findByUserName(userName);
        if(dummy==null){
            return false;
        }
        if(user.getName()!=null){
            dummy.setName(user.getName());
        }
        if(user.getPassword()!=null){
            dummy.setPassword(user.getPassword());
        }
        if(user.getUserName()!=null){
            dummy.setUserName(user.getUserName());
        }
        if(user.getJournals()!=null){
            dummy.setJournals(user.getJournals());
        }
        return true;
    }
    public User findByUserName(String userName){
        return userEntry.findByUserName(userName);
    }
}
