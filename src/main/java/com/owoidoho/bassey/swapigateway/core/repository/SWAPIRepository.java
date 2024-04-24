package com.owoidoho.bassey.swapigateway.core.repository;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;


public interface SWAPIRepository {

  @NotNull
  JSONObject fetchResource(@NotNull String resource, @NotNull String path);
}
