package HistoryAppGradleSecurity.web;

import HistoryAppGradleSecurity.exception.ArticleNotAuthorisedToEditException;
import HistoryAppGradleSecurity.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFound(ObjectNotFoundException onfe) {
        ModelAndView modelAndView = new ModelAndView("object-not-found");
        modelAndView.addObject("objectId", onfe.getId());

        return modelAndView;
    }
    @ExceptionHandler(ArticleNotAuthorisedToEditException.class)
    public ModelAndView handleArticleNotAuthorisedToEditException(
            ArticleNotAuthorisedToEditException ex, Model model) {
model.addAttribute("message",ex.getMessage());
        ModelAndView modelAndView = new ModelAndView("403");
        return modelAndView;
    }
}
