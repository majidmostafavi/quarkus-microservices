package com.majidmostafavi.quarkus.microservice.api.boundary.model.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.List;


@Setter
@Getter
@ToString
public class SearchableCriteria {

    @QueryParam("imsi")
    private String imsi;

    @QueryParam("limit")
    @DefaultValue("100")
    private String limit;

    @QueryParam("cursor")
    @DefaultValue("1")
    private String cursor;

    @QueryParam("sort")
    @DefaultValue("id:asc")
    private String sort;

    @Context
    private UriInfo uriInfo;

    @QueryParam("from")
    private String from;

    @QueryParam("to")
    private String to;

    @QueryParam("code")
    private List<String> codes;

}
