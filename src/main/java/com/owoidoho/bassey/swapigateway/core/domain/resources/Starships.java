package com.owoidoho.bassey.swapigateway.core.domain.resources;

import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

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
  protected JSONObject resolveResourceAttributes(JSONObject starshipData) {
    JSONArray pilots = resolveResourceLinks(starshipData.getJSONArray("pilots"));
    JSONArray films = resolveResourceLinks(starshipData.getJSONArray("films"));

    JSONObject resolvedStarshipData = new JSONObject(starshipData.toString());
    resolvedStarshipData.put("pilots", pilots);
    resolvedStarshipData.put("films", films);

    return resolvedStarshipData;
  }

}
