package HistoryAppGradleSecurity.service.impl;

import HistoryAppGradleSecurity.service.ChangeImageService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class ChangeImageServiceImpl implements ChangeImageService {
    @Override
    public void change(List<String> images) {
        Collections.shuffle(images);
    }
}
