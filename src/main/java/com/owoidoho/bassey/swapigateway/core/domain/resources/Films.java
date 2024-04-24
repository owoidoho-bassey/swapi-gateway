package com.owoidoho.bassey.swapigateway.core.domain.resources;

import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

public final class Films extends AggregateResource {

  public Films(String path, SWAPIRepository repository) {
    super(path, repository);
  }

  @Override
  @NotNull
  protected String name() {
    return "films";
  }

  @Override
  @NotNull
  protected JSONObject resolveResourceAttributes(@NotNull JSONObject filmData) {
    JSONArray characters = resolveResourceLinks(filmData.getJSONArray("characters"));
    JSONArray planets = resolveResourceLinks(filmData.getJSONArray("planets"));
    JSONArray species = resolveResourceLinks(filmData.getJSONArray("species"));
    JSONArray starships = resolveResourceLinks(filmData.getJSONArray("starships"));
    JSONArray vehicles = resolveResourceLinks(filmData.getJSONArray("vehicles"));

    JSONObject resolvedFilmData = new JSONObject(filmData.toString());
    resolvedFilmData.put("characters", characters);
    resolvedFilmData.put("planets", planets);
    resolvedFilmData.put("species", species);
    resolvedFilmData.put("starships", starships);
    resolvedFilmData.put("vehicles", vehicles);

    return resolvedFilmData;
  }

  @Override
  protected @NotNull JSONObject fetchMinimal() {
    JSONObject resourceData = fetch();
    JSONObject resolvedRersource = new JSONObject();
    resolvedRersource.put("title", resourceData.getString("title"));
    resolvedRersource.put("id", path);
    return resolvedRersource;
  }

  @Override
  protected JSONObject fetchRecursively(Resource parentResource) {
    if (parentResource instanceof AggregateResource) {
      return fetchMinimal();
    }

    JSONObject filmData = fetch();
    JSONArray characters = resolveResourceLinks(filmData.getJSONArray("characters"));
    JSONArray planets = resolveResourceLinks(filmData.getJSONArray("planets"));
    JSONArray species = resolveResourceLinks(filmData.getJSONArray("species"));
    JSONArray starships = resolveResourceLinks(filmData.getJSONArray("starships"));
    JSONArray vehicles = resolveResourceLinks(filmData.getJSONArray("vehicles"));

    JSONObject resolvedFilmData = new JSONObject(filmData.toString());
    resolvedFilmData.put("characters", characters);
    resolvedFilmData.put("planets", planets);
    resolvedFilmData.put("species", species);
    resolvedFilmData.put("starships", starships);
    resolvedFilmData.put("vehicles", vehicles);

    return resolvedFilmData;
  }

}
