package com.owoidoho.bassey.swapigateway.interfaces.api;


import com.owoidoho.bassey.swapigateway.core.services.SWAPIService;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class SWAPIController {

  private final String swapiBaseUrl;
  private final SWAPIService swapiService;

  public SWAPIController(
      @Value("${swapi.baseUrl}") String swapiBaseUrl,
      SWAPIService swapiService
  ) {
    this.swapiBaseUrl = swapiBaseUrl;
    this.swapiService = swapiService;
  }

  @GetMapping(
      value = {
          "/api/v1",
          "/api/v1/",
          "/api/v1/{resources}/{path}",
          "/api/v1/{resources}/{path}/",
      },
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<String> getResource(
      @PathVariable(required = false) String resources,
      @PathVariable(required = false) String path,
      HttpServletRequest request
  ) {
    JSONObject result = swapiService.getResource(resources, path);
    String resultString = result.toString();
    String requestBaseUrl = getRequestBaseUrl(request);
    return ResponseEntity
        .ok(replaceAllSWAPIUrls(resultString, requestBaseUrl));

  }

  @GetMapping(
      value = {
          "/api/v1/{resources}",
          "/api/v1/{resources}/"
      },
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<String> getPagedResources(
      @PathVariable String resources,
      @RequestParam(name = "page", required = false) Integer page,
      HttpServletRequest request
  ) {
    JSONObject result;
    int pageNo = page == null ? 1 : page;
    if (pageNo == -1) {
      result = new JSONObject();
      result.put("results", swapiService.getAllResources(resources));
    } else {
      result = swapiService.getPagedResources(resources, pageNo == 0 ? 1 : pageNo);
    }
    String resultString = result.toString();
    String requestBaseUrl = getRequestBaseUrl(request);
    return ResponseEntity.ok(replaceAllSWAPIUrls(resultString, requestBaseUrl));
  }

  @GetMapping(
      value = {
          "/api/v1/resolved/{resources}/{path}",
          "/api/v1/resolved/{resources}/{path}/"
      },
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<String> getResolvedResource(
      @PathVariable String resources,
      @PathVariable String path,
      HttpServletRequest request
  ) {
    JSONObject result = swapiService.getResolvedResource(resources, path);
    String resultString = result.toString();
    String requestBaseUrl = getRequestBaseUrl(request);
    return ResponseEntity.ok(replaceAllSWAPIUrls(resultString, requestBaseUrl));
  }

  @GetMapping(
      value = {
          "/api/v1/resolved/{resources}",
          "/api/v1/resolved/{resources}/",
      },
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<String> getPagedResolvedResources(
      @PathVariable String resources,
      @RequestParam(name = "page", required = false) Integer page,
      HttpServletRequest request
  ) {
    JSONObject result;
    int pageNo = page == null ? 1 : page;
    if (pageNo == -1) {
      result = new JSONObject();
      result.put("results", swapiService.getAllResolvedResources(resources));
    } else {
      result = swapiService.getPagedResolvedResources(resources, pageNo == 0 ? 1 : pageNo);
    }
    String resultString = result.toString();
    String requestBaseUrl = getRequestBaseUrl(request);
    return ResponseEntity.ok(replaceAllSWAPIUrls(resultString, requestBaseUrl));
  }


  private String replaceAllSWAPIUrls(
      @NotNull String body,
      @Nullable String requestBaseUrl
  ) {
    if (requestBaseUrl == null || requestBaseUrl.isBlank()) {
      return body;
    }
    return body.replaceAll(swapiBaseUrl, requestBaseUrl);
  }

  private String getRequestBaseUrl(HttpServletRequest request) {
    return ServletUriComponentsBuilder.fromRequestUri(request)
        .replacePath("/api")
        .replaceQuery(null)
        .toUriString();
  }
}
