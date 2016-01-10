package lt.ak.lunchvoter.domain.repository;

import io.katharsis.errorhandling.ErrorData;
import io.katharsis.errorhandling.exception.KatharsisMappableException;
import io.katharsis.response.HttpStatus;

/**
 * Created by Arturas Kazenas
 */
public class ExpiredMenuException extends KatharsisMappableException {

    private static final String TITLE = "Menu is expired";

    public ExpiredMenuException() {
        this(TITLE);
    }

    public ExpiredMenuException(String message) {
        super(HttpStatus.CONFLICT_409, ErrorData.builder()
                .setTitle(TITLE)
                .setDetail(message)
                .setStatus(String.valueOf(HttpStatus.CONFLICT_409))
                .build());
    }
}
