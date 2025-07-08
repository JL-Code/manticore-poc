package com.jiangy.manticorepoc.service;

import com.jiangy.manticorepoc.dto.ListOfficialEnterpriseReq;
import com.manticoresearch.client.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * 创建时间: 2025/7/8 17:59
 * </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">JL-Code</a>
 */
@Slf4j
class ManticoreEnterpriseServiceTest {

    ListOfficialEnterpriseReq req;

    @BeforeEach
    void setUp() {
        req = new ListOfficialEnterpriseReq();
        req.setKeyword("公司");
        req.setKeywordType("COMPANY_NAME");
        req.setPage(1L);
        req.setLimit(20L);
    }

    @Test
    void pageOfficialEnterprise() throws ApiException {
        ManticoreEnterpriseService service = new ManticoreEnterpriseService();

        var result = service.pageOfficialEnterprise(req);

        Assertions.assertNotNull(result);

        log.info("result ==>> {}", result);
    }
}