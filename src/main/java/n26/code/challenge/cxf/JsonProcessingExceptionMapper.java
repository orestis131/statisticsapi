package n26.code.challenge.cxf;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Component
public class JsonProcessingExceptionMapper implements ExceptionMapper<JsonProcessingException> {

    @Override
    public Response toResponse(JsonProcessingException e) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}

