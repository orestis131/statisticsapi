package n26.code.challenge.validation;

import java.util.Date;

public class TimestampValidator {

    private static final Long THRESHOLD = 60000L;

    public static boolean isWithinLastMinute(Date date) {
        Long currentTime = System.currentTimeMillis();
        return date.getTime() <= currentTime &&
                date.getTime() >= currentTime - THRESHOLD;
    }
}
