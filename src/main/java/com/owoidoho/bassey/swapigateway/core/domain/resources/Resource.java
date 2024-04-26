package com.owoidoho.bassey.swapigateway.core.domain.resources;

import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents the base class for all SWAPI resources.
 * <p>
 * It provides methods for fetching resources, fetching all resources, and resolving linked
 * resources. It takes a repository parameter for fetching resources from a storage.
 */
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
  public JSONObject fetchAndResolveResources() {
    JSONObject resourceData = fetch();
    return resolveLinkedResources(resourceData);
  }

  public JSONObject fetchPaged(int page) {
    return repository.fetchResource(name(), "?page=" + page);
  }

  @NotNull
  public JSONObject fetchPagedAndResolveResources(int page) {
    JSONObject resourceData = fetchPaged(page);
    JSONArray resources = resourceData.getJSONArray("results");
    JSONArray resolvedResources = new JSONArray();

    resources.forEach(resource -> {
      JSONObject resolvedResource = resolveLinkedResources((JSONObject) resource);
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
  public JSONArray fetchAllAndResolveResources() {
    return getAllResources(this::fetchPagedAndResolveResources);
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
  protected abstract JSONObject resolveLinkedResources(JSONObject resourceData);

  @NotNull
  protected String path() {
    return path;
  }

  protected JSONObject resolveResource(@NotNull String resourceLink) {
    Resource resource = extractResourceFromUrl(resourceLink);
    if (resource instanceof AggregateResource aggregateResource) {
      return aggregateResource.fetchRecursively(this);
    } else {
      return resource.fetchMinimal();
    }
  }

  protected JSONArray resolveResources(@NotNull JSONArray resourceLinks) {
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
      default -> throw new ResourceUnknownException("Invalid url specified in resource field");
    };
  }
}
