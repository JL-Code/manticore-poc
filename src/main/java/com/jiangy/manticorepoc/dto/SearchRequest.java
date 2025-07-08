package com.jiangy.manticorepoc.dto;

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
public class SearchRequest {
    private String query;
    private String index;
    private Integer limit;
    private Integer offset;
    private Map<String, Object> filters;
    private List<String> sort;
}
