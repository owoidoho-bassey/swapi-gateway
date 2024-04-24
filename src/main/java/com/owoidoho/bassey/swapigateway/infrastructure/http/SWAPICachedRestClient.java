package com.owoidoho.bassey.swapigateway.infrastructure.http;

import static io.micrometer.common.util.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.removeEnd;

import com.owoidoho.bassey.swapigateway.core.repository.RepositoryException;
import com.owoidoho.bassey.swapigateway.core.repository.ResourceNotFoundException;
import com.owoidoho.bassey.swapigateway.core.repository.SWAPIRepository;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
public class SWAPICachedRestClient implements SWAPIRepository {

  private static final JSONObject EMPTY_JSON = new JSONObject();
  private final ConcurrentHashMap<String, JSONObject> cache = new ConcurrentHashMap<>();

  private final RestClient restClient;
  private final String swapiBaseUrl;

  public SWAPICachedRestClient(
      @Value("${swapi.baseUrl}") String swapiBaseUrl,
      RestClient restClient
  ) {
    this.swapiBaseUrl = removeEnd(swapiBaseUrl.trim(), "/");
    this.restClient = restClient;
  }

  @Override
  @NotNull
  public JSONObject fetchResource(@NotNull String resource, @NotNull String path) {
    String url = buildUrl(resource, path);
    JSONObject result = cache.get(url);
    if (result == null) {
      result = getResourceFromSWAPI(url);
      writeResponseToCache(url, result);
    }
    return result;
  }

  @NotNull
  private JSONObject getResourceFromSWAPI(@NotNull String url) {
    try {
      return restClient
          .get()
          .uri(url)
          .accept(MediaType.APPLICATION_JSON)
          .exchange((request, response) -> {
            var responseBody = response.bodyTo(String.class);
            var responseJson = responseBody == null ? EMPTY_JSON : new JSONObject(responseBody);
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
              throw new ResourceNotFoundException("Resource not found", responseJson);
            }
            return responseJson;
          });
    } catch (RestClientException ex) {
      throw new RepositoryException("Encountered getting data from SWAPI", ex);
    }
  }

  private void writeResponseToCache(@NotNull String url, @NotNull JSONObject response) {
    JSONArray results = response.optJSONArray("results");
    if (results != null) {
      results.forEach(result -> {
        JSONObject resultObject = (JSONObject) result;
        String returnedUrl = resultObject.getString("url");
        cache.put(trim(returnedUrl), resultObject);
      });
    }

    String returnedUrl = response.optString("url");
    returnedUrl = isBlank(returnedUrl) ? url : returnedUrl;
    cache.put(trim(returnedUrl), response);
  }

  @NotNull
  private String buildUrl(@NotNull String resource, @NotNull String path) {
    String cleanedResource = trim(resource);
    String cleanedPath = trim(path);
    StringBuilder urlBuilder = new StringBuilder(swapiBaseUrl);
    if (isNotBlank(cleanedResource)) {
      urlBuilder.append("/").append(cleanedResource);
    }
    if (isNotBlank(cleanedPath)) {
      urlBuilder.append("/").append(cleanedPath);
    }
    return urlBuilder.toString();
  }


  @NotNull
  private String trim(@Nullable String url) {
    if (url == null) {
      return "";
    }
    return removeEnd(url.trim(), "/");
  }


}
