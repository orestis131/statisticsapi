package n26.code.challenge;

import n26.code.challenge.service.StatisticsService;
import n26.code.challenge.service.model.Transaction;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.DoubleSummaryStatistics;

public class StatisticsServiceTest {

    private static final StatisticsService statisticsService = new StatisticsService();

    @Test
    public void test() {

        Transaction t1 = new Transaction();
        t1.setAmount(12.3);
        t1.setTimestamp(new Date());
        Response res1 = statisticsService.registerTransaction(t1);
        Assert.assertEquals(res1.getStatus(), Response.Status.CREATED.getStatusCode());

        Transaction t2 = new Transaction();
        t2.setAmount(12.7);
        t2.setTimestamp(new Date(System.currentTimeMillis()-10000));
        Response res2 = statisticsService.registerTransaction(t2);
        Assert.assertEquals(res2.getStatus(), Response.Status.CREATED.getStatusCode());

        Transaction t3 = new Transaction();
        t3.setAmount(12.3);
        t3.setTimestamp(new Date(System.currentTimeMillis()-70000));
        Response res3 = statisticsService.registerTransaction(t3);
        Assert.assertEquals(res3.getStatus(), Response.Status.NO_CONTENT.getStatusCode());

        DoubleSummaryStatistics stats = statisticsService.getStatistics();
        Assert.assertEquals(stats.getMax(),12.7, 0);
        Assert.assertEquals(stats.getMin(),12.3, 0);
        Assert.assertEquals(stats.getAverage(),12.5, 0);
        Assert.assertEquals(stats.getCount(),2, 0);
    }
}
