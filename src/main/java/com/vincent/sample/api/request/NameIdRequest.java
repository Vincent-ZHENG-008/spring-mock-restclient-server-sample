package com.vincent.sample.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * todo: what are capabilities in this class. and plz introduction of this class
 *
 * @author wunhwantseng@gmail.com
 * @since todo - since from which version
 */
public record NameIdRequest(
    @JsonProperty("name") String username
) {

  public static NameIdRequest of(String username) {
    return new NameIdRequest(username);
  }

}
