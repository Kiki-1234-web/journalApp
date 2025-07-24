package net.engineeringdigest.journalApp.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
    private Current current;

    @Getter
    @Setter
    public static class Current {
        @JsonProperty("temp_c")
        private double temperature;

        @JsonProperty("feelslike_c")
        private double feelslike;

        private Condition condition;
    }

    @Getter
    @Setter
    public static class Condition {
        private String text;
        private String icon;
        private int code;
    }
}
