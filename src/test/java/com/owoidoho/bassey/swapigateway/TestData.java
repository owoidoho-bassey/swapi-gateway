package com.owoidoho.bassey.swapigateway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

public final class TestData {

  private TestData(){}

  public static String unPretty(@NotNull String json) {
    return json.charAt(0) == '{' ?
        new JSONObject(json).toString() :
        new JSONArray(json).toString();
  }
  public static final String SWAPI_BASE_URL = "https://swapi.py4e.com/api";

  public static final Map<String, String> RESOURCE_DATA_MAP = new HashMap<>() {{
    put("", ROOTS);
    put("people", PEOPLE_1);
    put("films", FILMS_1);
    put("planets", PLANETS_1);
    put("species", SPECIES_1);
    put("vehicles", VEHICLES_1);
    put("starships", STARSHIP_1);
  }};

  public static final String ROOTS = """
      {
          "people": "https://swapi.py4e.com/api/people/",
          "planets": "https://swapi.py4e.com/api/planets/",
          "films": "https://swapi.py4e.com/api/films/",
          "species": "https://swapi.py4e.com/api/species/",
          "vehicles": "https://swapi.py4e.com/api/vehicles/",
          "starships": "https://swapi.py4e.com/api/starships/"
      }
      """;

  public static final String PEOPLE_1 = """
      {
          "name": "Luke Skywalker",
          "height": "172",
          "mass": "77",
          "hair_color": "blond",
          "skin_color": "fair",
          "eye_color": "blue",
          "birth_year": "19BBY",
          "gender": "male",
          "homeworld": "https://swapi.py4e.com/api/planets/1/",
          "films": [
              "https://swapi.py4e.com/api/films/1/",
          ],
          "species": [
              "https://swapi.py4e.com/api/species/1/"
          ],
          "vehicles": [
              "https://swapi.py4e.com/api/vehicles/1/",
          ],
          "starships": [
              "https://swapi.py4e.com/api/starships/1/"
          ],
          "created": "2014-12-09T13:50:51.644000Z",
          "edited": "2014-12-20T21:17:56.891000Z",
          "url": "https://swapi.py4e.com/api/people/1/"
      }
      """;

  public static final String FILMS_1 = """
      {
          "title": "A New Hope",
          "episode_id": 4,
          "opening_crawl": "It is a period of civil war.\\r\\nRebel spaceships, striking\\r\\nfrom a hidden base, have won\\r\\ntheir first victory against\\r\\nthe evil Galactic Empire.\\r\\n\\r\\nDuring the battle, Rebel\\r\\nspies managed to steal secret\\r\\nplans to the Empire's\\r\\nultimate weapon, the DEATH\\r\\nSTAR, an armored space\\r\\nstation with enough power\\r\\nto destroy an entire planet.\\r\\n\\r\\nPursued by the Empire's\\r\\nsinister agents, Princess\\r\\nLeia races home aboard her\\r\\nstarship, custodian of the\\r\\nstolen plans that can save her\\r\\npeople and restore\\r\\nfreedom to the galaxy....",
          "director": "George Lucas",
          "producer": "Gary Kurtz, Rick McCallum",
          "release_date": "1977-05-25",
          "characters": [
              "https://swapi.py4e.com/api/people/1/",
          ],
          "planets": [
              "https://swapi.py4e.com/api/planets/1/",
          ],
          "starships": [
              "https://swapi.py4e.com/api/starships/1/",
          ],
          "vehicles": [
              "https://swapi.py4e.com/api/vehicles/1/",
          ],
          "species": [
              "https://swapi.py4e.com/api/species/1/",
          ],
          "created": "2014-12-10T14:23:31.880000Z",
          "edited": "2014-12-20T19:49:45.256000Z",
          "url": "https://swapi.py4e.com/api/films/1/"
      }
      """;

  public static final String PLANETS_1 = """
      {
          "name": "Tatooine",
          "rotation_period": "23",
          "orbital_period": "304",
          "diameter": "10465",
          "climate": "arid",
          "gravity": "1 standard",
          "terrain": "desert",
          "surface_water": "1",
          "population": "200000",
          "residents": [
              "https://swapi.py4e.com/api/people/1/",
          ],
          "films": [
              "https://swapi.py4e.com/api/films/1/",
          ],
          "created": "2014-12-09T13:50:49.641000Z",
          "edited": "2014-12-20T20:58:18.411000Z",
          "url": "https://swapi.py4e.com/api/planets/1/"
      }
      """;

  public static final String SPECIES_1 = """
      {
          "name": "Human",
          "classification": "mammal",
          "designation": "sentient",
          "average_height": "180",
          "skin_colors": "caucasian, black, asian, hispanic",
          "hair_colors": "blonde, brown, black, red",
          "eye_colors": "brown, blue, green, hazel, grey, amber",
          "average_lifespan": "120",
          "homeworld": "https://swapi.py4e.com/api/planets/1/",
          "language": "Galactic Basic",
          "people": [
              "https://swapi.py4e.com/api/people/1/",
          ],
          "films": [
              "https://swapi.py4e.com/api/films/1/",
          ],
          "created": "2014-12-10T13:52:11.567000Z",
          "edited": "2014-12-20T21:36:42.136000Z",
          "url": "https://swapi.py4e.com/api/species/1/"
      }
      """;

  public static final String VEHICLES_1 = """
      {
          "name": "Sand Crawler",
          "model": "Digger Crawler",
          "manufacturer": "Corellia Mining Corporation",
          "cost_in_credits": "150000",
          "length": "36.8 ",
          "max_atmosphering_speed": "30",
          "crew": "46",
          "passengers": "30",
          "cargo_capacity": "50000",
          "consumables": "2 months",
          "vehicle_class": "wheeled",
          "pilots": [
              "https://swapi.py4e.com/api/people/1/"
          ],
          "films": [
              "https://swapi.py4e.com/api/films/1/",
          ],
          "created": "2014-12-10T15:36:25.724000Z",
          "edited": "2014-12-20T21:30:21.661000Z",
          "url": "https://swapi.py4e.com/api/vehicles/1/"
      }
      """;

  public static final String VEHICLES_2 = """
      {
          "name": "T-16 skyhopper",
          "model": "T-16 skyhopper",
          "manufacturer": "Incom Corporation",
          "cost_in_credits": "14500",
          "length": "10.4 ",
          "max_atmosphering_speed": "1200",
          "crew": "1",
          "passengers": "1",
          "cargo_capacity": "50",
          "consumables": "0",
          "vehicle_class": "repulsorcraft",
          "pilots": [
              "https://swapi.py4e.com/api/people/1/"
          ],
          "films": [
              "https://swapi.py4e.com/api/films/1/"
          ],
          "created": "2014-12-10T16:01:52.434000Z",
          "edited": "2014-12-20T21:30:21.665000Z",
          "url": "https://swapi.py4e.com/api/vehicles/2/"
      }
      """;

  public static final String STARSHIP_1 = """
      {
          "name": "CR90 corvette",
          "model": "CR90 corvette",
          "manufacturer": "Corellian Engineering Corporation",
          "cost_in_credits": "3500000",
          "length": "150",
          "max_atmosphering_speed": "950",
          "crew": "30-165",
          "passengers": "600",
          "cargo_capacity": "3000000",
          "consumables": "1 year",
          "hyperdrive_rating": "2.0",
          "MGLT": "60",
          "starship_class": "corvette",
          "pilots": [],
          "films": [
              "https://swapi.py4e.com/api/films/1/",
          ],
          "created": "2014-12-10T14:20:33.369000Z",
          "edited": "2014-12-20T21:23:49.867000Z",
          "url": "https://swapi.py4e.com/api/starships/1/"
      }
      """;

  public static final String PEOPLE_1_RESOLVED = """
      {
          "films": [
              {
                  "id": "1",
                  "title": "A New Hope"
              }
          ],
          "homeworld": {
              "name": "Tatooine",
              "id": "1"
          },
          "gender": "male",
          "skin_color": "fair",
          "edited": "2014-12-20T21:17:56.891000Z",
          "created": "2014-12-09T13:50:51.644000Z",
          "mass": "77",
          "vehicles": [
              {
                  "name": "Sand Crawler",
                  "id": "1"
              }
          ],
          "url": "https://swapi.py4e.com/api/people/1/",
          "hair_color": "blond",
          "birth_year": "19BBY",
          "eye_color": "blue",
          "species": [
              {
                  "name": "Human",
                  "id": "1"
              }
          ],
          "starships": [
              {
                  "name": "CR90 corvette",
                  "id": "1"
              }
          ],
          "name": "Luke Skywalker",
          "height": "172"
      }
      """;

  public static final String VEHICLES_PAGE_1 = """
      {
          "count": 39,
          "next": "https://swapi.py4e.com/api/vehicles/?page=2",
          "previous": null,
          "results": [
              {
                  "name": "Sand Crawler",
                  "model": "Digger Crawler",
                  "manufacturer": "Corellia Mining Corporation",
                  "cost_in_credits": "150000",
                  "length": "36.8 ",
                  "max_atmosphering_speed": "30",
                  "crew": "46",
                  "passengers": "30",
                  "cargo_capacity": "50000",
                  "consumables": "2 months",
                  "vehicle_class": "wheeled",
                  "pilots": [
                      "https://swapi.py4e.com/api/people/1/"
                  ],
                  "films": [
                      "https://swapi.py4e.com/api/films/1/"
                  ],
                  "created": "2014-12-10T15:36:25.724000Z",
                  "edited": "2014-12-20T21:30:21.661000Z",
                  "url": "https://swapi.py4e.com/api/vehicles/1/"
              }
          ]
      }
      """;

  public static final String VEHICLES_PAGE_2 = """
      {
          "count": 39,
          "next": null,
          "previous": null,
          "results": [
              {
                "name": "T-16 skyhopper",
                "model": "T-16 skyhopper",
                "manufacturer": "Incom Corporation",
                "cost_in_credits": "14500",
                "length": "10.4 ",
                "max_atmosphering_speed": "1200",
                "crew": "1",
                "passengers": "1",
                "cargo_capacity": "50",
                "consumables": "0",
                "vehicle_class": "repulsorcraft",
                "pilots": [
                    "https://swapi.py4e.com/api/people/1/"
                ],
                "films": [
                    "https://swapi.py4e.com/api/films/1/"
                ],
                "created": "2014-12-10T16:01:52.434000Z",
                "edited": "2014-12-20T21:30:21.665000Z",
                "url": "https://swapi.py4e.com/api/vehicles/2/"
            }
          ]
      }
      """;

  public static final String VEHICLES_1_RESOLVED = """
      {
          "name": "Sand Crawler",
          "model": "Digger Crawler",
          "manufacturer": "Corellia Mining Corporation",
          "cost_in_credits": "150000",
          "length": "36.8 ",
          "max_atmosphering_speed": "30",
          "crew": "46",
          "passengers": "30",
          "cargo_capacity": "50000",
          "consumables": "2 months",
          "vehicle_class": "wheeled",
          "pilots": [
              {
                  "films": [
                      {
                          "id": "1",
                          "title": "A New Hope"
                      }
                  ],
                  "homeworld": {
                      "name": "Tatooine",
                      "id": "1"
                  },
                  "gender": "male",
                  "skin_color": "fair",
                  "edited": "2014-12-20T21:17:56.891000Z",
                  "created": "2014-12-09T13:50:51.644000Z",
                  "mass": "77",
                  "vehicles": [
                      {
                          "name": "Sand Crawler",
                          "id": "1"
                      }
                  ],
                  "url": "https://swapi.py4e.com/api/people/1/",
                  "hair_color": "blond",
                  "birth_year": "19BBY",
                  "eye_color": "blue",
                  "species": [
                      {
                          "name": "Human",
                          "id": "1"
                      }
                  ],
                  "starships": [
                      {
                          "name": "CR90 corvette",
                          "id": "1"
                      }
                  ],
                  "name": "Luke Skywalker",
                  "height": "172"
              }
          ],
          "films": [
               {
                  "edited": "2014-12-20T19:49:45.256000Z",
                  "director": "George Lucas",
                  "created": "2014-12-10T14:23:31.880000Z",
                  "vehicles": [
                      {
                          "name": "Sand Crawler",
                          "id": "1"
                      }
                  ],
                  "opening_crawl": "It is a period of civil war.\\r\\nRebel spaceships, striking\\r\\nfrom a hidden base, have won\\r\\ntheir first victory against\\r\\nthe evil Galactic Empire.\\r\\n\\r\\nDuring the battle, Rebel\\r\\nspies managed to steal secret\\r\\nplans to the Empire's\\r\\nultimate weapon, the DEATH\\r\\nSTAR, an armored space\\r\\nstation with enough power\\r\\nto destroy an entire planet.\\r\\n\\r\\nPursued by the Empire's\\r\\nsinister agents, Princess\\r\\nLeia races home aboard her\\r\\nstarship, custodian of the\\r\\nstolen plans that can save her\\r\\npeople and restore\\r\\nfreedom to the galaxy....",
                  "title": "A New Hope",
                  "url": "https://swapi.py4e.com/api/films/1/",
                  "characters": [
                      {
                          "name": "Luke Skywalker",
                          "id": "1"
                      }
                  ],
                  "episode_id": 4,
                  "planets": [
                      {
                          "name": "Tatooine",
                          "id": "1"
                      }
                  ],
                  "release_date": "1977-05-25",
                  "starships": [
                      {
                          "name": "CR90 corvette",
                          "id": "1"
                      }
                  ],
                  "species": [
                      {
                          "name": "Human",
                          "id": "1"
                      }
                  ],
                  "producer": "Gary Kurtz, Rick McCallum"
              }
          ],
          "created": "2014-12-10T15:36:25.724000Z",
          "edited": "2014-12-20T21:30:21.661000Z",
          "url": "https://swapi.py4e.com/api/vehicles/1/"
      }
      """;

  public static final String VEHICLES_2_RESOLVED = """
      {
          "name": "T-16 skyhopper",
          "model": "T-16 skyhopper",
          "manufacturer": "Incom Corporation",
          "cost_in_credits": "14500",
          "length": "10.4 ",
          "max_atmosphering_speed": "1200",
          "crew": "1",
          "passengers": "1",
          "cargo_capacity": "50",
          "consumables": "0",
          "vehicle_class": "repulsorcraft",
          "pilots": [
                {
                    "films": [
                        {
                            "id": "1",
                            "title": "A New Hope"
                        }
                    ],
                    "homeworld": {
                        "name": "Tatooine",
                        "id": "1"
                    },
                    "gender": "male",
                    "skin_color": "fair",
                    "edited": "2014-12-20T21:17:56.891000Z",
                    "created": "2014-12-09T13:50:51.644000Z",
                    "mass": "77",
                    "vehicles": [
                        {
                            "name": "Sand Crawler",
                            "id": "1"
                        }
                    ],
                    "url": "https://swapi.py4e.com/api/people/1/",
                    "hair_color": "blond",
                    "birth_year": "19BBY",
                    "eye_color": "blue",
                    "species": [
                        {
                            "name": "Human",
                            "id": "1"
                        }
                    ],
                    "starships": [
                        {
                            "name": "CR90 corvette",
                            "id": "1"
                        }
                    ],
                    "name": "Luke Skywalker",
                    "height": "172"
                }
            ],
            "films": [
                 {
                    "edited": "2014-12-20T19:49:45.256000Z",
                    "director": "George Lucas",
                    "created": "2014-12-10T14:23:31.880000Z",
                    "vehicles": [
                        {
                            "name": "Sand Crawler",
                            "id": "1"
                        }
                    ],
                    "opening_crawl": "It is a period of civil war.\\r\\nRebel spaceships, striking\\r\\nfrom a hidden base, have won\\r\\ntheir first victory against\\r\\nthe evil Galactic Empire.\\r\\n\\r\\nDuring the battle, Rebel\\r\\nspies managed to steal secret\\r\\nplans to the Empire's\\r\\nultimate weapon, the DEATH\\r\\nSTAR, an armored space\\r\\nstation with enough power\\r\\nto destroy an entire planet.\\r\\n\\r\\nPursued by the Empire's\\r\\nsinister agents, Princess\\r\\nLeia races home aboard her\\r\\nstarship, custodian of the\\r\\nstolen plans that can save her\\r\\npeople and restore\\r\\nfreedom to the galaxy....",
                    "title": "A New Hope",
                    "url": "https://swapi.py4e.com/api/films/1/",
                    "characters": [
                        {
                            "name": "Luke Skywalker",
                            "id": "1"
                        }
                    ],
                    "episode_id": 4,
                    "planets": [
                        {
                            "name": "Tatooine",
                            "id": "1"
                        }
                    ],
                    "release_date": "1977-05-25",
                    "starships": [
                        {
                            "name": "CR90 corvette",
                            "id": "1"
                        }
                    ],
                    "species": [
                        {
                            "name": "Human",
                            "id": "1"
                        }
                    ],
                    "producer": "Gary Kurtz, Rick McCallum"
                }
            ],
          "created": "2014-12-10T16:01:52.434000Z",
          "edited": "2014-12-20T21:30:21.665000Z",
          "url": "https://swapi.py4e.com/api/vehicles/2/"
      }
      """;

      public static final String VEHICLES_PAGE_1_RESOLVED =
          new JSONObject()
          .put("count", 39)
          .put("next", "https://swapi.py4e.com/api/vehicles/?page=2")
          .put("previous", JSONObject.NULL)
          .put("results", new JSONArray().put(new JSONObject(VEHICLES_1_RESOLVED)))
          .toString();

  public static final String VEHICLES_PAGE_2_RESOLVED =
      new JSONObject()
          .put("count", 39)
          .put("next", "https://swapi.py4e.com/api/vehicles/?page=2")
          .put("previous", JSONObject.NULL)
          .put("results", new JSONArray().put(new JSONObject(VEHICLES_2_RESOLVED)))
          .toString();

    public static final String VEHICLES_ALL =
        new JSONArray(
            List.of(
            new JSONObject(VEHICLES_1),
            new JSONObject(VEHICLES_2)
        )).toString();

  public static final String VEHICLES_ALL_RESOLVED =
      new JSONArray(
          List.of(
              new JSONObject(VEHICLES_1_RESOLVED),
              new JSONObject(VEHICLES_2_RESOLVED)
          )).toString();

}
