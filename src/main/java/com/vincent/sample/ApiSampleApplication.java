package com.vincent.sample;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * todo: what are capabilities in this class. and plz introduction of this class
 *
 * @author wunhwantseng@gmail.com
 * @since todo - since from which version
 */
@SpringBootApplication
public class ApiSampleApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder()
        .bannerMode(Mode.OFF)
        .run(args);
  }
}
