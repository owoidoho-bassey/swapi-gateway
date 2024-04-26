package com.owoidoho.bassey.swapigateway.core.services;

import static com.owoidoho.bassey.swapigateway.TestData.FILMS_1;
import static com.owoidoho.bassey.swapigateway.TestData.FILMS_2;
import static com.owoidoho.bassey.swapigateway.TestData.FILMS_PAGE_1;
import static com.owoidoho.bassey.swapigateway.TestData.FILMS_PAGE_2;
import static com.owoidoho.bassey.swapigateway.TestData.PEOPLE_1;
import static com.owoidoho.bassey.swapigateway.TestData.PEOPLE_1_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.PEOPLE_2;
import static com.owoidoho.bassey.swapigateway.TestData.PEOPLE_PAGE_1;
import static com.owoidoho.bassey.swapigateway.TestData.PEOPLE_PAGE_2;
import static com.owoidoho.bassey.swapigateway.TestData.PLANETS_1;
import static com.owoidoho.bassey.swapigateway.TestData.PLANETS_2;
import static com.owoidoho.bassey.swapigateway.TestData.PLANETS_PAGE_1;
import static com.owoidoho.bassey.swapigateway.TestData.PLANETS_PAGE_2;
import static com.owoidoho.bassey.swapigateway.TestData.ROOTS;
import static com.owoidoho.bassey.swapigateway.TestData.SPECIES_1;
import static com.owoidoho.bassey.swapigateway.TestData.SPECIES_2;
import static com.owoidoho.bassey.swapigateway.TestData.SPECIES_PAGE_1;
import static com.owoidoho.bassey.swapigateway.TestData.SPECIES_PAGE_2;
import static com.owoidoho.bassey.swapigateway.TestData.STARSHIPS_1;
import static com.owoidoho.bassey.swapigateway.TestData.STARSHIPS_2;
import static com.owoidoho.bassey.swapigateway.TestData.STARSHIPS_PAGE_1;
import static com.owoidoho.bassey.swapigateway.TestData.STARSHIPS_PAGE_2;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_1;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_2;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_ALL;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_ALL_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_PAGE_1;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_PAGE_1_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_PAGE_2;
import static com.owoidoho.bassey.swapigateway.TestData.unPretty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.owoidoho.bassey.swapigateway.core.domain.resources.Films;
import com.owoidoho.bassey.swapigateway.core.domain.resources.People;
import com.owoidoho.bassey.swapigateway.core.domain.resources.Planets;
import com.owoidoho.bassey.swapigateway.core.domain.resources.Resource;
import com.owoidoho.bassey.swapigateway.core.domain.resources.Root;
import com.owoidoho.bassey.swapigateway.core.domain.resources.Species;
import com.owoidoho.bassey.swapigateway.core.domain.resources.Starships;
import com.owoidoho.bassey.swapigateway.core.domain.resources.Vehicles;
import com.owoidoho.bassey.swapigateway.core.repository.ResourceNotFoundException;
import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import java.util.stream.Stream;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SWAPIServiceTest {

  private final SWAPIRepository repository = mock(SWAPIRepository.class);

  private final SWAPIService swapiService =
      new SWAPIService(repository);

  public static Stream<Arguments> testDataProvider() {
    SWAPIRepository repository = mock(SWAPIRepository.class);
    return Stream.of(
        Arguments.of(
            "people",
            "1",
            new People("1", repository)
        ),
        Arguments.of(
            "planets",
            "1",
            new Planets("1", repository)
        ),
        Arguments.of(
            "films",
            "1",
            new Films("1", repository)
        ),
        Arguments.of(
            "species",
            "1",
            new Species("1", repository)
        ),
        Arguments.of(
            "vehicles",
            "schema",
            new Vehicles("schema", repository)
        ),
        Arguments.of(
            "starships",
            "1",
            new Starships("1", repository)
        ),
        Arguments.of(
            "",
            "",
            new Root(repository)
        ),
        Arguments.of(
            "people",
            "",
            new People("", repository)
        ),
        Arguments.of(
            "planets",
            "",
            new Planets("", repository)
        ),
        Arguments.of(
            "films",
            "",
            new Films("", repository)
        )
    );
  }

  @BeforeEach
  void setup() {
    when(repository.fetchResource("", ""))
        .thenReturn(new JSONObject(ROOTS));
    when(repository.fetchResource("people", "1"))
        .thenReturn(new JSONObject(PEOPLE_1));
    when(repository.fetchResource("people", "2"))
        .thenReturn(new JSONObject(PEOPLE_2));
    when(repository.fetchResource("films", "1"))
        .thenReturn(new JSONObject(FILMS_1));
    when(repository.fetchResource("films", "2"))
        .thenReturn(new JSONObject(FILMS_2));
    when(repository.fetchResource("planets", "1"))
        .thenReturn(new JSONObject(PLANETS_1));
    when(repository.fetchResource("planets", "2"))
        .thenReturn(new JSONObject(PLANETS_2));
    when(repository.fetchResource("species", "1"))
        .thenReturn(new JSONObject(SPECIES_1));
    when(repository.fetchResource("species", "2"))
        .thenReturn(new JSONObject(SPECIES_2));
    when(repository.fetchResource("vehicles", "1"))
        .thenReturn(new JSONObject(VEHICLES_1));
    when(repository.fetchResource("vehicles", "2"))
        .thenReturn(new JSONObject(VEHICLES_2));
    when(repository.fetchResource("starships", "1"))
        .thenReturn(new JSONObject(STARSHIPS_1));
    when(repository.fetchResource("starships", "2"))
        .thenReturn(new JSONObject(STARSHIPS_2));
    when(repository.fetchResource("", ""))
        .thenReturn(new JSONObject(ROOTS));
    when(repository.fetchResource("people", "?page=1"))
        .thenReturn(new JSONObject(PEOPLE_PAGE_1));
    when(repository.fetchResource("people", "?page=2"))
        .thenReturn(new JSONObject(PEOPLE_PAGE_2));
    when(repository.fetchResource("films", "?page=1"))
        .thenReturn(new JSONObject(FILMS_PAGE_1));
    when(repository.fetchResource("films", "?page=2"))
        .thenReturn(new JSONObject(FILMS_PAGE_2));
    when(repository.fetchResource("planets", "?page=1"))
        .thenReturn(new JSONObject(PLANETS_PAGE_1));
    when(repository.fetchResource("planets", "?page=2"))
        .thenReturn(new JSONObject(PLANETS_PAGE_2));
    when(repository.fetchResource("species", "?page=1"))
        .thenReturn(new JSONObject(SPECIES_PAGE_1));
    when(repository.fetchResource("species", "?page=2"))
        .thenReturn(new JSONObject(SPECIES_PAGE_2));
    when(repository.fetchResource("vehicles", "?page=1"))
        .thenReturn(new JSONObject(VEHICLES_PAGE_1));
    when(repository.fetchResource("vehicles", "?page=2"))
        .thenReturn(new JSONObject(VEHICLES_PAGE_2));
    when(repository.fetchResource("starships", "?page=1"))
        .thenReturn(new JSONObject(STARSHIPS_PAGE_1));
    when(repository.fetchResource("starships", "?page=2"))
        .thenReturn(new JSONObject(STARSHIPS_PAGE_2));
  }

  @Test
  public void getRootResourceWorks() {
    JSONObject actualResponse =
        swapiService.getResource("", "");

    assertEquals(unPretty(ROOTS), actualResponse.toString());
  }

  @Test
  public void getResourceWorks() {
    JSONObject actualResponse =
        swapiService.getResource("people", "1");

    assertEquals(unPretty(PEOPLE_1), actualResponse.toString());
  }

  @Test
  public void getResolvedResourceWorks() {
    JSONObject actualResponse =
        swapiService.getResolvedResource("people", "1");

    assertEquals(unPretty(PEOPLE_1_RESOLVED), actualResponse.toString());
  }

  @Test
  public void getPagedResourceWorks() {
    JSONObject actualResponse =
        swapiService.getPagedResources("vehicles", 1);

    assertEquals(unPretty(VEHICLES_PAGE_1), actualResponse.toString());
  }

  @Test
  public void getPagedResolvedResourceWorks() {
    JSONObject actualResponse =
        swapiService.getPagedResolvedResources("vehicles", 1);

    assertEquals(unPretty(VEHICLES_PAGE_1_RESOLVED), actualResponse.toString());
  }

  @Test
  public void getAllResourcesWorks() {
    JSONArray actualResponse =
        swapiService.getAllResources("vehicles");

    assertEquals(unPretty(VEHICLES_ALL), actualResponse.toString());
  }

  @Test
  public void getAllResourcesResolvedWorks() {
    JSONArray actualResponse =
        swapiService.getAllResolvedResources("vehicles");

    assertEquals(unPretty(VEHICLES_ALL_RESOLVED), actualResponse.toString());
  }

  @ParameterizedTest
  @MethodSource("testDataProvider")
  void testGetResourceType(
      String resource,
      String path,
      Resource expected
  ) {
    assertThat(swapiService.getResourceType(resource, path), samePropertyValuesAs(expected));
  }

  @Test
  void testGetResourceTypeWithInvalidResource() {
    assertThrowsExactly(ResourceNotFoundException.class,
        () -> swapiService.getResourceType("invalid", "1")
    );
  }

}