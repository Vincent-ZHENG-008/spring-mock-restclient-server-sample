package com.vincent.sample.api.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * todo: what are capabilities in this class. and plz introduction of this class
 *
 * @author wunhwantseng@gmail.com
 * @since todo - since from which version
 */
@ConfigurationProperties(prefix = "api.user")
public class ApiUserProperties {

  /**
   * Endpoint for user info with name path param
   */
  private String endpointsInfoWithName = "http://192.169.1.1:8080/api/user/v1/{name}";

  public String getEndpointsInfoWithName() {
    return endpointsInfoWithName;
  }

  public void setEndpointsInfoWithName(String endpointsInfoWithName) {
    this.endpointsInfoWithName = endpointsInfoWithName;
  }

  @Override
  public String toString() {
    return "ApiUserProperties{" +
        "endpointsInfoWithName='" + endpointsInfoWithName + '\'' +
        '}';
  }
}
