package com.majidmostafavi.quarkus.microservice.api.control.gateway;


import org.apache.http.HttpHost;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@ApplicationScoped
public class ElasticSearchConnectionImpl implements ElasticSearchConnection {

    @Inject
    @ConfigProperty(name = "service.sw.example.api.es.hostname")
    String hostname;
    @Inject
    @ConfigProperty(name = "service.sw.example.api.es.port")
    int port;
    @Inject
    @ConfigProperty(name = "service.sw.example.api.es.scheme")
    String scheme;
    @Inject
    @ConfigProperty(name = "service.sw.example.api.es.indexname")
    String indexname;
    @Inject
    RestHighLevelClient highLevelClient;

    @Override
    public RestHighLevelClient getClient() {
        checkConnection(highLevelClient);

        return highLevelClient;
    }

    @Override
    public void checkConnection(RestHighLevelClient client) {

        try {
            GetIndexRequest index = new GetIndexRequest(this.indexname);
            client.indices().exists(index, RequestOptions.DEFAULT);
        } catch (IOException exception) {
            throw new IllegalStateException("Elasticsearch connection failed");
        }
    }

    @Override
    public String getIndexName() {
        return indexname;
    }

    @Override
    public String getHttpHostURI() {
        try {

            return new URI(buildHttpHost().toURI()).toURL().toURI().toASCIIString();
        } catch (URISyntaxException | MalformedURLException ue) {
            throw new IllegalStateException("could not parse http host URI");
        }
    }

    private HttpHost buildHttpHost() {
        return new HttpHost(this.hostname,
                this.port,
                this.scheme);
    }
}

