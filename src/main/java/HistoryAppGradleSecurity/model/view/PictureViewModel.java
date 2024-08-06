package HistoryAppGradleSecurity.model.view;

import lombok.Getter;


public class PictureViewModel {
   private String alt;
   private String src;

    public PictureViewModel() {
    }



    public PictureViewModel setAlt(String alt) {
        this.alt = alt;
        return this;
    }

    public String getAlt() {
        return alt;
    }

    public String getSrc() {
        return src;
    }

    public PictureViewModel setSrc(String src) {
        this.src = src;
        return this;
    }
}
