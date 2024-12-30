package com.vincent.sample.api.impl;

import com.vincent.sample.api.ApiUserService;
import com.vincent.sample.api.request.NameIdRequest;
import com.vincent.sample.api.response.UserInfoResponse;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * todo: what are capabilities in this class. and plz introduction of this class
 *
 * @author wunhwantseng@gmail.com
 * @since todo - since from which version
 */
public class ApiUserServiceImpl implements ApiUserService {

  /**
   * Path for query info by name
   */
  private final String pathInfoWithName;

  /**
   * Interface rest client
   */
  private final RestClient client;

  public ApiUserServiceImpl(String pathInfoWithName, RestClient.Builder clientBuilder) {
    this.pathInfoWithName = pathInfoWithName;
    this.client = clientBuilder.build();
  }

  @Override
  public Optional<UserInfoResponse> find(NameIdRequest nameId) {
    return this.client.get()
        .uri(builder -> builder.path(this.pathInfoWithName).build(nameId.username()))
        .accept(MediaType.APPLICATION_JSON).acceptCharset(StandardCharsets.UTF_8)
        .exchange((request, response) -> {
          final HttpStatusCode statusCode = response.getStatusCode();
          if (statusCode.is4xxClientError()) {
            return Optional.empty();
          }

          final UserInfoResponse userInfo = response.bodyTo(UserInfoResponse.class);
          if (userInfo != null) {
            return Optional.of(userInfo);
          }
          return Optional.empty();
        });
  }
}
