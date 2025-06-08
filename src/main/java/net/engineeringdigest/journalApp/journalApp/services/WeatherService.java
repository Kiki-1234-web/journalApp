package net.engineeringdigest.journalApp.journalApp.services;//package net.engineeringdigest.journalApp.journalApp.services;
//
//import net.engineeringdigest.journalApp.journalApp.api.response.WeatherResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class WeatherService {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    private static final finalAPI = "https://api.weatherapi.com/v1/current.json"
//
//
//    public WeatherResponse getWeather(String city) {
//            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, null, WeatherResponse.class);
//            WeatherResponse body = response.getBody();
//            return body;
//        }
//
//    }
//}

import net.engineeringdigest.journalApp.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String FINAL_API = "https://api.weatherapi.com/v1/current.json";

    public WeatherResponse getWeather(String city) {
        // Build URL with query parameters (Weather API typically expects GET, not POST)
        String url = FINAL_API + "?key=e218aac9b46f4ec988a140923250806&q=" + city;

        // For POST request (unusual for weather APIs — but showing it anyway)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<WeatherResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST, // typically this would be HttpMethod.GET
                request,
                WeatherResponse.class
        );

        return response.getBody();
    }
}
