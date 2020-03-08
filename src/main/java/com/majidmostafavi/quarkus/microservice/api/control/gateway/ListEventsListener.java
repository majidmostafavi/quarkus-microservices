package com.majidmostafavi.quarkus.microservice.api.control.gateway;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.SearchResponse;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

@Getter
@Setter
@Builder
public class ListEventsListener<T extends SearchResponse> implements ActionListener<T> {

    private AsyncResponse asyncResponse;

    public ListEventsListener(AsyncResponse asyncResponse) {
        this.asyncResponse = asyncResponse;
    }

    @Override
    public void onResponse(T searchResponse) {
        asyncResponse.resume(Response.ok("Response is returned correctly").build());
    }

    @Override
    public void onFailure(Exception exception) {
        asyncResponse.resume(Response.status(Response.Status.NOT_FOUND).entity("Response is returned with some errors").build());
    }

}
