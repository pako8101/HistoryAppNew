package history.web;

import HistoryAppGradleSecurity.HistoryAppGradleSecurityApplication;
import HistoryAppGradleSecurity.model.entity.Article;
import HistoryAppGradleSecurity.model.enums.PeriodEnum;
import HistoryAppGradleSecurity.repository.ArticleRepository;
import HistoryAppGradleSecurity.repository.LogRepository;
import HistoryAppGradleSecurity.repository.UserRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = HistoryAppGradleSecurityApplication.class)
@AutoConfigureMockMvc
//@AutoConfigureTestDatabase
//@ContextConfiguration
@Testcontainers
@WithMockUser(username = "pako@ex.com",
roles = {"USER", "ADMIN"})
public class ArticleControllerTest {
    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer =
            new MySQLContainer<>("mysql");

    private static final String ARTICLE_CONTROLLER_PREFIX = "/articles";

    private long testArticleId;



    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private LogRepository logRepository;

    private ArticleTestData testData;

    @BeforeEach
    public void setup() {
        testData = new ArticleTestData(
                userRepository,
                articleRepository,
                logRepository
        );
        testData.init();
        testArticleId = testData.getTestArticleId();
    }
    @AfterEach
    public void tearDown() {
        testData.cleanUp();
    }

    @Test
    @WithMockUser(value = "pako", roles = {"USER", "ADMIN"})
    void testShouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                        ARTICLE_CONTROLLER_PREFIX + "/details/{id}", testArticleId
                )).
                andExpect(status().isOk()).
                andExpect(view().name("details")).
                andExpect(model().attributeExists("article"));
    }

    @Test
    @WithMockUser(value = "pako", roles = {"USER", "ADMIN"})
    void addArticle() throws Exception {
      MvcResult result= mockMvc.perform(MockMvcRequestBuilders.post(ARTICLE_CONTROLLER_PREFIX + "/add")
                        .param("title", "test article").
                        param("period", PeriodEnum.ANDEAN_REGION.name()).
                        param("imageUrl", "example.com/image.png").
                        param("description", "Description test").
                        param("releaseDate", "2023-01-01").
                        param("author", "pako").
                        with(csrf())).
                andExpect(status().isCreated()).
                andExpect(status().is3xxRedirection())
                .andReturn();
String body = result.getResponse().getContentAsString();
int id = JsonPath.read(body,"$.id");
        Optional<Article>createArticleOpt =
articleRepository.findById((long)id);
        Assertions.assertTrue(createArticleOpt.isPresent());
        Assertions.assertEquals(3, articleRepository.count());
Article addArticle = createArticleOpt.get();
Assertions.assertEquals("Test content", addArticle.getContent());
Assertions.assertEquals("Test title", addArticle.getTitle());
Assertions.assertEquals("pako", addArticle.getAuthor());
Assertions.assertEquals(PeriodEnum.ANCIENT_GREECE, addArticle.getPeriod());


    }


}
//private Article createArticle(){
//
//}