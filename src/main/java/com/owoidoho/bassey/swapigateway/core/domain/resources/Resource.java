package com.owoidoho.bassey.swapigateway.core.domain.resources;

import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Resource {

  protected String path;
  protected SWAPIRepository repository;

  public Resource(
      @NotNull String path,
      @NotNull SWAPIRepository repository
  ) {
    this.path = path.trim();
    this.repository = repository;
  }

  @NotNull
  public JSONObject fetch() {
    return repository.fetchResource(name(), path);
  }

  @NotNull
  public JSONObject fetchAndResolveAttributes() {
    JSONObject resourceData = fetch();
    return resolveResourceAttributes(resourceData);
  }

  public JSONObject fetchPaged(int page) {
    return repository.fetchResource(name(), "?page=" + page);
  }

  @NotNull
  public JSONObject fetchPagedAndResolveAttributes(int page) {
    JSONObject resourceData = fetchPaged(page);
    JSONArray resources = resourceData.getJSONArray("results");
    JSONArray resolvedResources = new JSONArray();

    resources.forEach(resource -> {
      JSONObject resolvedResource = resolveResourceAttributes((JSONObject) resource);
      resolvedResources.put(resolvedResource);
    });

    resourceData.put("results", resolvedResources);
    return resourceData;
  }

  @NotNull
  public JSONArray fetchAll() {
    return getAllResources(this::fetchPaged);
  }

  @NotNull
  public JSONArray fetchAllAndResolveAttributes() {
    return getAllResources(this::fetchPagedAndResolveAttributes);
  }

  @NotNull
  private JSONArray getAllResources(Function<Integer, JSONObject> pagedDataSupplier) {
    int page = 1;
    JSONArray resourcesArray = new JSONArray();
    JSONObject resourcesData;
    JSONArray results;
    String next;
    do {
      resourcesData = pagedDataSupplier.apply(page);
      results = resourcesData.getJSONArray("results");
      next = resourcesData.optString("next");
      resourcesArray.putAll(results);
      page++;
    } while (next != null && !next.isBlank());

    return resourcesArray;
  }

  @NotNull
  protected abstract String name();

  @NotNull
  protected abstract JSONObject resolveResourceAttributes(JSONObject resourceData);

  @NotNull
  protected String path() {
    return path;
  }

  protected JSONObject resolveResourceLink(@NotNull String resourceLink) {
    Resource resource = extractResourceFromUrl(resourceLink);
    return resource.fetchMinimal();
  }

  protected JSONArray resolveResourceLinks(@NotNull JSONArray resourceLinks) {
    JSONArray resolvedResources = new JSONArray();
    resourceLinks.forEach(resourceLink -> {
      Resource resource = extractResourceFromUrl(resourceLink.toString());
      if (resource instanceof AggregateResource aggregateResource) {
        resolvedResources.put(aggregateResource.fetchRecursively(this));
      } else {
        resolvedResources.put(resource.fetchMinimal());
      }
    });

    return resolvedResources;
  }

  @NotNull
  protected JSONObject fetchMinimal() {
    JSONObject resourceData = fetch();
    JSONObject resolvedRersource = new JSONObject();
    resolvedRersource.put("name", resourceData.getString("name"));
    resolvedRersource.put("id", path);
    return resolvedRersource;
  }


  private Resource extractResourceFromUrl(@NotNull String url) {
    String[] urlParts = url.split("/");
    int resourceIndex = urlParts.length - 2;
    int idIndex = urlParts.length - 1;
    return switch (urlParts[resourceIndex]) {
      case "people" -> new People(urlParts[idIndex], repository);
      case "films" -> new Films(urlParts[idIndex], repository);
      case "planets" -> new Planets(urlParts[idIndex], repository);
      case "species" -> new Species(urlParts[idIndex], repository);
      case "vehicles" -> new Vehicles(urlParts[idIndex], repository);
      case "starships" -> new Starships(urlParts[idIndex], repository);
      default -> throw new ResourceFormatException("Invalid url specified in resource field");
    };
  }
}
