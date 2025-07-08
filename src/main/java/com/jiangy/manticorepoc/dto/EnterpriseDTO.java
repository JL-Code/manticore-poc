package com.jiangy.manticorepoc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * <p>
 * 创建时间: 2025/7/8 10:24
 * </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">JL-Code</a>
 */
@Getter
@Setter
public class EnterpriseDTO implements Serializable {
    @JsonProperty("id")
    Long id;
    @JsonProperty("name")
    String name;
    @JsonProperty("status")
    String status;
    @JsonProperty("legal_person")
    String legalPerson;
    @JsonProperty("size")
    String size;
    @JsonProperty("reg_capital")
    String regCapital;
    @JsonProperty("reg_capital_number")
    BigDecimal regCapitalNumber;
    @JsonProperty("paid_capital")
    String paidCapital;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("established_date")
    LocalDate establishedDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("approval_date")
    LocalDate approvalDate;
    @JsonProperty("business_term")
    String businessTerm;
    @JsonProperty("province_name")
    String provinceName;
    @JsonProperty("city_name")
    String cityName;
    @JsonProperty("county_name")
    String countyName;
    @JsonProperty("province_code")
    Integer provinceCode;
    @JsonProperty("region_code")
    String regionCode;
    @JsonProperty("type")
    String type;
    @JsonProperty("industry_class")
    String industryClass;
    @JsonProperty("industry_subclass")
    String industrySubclass;
    @JsonProperty("industry_midclass")
    String industryMidclass;
    @JsonProperty("old_name")
    String oldName;
    @JsonProperty("english_name")
    String englishName;
    @JsonProperty("credit_code")
    String creditCode;
    @JsonProperty("tax_id")
    String taxId;
    @JsonProperty("reg_no")
    String regNo;
    @JsonProperty("org_code")
    String orgCode;
    @JsonProperty("insured_num")
    Integer insuredNum;
    @JsonProperty("insured_year")
    String insuredYear;
    @JsonProperty("mobile_phone")
    String mobilePhone;
    @JsonProperty("more_phones")
    String morePhones;
    @JsonProperty("reg_address")
    String regAddress;
    @JsonProperty("latest_report_address")
    String latestReportAddress;
    @JsonProperty("address")
    String address;
    @JsonProperty("website")
    String website;
    @JsonProperty("email")
    String email;
    @JsonProperty("other_email")
    String otherEmail;
    @JsonProperty("business_scope")
    String businessScope;
    @JsonProperty("phone_type")
    String phoneType;
    @JsonProperty("telephone")
    String telephone;
    @JsonProperty("contact")
    String contact;
    @JsonProperty("introduction")
    String introduction;
    @JsonProperty("industry")
    String industry;
    @JsonProperty("industry_code")
    String industryCode;
    @JsonProperty("latitude")
    BigDecimal latitude;
    @JsonProperty("longitude")
    BigDecimal longitude;
    @JsonProperty("created_at")
    Instant createdAt;
    @JsonProperty("version")
    Integer version;
}
