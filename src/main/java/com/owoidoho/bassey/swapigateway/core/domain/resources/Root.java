package com.owoidoho.bassey.swapigateway.core.domain.resources;

import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

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
  protected @NotNull JSONObject resolveResourceAttributes(JSONObject resourceData) {
    return new JSONObject(resourceData.toString());
  }
}