package com.majidmostafavi.quarkus.microservice.api.control.util;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.ConstantScoreQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.util.concurrent.TimeUnit;

public final class SearchRequestBuilder {

    public static SearchRequest getSearchRequestForEventList(String imsi,
                                                             long timeout, String indexname) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        boolQueryBuilder.should(QueryBuilders.matchPhraseQuery("IMSI", imsi));

        ConstantScoreQueryBuilder listEventsQuery = QueryBuilders.constantScoreQuery(boolQueryBuilder);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(listEventsQuery);

        sourceBuilder.sort(new FieldSortBuilder("IMSI").order(
                SortOrder.fromString("asc")));

        sourceBuilder.fetchSource(Constants.EventListIncludeFields, Constants.excludeFields);

        sourceBuilder.timeout(new TimeValue(timeout, TimeUnit.SECONDS));

        SearchRequest searchRequest = new SearchRequest(indexname.trim());
        searchRequest.source(sourceBuilder);

        return searchRequest;
    }

}
