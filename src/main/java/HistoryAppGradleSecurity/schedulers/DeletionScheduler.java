package HistoryAppGradleSecurity.schedulers;

import HistoryAppGradleSecurity.service.ArticleService;
import HistoryAppGradleSecurity.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
public class DeletionScheduler {
    private final Logger LOGGER = LoggerFactory.getLogger(DeletionScheduler.class);

    private final ArticleService articleService;
    private final LogService logService;

    public DeletionScheduler(ArticleService articleService, LogService logService) {
        this.articleService = articleService;
        this.logService = logService;
    }


    @Scheduled(cron = "0 0 5 * * SUN")
    public void scheduleDeletionOldArticles() {
        LOGGER.info(String.format("Deletion scheduled at: %s", System.currentTimeMillis()));

        articleService.deleteOldArticles();

        LOGGER.info("Deletion finished.");

    }
    @Scheduled(cron = "0 0 23 * * *")
    public void scheduleCleanOldLogs() {
        LOGGER.info(String.format("Deletion scheduled at: %s", System.currentTimeMillis()));

        logService.deleteOldLogs();

        LOGGER.info("Deletion done.");

    }
}
