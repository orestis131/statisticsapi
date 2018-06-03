package n26.code.challenge;

import n26.code.challenge.validation.TimestampValidator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class TimestampValidatorTest {

    @Test
    public void test() {
        Date now = new Date();
        Assert.assertEquals(TimestampValidator.isWithinLastMinute(now),true);

        Date d1 = new Date(System.currentTimeMillis()-50000);
        Assert.assertEquals(TimestampValidator.isWithinLastMinute(d1),true);

        Date d2 = new Date(System.currentTimeMillis()-70000);
        Assert.assertEquals(TimestampValidator.isWithinLastMinute(d2),false);

        Date d3 = new Date(System.currentTimeMillis()-60000);
        Assert.assertEquals(TimestampValidator.isWithinLastMinute(d3),true);
    }
}
