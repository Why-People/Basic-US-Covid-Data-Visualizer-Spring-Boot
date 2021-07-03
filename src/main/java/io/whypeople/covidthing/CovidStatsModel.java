package io.whypeople.covidthing;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CovidStatsModel {

    private String state;
    private String errorMessage; // easier to handle with thymeleaf if this is in here
    private int positive;
    private int positiveIncrease;
    private int death;
    private int deathIncrease;
    private int recovered;

    public CovidStatsModel(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
