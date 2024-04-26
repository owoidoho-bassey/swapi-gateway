package com.owoidoho.bassey.swapigateway.core.domain.resources;

import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents the SWAPI resource for planets.
 */
public final class Planets extends Resource {

  public Planets(String path, SWAPIRepository repository) {
    super(path, repository);
  }

  @Override
  @NotNull
  protected String name() {
    return "planets";
  }

  @Override
  @NotNull
  protected JSONObject resolveLinkedResources(JSONObject planetData) {
    JSONArray residents = resolveResources(planetData.getJSONArray("residents"));
    JSONArray films = resolveResources(planetData.getJSONArray("films"));

    JSONObject resolvedPlanetData = new JSONObject(planetData.toString());
    resolvedPlanetData.put("residents", residents);
    resolvedPlanetData.put("films", films);

    return resolvedPlanetData;
  }

}