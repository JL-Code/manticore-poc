package com.jiangy.manticorepoc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * 创建时间: 2025/6/16 10:12
 * </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">JL-Code</a>
 */
@Getter
@Setter
public class ListOfficialEnterpriseReq extends OfficialEnterpriseReq {

    private String keywordType;

    private List<String> regionCode;

    @JsonIgnore
    public String getRegionCodeAsText() {
        if (regionCode == null) {
            return null;
        }
        return String.join(".", regionCode);
    }

    @JsonIgnore
    public String getIndustryCodeAsText() {
        if (getIndustryCode() == null) {
            return null;
        }
        return String.join(".", getIndustryCode());
    }

}
