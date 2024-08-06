package HistoryAppGradleSecurity.web;

import HistoryAppGradleSecurity.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticController {

    private final LogService logService;

    public StatisticController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/statistic")
    public String statistics(Model model){

        model.addAttribute("logs",
                logService.findAllLogs());

        return "statistic";
    }

}
