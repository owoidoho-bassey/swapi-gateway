package com.owoidoho.bassey.swapigateway.infrastructure.web.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import org.apache.hc.core5.http.HttpStatus;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * The Bulkhead filter implementation that limits the number of concurrent requests.
 *
 * It makes use of a Semaphore set to permit only the configured number of concurrent requests.
 */
@Component
@Order(2)
public class BulkheadFilter implements Filter {

  private static final String RATE_LIMITED_RESPONSE = new JSONObject()
      .put("message", "Too many concurrent requests").toString();
  private final ConcurrentHashMap<String, Semaphore> limiterStore =
      new ConcurrentHashMap<>();

  private final int quota;

  public BulkheadFilter(@Value("${bulkhead.maxConcurrentCalls:5}") int quota) {
    this.quota = quota;
  }

  @NotNull
  private static String getLimiterKey(HttpServletRequest request) {
    return request.getRemoteAddr();
  }

  @Override
  public void doFilter(
      @NotNull ServletRequest request,
      @NotNull ServletResponse response,
      @NotNull FilterChain chain
  ) throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    String limiterKey = getLimiterKey(httpRequest);
    Semaphore limiter =
        limiterStore.computeIfAbsent(limiterKey, key -> new Semaphore(quota));

    try {
      if (limiter.tryAcquire()) {
        chain.doFilter(request, response);
      } else {
        httpResponse.setStatus(HttpStatus.SC_TOO_MANY_REQUESTS);
        httpResponse.getWriter().write(RATE_LIMITED_RESPONSE);
      }
    } finally {
      limiter.release();
    }
  }
}
