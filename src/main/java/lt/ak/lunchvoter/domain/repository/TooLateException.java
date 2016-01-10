package lt.ak.lunchvoter.domain.repository;

import io.katharsis.errorhandling.ErrorData;
import io.katharsis.errorhandling.exception.KatharsisMappableException;
import io.katharsis.response.HttpStatus;

/**
 * Created by Arturas Kazenas
 */
public class TooLateException extends KatharsisMappableException {

    private static final String TITLE = "Too late to vote";

    public TooLateException() {
        this(TITLE);
    }

    public TooLateException(String message) {
        super(HttpStatus.CONFLICT_409, ErrorData.builder()
                .setTitle(TITLE)
                .setDetail(message)
                .setStatus(String.valueOf(HttpStatus.CONFLICT_409))
                .build());
    }
}
