package com.owoidoho.bassey.swapigateway.core.domain.resources;

import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/**
 * Represents the SWAPI resource for the root resource.
 */
public final class Root extends Resource {

  public Root(SWAPIRepository repository) {
    super("", repository);
  }


  @Override
  @NotNull
  protected String name() {
    return "";
  }

  @Override
  protected @NotNull JSONObject resolveLinkedResources(JSONObject resourceData) {
    return new JSONObject(resourceData.toString());
  }
}