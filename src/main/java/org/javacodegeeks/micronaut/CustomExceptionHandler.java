package org.javacodegeeks.micronaut;

import java.util.Map;
import java.util.HashMap;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.context.annotation.Requires;

import jakarta.inject.Singleton;

@Singleton
@Requires(classes = {CustomException.class, ExceptionHandler.class})
public class CustomExceptionHandler implements ExceptionHandler<CustomException,HttpResponse> { 
@Override
public HttpResponse handle(HttpRequest request, CustomException exception) {
Map errorResponse = new HashMap();
errorResponse.put("message", exception.getMessage());
errorResponse.put("status", "error");
return HttpResponse.badRequest(errorResponse);
}
}