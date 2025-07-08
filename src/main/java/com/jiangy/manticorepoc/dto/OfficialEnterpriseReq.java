package com.jiangy.manticorepoc.dto;

import com.jiangy.manticorepoc.entity.PhoneTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * <p>
 * 创建时间: 2025/6/16 10:56
 * </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">JL-Code</a>
 */
@Getter
@Setter
public class OfficialEnterpriseReq extends PageRequest {

    private BigDecimal minRegCapitalNumber;

    private BigDecimal maxRegCapitalNumber;

    private Instant establishedStart;

    private Instant establishedEnd;

    private Boolean hasEmail;

    private PhoneTypeEnum phoneType;

    private List<String> industryCode;

}
