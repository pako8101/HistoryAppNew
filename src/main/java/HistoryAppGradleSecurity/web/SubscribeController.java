package HistoryAppGradleSecurity.web;

import HistoryAppGradleSecurity.model.binding.ReCaptchaResponseDTO;
import HistoryAppGradleSecurity.model.binding.UserLoginBindingModel;
import HistoryAppGradleSecurity.model.binding.UserSubscribeBindingModel;
import HistoryAppGradleSecurity.service.ICaptchaService;
import HistoryAppGradleSecurity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")

public class SubscribeController {

    private final UserService userService;
    private final SecurityContextRepository securityContextRepository;
    @Autowired
    private ICaptchaService captchaService;
    private final ModelMapper modelMapper;
    @Value("${site_key}")
    private String recaptchaSiteKey;


    public SubscribeController(UserService userService,
                               SecurityContextRepository securityContextRepository,
                               ModelMapper modelMapper) {
        this.userService = userService;
        this.securityContextRepository = securityContextRepository;
        this.modelMapper = modelMapper;

    }


    @ModelAttribute("userSubscribeBindingModel")
    public UserSubscribeBindingModel userSubscribeBindingModel() {
        return new UserSubscribeBindingModel();
    }

    @ModelAttribute("userLoginBindingModel")
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @GetMapping("/subscribe")
    public String subscribe(Model model) {
        if (!model.containsAttribute("userSubscribeBindingModel")) {
            model.addAttribute("userSubscribeBindingModel", new UserSubscribeBindingModel());
        }
        model.addAttribute("recaptchaSiteKey", recaptchaSiteKey);
        return "subscribe";
    }


    @PostMapping("/subscribe")
    public String subscribeConfirm(@RequestParam("g-recaptcha-response") String captchaResponse,
                                   @Valid UserSubscribeBindingModel userSubscribeBindingModel,
                                   HttpServletRequest request,
                                   HttpServletResponse response,
                                    Model model,
                                    BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {


            boolean isBot = !captchaService
                    .verify(captchaResponse)
                    .map(ReCaptchaResponseDTO::isSuccess)
                    .orElse(false);

            if (isBot) {
                return "redirect:/";
            }

//        String captchaResponse = request.getParameter("g-recaptcha-response");
//        System.out.println("Captcha Response: " + captchaResponse);
//        if (!captchaService.verifyCaptcha(captchaResponse)) {
//            model.addAttribute("message",
//                    "Captcha verification failed");
//            return "subscribe";
//
//        }


        if (bindingResult.hasErrors() || !userSubscribeBindingModel.getPassword()
                .equals(userSubscribeBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("subscribeBindingModel", userSubscribeBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.subscribeBindingModel", bindingResult);

            return "redirect:/users/subscribe";
        }


        userService.subscribeUser(userSubscribeBindingModel, successfulAuth -> {
            SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy();

            SecurityContext context = strategy.createEmptyContext();
            context.setAuthentication(successfulAuth);

            strategy.setContext(context);
            securityContextRepository.saveContext(context, request, response);
        });

        model.addAttribute("message", "Registration successful");
        return "redirect:/";
    }

}
