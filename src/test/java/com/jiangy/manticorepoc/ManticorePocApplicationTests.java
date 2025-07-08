package com.jiangy.manticorepoc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jiangy.manticorepoc.dto.EnterpriseDTO;
import com.jiangy.manticorepoc.entity.Enterprise;
import com.jiangy.manticorepoc.repository.EnterpriseRepository;
import com.manticoresearch.client.ApiClient;
import com.manticoresearch.client.ApiException;
import com.manticoresearch.client.Configuration;
import com.manticoresearch.client.api.IndexApi;
import com.manticoresearch.client.api.SearchApi;
import com.manticoresearch.client.api.UtilsApi;
import com.manticoresearch.client.model.InsertDocumentRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class ManticorePocApplicationTests {

    @Autowired
    EnterpriseRepository enterpriseRepository;

    IndexApi indexApi;
    SearchApi searchApi;
    UtilsApi utilsApi;
    Boolean rawResponse;
    String tableName;

    @BeforeEach
    void setUp() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://127.0.0.1:9308");
        tableName = "mdm_enterprise_rt";
        rawResponse = true;
        indexApi = new IndexApi(defaultClient);
        utilsApi = new UtilsApi(defaultClient);
        searchApi = new SearchApi(defaultClient);
    }

    @Test
    void insertDocOfMySQL() throws ApiException, JsonProcessingException {

        Pageable pageable = PageRequest.of(1, 50);
        Page<Enterprise> all = enterpriseRepository.findAll(pageable);

        for (Enterprise enterprise : all) {
            InsertDocumentRequest request = new InsertDocumentRequest();

            EnterpriseDTO dto = new EnterpriseDTO();
            BeanUtils.copyProperties(enterprise, dto);

            // 兼容性处理，Manticore Table 字段必须时全小写
            Map<String, Object> doc = toMap(dto);

            doc.forEach((key, value) -> {
                if (value == null) {
                    switch (key) {
                        case "reg_capital_number", "latitude", "longitude" -> doc.put(key, 0.0);
                        case "version" -> doc.put(key, 0);
                        default -> doc.put(key, "");
                    }
                }
            });
            // doc {"error":{"type":"action_request_validation_exception","reason":"column 'id' specified twice","table":"mdm_enterprise_rt"},"status":409}
            doc.remove("id");

            log.info("[insert] doc: {}", new ObjectMapper().writeValueAsString(doc));

            request.table(tableName).id(enterprise.getId()).setDoc(doc);

            indexApi.insert(request);
        }
    }

    public static Map<String, Object> toMap(Object javaBean) {
        ObjectMapper MAPPER = new ObjectMapper();
        MAPPER.registerModule(new JavaTimeModule());
        return MAPPER.convertValue(javaBean, new TypeReference<>() {
        });
    }
}
