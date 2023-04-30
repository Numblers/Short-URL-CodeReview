package sondho.linkerly.exception;

public class UrlNotExistsException extends RuntimeException {
    public UrlNotExistsException(String linkUrl) {
        super("존재하지 않는 Link URL 입니다." + linkUrl);
    }
}
