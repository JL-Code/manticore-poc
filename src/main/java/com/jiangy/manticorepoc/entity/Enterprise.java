package com.jiangy.manticorepoc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">JL-Code</a>
 * <p>创建时间: 2025/7/6 </p>
 */
// 产品实体类
@Entity
@Table(name = "mdm_enterprise")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Enterprise {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status", length = 64)
    private String status;

    @Column(name = "legal_person", length = 100)
    private String legalPerson;

    @Column(name = "size", length = 64)
    private String size;

    @Column(name = "reg_capital", length = 50)
    private String regCapital;

    @Column(name = "reg_capital_number", precision = 18, scale = 4)
    private BigDecimal regCapitalNumber;

    @Column(name = "paid_capital", length = 64)
    private String paidCapital;

    @Column(name = "established_date")
    private LocalDate establishedDate;

    @Column(name = "approval_date")
    private LocalDate approvalDate;

    @Column(name = "business_term", length = 128)
    private String businessTerm;

    @Column(name = "province_name", length = 100)
    private String provinceName;

    @Column(name = "city_name", length = 100)
    private String cityName;

    @Column(name = "county_name", length = 100)
    private String countyName;

    @Column(name = "province_code")
    private Integer provinceCode;

    @Column(name = "region_code")
    private String regionCode;

    @Column(name = "type", length = 128)
    private String type;

    @Column(name = "industry_class", length = 128)
    private String industryClass;

    @Column(name = "industry_subclass", length = 128)
    private String industrySubclass;

    @Column(name = "industry_midclass", length = 128)
    private String industryMidclass;

    @Column(name = "old_name", length = 1000)
    private String oldName;

    @Column(name = "english_name")
    private String englishName;

    @Column(name = "credit_code", length = 50)
    private String creditCode;

    @Column(name = "tax_id", length = 64)
    private String taxId;

    @Column(name = "reg_no", length = 64)
    private String regNo;

    @Column(name = "org_code", length = 64)
    private String orgCode;

    @Column(name = "insured_num")
    private Integer insuredNum;

    @Column(name = "insured_year", length = 16)
    private String insuredYear;

    @Column(name = "mobile_phone", length = 500)
    private String mobilePhone;

    @Column(name = "more_phones", length = 500)
    private String morePhones;

    @Column(name = "reg_address", length = 500)
    private String regAddress;

    @Column(name = "latest_report_address", length = 500)
    private String latestReportAddress;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "website", length = 500)
    private String website;

    @Column(name = "email", length = 500)
    private String email;

    @Column(name = "other_email", length = 500)
    private String otherEmail;

    @Column(name = "business_scope", length = 2500)
    private String businessScope;

    @Column(name = "phone_type", length = 50)
    private String phoneType;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @Column(name = "contact", length = 20)
    private String contact;

    @Column(name = "introduction", length = 1000)
    private String introduction;

    @Column(name = "industry")
    private String industry;

    @Column(name = "industry_code", length = 50)
    private String industryCode;

    @Column(name = "latitude", precision = 18, scale = 14)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 18, scale = 14)
    private BigDecimal longitude;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "version")
    private Integer version;

}