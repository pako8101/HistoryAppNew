package HistoryAppGradleSecurity.web;


import HistoryAppGradleSecurity.model.AppUserDetails;
import HistoryAppGradleSecurity.service.PictureService;
import HistoryAppGradleSecurity.service.ShiftImageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    private final PictureService pictureService;
    private final ShiftImageService shiftImageService;

    public HomeController(PictureService pictureService, ShiftImageService shiftImageService) {
        this.pictureService = pictureService;
        this.shiftImageService = shiftImageService;
    }

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal AppUserDetails appUserDetails){

        if (appUserDetails != null) {
            model.addAttribute("fullName", appUserDetails.getFullName());
            model.addAttribute("age", appUserDetails.getAge());
        }
        model.addAttribute("firstImg", shiftImageService.firstImage());
        model.addAttribute("secondImg", shiftImageService.secondImage());
        model.addAttribute("thirdImg", shiftImageService.thirdImage());
        model.addAttribute("fourthImg", shiftImageService.fourthImage());

        model.addAttribute("pictures",pictureService.findAllUrls());

        return "index";
    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }
    @GetMapping("/explore")
    public String explore(){
        return "explore";
    }
    @GetMapping("/pages/all")
    public String all() {
        return "all";
    }

    @GetMapping("/pages/admins")
    public String admins() {
        return "admins";
    }

    @GetMapping("/pages/moderators")
    public String moderators() {
        return "moderators";
    }
    @GetMapping("/maps")
    public String maps(){
        return "maps";
    }
    @GetMapping("/time-line")
    public String timeLine(){
        return "time-line";
    }
    @GetMapping("/translations")
    public String translations(){
        return "translations";
    }
    @GetMapping("/teachingMaterials")
    public String teachingMaterials(){
        return "teachingMaterials";
    }

    @GetMapping("/school-subscriptions")
    public String schoolSubscriptions(){
        return "school-subscriptions";
    }

    @GetMapping("/our-team")
    public String ourTeam(){
        return "our-team";
    }

    @GetMapping("/membership")
    public String membership(){
        return "membership";
    }

}
