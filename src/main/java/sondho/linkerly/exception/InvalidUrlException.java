package sondho.linkerly.exception;

public class InvalidUrlException extends RuntimeException{
    public InvalidUrlException(String url) {
        super("유효하지 않은 URL: " + url);
    }
}
