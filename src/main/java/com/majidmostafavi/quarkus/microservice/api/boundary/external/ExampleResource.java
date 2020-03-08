package com.majidmostafavi.quarkus.microservice.api.boundary.external;

import com.majidmostafavi.quarkus.microservice.api.boundary.model.input.SearchableCriteria;
import com.majidmostafavi.quarkus.microservice.api.control.gateway.RTAGateway;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


@RequestScoped
@Path("example/api")
public class ExampleResource {

    @Inject
    RTAGateway rtaGateway;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/exampleList")
    public void exampleList(@Suspended AsyncResponse asyncResponse,
                            @BeanParam @Valid SearchableCriteria criteria) {


        rtaGateway.listEvents(asyncResponse, criteria.getImsi());


    }

}
