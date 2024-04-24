package com.owoidoho.bassey.swapigateway.infrastructure.web.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.hc.core5.http.HttpStatus;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * The Rate Limiter filter implementation that limits the number of requests per period.
 *
 * It makes use of an Atomic counter and timestamp to implement a simple Token Bucket algorithm.
 */
@Component
@Order(1)
public class RateLimiterFilter implements Filter {

  private static final String RATE_LIMITED_RESPONSE =
      new JSONObject().put("message", "Too many requests").toString();
  private final ConcurrentHashMap<String, TimedLimiter> limiterStore =
      new ConcurrentHashMap<>();
  private final int quota;
  private final int refreshPeriodSec;

  public RateLimiterFilter(
      @Value("${rateLimiter.quota:60}") int quota,
      @Value("${rateLimiter.refreshPeriodSec:60}") int refreshPeriodSec
  ) {
    this.quota = quota;
    this.refreshPeriodSec = refreshPeriodSec;
  }

  @NotNull
  private static String getLimiterKey(@NotNull HttpServletRequest request) {
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
    TimedLimiter limiter =
        limiterStore.computeIfAbsent(limiterKey, key -> new TimedLimiter(quota, refreshPeriodSec));

    if (limiter.decrement()) {
      chain.doFilter(request, response);
    } else {
      httpResponse.setStatus(HttpStatus.SC_TOO_MANY_REQUESTS);
      httpResponse.getWriter().write(RATE_LIMITED_RESPONSE);
    }
  }

  private static class TimedLimiter {

    private final int quota;
    private final long refreshPeriodNano;
    private final AtomicInteger counter;
    private final AtomicLong timestamp;

    public TimedLimiter(int quota, int refreshPeriodSec) {
      this.quota = quota;
      this.counter = new AtomicInteger(quota);
      this.refreshPeriodNano = Duration.ofSeconds(refreshPeriodSec).toNanos();
      this.timestamp = new AtomicLong(System.nanoTime());
    }

    public boolean decrement() {
      return counter.updateAndGet(this::update) >= 0;
    }

    private int update(int value) {
      long timeDifference = System.nanoTime() - timestamp.get();
      int nextValue = (timeDifference > refreshPeriodNano ? quota : value) - 1;
      timestamp.set(System.nanoTime());
      return nextValue;
    }
  }
}
