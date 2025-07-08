package com.jiangy.manticorepoc.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">JL-Code</a>
 * <p>创建时间: 2025/7/6 </p>
 */
@Configuration
@EnableConfigurationProperties(ManticoreConfig.ManticoreProperties.class)
public class ManticoreConfig {

    @Bean
    public WebClient manticoreWebClient(ManticoreProperties properties) {
        return WebClient.builder()
                .baseUrl(properties.getHttp().getUrl())
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024)) // 16MB
                .build();
    }

    @Setter
    @Getter
    @ConfigurationProperties(prefix = "manticore")
    public static class ManticoreProperties {
        private Http http = new Http();
        private Search search = new Search();

        @Setter
        @Getter
        public static class Http {
            private String url = "http://localhost:9308";
            private int timeout = 10000;

        }

        @Setter
        @Getter
        public static class Search {
            private String defaultIndex = "products";
            private int maxResults = 1000;
        }
    }
}