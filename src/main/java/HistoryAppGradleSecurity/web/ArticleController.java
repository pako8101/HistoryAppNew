package HistoryAppGradleSecurity.web;


import HistoryAppGradleSecurity.model.binding.ArticleAddBindingModel;
import HistoryAppGradleSecurity.model.binding.UploadPictureArticleBindingModel;
import HistoryAppGradleSecurity.model.entity.Article;
import HistoryAppGradleSecurity.model.enums.CategoryNameEnum;
import HistoryAppGradleSecurity.model.service.ArticleServiceModel;
import HistoryAppGradleSecurity.model.view.ArticleCategoryViewModel;
import HistoryAppGradleSecurity.model.view.ArticleDetailsViewModel;
import HistoryAppGradleSecurity.model.view.ArticleViewModel;
import HistoryAppGradleSecurity.repository.ArticleRepository;
import HistoryAppGradleSecurity.repository.UserRepository;
import HistoryAppGradleSecurity.service.ArticleService;
import HistoryAppGradleSecurity.session.LoggedUser;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;
private final UserRepository userRepository;
private final ArticleRepository articleRepository;

    public ArticleController(ArticleService articleService,
                             LoggedUser loggedUser, ModelMapper modelMapper, UserRepository userRepository, ArticleRepository articleRepository) {
        this.articleService = articleService;
        this.loggedUser = loggedUser;

        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @ModelAttribute
    public ArticleAddBindingModel articleAddBindingModel() {
        return new ArticleAddBindingModel();
    }

    @GetMapping("/all")
    public String allArticles(Model model) {

        List<ArticleViewModel> articleViewModelList =
                articleService.findAllArticlesView();
        model.addAttribute("articles", articleViewModelList);

        return "articles";
    }
    @PostMapping("/upload-picture")
    public ModelAndView uploadPicture(@Valid
                                      UploadPictureArticleBindingModel uploadPictureArticleBindingModel) {
        articleService.uploadPicture(uploadPictureArticleBindingModel);

        return new ModelAndView("redirect:/articles");
    }
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, Model model) {
        ArticleDetailsViewModel article =
                articleService.getDetails(id);

        if (article== null) throw  new NoSuchElementException();

        model.addAttribute("article",
                articleService.findArticleBId(id));

        return "article-details";
    }

    @GetMapping("/add")
    public String add() {
        if (loggedUser.getUsername() == null) {
            return "redirect:/users/login";

        }
        return "add-article";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ArticleAddBindingModel articleAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
    @AuthenticationPrincipal UserDetails principal){
        if (principal.getUsername() == null) {
            throw  new IllegalArgumentException();

        }

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("articleAddBindingModel",articleAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework" +
                    ".validation.BindingResult" +
                    ".articleAddBindingModel", bindingResult);
            return "redirect:add";
        }
        ArticleServiceModel articleServiceModel = modelMapper.map(articleAddBindingModel, ArticleServiceModel.class);
articleServiceModel.setCategories(articleAddBindingModel.getCategories());
        articleServiceModel.setAuthor(principal.getUsername());
articleServiceModel.setCreated(articleAddBindingModel.getCreated());

        articleService.addNewArticle(articleServiceModel);

        return "redirect:all";



    }

    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable Long id){
        articleService.delete(id);
        return "redirect:/articles/all";

    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ArticleAddBindingModel> deleteById(@PathVariable("id") Long id,
//                                                             @AuthenticationPrincipal UserDetails userDetails) {
//        articleService.delete(id);
//        return ResponseEntity
//                .noContent()
//                .build();
//    }


    @GetMapping("/{categoryName}")
    public ModelAndView getByCategory(@PathVariable("categoryName") CategoryNameEnum categoryName) {
        List<ArticleCategoryViewModel> articles = articleService.getAllByCategory(categoryName);

        String view =
                switch (categoryName) {
                    case WAR -> "war";
                    case POLITICAL -> "political";
                    case CULTURAL -> "cultural";
                    case ECONOMIC -> "economic";
                };

        ModelAndView modelAndView = new ModelAndView(view);

        modelAndView.addObject("articles", articles);

        return modelAndView;
    }

//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable Long id, Model model) {
//        Article article = articleService.findArticleById(id);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUsername = authentication.getName();
//
//        if (!article.getUser().getUsername().equals(currentUsername)) {
//            throw new RuntimeException("You are not authorized to edit this article.");
//        }
//
//        model.addAttribute("article", article);
//        return "edit-article";
//    }
//
//    @PostMapping("/{id}")
//    public String updateArticle(@PathVariable Long id, @ModelAttribute Article article) {
//        articleService.updateArticle(id, article);
//        return "redirect:/articles";
//    }



//      @ResponseStatus(code = HttpStatus.NOT_FOUND)
//  @ExceptionHandler(ObjectNotFoundException.class)
//  public ModelAndView handleObjectNotFound(ObjectNotFoundException onfe) {
//    ModelAndView modelAndView = new ModelAndView("article-not-found");
//    modelAndView.addObject("articleId", onfe.getId());
//
//    return modelAndView;
//  }
}
