package com.jiangy.manticorepoc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">JL-Code</a>
 * <p>创建时间: 2025/7/6 </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class SearchResponse {
    private boolean took;
    private boolean timedOut;
    private Hits hits;
    private Map<String, Object> aggregations;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Hits {
        private int total;
        private List<Hit> hits;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Hit {
            @JsonProperty("_id")
            private String id;
            @JsonProperty("_score")
            private Double score;
            @JsonProperty("_source")
            private Map<String, Object> source;
        }
    }
}