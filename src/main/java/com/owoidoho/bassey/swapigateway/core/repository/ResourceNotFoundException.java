package com.owoidoho.bassey.swapigateway.core.repository;

import org.json.JSONObject;

public class ResourceNotFoundException extends RuntimeException {

  private final JSONObject response;

  public ResourceNotFoundException(String message, JSONObject response) {
    super(message);
    this.response = response;
  }

  public JSONObject getResponse() {
    return response;
  }

}
