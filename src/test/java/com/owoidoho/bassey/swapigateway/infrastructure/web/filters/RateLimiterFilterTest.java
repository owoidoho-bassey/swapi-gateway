package com.owoidoho.bassey.swapigateway.infrastructure.web.filters;

import static java.util.concurrent.CompletableFuture.runAsync;
import static org.apache.commons.lang3.function.Failable.asPredicate;
import static org.apache.commons.lang3.function.Failable.asRunnable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class RateLimiterFilterTest {

  private static final int REFRESH_PERIOD_SEC = 60;

  @Test
  void testRateLimiting() throws Exception {
    int quota = 60;
    int extra = 5;
    int numberOfRequests = quota + extra;
    var rateLimiterFilter = new RateLimiterFilter(quota, REFRESH_PERIOD_SEC);

    var chain = createMockedFilterChain();
    var requests = createMockRequests(numberOfRequests);
    var responses = createMockResponse(numberOfRequests);

    var futures = new CompletableFuture[numberOfRequests];
    for (int i = 0; i < numberOfRequests; i++) {
      int finalI = i;
      futures[i] = runAsync(
          asRunnable(() -> rateLimiterFilter.doFilter(requests[finalI], responses[finalI], chain))
      );
    }

    CompletableFuture.allOf(futures).join();

    var rejected = Stream.of(responses)
        .filter(asPredicate(response ->
            response.getStatus() == HttpStatus.TOO_MANY_REQUESTS.value() &&
                response.getContentAsString().contains("Too many requests")
        ))
        .count();

    assertEquals(extra, rejected);
    verify(chain, times(quota)).doFilter(any(), any());
  }

  private FilterChain createMockedFilterChain() throws ServletException, IOException {
    var chain = mock(FilterChain.class);

    doNothing()
        .when(chain)
        .doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));

    return chain;
  }

  private MockHttpServletRequest[] createMockRequests(int numberOfRequests) {
    var mockedRequests = new MockHttpServletRequest[numberOfRequests];
    for (int i = 0; i < numberOfRequests; i++) {
      var mockedRequest = new MockHttpServletRequest();
      mockedRequests[i] = mockedRequest;
    }
    return mockedRequests;
  }

  private MockHttpServletResponse[] createMockResponse(int numberOfResponses) {
    var mockedResponses = new MockHttpServletResponse[numberOfResponses];
    for (int i = 0; i < numberOfResponses; i++) {
      var mockedResponse = new MockHttpServletResponse();
      mockedResponses[i] = mockedResponse;
    }
    return mockedResponses;
  }

}
