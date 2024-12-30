package com.vincent.sample.api.factory;

import com.vincent.sample.api.ApiUserService;
import com.vincent.sample.api.impl.ApiUserServiceImpl;
import com.vincent.sample.api.properties.ApiUserProperties;
import com.vincent.sample.config.RestClientConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.Builder;

/**
 * todo: what are capabilities in this class. and plz introduction of this class
 *
 * @author wunhwantseng@gmail.com
 * @since todo - since from which version
 */
@Component
public class ApiUserServiceFactory implements FactoryBean<ApiUserService> {

  /**
   * LoggingAppender
   */
  private final Logger log = LoggerFactory.getLogger(getClass());

  /**
   * Properties for api user
   */
  private final ApiUserProperties properties;

  /**
   * Builder for rest client
   */
  private RestClient.Builder clientBuilder;

  public ApiUserServiceFactory(ApiUserProperties properties) {
    this.properties = properties;
  }

  /**
   * Setter rest client builder
   */
  @Autowired(required = false)
  public void setClientBuilder(
      @Qualifier(RestClientConfiguration.BEAN_USER_REST_BUILDER) Builder builder
  ) {
    if (builder == null) {
      log.error("Factory api user service set builder is null.");

      return;
    }

    this.clientBuilder = builder;
  }

  @Override
  public ApiUserService getObject() {
    RestClient.Builder builder = this.clientBuilder;
    if (builder == null) {
      builder = RestClient.builder();
    }

    final String endpointsInfoWithName = this.properties.getEndpointsInfoWithName();
    if (endpointsInfoWithName == null || endpointsInfoWithName.isBlank()) {
      throw new NullPointerException("Endpoint for user-info with name should not blank.");
    }

    return new ApiUserServiceImpl(endpointsInfoWithName, builder);
  }

  @Override
  public Class<?> getObjectType() {
    return ApiUserService.class;
  }

}
