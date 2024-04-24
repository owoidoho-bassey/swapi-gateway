package com.owoidoho.bassey.swapigateway.core.domain.resources;

import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

public final class Species extends Resource {

  public Species(String path, SWAPIRepository repository) {
    super(path, repository);
  }

  @Override
  @NotNull
  protected String name() {
    return "species";
  }

  @Override
  @NotNull
  protected JSONObject resolveResourceAttributes(JSONObject specieData) {
    JSONObject homeworld = resolveResourceLink(specieData.getString("homeworld"));
    JSONArray people = resolveResourceLinks(specieData.getJSONArray("people"));
    JSONArray films = resolveResourceLinks(specieData.getJSONArray("films"));

    JSONObject resolvedSpecieData = new JSONObject(specieData.toString());
    resolvedSpecieData.put("homeworld", homeworld);
    resolvedSpecieData.put("people", people);
    resolvedSpecieData.put("films", films);

    return resolvedSpecieData;
  }


}