package HistoryAppGradleSecurity.web;

import HistoryAppGradleSecurity.exception.DBInconsistentException;
import ch.qos.logback.core.model.Model;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HPErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handelError(HttpServletRequest request){
        Object status =
                request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "500";
            }
        }
        return "error";

    }

@GetMapping("/check-database")
    public ModelAndView checkDatabase(){
        throw  new DBInconsistentException("Database is not ready!");
}

@GetMapping("/crash")

    public ModelAndView crash(){
        throw new RuntimeException("Uuups ,we crashed");
}

@ExceptionHandler({DBInconsistentException.class})
    public ModelAndView handleDBException(DBInconsistentException ex){
    ModelAndView modelAndView = new ModelAndView("handler");

    modelAndView.addObject("message",ex.getMessage());

    return modelAndView;
}


}
