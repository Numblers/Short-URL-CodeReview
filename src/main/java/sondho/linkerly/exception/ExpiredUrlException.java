package sondho.linkerly.exception;

public class ExpiredUrlException extends RuntimeException{
    public ExpiredUrlException(String linkUrl) {
        super("만료된 URI: " + linkUrl);
    }
}
