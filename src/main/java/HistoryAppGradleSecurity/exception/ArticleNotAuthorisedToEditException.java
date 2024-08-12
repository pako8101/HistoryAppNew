package HistoryAppGradleSecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code= HttpStatus.FORBIDDEN)
public class ArticleNotAuthorisedToEditException extends RuntimeException {

    public ArticleNotAuthorisedToEditException() {
    }

    public ArticleNotAuthorisedToEditException(String message) {
        super(message);
    }

    public ArticleNotAuthorisedToEditException(String message,Throwable cause) {
        super(message,cause);
    }
}
