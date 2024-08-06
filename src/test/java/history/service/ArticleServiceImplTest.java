package history.service;

import HistoryAppGradleSecurity.model.entity.Article;
import HistoryAppGradleSecurity.model.entity.UserEnt;
import HistoryAppGradleSecurity.model.enums.PeriodEnum;
import HistoryAppGradleSecurity.model.view.ArticleViewModel;
import HistoryAppGradleSecurity.repository.ArticleRepository;
import HistoryAppGradleSecurity.repository.UserRepository;
import HistoryAppGradleSecurity.service.ArticleService;
import HistoryAppGradleSecurity.service.CategoryService;
import HistoryAppGradleSecurity.service.UserService;
import HistoryAppGradleSecurity.service.assists.PictureAssistService;
import HistoryAppGradleSecurity.service.impl.ArticleServiceImpl;
import HistoryAppGradleSecurity.session.LoggedUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;

import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceImplTest {
    @Mock
     UserService userService;
    @Mock
    PictureAssistService pictureAssistService;
    @Mock
    CategoryService categoryService;
    @Mock
     LoggedUser loggedUser;

    private UserEnt testUser1, testUser2;
    private Article testArticleEntity1, testArticleEntity2;
    private ArticleServiceImpl serviceToTest;

    @Mock
    ArticleRepository mockArticleRepository;
    @Mock
     Logger LOGGER = (Logger) LoggerFactory.getLogger(ArticleService.class);
    @Mock
     Period deletePeriod;

    @Mock
    UserRepository mockUserRepository;




    @BeforeEach
    public void init() {

        testUser1 = new UserEnt();
        testUser1.setUsername("user 1");

        testArticleEntity1 = new Article();
        testArticleEntity1.setTitle("article 1");
        testArticleEntity1.setImageUrl("image 1");
        testArticleEntity1.setPeriod(PeriodEnum.ANCIENT_GREECE);
        testArticleEntity1.setContent("content 1");
        testArticleEntity1.setAuthor(testUser1.getUsername());
//        testArticleEntity1.setEstablished(Instant.now());

        testUser2 = new UserEnt();
        testUser2.setUsername("user 2");

        testArticleEntity2 = new Article();
        testArticleEntity2.setTitle("article 2");
        testArticleEntity2.setImageUrl("image 2");
        testArticleEntity2.setPeriod(PeriodEnum.BLUR_PERIOD);
        testArticleEntity2.setContent("content 2");
        testArticleEntity2.setAuthor(testUser2.getUsername());
//        testArticleEntity2.setEstablished(Instant.now());

        serviceToTest = new ArticleServiceImpl(mockArticleRepository,new ModelMapper(),userService
                ,pictureAssistService,categoryService
                ,loggedUser,mockUserRepository,deletePeriod

                );
    }


    @Test
    public void testFindAll() {

        when(mockArticleRepository.findAll()).thenReturn(List.of(testArticleEntity1, testArticleEntity2));

        List<ArticleViewModel> allModels = serviceToTest.findAllArticlesView();

        Assertions.assertEquals(2, allModels.size());

        ArticleViewModel model1 = allModels.get(0);
        ArticleViewModel model2 = allModels.get(1);

        // verify model 1
        Assertions.assertEquals(testArticleEntity1.getTitle(), model1.getTitle());
       // Assertions.assertEquals(testArticleEntity1.getImageUrl(), model1.getPictureUrl());
        Assertions.assertEquals(testArticleEntity1.getPeriod(), model1.getPeriod());
        Assertions.assertEquals(testArticleEntity1.getContent(), model1.getContent());
        Assertions.assertEquals(testUser1.getUsername(), model1.getAuthor());

        // verify model 2
        Assertions.assertEquals(testArticleEntity2.getTitle(), model2.getTitle());
       // Assertions.assertEquals(testArticleEntity2.getImageUrl(), model2.getPictureUrl());
        Assertions.assertEquals(testArticleEntity2.getPeriod(), model2.getPeriod());
        Assertions.assertEquals(testArticleEntity2.getContent(), model2.getContent());
        Assertions.assertEquals(testUser2.getUsername(), model2.getAuthor());
    }

    @Test
    void testLatestArticle() {
        when(mockArticleRepository.findTopByOrderByCreatedDesc()).thenReturn(Optional.of(testArticleEntity1));

        Optional<ArticleViewModel> articleViewOpt = serviceToTest.findLatestArticle();

        Assertions.assertTrue(articleViewOpt.isPresent());
        ArticleViewModel actualModel = articleViewOpt.get();

        Assertions.assertEquals(testArticleEntity1.getTitle(), actualModel.getTitle());
        Assertions.assertEquals(testArticleEntity1.getContent(), actualModel.getContent());

    }

    @Test
    void testLatestArticle_NotFound() {
        when(mockArticleRepository.findTopByOrderByCreatedDesc()).thenReturn(Optional.empty());

        Optional<ArticleViewModel> articleViewOpt = serviceToTest.findLatestArticle();

        Assertions.assertTrue(articleViewOpt.isEmpty());
    }

}
