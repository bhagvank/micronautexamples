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

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/newresource") 
public class NewResourceController {
	private static final Logger LOG = LoggerFactory.getLogger(NewResourceController.class);
  private final ViewsRenderer viewsRenderer;

   public NewResourceController(ViewsRenderer viewsRenderer) { 
       this.viewsRenderer = viewsRenderer;
   }

   @Error(status = HttpStatus.NOT_FOUND, global = true)  
   public HttpResponse notFound(HttpRequest request) {
	   LOG.error("there is no new resource");
       if (request.getHeaders()
               .accept()
               .stream()
               .anyMatch(mediaType -> mediaType.getName().contains(MediaType.TEXT_HTML))) { 
           return HttpResponse.ok(viewsRenderer.render("notFound", Collections.emptyMap(), request))
                   .contentType(MediaType.TEXT_HTML);
       }

       JsonError error = new JsonError("New Resource is not Found")
               .link(Link.SELF, Link.of(request.getUri()));

       return HttpResponse.<JsonError>notFound()
               .body(error); 
   }

}