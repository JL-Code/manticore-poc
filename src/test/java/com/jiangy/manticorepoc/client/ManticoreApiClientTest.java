package com.jiangy.manticorepoc.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.manticoresearch.client.ApiClient;
import com.manticoresearch.client.ApiException;
import com.manticoresearch.client.Configuration;
import com.manticoresearch.client.api.IndexApi;
import com.manticoresearch.client.api.SearchApi;
import com.manticoresearch.client.api.UtilsApi;
import com.manticoresearch.client.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">JL-Code</a>
 * <p>创建时间: 2025/7/7 </p>
 * @see <a href="https://github.com/manticoresoftware/manticoresearch-java/tree/8.0.0">Manticore Java client</a>
 */
@Slf4j
class ManticoreApiClientTest {

    IndexApi indexApi;
    SearchApi searchApi;
    UtilsApi utilsApi;
    Boolean rawResponse;
    String tableName;

    @BeforeEach
    void setUp() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://127.0.0.1:9318");
        tableName = "mdm_enterprise";
        rawResponse = true;
        indexApi = new IndexApi(defaultClient);
        utilsApi = new UtilsApi(defaultClient);
        searchApi = new SearchApi(defaultClient);
    }

    @Test
    void testCreateTable() throws ApiException {
        SqlResponse sqlResponse = utilsApi.sql("CREATE TABLE products(title text, price float) charset_table = 'cont' morphology = 'icu_chinese'", rawResponse);
        log.info("sqlResponse: {}", sqlResponse);
    }

    @Test
    void testCreateTable2() throws ApiException {
        String tableSql = """
                CREATE TABLE mdm_enterprise_rt (
                    id                    BIGINT,
                    name                  TEXT,
                    status                STRING,
                    legal_person          TEXT,
                    size                  STRING,
                    reg_capital           STRING,
                    reg_capital_number    FLOAT,
                    paid_capital          STRING,
                    established_date      TIMESTAMP,
                    approval_date         TIMESTAMP,
                    business_term         STRING,
                    province_name         STRING,
                    city_name             STRING,
                    county_name           STRING,
                    province_code         UINT,
                    region_code           STRING,
                    type                  STRING,
                    industry_class        STRING,
                    industry_subclass     STRING,
                    industry_midclass     STRING,
                    old_name              STRING,
                    english_name          STRING,
                    credit_code           STRING,
                    tax_id                STRING,
                    reg_no                STRING,
                    org_code              STRING,
                    insured_num           UINT,
                    insured_year          STRING,
                    mobile_phone          STRING,
                    more_phones           STRING,
                    reg_address           STRING,
                    latest_report_address STRING,
                    address               STRING,
                    website               STRING,
                    email                 STRING,
                    other_email           STRING,
                    business_scope        STRING,
                    phone_type            STRING,
                    telephone             STRING,
                    contact               STRING,
                    introduction          STRING,
                    industry              STRING,
                    industry_code         STRING,
                    latitude              FLOAT,
                    longitude             FLOAT,
                    created_at            TIMESTAMP,
                    version               UINT
                )
                morphology = 'jieba_chinese'
                charset_table = 'cont'
                """;
        utilsApi.sql(tableSql, rawResponse);
    }

    @Test
    void dropTable() throws ApiException {
        SqlResponse sqlResponse = utilsApi.sql("DROP TABLE mdm_enterprise_rt", rawResponse);
        log.info("sqlResponse: {}", sqlResponse);
    }

    @Test
    void searchByGEODISTViaSql() throws ApiException {
        SqlResponse sqlResponse = utilsApi.sql("""
                SELECT *,GEODIST(latitude, longitude,30.28, 120.15,{in=deg, out=mi}) AS distance
                FROM mdm_enterprise_rt
                WHERE MATCH('@name 动力电池')\s
                AND distance < 50000
                ORDER BY distance ASC
                LIMIT 20""", rawResponse);
        outputSqlResponse(sqlResponse);
    }

    @Test
    void searchViaSearchApi() throws ApiException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setTable(tableName);
        var query = new SearchQuery();
        query.setMatch(Map.of("@name", "*公司"));
        searchRequest.setQuery(query);

        SearchResponse response = searchApi.search(searchRequest);

        Assertions.assertNotNull(response.getHits());
        var total = response.getHits().getTotal();

        log.info("total: {}", total);
        Assertions.assertNotNull(response.getHits().getHits());

        response.getHits().getHits().forEach(hit -> {
            log.info("hit: {}", hit);
        });

    }

    @Test
    void insertDocument() throws ApiException {
        InsertDocumentRequest newdoc = new InsertDocumentRequest();
        HashMap<String, Object> doc = new HashMap<String, Object>() {{
            put("title", "Crossbody Bag with Tassel");
            put("price", 19.85);
        }};
        newdoc.table("products").id(1L).setDoc(doc);
        indexApi.insert(newdoc);

        newdoc = new InsertDocumentRequest();
        HashMap<String, Object> doc2 = new HashMap<String, Object>() {{
            put("title", "Crossbody Bag with Tassel");
        }};
        newdoc.table("products").id(2L).setDoc(doc2);
        indexApi.insert(newdoc);

        newdoc = new InsertDocumentRequest();
        HashMap<String, Object> doc3 = new HashMap<String, Object>() {{
            put("title", "Yellow bag");
        }};
        newdoc.table("products").id(0L).setDoc(doc3);
        indexApi.insert(newdoc);
    }


    void output(SqlResponse sqlResponse) {
        log.info("sqlResponse: {}", sqlResponse);
    }

    void outputSqlResponse(SqlResponse sqlResponse) {
        if (sqlResponse.isNullable()) {
            log.info("未命中数据");
            return;
        }
        sqlResponse.get().forEach(row -> {
            log.info("row: {}", row);
        });
    }

    public static Map<String, Object> toMap(Object javaBean) {
        ObjectMapper MAPPER = new ObjectMapper();
        MAPPER.registerModule(new JavaTimeModule());
        return MAPPER.convertValue(javaBean, new TypeReference<>() {
        });
    }
}