package com.vincent.sample.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

/**
 * todo: what are capabilities in this class. and plz introduction of this class
 *
 * @author wunhwantseng@gmail.com
 * @since todo - since from which version
 */
public record UserInfoResponse(
    @JsonProperty("username") String username,
    @JsonProperty("role") String role,
    @JsonProperty("unx_created_at") Long createdAt
) {

}
