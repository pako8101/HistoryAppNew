package HistoryAppGradleSecurity.exception;

public class InvalidReCaptchaException extends RuntimeException{
    public InvalidReCaptchaException(String responseContainsInvalidCharacters) {
    }
}
