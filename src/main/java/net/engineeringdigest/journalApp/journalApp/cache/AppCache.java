package net.engineeringdigest.journalApp.journalApp.cache;

import lombok.Data;
import net.engineeringdigest.journalApp.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Data
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    public Map<String, String> appCache;

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }

}
