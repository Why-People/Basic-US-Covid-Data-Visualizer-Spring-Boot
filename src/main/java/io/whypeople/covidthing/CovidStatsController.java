package io.whypeople.covidthing;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class CovidStatsController {

    private final CovidStatsService covidStatsService;

    @GetMapping("/")
    public String index(Model pageModel) {
        List<CovidStatsModel> usModel = covidStatsService.fetchUsStats();
        pageModel.addAttribute("usStats", usModel.get(0));

        List<CovidStatsModel> statesModels = covidStatsService.fetchStatesStats();
        pageModel.addAttribute("statesStats", statesModels);

        return "index";
    }

}
