package net.engineeringdigest.journalApp.journalApp.services;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.journalApp.entity.SampleData;
import net.engineeringdigest.journalApp.journalApp.repository.SampleDataRepository;
import net.engineeringdigest.journalApp.journalApp.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class BenchmarkService {

    @Autowired
    private SampleDataRepository sampleRepo;

    @Autowired
    private RedisService redisService;

    public Map<String, Object> benchmark() {
        String redisKey = "sample_data";
        SampleData data = new SampleData("1", "test", "value");

        // 1. Save to Mongo
        sampleRepo.save(data);

        // 2. Save to Redis
        redisService.set(redisKey, data, 300L);

        // 3. Fetch from Mongo
        long mongoStart = System.nanoTime();
        Optional<SampleData> mongoResult = sampleRepo.findById("1");
        long mongoEnd = System.nanoTime();
        long mongoTime = (mongoEnd - mongoStart) / 1_000_000;

        // 4. Fetch from Redis
        long redisStart = System.nanoTime();
        SampleData redisResult = redisService.get(redisKey, SampleData.class);
        long redisEnd = System.nanoTime();
        long redisTime = (redisEnd - redisStart) / 1_000_000;

        // 5. Return result
        Map<String, Object> result = new HashMap<>();
        result.put("mongoTimeMs", mongoTime);
        result.put("redisTimeMs", redisTime);
        result.put("mongoHit", mongoResult.isPresent());
        result.put("redisHit", redisResult != null);
        return result;
    }
}
