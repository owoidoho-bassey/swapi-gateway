package com.owoidoho.bassey.swapigateway.core.domain.resources;

import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents the SWAPI resource for starships.
 */
public final class Starships extends Resource {

  public Starships(String path, SWAPIRepository repository) {
    super(path, repository);
  }

  @Override
  @NotNull
  protected String name() {
    return "starships";
  }

  @Override
  @NotNull
  protected JSONObject resolveLinkedResources(JSONObject starshipData) {
    JSONArray pilots = resolveResources(starshipData.getJSONArray("pilots"));
    JSONArray films = resolveResources(starshipData.getJSONArray("films"));

    JSONObject resolvedStarshipData = new JSONObject(starshipData.toString());
    resolvedStarshipData.put("pilots", pilots);
    resolvedStarshipData.put("films", films);

    return resolvedStarshipData;
  }

}
