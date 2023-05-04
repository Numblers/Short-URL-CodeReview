package numble.shorturl.application;

import org.springframework.stereotype.Service;

@Service
public class UrlEncodingService {
    static final int RADIX = 62;
    static final String CODEC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String encoding(long param) {
        StringBuilder sb = new StringBuilder();
        while(param > 0) {
            sb.append(CODEC.charAt((int) (param % RADIX)));
            param /= RADIX;
        }
        return sb.toString();
    }
}
