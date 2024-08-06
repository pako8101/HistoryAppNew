package HistoryAppGradleSecurity.service.impl;

import HistoryAppGradleSecurity.repository.PictureRepository;
import HistoryAppGradleSecurity.service.PictureService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<String> findAllUrls() {
        return pictureRepository.findAllUrls();
    }
}
