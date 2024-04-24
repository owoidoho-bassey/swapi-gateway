package com.owoidoho.bassey.swapigateway.core.domain.resources;

import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents the SWAPI resource for people.
 */
public final class People extends AggregateResource {

  public People(String path, SWAPIRepository repository) {
    super(path, repository);
  }

  @Override
  @NotNull
  protected String name() {
    return "people";
  }

  @Override
  @NotNull
  protected JSONObject resolveLinkedResources(JSONObject personData) {
    JSONArray films = resolveResourcesLinks(personData.getJSONArray("films"));
    JSONObject planets = resolveResourceLink(personData.getString("homeworld"));
    JSONArray species = resolveResourcesLinks(personData.getJSONArray("species"));
    JSONArray starships = resolveResourcesLinks(personData.getJSONArray("starships"));
    JSONArray vehicles = resolveResourcesLinks(personData.getJSONArray("vehicles"));

    JSONObject resolvedPersonData = new JSONObject(personData.toString());
    resolvedPersonData.put("films", films);
    resolvedPersonData.put("homeworld", planets);
    resolvedPersonData.put("species", species);
    resolvedPersonData.put("starships", starships);
    resolvedPersonData.put("vehicles", vehicles);

    return resolvedPersonData;
  }

  @Override
  public JSONObject fetchRecursively(Resource parentResource) {
    if (parentResource instanceof AggregateResource) {
      return fetchMinimal();
    }

    JSONObject personData = fetch();
    JSONArray films = resolveResourcesLinks(personData.getJSONArray("films"));
    JSONObject planet = resolveResourceLink(personData.getString("homeworld"));
    JSONArray species = resolveResourcesLinks(personData.getJSONArray("species"));
    JSONArray starships = resolveResourcesLinks(personData.getJSONArray("starships"));
    JSONArray vehicles = resolveResourcesLinks(personData.getJSONArray("vehicles"));

    JSONObject resolvedPersonData = new JSONObject(personData.toString());
    resolvedPersonData.put("films", films);
    resolvedPersonData.put("homeworld", planet);
    resolvedPersonData.put("species", species);
    resolvedPersonData.put("starships", starships);
    resolvedPersonData.put("vehicles", vehicles);

    return resolvedPersonData;
  }
}
