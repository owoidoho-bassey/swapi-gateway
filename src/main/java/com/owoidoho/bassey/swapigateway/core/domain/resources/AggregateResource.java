package com.owoidoho.bassey.swapigateway.core.domain.resources;

import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

abstract class AggregateResource extends Resource {

  public AggregateResource(@NotNull String path,
      @NotNull SWAPIRepository repository) {
    super(path, repository);
  }

  protected abstract JSONObject fetchRecursively(Resource parentResource);

}
