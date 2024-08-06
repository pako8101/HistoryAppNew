package history.web;

import HistoryAppGradleSecurity.model.entity.Article;
import HistoryAppGradleSecurity.model.entity.UserEnt;
import HistoryAppGradleSecurity.model.enums.PeriodEnum;
import HistoryAppGradleSecurity.repository.ArticleRepository;
import HistoryAppGradleSecurity.repository.LogRepository;
import HistoryAppGradleSecurity.repository.UserRepository;

import java.time.LocalDate;
import java.time.ZoneId;

public class ArticleTestData {

    private long testArticleId;

    private UserRepository userRepository;
    private ArticleRepository articleRepository;

    private LogRepository logRepository;

    public ArticleTestData(UserRepository userRepository, ArticleRepository articleRepository,
                           LogRepository logRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.logRepository = logRepository;
    }

    public void init() {

        UserEnt userEntity = new UserEnt();
        userEntity.setUsername("pako").setPassword("123").setFullName("pako pakov");
        userEntity = userRepository.save(userEntity);

            Article articleInky = new Article();

            articleInky
                    .setPeriod(PeriodEnum.ANDEAN_REGION)
                    .setAuthor("boko")
                    .setContent("and")
                    .setCreated(LocalDate.from(LocalDate.of(2024, 3, 8)
                            .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    .setImageUrl("https://images.unsplash.com/photo-1461863109726-246fa9598dc3?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                    .setTitle("inky");
articleInky = articleRepository.save(articleInky);

Article articleMaya = new Article();

articleMaya
        .setPeriod(PeriodEnum.CENTRAL_AMERICA)
                .setAuthor("miro")
                .setContent("may")
                .setCreated(LocalDate.from(LocalDate.of(2024, 6, 8)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setImageUrl("https://images.unsplash.com/photo-1465513527097-544020a68b06?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .setTitle("maya");

articleMaya = articleRepository.save(articleMaya);

testArticleId = articleInky.getId();



    }
    void cleanUp() {
        logRepository.deleteAll();
        articleRepository.deleteAll();
        userRepository.deleteAll();
    }

    public long getTestArticleId() {
        return testArticleId;
    }

}
