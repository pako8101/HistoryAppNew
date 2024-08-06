package HistoryAppGradleSecurity.service.impl;

import HistoryAppGradleSecurity.exception.ArticleNotFoundException;
import HistoryAppGradleSecurity.exception.ObjectNotFoundException;
import HistoryAppGradleSecurity.model.binding.UploadPictureArticleBindingModel;
import HistoryAppGradleSecurity.model.entity.Article;
import HistoryAppGradleSecurity.model.enums.CategoryNameEnum;
import HistoryAppGradleSecurity.model.enums.PeriodEnum;
import HistoryAppGradleSecurity.model.service.ArticleServiceModel;
import HistoryAppGradleSecurity.model.view.ArticleCategoryViewModel;
import HistoryAppGradleSecurity.model.view.ArticleDetailsViewModel;
import HistoryAppGradleSecurity.model.view.ArticleViewModel;
import HistoryAppGradleSecurity.repository.ArticleRepository;
import HistoryAppGradleSecurity.repository.UserRepository;
import HistoryAppGradleSecurity.service.ArticleService;
import HistoryAppGradleSecurity.service.CategoryService;
import HistoryAppGradleSecurity.service.UserService;
import HistoryAppGradleSecurity.service.assists.PictureAssistService;
import HistoryAppGradleSecurity.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);
    private final UserService userService;
    private final PictureAssistService pictureAssistService;
    private final CategoryService categoryService;
    private static final String BASE_IMAGES_PATH = ".\\src\\main\\resources\\static\\images\\";
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;
    private final Period deletePeriod;

    public ArticleServiceImpl(ArticleRepository articleRepository,
                              ModelMapper modelMapper,
                              UserService userService,
                              PictureAssistService pictureAssistService,
                              CategoryService categoryService,
                              LoggedUser loggedUser,
                              UserRepository userRepository,
                              @Value("${article.deletion}")Period deletePeriod ) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.pictureAssistService = pictureAssistService;
        this.categoryService = categoryService;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;

        this.deletePeriod = deletePeriod;
    }



    @Override
    public List<ArticleViewModel> findAllArticlesView() {
        return articleRepository
                .findAll()
                .stream()
                .map(article -> {
                    ArticleViewModel articleViewModel
                            = modelMapper.map(article, ArticleViewModel.class);

                    articleViewModel.setPictureUrl(article.getPictures()
                            .isEmpty() ? "images/rome.jpg" : article.getPictures().stream().findFirst()
                            .get().getUrl());
                    return articleViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public void addNewArticle(ArticleServiceModel articleServiceModel) {
        Article article = modelMapper.map(articleServiceModel,Article.class);

        //article.setAuthor(userService.findCurrentUserLoginEntity());

        article.setCategories(articleServiceModel.getCategories()
                .stream()
                .map(categoryService::findCategoryByName)
                .collect(Collectors.toSet()));

        articleRepository.save(article);
    }

    @Override
    public ArticleDetailsViewModel findArticleBId(Long id) {
        return articleRepository.findById(id)
                .map(article -> modelMapper.map(article, ArticleDetailsViewModel.class))
                .orElseThrow(()-> new ObjectNotFoundException("No such article with id " + id,id));
    }

    @Override
    public Article findArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("No such article with id " + id,id));
    }
    @Override
    public ArticleDetailsViewModel getDetails(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() ->
                        new ArticleNotFoundException("Article with id: " + id + " not found!"));

        return modelMapper.map(article, ArticleDetailsViewModel.class);
    }
    @Override
    @Transactional
    public void delete(Long id) {
       if (loggedUser.isAdmin()){
           articleRepository.deleteById(id);
       }

    }

    @Override
    public void uploadPicture(UploadPictureArticleBindingModel uploadPictureArticleBindingModel) {
        MultipartFile pictureFile = uploadPictureArticleBindingModel.getPicture();
        boolean isPrimary = uploadPictureArticleBindingModel.getPrimary();

        Article article = articleRepository.findById(uploadPictureArticleBindingModel.getId())
                .orElseThrow(() -> new ArticleNotFoundException("Article not found!"));

        String picturePath = getPicturePath(pictureFile, article.getTitle(), isPrimary);

        try {
            File file = new File(BASE_IMAGES_PATH + picturePath);
            file.getParentFile().mkdirs();
            file.createNewFile();

            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(pictureFile.getBytes());

            if (isPrimary) {
                article.setImageUrl(picturePath);
                articleRepository.save(article);
            } else {
                pictureAssistService.create(article, picturePath);
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }

    @Override
    public List<ArticleCategoryViewModel> getAllByCategory(CategoryNameEnum categoryName) {
        List<Article> articles = articleRepository.findAllByCategories_Name(categoryName);
        List<ArticleCategoryViewModel> viewArticles = articles.stream()
                .map(a -> modelMapper.map(a, ArticleCategoryViewModel.class))
                .toList();

        return viewArticles;
    }

    @Override
    public Optional<ArticleViewModel> findLatestArticle() {
        return articleRepository.
                findTopByOrderByCreatedDesc().
                map(ae -> {
                    ArticleViewModel avm = modelMapper.map(ae, ArticleViewModel.class);
                    avm.setAuthor(ae.getAuthor());
                    return avm;
                });
    }

    @Override
    public List<ArticleViewModel> getArticleByPeriod(PeriodEnum period) {
        List<Article> allByPeriodName = articleRepository
                .findAllByPeriod(period);

        return allByPeriodName.stream()
                .map(article -> modelMapper.map(article, ArticleViewModel.class))
                .toList();
    }

    @Override
    public void deleteOldArticles() {
//        Instant now = Instant.now();
        LocalDate deleteBefore = LocalDate.now().minus(deletePeriod);
    LOGGER.info("Delete all articles before " + deleteBefore);
    articleRepository.deleteOldArticles(deleteBefore);
LOGGER.info("Old articles will be deleted");

    }

    private String getPicturePath(MultipartFile pictureFile, String routeName, boolean isPrimary) {
        String ext = getFileExtension(pictureFile.getOriginalFilename());

        String pathPattern = "%s\\%s\\%s." + ext;

        return String.format(pathPattern,
                transformArticleName(routeName),
                isPrimary ? "" : "gallery",
                UUID.randomUUID());
    }
    private String getFileExtension(String fileName) {
        String[] splitPictureName = fileName.split("\\.");
        return splitPictureName[splitPictureName.length - 1];
    }

    private String getFilePath(String routeName) {
        String pathPattern = "%s\\%s_%s.xml";
        return String.format(pathPattern,
                loggedUser.getUsername(),
                transformArticleName(routeName),
                UUID.randomUUID());
    }
  private String transformArticleName(String routeName) {
    return routeName.toLowerCase()
            .replaceAll("\\s+", "_");
}
}