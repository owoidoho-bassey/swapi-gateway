package com.owoidoho.bassey.swapigateway.core.domain.resources;

import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/**
 * An abstract class that represents a resource that is an aggregate of other resources.
 * <p>
 * The class provides a method to fetch the linked resources recursively. To avoid infinite
 * recursion, the method fetchRecursively will not recurse when it is being called by another
 * aggregate resource.
 */
abstract class AggregateResource extends Resource {

  public AggregateResource(@NotNull String path,
      @NotNull SWAPIRepository repository) {
    super(path, repository);
  }

  protected abstract JSONObject fetchRecursively(Resource parentResource);

}
