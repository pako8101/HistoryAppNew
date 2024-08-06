package HistoryAppGradleSecurity.service.assists;

import HistoryAppGradleSecurity.model.entity.Article;
import HistoryAppGradleSecurity.model.entity.Picture;
import HistoryAppGradleSecurity.repository.PictureRepository;
import HistoryAppGradleSecurity.session.LoggedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

public class PictureAssistService {

    private  final PictureRepository pictureRepository;
    private final LoggedUser loggedUser;

    public PictureAssistService(PictureRepository pictureRepository, LoggedUser loggedUser) {
        this.pictureRepository = pictureRepository;
        this.loggedUser = loggedUser;
    }

    public void create(Article article, String picturePath){
        Picture picture = new Picture();

        picture.setTitle(article.getTitle());
        picture.setArticle(article);
        picture.setUrl(picturePath);
        picture.setAuthor(loggedUser.get());

        pictureRepository.save(picture);
    }
}
