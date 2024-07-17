package com.example.purithm.auth.dto.response;

import java.util.List;
import lombok.Getter;

@Getter
public class JWKSetKeys {

  private List<Key> keys;
  @Getter
  class Key {
    private String alg;
    private String e;
    private String kid;
    private String kty;
    private String n;
    private String use;
  }
}
