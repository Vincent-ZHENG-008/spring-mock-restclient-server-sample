package com.vincent.sample.api;

import static org.junit.jupiter.api.Assertions.*;

import com.vincent.sample.ApiSampleApplication;
import com.vincent.sample.api.properties.ApiUserProperties;
import com.vincent.sample.api.request.NameIdRequest;
import com.vincent.sample.api.response.UserInfoResponse;
import com.vincent.sample.config.RestClientConfiguration;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.*;

import java.net.URI;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.springframework.http.HttpMethod.*;

import static org.springframework.http.MediaType.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;

import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import org.springframework.web.client.RestClient;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

/**
 * todo: what are capabilities in this class. and plz introduction of this class
 *
 * @author wunhwantseng@gmail.com
 * @since todo - since from which version
 */
@SpringBootTest(classes = ApiSampleApplication.class)
class ApiUserServiceTest {

  // For all mock server
  private MockRestServiceServer mockUserServer;

  @Autowired
  private ApiUserProperties properties;

  /**
   * Prepare steps.
   *
   * <pre>
   * TODO: This build mock server can not register interceptor into RestClient builder. because the {@link com.vincent.sample.api.factory.ApiUserServiceFactory} is faster than here.
   */
  @BeforeEach
  void setup(
      @Qualifier(RestClientConfiguration.BEAN_USER_REST_BUILDER) RestClient.Builder userClientBuilder
  ) {
    this.mockUserServer = MockRestServiceServer.bindTo(userClientBuilder).build();
  }

  @Test
  void find(
      ApiUserService apiUserService,
      @Value("classpath:stubs/user/mock-successful-response.json") Resource responseResource
  ) throws IOException {
    final String username = "Vincent";

    this.mockUserServer.expect(requestTo(fromHttpUrl(this.properties.getEndpointsInfoWithName())
            .build(Map.of("name", username))))
        .andExpect(method(GET))
        .andRespond(withSuccess(responseResource.getContentAsString(UTF_8), APPLICATION_JSON));

    final Optional<UserInfoResponse> opt = apiUserService.find(NameIdRequest.of(username));
    assertTrue(opt.isPresent());

    final UserInfoResponse userInfo = opt.get();
  }
}