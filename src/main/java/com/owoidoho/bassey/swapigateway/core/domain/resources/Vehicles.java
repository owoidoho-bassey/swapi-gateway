package com.owoidoho.bassey.swapigateway.core.domain.resources;

import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

public final class Vehicles extends Resource {

  public Vehicles(String path, SWAPIRepository repository) {
    super(path, repository);
  }


  @Override
  @NotNull
  protected String name() {
    return "vehicles";
  }

  @Override
  @NotNull
  protected JSONObject resolveResourceAttributes(JSONObject vehicleData) {
    JSONArray pilots = resolveResourceLinks(vehicleData.getJSONArray("pilots"));
    JSONArray films = resolveResourceLinks(vehicleData.getJSONArray("films"));

    JSONObject resolvedVehicleData = new JSONObject(vehicleData.toString());
    resolvedVehicleData.put("pilots", pilots);
    resolvedVehicleData.put("films", films);

    return resolvedVehicleData;
  }


}
