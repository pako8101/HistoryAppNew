package HistoryAppGradleSecurity.service;

import HistoryAppGradleSecurity.model.binding.ReCaptchaResponseDTO;

import java.util.Optional;

public interface ICaptchaService {

//    boolean verifyCaptcha(String response);
    Optional<ReCaptchaResponseDTO> verify(String token);
}
