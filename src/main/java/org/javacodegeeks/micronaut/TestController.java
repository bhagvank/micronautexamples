package org.javacodegeeks.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;
import io.micronaut.views.ViewsRenderer;
import io.micronaut.http.annotation.Get;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/api")
public class TestController {

@Get("/test")
public String testEndpoint() {
    throw new CustomException("This is a custom error message.");
}
}