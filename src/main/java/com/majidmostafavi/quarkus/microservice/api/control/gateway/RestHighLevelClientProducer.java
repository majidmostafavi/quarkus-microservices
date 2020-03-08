package com.majidmostafavi.quarkus.microservice.api.control.gateway;


import org.apache.http.HttpHost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@ApplicationScoped
public class RestHighLevelClientProducer {

    @Inject
    @ConfigProperty(name = "service.sw.example.api.es.cert")
    String cert;
    @Inject
    @ConfigProperty(name = "service.sw.example.api.es.hostname")
    String hostname;
    @Inject
    @ConfigProperty(name = "service.sw.example.api.es.port")
    int port;
    @Inject
    @ConfigProperty(name = "service.sw.example.api.es.connection-timeout-milliseconds")
    int connectionTimeOut;
    @Inject
    @ConfigProperty(name = "service.sw.example.api.es.socket-timeout-milliseconds")
    int socketTimeOut;
    @Inject
    @ConfigProperty(name = "service.sw.example.api.es.scheme", defaultValue = "NO_CERT")
    String scheme;

    @Produces
    public RestHighLevelClient getRestHighLevelClient() {

        final RestHighLevelClient restHighLevelClient;

        if (this.scheme.contains("https")) {

            restHighLevelClient = new RestHighLevelClient(RestClient.builder(buildHttpHost())
                    .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
                            .setConnectTimeout(connectionTimeOut)
                            .setSocketTimeout(socketTimeOut))
                    .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
                            .setSSLContext(getSSLContext())
                            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)));
        } else {

            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(
                            buildHttpHost())
                            .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
                                    .setConnectTimeout(connectionTimeOut)
                                    .setSocketTimeout(socketTimeOut)));
        }

        return restHighLevelClient;
    }


    private SSLContext getSSLContext() {

        SSLContext sslContext = null;

        System.setProperty("https.protocols", "TLSv1.2");

        CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance("X.509");

            X509Certificate caCert = (X509Certificate) cf.generateCertificate(
                    new ByteArrayInputStream(this.cert.getBytes(StandardCharsets.UTF_8)));

            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());

            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null);
            ks.setCertificateEntry("caCert", caCert);
            tmf.init(ks);

            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);

        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException
                | IOException | KeyManagementException e) {
            e.printStackTrace();
        }
        return sslContext;
    }

    private HttpHost buildHttpHost() {
        try {

            new URI(scheme, hostname, new Integer(port).toString());

            return new HttpHost(hostname, port, scheme);
        } catch (URISyntaxException e) {
            throw new IllegalStateException("not a valid uri");
        }
    }
}
