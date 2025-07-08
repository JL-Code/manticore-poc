package com.jiangy.manticorepoc.service;

import com.jiangy.manticorepoc.dto.ListOfficialEnterpriseReq;
import com.manticoresearch.client.ApiException;
import com.manticoresearch.client.Configuration;
import com.manticoresearch.client.api.UtilsApi;
import com.manticoresearch.client.model.SqlResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Manticore 企服服务
 */
@Slf4j
public class ManticoreEnterpriseService {

    public Object pageOfficialEnterprise(ListOfficialEnterpriseReq req) throws ApiException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, name, mobile_phone, legal_person, address, established_date, reg_capital, email FROM mdm_enterprise WHERE 1=1 ");

        if (req.getHasEmail() != null && req.getHasEmail()) {
            sql.append("AND email IS NOT NULL ");
        }
        if (req.getPhoneType() != null) {
            sql.append("AND phone_type = ").append(req.getPhoneType()).append(" ");
        }
        if (req.getEstablishedStart() != null && req.getEstablishedEnd() != null) {
            sql.append("AND established_date BETWEEN '").append(req.getEstablishedStart()).append("' AND '").append(req.getEstablishedEnd()).append("' ");
        }
        if (req.getMinRegCapitalNumber() != null && req.getMaxRegCapitalNumber() != null) {
            sql.append("AND reg_capital_number BETWEEN ").append(req.getMinRegCapitalNumber()).append(" AND ").append(req.getMaxRegCapitalNumber()).append(" ");
        }
        if (req.getIndustryCode() != null && !req.getIndustryCode().isEmpty()) {
            sql.append("AND industry_code LIKE '").append(req.getIndustryCodeAsText()).append("%' ");
        }
        if (req.getRegionCode() != null && !req.getRegionCode().isEmpty()) {
            sql.append("AND region_code LIKE '").append(req.getRegionCodeAsText()).append("%' ");
        }
        if (req.getKeywordType() != null && req.getKeyword() != null && !req.getKeyword().isEmpty()) {
            if ("COMPANY_NAME".equals(req.getKeywordType())) {
                sql.append("AND MATCH(name) AGAINST('").append(req.getKeyword()).append("') ");
            } else if ("BOSS_NAME".equals(req.getKeywordType())) {
                sql.append("AND MATCH(legal_person) AGAINST('").append(req.getKeyword()).append("') ");
            }
        }
        // 排序
        if (req.getKeywordType() != null && req.getKeyword() != null && !req.getKeyword().isEmpty()) {
            if ("COMPANY_NAME".equals(req.getKeywordType())) {
                sql.append("ORDER BY MATCH(name) AGAINST('").append(req.getKeyword()).append("') DESC ");
            } else if ("BOSS_NAME".equals(req.getKeywordType())) {
                sql.append("ORDER BY MATCH(legal_person) AGAINST('").append(req.getKeyword()).append("') DESC ");
            }
        } else {
            sql.append("ORDER BY established_date DESC ");
        }
        long offset = (req.getPage() - 1) * req.getLimit();
        sql.append("LIMIT ").append(req.getLimit()).append(" OFFSET ").append(offset);

        try {
            var client = Configuration.getDefaultApiClient();
            client.setBasePath("http://127.0.0.1:9318"); // 你的 Manticore HTTP 地址
            UtilsApi utilsApi = new UtilsApi();
            SqlResponse sqlResponse = utilsApi.sql(sql.toString(), false);
            // 你需要把 sqlResponse 里的数据转成 List<OfficialEnterpriseDTO>
            return sqlResponse;
        } catch (ApiException e) {
            log.error("Manticore SQL error", e);
            return Collections.emptyList();
        }
    }
}
