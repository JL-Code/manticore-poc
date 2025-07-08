package com.jiangy.manticorepoc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Manticore查询构建器
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">JL-Code</a>
 * <p>创建时间: 2025/7/6 </p>
 */

@Data
@NoArgsConstructor
public class ManticoreQuery {
    private String index;
    private Query query;
    private Integer limit;
    private Integer offset;
    private Object sort;
    private Map<String, Object> aggs;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Query {
        private String queryString;
        private BoolQuery bool;
        private MatchQuery match;
        private RangeQuery range;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class BoolQuery {
            private List<Query> must;
            private List<Query> should;
            private List<Query> mustNot;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class MatchQuery {
            private String field;
            private String value;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class RangeQuery {
            private String field;
            private Object gte;
            private Object lte;
            private Object gt;
            private Object lt;
        }
    }

    public static ManticoreQuery builder() {
        return new ManticoreQuery();
    }

    public ManticoreQuery index(String index) {
        this.index = index;
        return this;
    }

    public ManticoreQuery query(String queryString) {
        this.query = new Query();
        this.query.setQueryString(queryString);
        return this;
    }

    public ManticoreQuery limit(int limit) {
        this.limit = limit;
        return this;
    }

    public ManticoreQuery offset(int offset) {
        this.offset = offset;
        return this;
    }

    public ManticoreQuery sort(Object sort) {
        this.sort = sort;
        return this;
    }
}