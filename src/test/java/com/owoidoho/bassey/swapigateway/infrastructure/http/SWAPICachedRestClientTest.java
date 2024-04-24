package com.owoidoho.bassey.swapigateway.infrastructure.http;

import static com.owoidoho.bassey.swapigateway.TestData.RESOURCE_DATA_MAP;
import static com.owoidoho.bassey.swapigateway.TestData.SWAPI_BASE_URL;
import static com.owoidoho.bassey.swapigateway.TestData.unPretty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.ExpectedCount.never;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

public class SWAPICachedRestClientTest {

  private final RestClient.Builder restClientBuilder = RestClient.builder();
  private final MockRestServiceServer server = MockRestServiceServer.bindTo(restClientBuilder)
      .build();
  private final RestClient restClient = restClientBuilder.build();
  private final SWAPICachedRestClient subject = new SWAPICachedRestClient(SWAPI_BASE_URL,
      restClient);

  @BeforeEach
  void setup() {
    server.reset();
  }

  @ParameterizedTest
  @ValueSource(strings = {"planets", "people", "films", "species", "vehicles", "starships"})
  void fetchResourceCallsSwapiTheFirstTime(String resource) {

    var expectedResponse = RESOURCE_DATA_MAP.get(resource);
    server.expect(once(), requestTo(swapiUrl(resource)))
        .andExpect(method(HttpMethod.GET))
        .andRespond(
            withSuccess(expectedResponse, APPLICATION_JSON)
        );

    JSONObject result = subject.fetchResource(resource, "1");
    assertEquals(unPretty(expectedResponse), result.toString());

    server.verify();
  }

  @ParameterizedTest
  @ValueSource(strings = {"planets", "people", "films", "species", "vehicles", "starships"})
  void fetchResourceCallsFromTheCacheGoingForward(String resource) {

    var expectedResponse = RESOURCE_DATA_MAP.get(resource);
    server.expect(once(), requestTo(swapiUrl(resource)))
        .andRespond(withSuccess(expectedResponse, APPLICATION_JSON));

    var subject = new SWAPICachedRestClient(SWAPI_BASE_URL, restClient);
    JSONObject result = subject.fetchResource(resource, "1");
    assertEquals(unPretty(expectedResponse), result.toString());

    server.verify();
    server.reset();

    server.expect(never(), requestTo(swapiUrl(resource)))
        .andRespond(
            withSuccess(expectedResponse, APPLICATION_JSON)
        );
    for (int i = 0; i < 3; i++) {
      result = subject.fetchResource(resource, "1");
      assertEquals(unPretty(expectedResponse), result.toString());
      server.verify();
    }
  }

  private String swapiUrl(String resource) {
    return SWAPI_BASE_URL + "/" + resource + "/1";
  }

}
