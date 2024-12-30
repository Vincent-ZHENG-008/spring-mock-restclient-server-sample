package com.vincent.sample.api;

import com.vincent.sample.api.request.NameIdRequest;
import com.vincent.sample.api.response.UserInfoResponse;
import java.util.Optional;

/**
 * todo: what are capabilities in this class. and plz introduction of this class
 *
 * @author wunhwantseng@gmail.com
 * @since todo - since from which version
 */
public interface ApiUserService {

  /**
   * For query userinfo by name
   */
  Optional<UserInfoResponse> find(NameIdRequest request);

}
