package org.javacodegeeks.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/customer") 
public class CustomerController {
	private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);
    @Get 
    @Produces(MediaType.TEXT_PLAIN) 
    public String index() {
		LOG.info("in the customer api {}");
        return "There are no customers yet"; 
    }
	@Get("/examerror")
	public String errorEndpoint() {
	    throw new RuntimeException("An unexpected error occurred");
	}

	@Error(exception = RuntimeException.class)
	public HttpResponse<String> handleRuntimeException(RuntimeException exception) {
	    return HttpResponse.serverError(" The error message is: " + exception.getMessage());
	}
	
	@Error(status = HttpStatus.NOT_FOUND)
	public HttpResponse<String> handleNotFound() {
	    return HttpResponse.notFound("The resource  does not exist.");
	}

}

