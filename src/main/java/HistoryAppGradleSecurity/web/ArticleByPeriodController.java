package HistoryAppGradleSecurity.web;

import HistoryAppGradleSecurity.model.enums.PeriodEnum;
import HistoryAppGradleSecurity.model.view.ArticleViewModel;
import HistoryAppGradleSecurity.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ArticleByPeriodController {
    private final ArticleService articleService;

    public ArticleByPeriodController(ArticleService articleService) {
        this.articleService = articleService;
    }
//    @ModelAttribute("articlesByPeriod")
//    public ArticleAddBindingModel articleAddBindingModel() {
//        return new ArticleAddBindingModel();
//    }

    @GetMapping("/articles/{period}")
    public ModelAndView getArticlesByPeriod(@PathVariable ("period") PeriodEnum period) {
        List<ArticleViewModel> articlesByPeriod = articleService
                .getArticleByPeriod(period);
        String view = "";
        switch (period){
            case EGYPT -> view ="egypt";
            case ANCIENT_GREECE -> view ="greece";
            case ROME_EMPIRE -> view ="rome";
            case STONE_AGE -> view ="stoneAge";
            case MESOPOTAMIA -> view ="mesopotamia";
            case CENTRAL_AMERICA -> view ="america";
            case ANDEAN_REGION -> view ="andean";
        }

        ModelAndView modelAndView = new ModelAndView(view);

        modelAndView.addObject("articlesByPeriod",articlesByPeriod);

        return modelAndView;
    }

    @GetMapping("/articlesByPeriods/stoneAge")
    public String stone(){
        return "articlesByPeriods/stoneAge";
    }

//    public String stone(@PathVariable ("period") PeriodEnum period, Model model) {
//        List<ArticleViewModel> articlesByPeriod = articleService
//                .getArticleByPeriod(period);
//        if (articlesByPeriod== null) throw  new NoSuchElementException();
//        String view = "";
//        switch (period){
//            case EGYPT -> view ="egypt";
//            case ANCIENT_GREECE -> view ="greece";
//            case ROME_EMPIRE -> view ="rome";
//            case STONE_AGE -> view ="stone";
//            case MESOPOTAMIA -> view ="mesopotamia";
//            case CENTRAL_AMERICA -> view ="america";
//            case ANDEAN_REGION -> view ="andean";
//        }
//        model.addAttribute(view);
//        model.addAttribute("articlesByPeriodStone",articlesByPeriod)  ;


    @GetMapping("/articlesByPeriods/andian")
    public String andian(){
        return "articlesByPeriods/andian";
    }
    @GetMapping("/articlesByPeriods/america")
    public String america(){
        return "articlesByPeriods/america";
    }
    @GetMapping("/articlesByPeriods/egypt")
    public String egypt(){
        return "articlesByPeriods/egypt";
    }
    @GetMapping("/articlesByPeriods/greece")
    public String greece(){
        return "articlesByPeriods/greece";
    }
    @GetMapping("/articlesByPeriods/mesopotamia")
    public String mesopotamia(){
        return "articlesByPeriods/mesopotamia";
    }
    @GetMapping("/articlesByPeriods/rome")
    public String rome(){
        return "articlesByPeriods/rome";
    }
}
