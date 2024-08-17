package HistoryAppGradleSecurity.web;

import HistoryAppGradleSecurity.exception.ObjectNotFoundException;
import HistoryAppGradleSecurity.model.view.ArticleViewModel;
import HistoryAppGradleSecurity.repository.ArticleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/articles")
public class ArticleRestController {

    private final ModelMapper modelMapper;

    private final ArticleRepository articleRepository;

    public ArticleRestController(ModelMapper modelMapper, ArticleRepository articleRepository) {
        this.modelMapper = modelMapper;
        this.articleRepository = articleRepository;
    }

    @GetMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<ArticleViewModel>> findAll(){
        List<ArticleViewModel> articleViewModels = articleRepository
                .findAll()
                .stream()
                .map(article -> {
                    ArticleViewModel viewModel = modelMapper.map(
                            article,ArticleViewModel.class);
                    viewModel.setContent(article.getContent());
                    return  viewModel;
                }).toList();

        return  ResponseEntity.ok()
                .body(articleViewModels);

    }

    @PutMapping("/api/{id}")
    public ResponseEntity<ArticleViewModel> updateArticle(@PathVariable Long id, @RequestBody ArticleViewModel articleViewModel) {
        return articleRepository.findById(id)
                .map(article -> {
                    article.setContent(articleViewModel.getContent());
                    articleRepository.save(article);
                    ArticleViewModel updatedViewModel = modelMapper.map(article, ArticleViewModel.class);
                    return ResponseEntity.ok(updatedViewModel);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseBody
    public NotFoundErrorInfo handleApiObjectNotFoundException(ObjectNotFoundException apiObjectNotFoundException) {
        return new NotFoundErrorInfo("NOT FOUND", apiObjectNotFoundException.getId());
    }


    public record NotFoundErrorInfo(String code, Object id) {

    }
}
