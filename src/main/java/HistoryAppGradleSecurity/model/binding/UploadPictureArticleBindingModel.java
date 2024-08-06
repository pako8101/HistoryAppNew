package HistoryAppGradleSecurity.model.binding;

import HistoryAppGradleSecurity.model.validators.FileAnnotation;
import org.springframework.web.multipart.MultipartFile;

public class UploadPictureArticleBindingModel {
    private long id;

    @FileAnnotation(contentTypes = {"image/png", "image/jpeg"})
    private MultipartFile picture;

    private Boolean isPrimary = false;

    public UploadPictureArticleBindingModel() {
    }

    public long getId() {
        return id;
    }

    public UploadPictureArticleBindingModel setId(long id) {
        this.id = id;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public UploadPictureArticleBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public UploadPictureArticleBindingModel setPrimary(Boolean primary) {
        isPrimary = primary;
        return this;
    }
}
