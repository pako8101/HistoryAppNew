package HistoryAppGradleSecurity.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class VideoController {
    @GetMapping("/video")
    public String showVideo(@RequestParam(name = "id", required = false, defaultValue = "8AgeNvHZ_ks") String videoId, Model model) {
        model.addAttribute("videoId", videoId);
        return "video";
    }
}
