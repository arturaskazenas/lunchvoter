package lt.ak.lunchvoter.domain.repository;

import io.katharsis.errorhandling.ErrorData;
import io.katharsis.errorhandling.exception.KatharsisMappableException;
import io.katharsis.response.HttpStatus;

/**
 * Created by Arturas Kazenas
 */
public class UnauthorizedException extends KatharsisMappableException {

    private static final String TITLE = "Unauthorized";

    public UnauthorizedException() {
        this(TITLE);
    }

    public UnauthorizedException(String message) {
        super(HttpStatus.FORBIDDEN_403, ErrorData.builder()
                .setTitle(TITLE)
                .setDetail(message)
                .setStatus(String.valueOf(HttpStatus.FORBIDDEN_403))
                .build());
    }
}
