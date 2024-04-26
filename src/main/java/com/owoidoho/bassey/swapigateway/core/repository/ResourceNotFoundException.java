package com.owoidoho.bassey.swapigateway.core.repository;

import org.json.JSONObject;

public class ResourceNotFoundException extends RuntimeException {

  private final JSONObject response;

  public ResourceNotFoundException(JSONObject response) {
    super("Resource not found");
    this.response = response;
  }

  public JSONObject getResponse() {
    return response;
  }

}
