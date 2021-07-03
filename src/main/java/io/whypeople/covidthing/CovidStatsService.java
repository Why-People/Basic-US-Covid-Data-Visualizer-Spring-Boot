package io.whypeople.covidthing;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CovidStatsService {

    private static final String COVID_API_URL = "https://api.covidtracking.com/v1";

    private final RestTemplate restTemplate;

    public List<CovidStatsModel> fetchUsStats() {
        return fetchData("/us/current.json", "Failed to Fetch US Data");
    }

    public List<CovidStatsModel> fetchStatesStats() {
        return fetchData("/states/current.json", "Failed to Fetch States Data");
    }

    private List<CovidStatsModel> fetchData(String url, String errorMessage) {
        // Api returns json array even for 1 element
        try {
            return restTemplate.exchange(
                    COVID_API_URL + url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CovidStatsModel>>() {}
            ).getBody();
        } catch (RestClientException e) {
            log.info(e.getMessage());
            // Fill in errorMessage field in CovidStatsModel if request fails
            return errorResponse(errorMessage);
        }
    }

    private List<CovidStatsModel> errorResponse(String message) {
        List<CovidStatsModel> temp = new ArrayList<>();
        temp.add(new CovidStatsModel(message));
        return temp;
    }

}
