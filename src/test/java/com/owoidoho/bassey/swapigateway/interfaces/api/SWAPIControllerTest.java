package com.owoidoho.bassey.swapigateway.interfaces.api;

import static com.owoidoho.bassey.swapigateway.TestData.PEOPLE_1;
import static com.owoidoho.bassey.swapigateway.TestData.PEOPLE_1_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.ROOTS;
import static com.owoidoho.bassey.swapigateway.TestData.SWAPI_BASE_URL;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_ALL;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_ALL_RESOLVED;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_PAGE_1;
import static com.owoidoho.bassey.swapigateway.TestData.VEHICLES_PAGE_1_RESOLVED;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.owoidoho.bassey.swapigateway.core.repository.ResourceNotFoundException;
import com.owoidoho.bassey.swapigateway.core.services.SWAPIService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SWAPIController.class)
public class SWAPIControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private SWAPIService swapiService;

  @BeforeEach
  void setup() {
    when(swapiService.getResource("", ""))
        .thenReturn(new JSONObject(ROOTS));
    when(swapiService.getResource("people", "1"))
        .thenReturn(new JSONObject(PEOPLE_1));
    when(swapiService.getResolvedResource("people", "1"))
        .thenReturn(new JSONObject(PEOPLE_1_RESOLVED));
    when(swapiService.getPagedResources("vehicles", 1))
        .thenReturn(new JSONObject(VEHICLES_PAGE_1));
    when(swapiService.getPagedResolvedResources("vehicles", 1))
        .thenReturn(new JSONObject(VEHICLES_PAGE_1_RESOLVED));
    when(swapiService.getAllResources("vehicles"))
        .thenReturn(new JSONArray(VEHICLES_ALL));
    when(swapiService.getAllResolvedResources("vehicles"))
        .thenReturn(new JSONArray(VEHICLES_ALL_RESOLVED));
  }

  @Test
  public void testGetRootResource() throws Exception {
    var expectedResponse = new JSONObject(
        ROOTS.replaceAll(SWAPI_BASE_URL, "http://localhost/api")
    ).toString();

    mockMvc
        .perform(get("/api/v1"))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse))
        .andReturn();

    verify(swapiService, times(1)).getResource("", "");
  }

  @Test
  public void testGetResource() throws Exception {
    var expectedResponse = new JSONObject(
        PEOPLE_1.replaceAll(SWAPI_BASE_URL, "http://localhost/api")
    ).toString();

    mockMvc
        .perform(get("/api/v1/people/1"))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse))
        .andReturn();

    verify(swapiService, times(1)).getResource("people", "1");
  }

  @Test
  public void testGetResolvedResource() throws Exception {
    var expectedResponse = new JSONObject(
        PEOPLE_1_RESOLVED.replaceAll(SWAPI_BASE_URL, "http://localhost/api")
    ).toString();

    mockMvc
        .perform(get("/api/v1/resolved/people/1"))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse))
        .andReturn();

    verify(swapiService, times(1)).getResolvedResource("people", "1");
  }

  @Test
  public void testGetPagedResources() throws Exception {
    var expectedResponse = new JSONObject(
        VEHICLES_PAGE_1.replaceAll(SWAPI_BASE_URL, "http://localhost/api")
    ).toString();

    mockMvc
        .perform(get("/api/v1/vehicles?page=1"))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse))
        .andReturn();

    verify(swapiService, times(1)).getPagedResources("vehicles", 1);
  }

  @Test
  public void testGetPagedResolvedResources() throws Exception {
    var expectedResponse = new JSONObject(
        VEHICLES_PAGE_1_RESOLVED.replaceAll(SWAPI_BASE_URL, "http://localhost/api")
    ).toString();

    mockMvc
        .perform(get("/api/v1/resolved/vehicles?page=1"))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse))
        .andReturn();

    verify(swapiService, times(1)).getPagedResolvedResources("vehicles", 1);
  }

  @Test
  public void testGetAllResources() throws Exception {
    var expectedResponse = new JSONObject()
        .put("results", new JSONArray(VEHICLES_ALL))
        .toString()
        .replaceAll(SWAPI_BASE_URL, "http://localhost/api");

    mockMvc
        .perform(get("/api/v1/vehicles?page=-1"))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse))
        .andReturn();

    verify(swapiService, times(1)).getAllResources("vehicles");
  }

  @Test
  public void testGetAllPagedResources() throws Exception {
    var expectedResponse = new JSONObject()
        .put("results", new JSONArray(VEHICLES_ALL_RESOLVED))
        .toString()
        .replaceAll(SWAPI_BASE_URL, "http://localhost/api");

    mockMvc
        .perform(get("/api/v1/resolved/vehicles?page=-1"))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse))
        .andReturn();

    verify(swapiService, times(1)).getAllResolvedResources("vehicles");
  }

  @Test
  public void testResourceNotFound() throws Exception {
    when(swapiService.getResource("unknown", "1"))
        .thenThrow(new ResourceNotFoundException("Resource not found"
            , new JSONObject().put("detail", "Not found")
        ));

    mockMvc
        .perform(get("/api/v1/unknown/1"))
        .andExpect(status().isNotFound())
        .andExpect(content().json("{\"detail\":\"Not found\"}"))
        .andReturn();

    verify(swapiService, times(1)).getResource("unknown", "1");
  }

  @Test
  public void testGenericExceptionHandled() throws Exception {
    when(swapiService.getResource("unknown", "1"))
        .thenThrow(new RuntimeException("Some error occurred"));

    mockMvc
        .perform(get("/api/v1/unknown/1"))
        .andExpect(status().isInternalServerError())
        .andExpect(content().json("{\"detail\":\"Some error occurred\"}"))
        .andReturn();

    verify(swapiService, times(1)).getResource("unknown", "1");
  }

}