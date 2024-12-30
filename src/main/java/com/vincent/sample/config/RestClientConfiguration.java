package com.vincent.sample.config;

import com.vincent.sample.api.properties.ApiUserProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

/**
 * todo: what are capabilities in this class. and plz introduction of this class
 *
 * @author wunhwantseng@gmail.com
 * @since todo - since from which version
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({ApiUserProperties.class})
public class RestClientConfiguration {

  /**
   * Bean name of user rest client builder
   */
  public static final String BEAN_USER_REST_BUILDER = "beanUserRestBuilder";

  @Bean
  public RestTemplate restTemplate(ObjectProvider<HttpMessageConverter<?>> messageConverters) {
    final RestTemplate restTemplate = new RestTemplate();
    restTemplate.setMessageConverters(messageConverters.stream().toList());

    return restTemplate;
  }

  /**
   * Builder for user rest client
   */
  @Bean(BEAN_USER_REST_BUILDER)
  public RestClient.Builder userRestClientBuilder(RestTemplate restTemplate) {
    return RestClient.builder(restTemplate);
  }

}
