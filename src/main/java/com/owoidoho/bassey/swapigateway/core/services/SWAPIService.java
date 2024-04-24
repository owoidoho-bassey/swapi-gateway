package com.owoidoho.bassey.swapigateway.core.services;

import com.owoidoho.bassey.swapigateway.core.domain.resources.Films;
import com.owoidoho.bassey.swapigateway.core.domain.resources.People;
import com.owoidoho.bassey.swapigateway.core.domain.resources.Planets;
import com.owoidoho.bassey.swapigateway.core.domain.resources.Resource;
import com.owoidoho.bassey.swapigateway.core.domain.resources.Root;
import com.owoidoho.bassey.swapigateway.core.domain.resources.Species;
import com.owoidoho.bassey.swapigateway.core.domain.resources.Starships;
import com.owoidoho.bassey.swapigateway.core.domain.resources.Vehicles;
import com.owoidoho.bassey.swapigateway.core.repository.ResourceNotFoundException;
import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class SWAPIService {

  private final SWAPIRepository repository;

  public SWAPIService(SWAPIRepository repository) {
    this.repository = repository;
  }

  @NotNull
  public JSONObject getResource(
      @Nullable String resources,
      @Nullable String path
  ) {
    return getResourceType(resources, path).fetch();
  }

  @NotNull
  public JSONObject getResolvedResource(
      @Nullable String resources,
      @Nullable String path
  ) {
    return getResourceType(resources, path)
        .fetchAndResolveAttributes();
  }

  @NotNull
  public JSONObject getPagedResources(
      @Nullable String resources,
      int page
  ) {
    return getResourceType(resources, "")
        .fetchPaged(page);
  }

  @NotNull
  public JSONObject getPagedResolvedResources(
      @Nullable String resources,
      int page
  ) {
    return getResourceType(resources, "")
        .fetchPagedAndResolveAttributes(page);
  }

  @NotNull
  public JSONArray getAllResources(@Nullable String resources) {
    return getResourceType(resources, "")
        .fetchAll();
  }

  @NotNull
  public JSONArray getAllResolvedResources(@Nullable String resources) {
    return getResourceType(resources, "")
        .fetchAllAndResolveAttributes();
  }

  Resource getResourceType(@Nullable String resources, @Nullable String path) {
    if (resources == null) {
      return new Root(repository);
    }
    return switch (resources.trim()) {
      case "people" -> new People(path, repository);
      case "films" -> new Films(path, repository);
      case "planets" -> new Planets(path, repository);
      case "species" -> new Species(path, repository);
      case "vehicles" -> new Vehicles(path, repository);
      case "starships" -> new Starships(path, repository);
      case "" -> new Root(repository);
      default -> throw new ResourceNotFoundException("Resource not found",
          new JSONObject().put("detail", "Not found"));
    };
  }

}
