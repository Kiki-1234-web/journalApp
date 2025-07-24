package net.engineeringdigest.journalApp.journalApp.services;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {
        long startNano = System.nanoTime();
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        long endNano = System.nanoTime();
        long durationInMillis = (endNano - startNano) / 1_000_000;
        log.info("Weather API call took {} ms", durationInMillis);
        if (weatherResponse != null) {
            return weatherResponse;
        } else {
            long startNano1 = System.nanoTime();
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            long endNano1 = System.nanoTime();
            long durationInMillis1 = (endNano1 - startNano1) / 1_000_000;

            log.info("Weather API call took {} ms", durationInMillis1);
            if (body != null) {
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }

    }
}
