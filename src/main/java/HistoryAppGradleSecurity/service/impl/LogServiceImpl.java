package HistoryAppGradleSecurity.service.impl;

import HistoryAppGradleSecurity.model.entity.Article;
import HistoryAppGradleSecurity.model.entity.LogEntity;
import HistoryAppGradleSecurity.model.entity.UserEnt;
import HistoryAppGradleSecurity.model.service.LogServiceModel;
import HistoryAppGradleSecurity.repository.LogRepository;
import HistoryAppGradleSecurity.service.ArticleService;
import HistoryAppGradleSecurity.service.LogService;
import HistoryAppGradleSecurity.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.Instant.now;

@Service
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;
    private final ArticleService articleService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(LogService.class);
    private final Period deleteTime;

    @Autowired
    public LogServiceImpl(LogRepository logRepository, ArticleService articleService,
                          UserService userService, ModelMapper modelMapper
            ,
                          @Value("${log.deletion}") Period deleteTime) {
        this.logRepository = logRepository;
        this.articleService = articleService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.deleteTime = deleteTime;
    }

    @Override
    public void createLog(String action, Long articleId) {

        Article article = articleService.findArticleById(articleId);

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        String username = authentication.getName();

        UserEnt userEnt = userService.findByName(username);

        LogEntity logEntity = new LogEntity()
                .setArticle(article)
                .setUserEnt(userEnt)
                .setAction(action)
                .setDateTime(LocalDateTime.now());

        logRepository.save(logEntity);
    }

    @Override
    public List<LogServiceModel> findAllLogs() {
        return logRepository
                .findAll()
                .stream()
                .map(logEntity -> {
                    LogServiceModel logServiceModel =
                            modelMapper.map(logEntity, LogServiceModel.class);
                    logServiceModel.setArticle(logEntity.getArticle().getTitle())
                            .setUser(logEntity.getUserEnt().getUsername());

                    return logServiceModel;

                }).collect(Collectors.toList());
    }

    @Override
    public void deleteOldLogs() {
        Instant now = now();
        Instant deleteBefore = now.minus(deleteTime);
        LOGGER.info("Delete all articles before " + deleteBefore);
        logRepository.deleteOldLogs(deleteBefore);
        LOGGER.info("Old articles will be deleted");

    }
}
