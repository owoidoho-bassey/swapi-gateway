package com.owoidoho.bassey.swapigateway.core.domain.resources;

import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents the SWAPI resource for species.
 */
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
  protected JSONObject resolveLinkedResources(JSONObject specieData) {
    String homeworldValue = specieData.optString("homeworld");
    Object homeworld = homeworldValue == null || homeworldValue.isBlank() ? JSONObject.NULL
        : resolveResource(homeworldValue);
    JSONArray people = resolveResources(specieData.getJSONArray("people"));
    JSONArray films = resolveResources(specieData.getJSONArray("films"));

    JSONObject resolvedSpecieData = new JSONObject(specieData.toString());
    resolvedSpecieData.put("homeworld", homeworld);
    resolvedSpecieData.put("people", people);
    resolvedSpecieData.put("films", films);

    return resolvedSpecieData;
  }


}