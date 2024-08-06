package HistoryAppGradleSecurity.service.impl;

import HistoryAppGradleSecurity.service.ChangeImageService;
import HistoryAppGradleSecurity.service.ShiftImageService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShiftImageServiceImpl implements ShiftImageService {

    private final ChangeImageService changeImageService;
    private List<String> images = new ArrayList<>();

    private final Logger LOGGER = LoggerFactory.getLogger(ChangeImageServiceImpl.class);

    public ShiftImageServiceImpl(@Value("${ancient_images}") List<String> images,
            ChangeImageService changeImageService) {
        this.changeImageService = changeImageService;
        this.images.addAll(images);
    }
    @PostConstruct
    public void afterInitialize() {
        if (images.size() < 3) {
            throw new IllegalArgumentException("Sorry, but you must configure at least three images...");
        }
    }

    @Override
    public String firstImage() {
        return images.get(0);
    }

    @Override
    public String secondImage() {
        return images.get(1);
    }

    @Override
    public String thirdImage() {
        return images.get(2);
    }

    @Override
    public String fourthImage() {
        return images.get(3);
    }

    @Scheduled(cron = "${shift.refresh-cron}")
    public void refresh() {
        LOGGER.info("Changing images...");
        changeImageService.change(images);
    }
}
