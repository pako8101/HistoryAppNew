package HistoryAppGradleSecurity.service.impl;

import HistoryAppGradleSecurity.model.binding.ReCaptchaResponseDTO;
import HistoryAppGradleSecurity.service.ICaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class ICaptchaServiceImpl implements ICaptchaService {
    @Value("${secret_key}")
    private String recaptchaSecret;
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ICaptchaServiceImpl.class);
    private final WebClient webClient;

    private static final String RECAPTCHA_URL =
            "https://www.google.com/recaptcha/api/siteverify";


    public ICaptchaServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<ReCaptchaResponseDTO> verify(String token) {
        ReCaptchaResponseDTO response = webClient
                .post()
                .uri(this::buildRecaptchaURI)
                .body(BodyInserters
                        .fromFormData("secret", recaptchaSecret)
                        .with("response", token))
                .retrieve()
                .bodyToMono(ReCaptchaResponseDTO.class)
                .doOnError(t -> LOGGER.error("Error fetching google reponse...", t))
                .onErrorComplete()
                .block();

        return Optional.ofNullable(response);
    }

    private URI buildRecaptchaURI(UriBuilder uriBuilder) {
        // REST endpoint for google verification.
        // https://www.google.com/recaptcha/api/siteverify
        return uriBuilder
                .scheme("https")
                .host("www.google.com")
                .path("/recaptcha/api/siteverify")
                .build();
    }
    }
//@Override
//    public boolean verifyCaptcha(String response) {
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, String> body = new HashMap<>();
//        body.put("secret", recaptchaSecret);
//        body.put("response", response);
//
//        Map<String, Object> responseBody = restTemplate.postForObject(RECAPTCHA_URL, body, Map.class);
//        return (Boolean) responseBody.get("success");
//    }
