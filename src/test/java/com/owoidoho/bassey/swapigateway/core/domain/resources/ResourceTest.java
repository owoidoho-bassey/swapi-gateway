package com.owoidoho.bassey.swapigateway.core.domain.resources;

import static com.owoidoho.bassey.swapigateway.TestData.FILMS_1;
import static com.owoidoho.bassey.swapigateway.TestData.FILMS_1_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.FILMS_2;
import static com.owoidoho.bassey.swapigateway.TestData.FILMS_2_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.FILMS_ALL;
import static com.owoidoho.bassey.swapigateway.TestData.FILMS_ALL_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.FILMS_PAGE_1;
import static com.owoidoho.bassey.swapigateway.TestData.FILMS_PAGE_1_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.FILMS_PAGE_2;
import static com.owoidoho.bassey.swapigateway.TestData.FILMS_PAGE_2_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.PEOPLE_1;
import static com.owoidoho.bassey.swapigateway.TestData.PEOPLE_1_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.PEOPLE_2;
import static com.owoidoho.bassey.swapigateway.TestData.PEOPLE_2_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.PEOPLE_ALL;
import static com.owoidoho.bassey.swapigateway.TestData.PEOPLE_ALL_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.PEOPLE_PAGE_1;
import static com.owoidoho.bassey.swapigateway.TestData.PEOPLE_PAGE_1_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.PEOPLE_PAGE_2;
import static com.owoidoho.bassey.swapigateway.TestData.PEOPLE_PAGE_2_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.PLANETS_1;
import static com.owoidoho.bassey.swapigateway.TestData.PLANETS_1_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.PLANETS_2;
import static com.owoidoho.bassey.swapigateway.TestData.PLANETS_2_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.PLANETS_ALL;
import static com.owoidoho.bassey.swapigateway.TestData.PLANETS_ALL_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.PLANETS_PAGE_1;
import static com.owoidoho.bassey.swapigateway.TestData.PLANETS_PAGE_1_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.PLANETS_PAGE_2;
import static com.owoidoho.bassey.swapigateway.TestData.PLANETS_PAGE_2_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.ROOTS;
import static com.owoidoho.bassey.swapigateway.TestData.SPECIES_1;
import static com.owoidoho.bassey.swapigateway.TestData.SPECIES_1_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.SPECIES_2;
import static com.owoidoho.bassey.swapigateway.TestData.SPECIES_2_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.SPECIES_ALL;
import static com.owoidoho.bassey.swapigateway.TestData.SPECIES_ALL_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.SPECIES_PAGE_1;
import static com.owoidoho.bassey.swapigateway.TestData.SPECIES_PAGE_1_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.SPECIES_PAGE_2;
import static com.owoidoho.bassey.swapigateway.TestData.SPECIES_PAGE_2_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.STARSHIPS_1;
import static com.owoidoho.bassey.swapigateway.TestData.STARSHIPS_1_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.STARSHIPS_2;
import static com.owoidoho.bassey.swapigateway.TestData.STARSHIPS_2_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.STARSHIPS_ALL;
import static com.owoidoho.bassey.swapigateway.TestData.STARSHIPS_ALL_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.STARSHIPS_PAGE_1;
import static com.owoidoho.bassey.swapigateway.TestData.STARSHIPS_PAGE_1_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.STARSHIPS_PAGE_2;
import static com.owoidoho.bassey.swapigateway.TestData.STARSHIPS_PAGE_2_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_1;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_1_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_2;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_2_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_ALL;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_ALL_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_PAGE_1;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_PAGE_1_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_PAGE_2;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_PAGE_2_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.unPretty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import java.util.stream.Stream;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ResourceTest {

  private static Stream<Arguments> testFetchDataProvider() {
    SWAPIRepository repository = mock(SWAPIRepository.class);
    mockRepository(repository);
    return Stream.of(
        Arguments.of(
            new Root(repository),
            ROOTS
        ),
        Arguments.of(
            new People("1", repository),
            PEOPLE_1
        ),
        Arguments.of(
            new People("2", repository),
            PEOPLE_2
        ),
        Arguments.of(
            new Films("1", repository),
            FILMS_1
        ),
        Arguments.of(
            new Films("2", repository),
            FILMS_2
        ),
        Arguments.of(
            new Planets("1", repository),
            PLANETS_1
        ),
        Arguments.of(
            new Planets("2", repository),
            PLANETS_2
        ),
        Arguments.of(
            new Species("1", repository),
            SPECIES_1
        ),
        Arguments.of(
            new Species("2", repository),
            SPECIES_2
        ),
        Arguments.of(
            new Vehicles("1", repository),
            VEHICLES_1
        ),
        Arguments.of(
            new Vehicles("2", repository),
            VEHICLES_2
        ),
        Arguments.of(
            new Starships("1", repository),
            STARSHIPS_1
        ),
        Arguments.of(
            new Starships("2", repository),
            STARSHIPS_2
        )
    );
  }

  private static Stream<Arguments> testFetchDataAndResolveProvider() {
    SWAPIRepository repository = mock(SWAPIRepository.class);
    mockRepository(repository);
    return Stream.of(
        Arguments.of(
            new People("1", repository),
            PEOPLE_1_RESOLVED
        ),
        Arguments.of(
            new People("2", repository),
            PEOPLE_2_RESOLVED
        ),
        Arguments.of(
            new Films("1", repository),
            FILMS_1_RESOLVED
        ),
        Arguments.of(
            new Films("2", repository),
            FILMS_2_RESOLVED
        ),
        Arguments.of(
            new Planets("1", repository),
            PLANETS_1_RESOLVED
        ),
        Arguments.of(
            new Planets("2", repository),
            PLANETS_2_RESOLVED
        ),
        Arguments.of(
            new Species("1", repository),
            SPECIES_1_RESOLVED
        ),
        Arguments.of(
            new Species("2", repository),
            SPECIES_2_RESOLVED
        ),
        Arguments.of(
            new Vehicles("1", repository),
            VEHICLES_1_RESOLVED
        ),
        Arguments.of(
            new Vehicles("2", repository),
            VEHICLES_2_RESOLVED
        ),
        Arguments.of(
            new Starships("1", repository),
            STARSHIPS_1_RESOLVED
        ),
        Arguments.of(
            new Starships("2", repository),
            STARSHIPS_2_RESOLVED
        )
    );
  }

  private static Stream<Arguments> testFetchPagedProvider() {
    SWAPIRepository repository = mock(SWAPIRepository.class);
    mockRepository(repository);
    return Stream.of(
        Arguments.of(
            new People("", repository),
            PEOPLE_PAGE_1,
            PEOPLE_PAGE_2
        ),
        Arguments.of(
            new Films("", repository),
            FILMS_PAGE_1,
            FILMS_PAGE_2
        ),
        Arguments.of(
            new Planets("", repository),
            PLANETS_PAGE_1,
            PLANETS_PAGE_2
        ),
        Arguments.of(
            new Species("", repository),
            SPECIES_PAGE_1,
            SPECIES_PAGE_2
        ),
        Arguments.of(
            new Vehicles("", repository),
            VEHICLES_PAGE_1,
            VEHICLES_PAGE_2
        ),
        Arguments.of(
            new Starships("", repository),
            STARSHIPS_PAGE_1,
            STARSHIPS_PAGE_2
        )
    );
  }

  private static Stream<Arguments> testFetchPagedAndResolveResourcesProvider() {
    SWAPIRepository repository = mock(SWAPIRepository.class);
    mockRepository(repository);
    return Stream.of(
        Arguments.of(
            new People("", repository),
            PEOPLE_PAGE_1_RESOLVED,
            PEOPLE_PAGE_2_RESOLVED
        ),
        Arguments.of(
            new Films("", repository),
            FILMS_PAGE_1_RESOLVED,
            FILMS_PAGE_2_RESOLVED
        ),
        Arguments.of(
            new Planets("", repository),
            PLANETS_PAGE_1_RESOLVED,
            PLANETS_PAGE_2_RESOLVED
        ),
        Arguments.of(
            new Species("", repository),
            SPECIES_PAGE_1_RESOLVED,
            SPECIES_PAGE_2_RESOLVED
        ),
        Arguments.of(
            new Vehicles("", repository),
            VEHICLES_PAGE_1_RESOLVED,
            VEHICLES_PAGE_2_RESOLVED
        ),
        Arguments.of(
            new Starships("", repository),
            STARSHIPS_PAGE_1_RESOLVED,
            STARSHIPS_PAGE_2_RESOLVED
        )
    );
  }

  private static Stream<Arguments> testFetchAllProvider() {
    SWAPIRepository repository = mock(SWAPIRepository.class);
    mockRepository(repository);
    return Stream.of(
        Arguments.of(
            new People("", repository),
            PEOPLE_ALL
        ),
        Arguments.of(
            new Films("", repository),
            FILMS_ALL
        ),
        Arguments.of(
            new Planets("", repository),
            PLANETS_ALL
        ),
        Arguments.of(
            new Species("", repository),
            SPECIES_ALL
        ),
        Arguments.of(
            new Vehicles("", repository),
            VEHICLES_ALL
        ),
        Arguments.of(
            new Starships("", repository),
            STARSHIPS_ALL
        )
    );
  }

  private static Stream<Arguments> testFetchAllAndResolveResourcesProvider() {
    SWAPIRepository repository = mock(SWAPIRepository.class);
    mockRepository(repository);
    return Stream.of(
        Arguments.of(
            new People("", repository),
            PEOPLE_ALL_RESOLVED
        ),
        Arguments.of(
            new Films("", repository),
            FILMS_ALL_RESOLVED
        ),
        Arguments.of(
            new Planets("", repository),
            PLANETS_ALL_RESOLVED
        ),
        Arguments.of(
            new Species("", repository),
            SPECIES_ALL_RESOLVED
        ),
        Arguments.of(
            new Vehicles("", repository),
            VEHICLES_ALL_RESOLVED
        ),
        Arguments.of(
            new Starships("", repository),
            STARSHIPS_ALL_RESOLVED
        )
    );
  }

  private static void mockRepository(SWAPIRepository repository) {
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

  @ParameterizedTest
  @MethodSource("testFetchDataProvider")
  public void fetchWorks(
      Resource resource,
      String expectedResponse
  ) {
    JSONObject actualResponse = resource.fetch();
    assertEquals(unPretty(expectedResponse), actualResponse.toString());
  }

  @ParameterizedTest
  @MethodSource("testFetchDataAndResolveProvider")
  public void fetchAndResolvedWorks(
      Resource resource,
      String expectedResponse
  ) {
    JSONObject actualResponse = resource.fetchAndResolveResources();
    assertEquals(unPretty(expectedResponse), actualResponse.toString());
  }

  @ParameterizedTest
  @MethodSource("testFetchPagedProvider")
  public void fetchPagedWorks(
      Resource resource,
      String expectedPage1Response,
      String expectedPage2Response
  ) {
    JSONObject actualResponse = resource.fetchPaged(1);
    assertEquals(unPretty(expectedPage1Response), actualResponse.toString());

    actualResponse = resource.fetchPaged(2);
    assertEquals(unPretty(expectedPage2Response), actualResponse.toString());
  }

  @ParameterizedTest
  @MethodSource("testFetchPagedAndResolveResourcesProvider")
  public void fetchPagedAndResolveResourcesWorks(
      Resource resource,
      String expectedPage1Response,
      String expectedPage2Response
  ) {
    JSONObject actualResponse = resource.fetchPagedAndResolveResources(1);
    assertEquals(unPretty(expectedPage1Response), actualResponse.toString());

    actualResponse = resource.fetchPagedAndResolveResources(2);
    assertEquals(unPretty(expectedPage2Response), actualResponse.toString());
  }

  @ParameterizedTest
  @MethodSource("testFetchAllProvider")
  public void fetchAllWorks(
      Resource resource,
      String expectedResponse
  ) {
    JSONArray actualResponse = resource.fetchAll();
    assertEquals(unPretty(expectedResponse), actualResponse.toString());
  }

  @ParameterizedTest
  @MethodSource("testFetchAllAndResolveResourcesProvider")
  public void fetchAllAndResolveResourcesWorks(
      Resource resource,
      String expectedResponse
  ) {
    JSONArray actualResponse = resource.fetchAllAndResolveResources();
    assertEquals(unPretty(expectedResponse), actualResponse.toString());
  }

  @Test
  public void invalidResourceUrlThrowsException() {
    SWAPIRepository repository = mock(SWAPIRepository.class);
    mockRepository(repository);
    when(repository.fetchResource("people", "1"))
        .thenReturn(
            new JSONObject(PEOPLE_1)
                .put("homeworld", "http://swapi.dev/api/unknown/1/")
        );

    Resource resource = new People("1", repository);
    assertThrows(ResourceUnknownException.class, resource::fetchAndResolveResources);
  }

}
