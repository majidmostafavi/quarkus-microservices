package com.majidmostafavi.quarkus.microservice.api.boundary.model;

import com.majidmostafavi.quarkus.microservice.api.boundary.model.output.eventList.EventListDto;
import org.elasticsearch.action.search.SearchResponse;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BoundaryMapper {

    public static List<EventListDto> eventListResult(SearchResponse response) {


        return Arrays.stream(response.getHits().getHits()).map(
                item -> {
                    return new EventListDto(item.getId(),
                            item.getSourceAsMap().get("rawEvent").toString(),
                            item.getSourceAsMap().get("IMSI").toString(),
                            item.getSourceAsMap().get("@timestamp").toString());
                }).collect(Collectors.toList());
    }
}
