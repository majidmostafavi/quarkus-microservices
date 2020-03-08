package com.majidmostafavi.quarkus.microservice.api.boundary.external.health;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Health
@ApplicationScoped
public class Healthz implements HealthCheck {

    @Inject
    @ConfigProperty(name = "service.sw.name")
    String swName;

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("Healthz")
                .withData("swName", swName)
                .up()
                .build();
    }
}
