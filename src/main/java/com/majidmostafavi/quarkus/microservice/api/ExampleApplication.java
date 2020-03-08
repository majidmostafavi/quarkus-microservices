package com.majidmostafavi.quarkus.microservice.api;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationScoped
@ApplicationPath("/")
public class ExampleApplication extends Application {
}
