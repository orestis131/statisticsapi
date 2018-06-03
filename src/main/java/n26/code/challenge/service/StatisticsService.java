package n26.code.challenge.service;

import n26.code.challenge.service.model.Transaction;
import n26.code.challenge.validation.TimestampValidator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.DoubleSummaryStatistics;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Component
@Path("/")
@Validated
public class StatisticsService {

    private ConcurrentLinkedQueue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    @GET
    @Path("statistics")
    @Produces({MediaType.APPLICATION_JSON_VALUE})
    public DoubleSummaryStatistics getStatistics() {
        return transactions.stream().filter(t -> TimestampValidator.isWithinLastMinute(t.getTimestamp())).
                collect(Collectors.summarizingDouble(Transaction::getAmount));
    }

    @POST
    @Path("transactions")
    @Consumes({MediaType.APPLICATION_JSON_VALUE})
    @Produces({MediaType.APPLICATION_JSON_VALUE})
    public Response registerTransaction(@Valid Transaction req) {
        if (!TimestampValidator.isWithinLastMinute(req.getTimestamp()))
            return Response.status(Response.Status.NO_CONTENT).build();
        transactions.add(req);
        return Response.status(Response.Status.CREATED).build();
    }
}