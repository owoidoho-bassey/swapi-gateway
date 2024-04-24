package com.owoidoho.bassey.swapigateway.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class ApplicatonConfiguration {

  @Bean
  public RestClient restClient(@Value("${swapi.baseUrl}") String baseUrl) {
    return RestClient
        .builder()
        .requestFactory(new HttpComponentsClientHttpRequestFactory())
        .baseUrl(baseUrl)
        .build();
  }
}
