package net.engineeringdigest.journalApp.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter public class WeatherResponse {
    private Current current;

    @Getter
    @Setter
    public class Current {
        @JsonProperty("temp_c")
        private int temperature;
        @JsonProperty("condition.text")
        private List<String> weatherDescriptions;
        private int feelslike;
    }


}