package com.jiangy.manticorepoc.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 分页请求
 */
@Getter
@Setter
public class PageRequest implements Serializable {

    private String keyword;

    private Long page = 1L;

    private Long limit = 20L;
}
