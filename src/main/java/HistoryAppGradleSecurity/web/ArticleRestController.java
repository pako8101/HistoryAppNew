package HistoryAppGradleSecurity.web;

import HistoryAppGradleSecurity.exception.ObjectNotFoundException;
import HistoryAppGradleSecurity.model.view.ArticleViewModel;
import HistoryAppGradleSecurity.repository.ArticleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/api")

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
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseBody
    public NotFoundErrorInfo handleApiObjectNotFoundException(ObjectNotFoundException apiObjectNotFoundException) {
        return new NotFoundErrorInfo("NOT FOUND", apiObjectNotFoundException.getId());
    }


    public record NotFoundErrorInfo(String code, Object id) {

    }
}
