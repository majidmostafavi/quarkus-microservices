package com.majidmostafavi.quarkus.microservice.api.control.gateway;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

@Named
@ApplicationScoped
public class RTAGateway {

    @Inject
    private ElasticSearchConnection elasticSearch;

    @Inject
    @ConfigProperty(name = "service.sw.example.api.timeout")
    long timeout;

    @Retry
    @CircuitBreaker
    public void listEvents(AsyncResponse asyncResponse, String imsi) {

        asyncResponse.resume(Response.ok("Response is returned correctly").build());


        //todo: open below comment if you have already elastic search installed
/*
        SearchRequest search = SearchRequestBuilder.getSearchRequestForEventList(imsi,
                this.timeout, this.elasticSearch.getIndexName());

                RestHighLevelClient client = this.elasticSearch.getClient();
        client.searchAsync(searchRequest, RequestOptions.DEFAULT,
                new ListEventsListener<>(asyncResponse));

*/
    }

}