package com.owoidoho.bassey.swapigateway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

public final class TestData {

  public static final String SWAPI_BASE_URL = "https://swapi.py4e.com/api";
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
              "https://swapi.py4e.com/api/films/1/"
          ],
          "species": [
              "https://swapi.py4e.com/api/species/1/"
          ],
          "vehicles": [
              "https://swapi.py4e.com/api/vehicles/1/"
          ],
          "starships": [
              "https://swapi.py4e.com/api/starships/1/"
          ],
          "created": "2014-12-09T13:50:51.644000Z",
          "edited": "2014-12-20T21:17:56.891000Z",
          "url": "https://swapi.py4e.com/api/people/1/"
      }
      """;

  public static final String PEOPLE_2 = """
      {
          "name": "C-3PO",
          "height": "167",
          "mass": "75",
          "hair_color": "n/a",
          "skin_color": "gold",
          "eye_color": "yellow",
          "birth_year": "112BBY",
          "gender": "n/a",
          "homeworld": "https://swapi.py4e.com/api/planets/2/",
          "films": [
              "https://swapi.py4e.com/api/films/2/"
          ],
          "species": [
              "https://swapi.py4e.com/api/species/2/"
          ],
          "vehicles": [
              "https://swapi.py4e.com/api/vehicles/2/"
          ],
          "starships": [
              "https://swapi.py4e.com/api/starships/2/"
          ],
          "created": "2014-12-10T15:10:51.357000Z",
          "edited": "2014-12-20T21:17:50.309000Z",
          "url": "https://swapi.py4e.com/api/people/2/"
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
              "https://swapi.py4e.com/api/people/1/"
          ],
          "planets": [
              "https://swapi.py4e.com/api/planets/1/"
          ],
          "starships": [
              "https://swapi.py4e.com/api/starships/1/"
          ],
          "vehicles": [
              "https://swapi.py4e.com/api/vehicles/1/"
          ],
          "species": [
              "https://swapi.py4e.com/api/species/1/"
          ],
          "created": "2014-12-10T14:23:31.880000Z",
          "edited": "2014-12-20T19:49:45.256000Z",
          "url": "https://swapi.py4e.com/api/films/1/"
      }
      """;

  public static final String FILMS_2 = """
      {
           "title": "The Empire Strikes Back",
           "episode_id": 5,
           "opening_crawl": "It is a dark time for the\\r\\nRebellion. Although the Death\\r\\nStar has been destroyed,\\r\\nImperial troops have driven the\\r\\nRebel forces from their hidden\\r\\nbase and pursued them across\\r\\nthe galaxy.\\r\\n\\r\\nEvading the dreaded Imperial\\r\\nStarfleet, a group of freedom\\r\\nfighters led by Luke Skywalker\\r\\nhas established a new secret\\r\\nbase on the remote ice world\\r\\nof Hoth.\\r\\n\\r\\nThe evil lord Darth Vader,\\r\\nobsessed with finding young\\r\\nSkywalker, has dispatched\\r\\nthousands of remote probes into\\r\\nthe far reaches of space....",
           "director": "Irvin Kershner",
           "producer": "Gary Kurtz, Rick McCallum",
           "release_date": "1980-05-17",
           "characters": [
               "https://swapi.py4e.com/api/people/2/"
           ],
           "planets": [
               "https://swapi.py4e.com/api/planets/2/"
           ],
           "starships": [
               "https://swapi.py4e.com/api/starships/2/"
           ],
           "vehicles": [
               "https://swapi.py4e.com/api/vehicles/2/"
           ],
           "species": [
               "https://swapi.py4e.com/api/species/2/"
           ],
           "created": "2014-12-12T11:26:24.656000Z",
           "edited": "2014-12-15T13:07:53.386000Z",
           "url": "https://swapi.py4e.com/api/films/2/"
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
              "https://swapi.py4e.com/api/people/1/"
          ],
          "films": [
              "https://swapi.py4e.com/api/films/1/"
          ],
          "created": "2014-12-09T13:50:49.641000Z",
          "edited": "2014-12-20T20:58:18.411000Z",
          "url": "https://swapi.py4e.com/api/planets/1/"
      }
      """;

  public static final String PLANETS_2 = """
      {
           "name": "Alderaan",
           "rotation_period": "24",
           "orbital_period": "364",
           "diameter": "12500",
           "climate": "temperate",
           "gravity": "1 standard",
           "terrain": "grasslands, mountains",
           "surface_water": "40",
           "population": "2000000000",
           "residents": [
               "https://swapi.py4e.com/api/people/2/"
           ],
           "films": [
               "https://swapi.py4e.com/api/films/2/"
           ],
           "created": "2014-12-10T11:35:48.479000Z",
           "edited": "2014-12-20T20:58:18.420000Z",
           "url": "https://swapi.py4e.com/api/planets/2/"
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
              "https://swapi.py4e.com/api/people/1/"
          ],
          "films": [
              "https://swapi.py4e.com/api/films/1/"
          ],
          "created": "2014-12-10T13:52:11.567000Z",
          "edited": "2014-12-20T21:36:42.136000Z",
          "url": "https://swapi.py4e.com/api/species/1/"
      }
      """;

  public static final String SPECIES_2 = """
      {
           "name": "Droid",
           "classification": "artificial",
           "designation": "sentient",
           "average_height": "n/a",
           "skin_colors": "n/a",
           "hair_colors": "n/a",
           "eye_colors": "n/a",
           "average_lifespan": "indefinite",
           "homeworld": "https://swapi.py4e.com/api/planets/2/",
           "language": "n/a",
           "people": [
               "https://swapi.py4e.com/api/people/2/"
           ],
           "films": [
               "https://swapi.py4e.com/api/films/2/"
           ],
           "created": "2014-12-10T15:16:16.259000Z",
           "edited": "2014-12-20T21:36:42.139000Z",
           "url": "https://swapi.py4e.com/api/species/2/"
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
              "https://swapi.py4e.com/api/films/1/"
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
              "https://swapi.py4e.com/api/people/2/"
          ],
          "films": [
              "https://swapi.py4e.com/api/films/2/"
          ],
          "created": "2014-12-10T16:01:52.434000Z",
          "edited": "2014-12-20T21:30:21.665000Z",
          "url": "https://swapi.py4e.com/api/vehicles/2/"
      }
      """;
  public static final String STARSHIPS_1 = """
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
          "pilots": [
              "https://swapi.py4e.com/api/people/1/"
          ],
          "films": [
              "https://swapi.py4e.com/api/films/1/",
          ],
          "created": "2014-12-10T14:20:33.369000Z",
          "edited": "2014-12-20T21:23:49.867000Z",
          "url": "https://swapi.py4e.com/api/starships/1/"
      }
      """;

  public static final String STARSHIPS_2 = """
      {
          "name": "Star Destroyer",
          "model": "Imperial I-class Star Destroyer",
          "manufacturer": "Kuat Drive Yards",
          "cost_in_credits": "150000000",
          "length": "1,600",
          "max_atmosphering_speed": "975",
          "crew": "47,060",
          "passengers": "n/a",
          "cargo_capacity": "36000000",
          "consumables": "2 years",
          "hyperdrive_rating": "2.0",
          "MGLT": "60",
          "starship_class": "Star Destroyer",
          "pilots": [],
          "films": [
              "https://swapi.py4e.com/api/films/2/"
          ],
          "created": "2014-12-10T15:08:19.848000Z",
          "edited": "2014-12-20T21:23:49.870000Z",
          "url": "https://swapi.py4e.com/api/starships/2/"
      }
      """;

  public static final String PEOPLE_1_RESOLVED = """
      {
          "gender": "male",
          "skin_color": "fair",
          "edited": "2014-12-20T21:17:56.891000Z",
          "created": "2014-12-09T13:50:51.644000Z",
          "mass": "77",
          "homeworld": {
              "name": "Tatooine",
              "id": "1"
          },
          "films": [
              {
                  "title": "A New Hope",
                  "id": "1"
              }
          ],
          "vehicles": [
              {
                  "name": "Sand Crawler",
                  "id": "1"
              }
          ],
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
          "url": "https://swapi.py4e.com/api/people/1/",
          "hair_color": "blond",
          "birth_year": "19BBY",
          "eye_color": "blue",
          "name": "Luke Skywalker",
          "height": "172"
      }
      """;

  public static final String PEOPLE_2_RESOLVED = """
      {
          "name": "C-3PO",
          "height": "167",
          "mass": "75",
          "hair_color": "n/a",
          "skin_color": "gold",
          "eye_color": "yellow",
          "birth_year": "112BBY",
          "gender": "n/a",
          "homeworld": {
              "name": "Alderaan",
              "id": "2"
          },
          "films": [
              {
                  "title": "The Empire Strikes Back",
                  "id": "2"
              }
          ],
          "species": [
              {
                  "name": "Droid",
                  "id": "2"
              }
          ],
          "vehicles": [
              {
                  "name": "T-16 skyhopper",
                  "id": "2"
              }
          ],
          "starships": [
              {
                  "name": "Star Destroyer",
                  "id": "2"
              }
          ],
          "created": "2014-12-10T15:10:51.357000Z",
          "edited": "2014-12-20T21:17:50.309000Z",
          "url": "https://swapi.py4e.com/api/people/2/"
      }
      """;

  public static final String FILMS_1_RESOLVED = """
      {
          "title": "A New Hope",
          "episode_id": 4,
          "opening_crawl": "It is a period of civil war.\\r\\nRebel spaceships, striking\\r\\nfrom a hidden base, have won\\r\\ntheir first victory against\\r\\nthe evil Galactic Empire.\\r\\n\\r\\nDuring the battle, Rebel\\r\\nspies managed to steal secret\\r\\nplans to the Empire's\\r\\nultimate weapon, the DEATH\\r\\nSTAR, an armored space\\r\\nstation with enough power\\r\\nto destroy an entire planet.\\r\\n\\r\\nPursued by the Empire's\\r\\nsinister agents, Princess\\r\\nLeia races home aboard her\\r\\nstarship, custodian of the\\r\\nstolen plans that can save her\\r\\npeople and restore\\r\\nfreedom to the galaxy....",
          "director": "George Lucas",
          "producer": "Gary Kurtz, Rick McCallum",
          "release_date": "1977-05-25",
          "characters": [
              {
                  "name": "Luke Skywalker",
                  "id": "1"
              }
          ],
          "planets": [
              {
                  "name": "Tatooine",
                  "id": "1"
              }
          ],
          "starships": [
              {
                  "name": "CR90 corvette",
                  "id": "1"
              }
          ],
          "vehicles": [
              {
                  "name": "Sand Crawler",
                  "id": "1"
              }
          ],
          "species": [
              {
                  "name": "Human",
                  "id": "1"
              }
          ],
          "created": "2014-12-10T14:23:31.880000Z",
          "edited": "2014-12-20T19:49:45.256000Z",
          "url": "https://swapi.py4e.com/api/films/1/"
      }
       """;

  public static final String FILMS_2_RESOLVED = """
          {
             "title": "The Empire Strikes Back",
             "episode_id": 5,
             "opening_crawl": "It is a dark time for the\\r\\nRebellion. Although the Death\\r\\nStar has been destroyed,\\r\\nImperial troops have driven the\\r\\nRebel forces from their hidden\\r\\nbase and pursued them across\\r\\nthe galaxy.\\r\\n\\r\\nEvading the dreaded Imperial\\r\\nStarfleet, a group of freedom\\r\\nfighters led by Luke Skywalker\\r\\nhas established a new secret\\r\\nbase on the remote ice world\\r\\nof Hoth.\\r\\n\\r\\nThe evil lord Darth Vader,\\r\\nobsessed with finding young\\r\\nSkywalker, has dispatched\\r\\nthousands of remote probes into\\r\\nthe far reaches of space....",
             "director": "Irvin Kershner",
             "producer": "Gary Kurtz, Rick McCallum",
             "release_date": "1980-05-17",
             "characters": [
                 {
                    "name": "C-3PO",
                    "id": "2"
                }
             ],
             "planets": [
                 {
                    "name": "Alderaan",
                    "id": "2"
                }
             ],
             "starships": [
                 {
                    "name": "Star Destroyer",
                    "id": "2"
                 }
             ],
             "vehicles": [
                 {
                    "name": "T-16 skyhopper",
                    "id": "2"
                 }
             ],
             "species": [
                  {
                      "name": "Droid",
                      "id": "2"
                  }
             ],
             "created": "2014-12-12T11:26:24.656000Z",
             "edited": "2014-12-15T13:07:53.386000Z",
             "url": "https://swapi.py4e.com/api/films/2/"
         }
      """;

  public static final String PLANETS_1_RESOLVED = new JSONObject(PLANETS_1) {{
    getJSONArray("residents").put(0, new JSONObject(PEOPLE_1_RESOLVED));
    getJSONArray("films").put(0, new JSONObject(FILMS_1_RESOLVED));
  }}.toString();

  public static final String PLANETS_2_RESOLVED = new JSONObject(PLANETS_2) {{
    getJSONArray("residents").put(0, new JSONObject(PEOPLE_2_RESOLVED));
    getJSONArray("films").put(0, new JSONObject(FILMS_2_RESOLVED));
  }}.toString();

  public static final String PLANETS_ALL_RESOLVED =
      new JSONArray(
          List.of(
              new JSONObject(PLANETS_1_RESOLVED),
              new JSONObject(PLANETS_2_RESOLVED)
          )).toString();

  public static final String SPECIES_1_RESOLVED = new JSONObject(SPECIES_1) {{
    put("homeworld", new JSONObject()
        .put("name", "Tatooine")
        .put("id", "1")
    );
    getJSONArray("people").put(0, new JSONObject(PEOPLE_1_RESOLVED));
    getJSONArray("films").put(0, new JSONObject(FILMS_1_RESOLVED));
  }}.toString();

  public static final String SPECIES_2_RESOLVED = new JSONObject(SPECIES_2) {{
    put("homeworld", new JSONObject()
        .put("name", "Alderaan")
        .put("id", "2")
    );
    getJSONArray("people").put(0, new JSONObject(PEOPLE_2_RESOLVED));
    getJSONArray("films").put(0, new JSONObject(FILMS_2_RESOLVED));
  }}.toString();
  public static final String SPECIES_ALL_RESOLVED =
      new JSONArray(
          List.of(
              new JSONObject(SPECIES_1_RESOLVED),
              new JSONObject(SPECIES_2_RESOLVED)
          )).toString();
  public static final String VEHICLES_1_RESOLVED = new JSONObject(VEHICLES_1) {{
    getJSONArray("pilots").put(0, new JSONObject(PEOPLE_1_RESOLVED));
    getJSONArray("films").put(0, new JSONObject(FILMS_1_RESOLVED));
  }}.toString();
  public static final String VEHICLES_2_RESOLVED = new JSONObject(VEHICLES_2) {{
    getJSONArray("pilots").put(0, new JSONObject(PEOPLE_2_RESOLVED));
    getJSONArray("films").put(0, new JSONObject(FILMS_2_RESOLVED));
  }}.toString();
  public static final String VEHICLES_ALL_RESOLVED =
      new JSONArray(
          List.of(
              new JSONObject(VEHICLES_1_RESOLVED),
              new JSONObject(VEHICLES_2_RESOLVED)
          )).toString();
  public static final String STARSHIPS_1_RESOLVED = new JSONObject(STARSHIPS_1) {{
    getJSONArray("pilots").put(0, new JSONObject(PEOPLE_1_RESOLVED));
    getJSONArray("films").put(0, new JSONObject(FILMS_1_RESOLVED));
  }}.toString();
  public static final String STARSHIPS_2_RESOLVED = new JSONObject(STARSHIPS_2) {{
    getJSONArray("films").put(0, new JSONObject(FILMS_2_RESOLVED));
  }}.toString();
  public static final String STARSHIPS_ALL_RESOLVED =
      new JSONArray(
          List.of(
              new JSONObject(STARSHIPS_1_RESOLVED),
              new JSONObject(STARSHIPS_2_RESOLVED)
          )).toString();
  public static final String PEOPLE_ALL =
      new JSONArray(
          List.of(
              new JSONObject(PEOPLE_1),
              new JSONObject(PEOPLE_2)
          )).toString();
  public static final String PEOPLE_ALL_RESOLVED =
      new JSONArray(
          List.of(
              new JSONObject(PEOPLE_1_RESOLVED),
              new JSONObject(PEOPLE_2_RESOLVED)
          )).toString();
  public static final String FILMS_ALL =
      new JSONArray(
          List.of(
              new JSONObject(FILMS_1),
              new JSONObject(FILMS_2)
          )).toString();
  public static final String FILMS_ALL_RESOLVED =
      new JSONArray(
          List.of(
              new JSONObject(FILMS_1_RESOLVED),
              new JSONObject(FILMS_2_RESOLVED)
          )).toString();
  public static final String PLANETS_ALL =
      new JSONArray(
          List.of(
              new JSONObject(PLANETS_1),
              new JSONObject(PLANETS_2)
          )).toString();
  public static final String SPECIES_ALL =
      new JSONArray(
          List.of(
              new JSONObject(SPECIES_1),
              new JSONObject(SPECIES_2)
          )).toString();
  public static final String VEHICLES_ALL =
      new JSONArray(
          List.of(
              new JSONObject(VEHICLES_1),
              new JSONObject(VEHICLES_2)
          )).toString();
  public static final String STARSHIPS_ALL =
      new JSONArray(
          List.of(
              new JSONObject(STARSHIPS_1),
              new JSONObject(STARSHIPS_2)
          )).toString();
  public static final Map<String, String> RESOURCE_DATA_MAP = new HashMap<>() {{
    put("", ROOTS);
    put("people", PEOPLE_1);
    put("films", FILMS_1);
    put("planets", PLANETS_1);
    put("species", SPECIES_1);
    put("vehicles", VEHICLES_1);
    put("starships", STARSHIPS_1);
  }};
  private static final String PAGE_CONTENTS = """
      {
        "count": 39,
        "next": null,
        "previous": null,
        "results": []
      }
      """;
  public static final String PEOPLE_PAGE_1 = new JSONObject(PAGE_CONTENTS) {{
    put("next", "https://swapi.py4e.com/api/people/?page=2");
    getJSONArray("results").put(new JSONObject(PEOPLE_1));
  }}.toString();
  public static final String PEOPLE_PAGE_1_RESOLVED = new JSONObject(PEOPLE_PAGE_1) {{
    getJSONArray("results").put(0, new JSONObject(PEOPLE_1_RESOLVED));
  }}.toString();
  public static final String PEOPLE_PAGE_2 = new JSONObject(PAGE_CONTENTS) {{
    put("previous", "https://swapi.py4e.com/api/people/?page=1");
    getJSONArray("results").put(new JSONObject(PEOPLE_2));
  }}.toString();
  public static final String PEOPLE_PAGE_2_RESOLVED = new JSONObject(PEOPLE_PAGE_2) {{
    getJSONArray("results").put(0, new JSONObject(PEOPLE_2_RESOLVED));
  }}.toString();
  public static final String FILMS_PAGE_1 = new JSONObject(PAGE_CONTENTS) {{
    put("next", "https://swapi.py4e.com/api/films/?page=2");
    getJSONArray("results").put(new JSONObject(FILMS_1));
  }}.toString();
  public static final String FILMS_PAGE_1_RESOLVED = new JSONObject(FILMS_PAGE_1) {{
    getJSONArray("results").put(0, new JSONObject(FILMS_1_RESOLVED));
  }}.toString();
  public static final String FILMS_PAGE_2 = new JSONObject(PAGE_CONTENTS) {{
    put("previous", "https://swapi.py4e.com/api/films/?page=1");
    getJSONArray("results").put(new JSONObject(FILMS_2));
  }}.toString();
  public static final String FILMS_PAGE_2_RESOLVED = new JSONObject(FILMS_PAGE_2) {{
    getJSONArray("results").put(0, new JSONObject(FILMS_2_RESOLVED));
  }}.toString();
  public static final String PLANETS_PAGE_1 = new JSONObject(PAGE_CONTENTS) {{
    put("next", "https://swapi.py4e.com/api/planets/?page=2");
    getJSONArray("results").put(new JSONObject(PLANETS_1));
  }}.toString();
  public static final String PLANETS_PAGE_1_RESOLVED = new JSONObject(PLANETS_PAGE_1) {{
    getJSONArray("results").put(0, new JSONObject(PLANETS_1_RESOLVED));
  }}.toString();
  public static final String PLANETS_PAGE_2 = new JSONObject(PAGE_CONTENTS) {{
    put("previous", "https://swapi.py4e.com/api/planets/?page=1");
    getJSONArray("results").put(new JSONObject(PLANETS_2));
  }}.toString();
  public static final String PLANETS_PAGE_2_RESOLVED = new JSONObject(PLANETS_PAGE_2) {{
    getJSONArray("results").put(0, new JSONObject(PLANETS_2_RESOLVED));
  }}.toString();
  public static final String SPECIES_PAGE_1 = new JSONObject(PAGE_CONTENTS) {{
    put("next", "https://swapi.py4e.com/api/species/?page=2");
    getJSONArray("results").put(new JSONObject(SPECIES_1));
  }}.toString();
  public static final String SPECIES_PAGE_1_RESOLVED = new JSONObject(SPECIES_PAGE_1) {{
    getJSONArray("results").put(0, new JSONObject(SPECIES_1_RESOLVED));
  }}.toString();
  public static final String SPECIES_PAGE_2 = new JSONObject(PAGE_CONTENTS) {{
    put("previous", "https://swapi.py4e.com/api/species/?page=1");
    getJSONArray("results").put(new JSONObject(SPECIES_2));
  }}.toString();
  public static final String SPECIES_PAGE_2_RESOLVED = new JSONObject(SPECIES_PAGE_2) {{
    getJSONArray("results").put(0, new JSONObject(SPECIES_2_RESOLVED));
  }}.toString();
  public static final String VEHICLES_PAGE_1 = new JSONObject(PAGE_CONTENTS) {{
    put("next", "https://swapi.py4e.com/api/vehicles/?page=2");
    getJSONArray("results").put(new JSONObject(VEHICLES_1));
  }}.toString();
  public static final String VEHICLES_PAGE_1_RESOLVED = new JSONObject(VEHICLES_PAGE_1) {{
    getJSONArray("results").put(0, new JSONObject(VEHICLES_1_RESOLVED));
  }}.toString();
  public static final String VEHICLES_PAGE_2 = new JSONObject(PAGE_CONTENTS) {{
    put("previous", "https://swapi.py4e.com/api/vehicles/?page=1");
    getJSONArray("results").put(new JSONObject(VEHICLES_2));
  }}.toString();
  public static final String VEHICLES_PAGE_2_RESOLVED = new JSONObject(VEHICLES_PAGE_2) {{
    getJSONArray("results").put(0, new JSONObject(VEHICLES_2_RESOLVED));
  }}.toString();
  public static final String STARSHIPS_PAGE_1 = new JSONObject(PAGE_CONTENTS) {{
    put("next", "https://swapi.py4e.com/api/starships/?page=2");
    getJSONArray("results").put(new JSONObject(STARSHIPS_1));
  }}.toString();
  public static final String STARSHIPS_PAGE_1_RESOLVED = new JSONObject(STARSHIPS_PAGE_1) {{
    getJSONArray("results").put(0, new JSONObject(STARSHIPS_1_RESOLVED));
  }}.toString();
  public static final String STARSHIPS_PAGE_2 = new JSONObject(PAGE_CONTENTS) {{
    put("previous", "https://swapi.py4e.com/api/starships/?page=1");
    getJSONArray("results").put(new JSONObject(STARSHIPS_2));
  }}.toString();
  public static final String STARSHIPS_PAGE_2_RESOLVED = new JSONObject(STARSHIPS_PAGE_2) {{
    getJSONArray("results").put(0, new JSONObject(STARSHIPS_2_RESOLVED));
  }}.toString();

  private TestData() {
  }

  public static String unPretty(@NotNull String json) {
    return json.trim().charAt(0) == '{' ?
        new JSONObject(json).toString() :
        new JSONArray(json).toString();
  }

}
