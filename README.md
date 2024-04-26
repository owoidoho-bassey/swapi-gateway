# SWAPI API Gateway

This service is a gateway to the SWAPI API. 
It is a simple service that allows you to query the SWAPI API and get the results in multiple forms.
They are two category of endpoints in this service:
1. **Endpoints that don't resolve their linked resources**: These endpoints fetch resources from the SWAPI API and return them as is. The links to other resources are not resolved.
2. **Endpoints that resolve their linked resources**: These endpoints fetch resources from the SWAPI API and resolve the links to other resources. 

### Endpoints that don't resolve resource links

1. **Fetch a single resource "GET /api/v1/{resource}/{id}"**: The endpoint fetches a single resource from the SWAPI API. 
The resource is specified by the resource path variable and the id is specified by the id path variable. The response is a JSON object with the resource data.
It basically mimics the SWAPI API endpoint for fetching a single resource but replaces the links to SWAPI with links to the gateway.
e.g. /api/v1/people/1, fetches the first person resource.
     /api/v1/planets/2, fetches the second planet resource.
     /api/v1/starships/5, fetches the fifth starship resource.
2. **Fetch a page of resources "GET /api/v1/{resource}<?page={pageNo}>"**: The endpoint fetches a page of resources from the SWAPI API.
It is similar to the previous endpoint, but it fetches a page of resources. The page number is specified by the page query parameter.
e.g. /api/v1/people?page=2, fetches the second page of people resources.
     /api/v1/planets?page=1, fetches the first page of planet resources.
     /api/v1/starships fetches the first page of starship resources.
3. **Fetch all of a type of resource "GET /api/v1/{resource}<?page=-1>"**: The endpoint fetches all the resources of a type from the SWAPI API.
It does this by recursively fetching all the pages of the resource until there are no more pages.

### Endpoints that resolve resource links

1. **Fetch a single resource "GET /api/v2/resolved/{resource}/{id}"**: The endpoint fetches a single resource from the SWAPI API and resolves all links to other resources.
2. **Fetch a page of resources "GET /api/v2/resolved/{resource}<?page={pageNo}>"**: The endpoint fetches a page of resources from the SWAPI API and resolves all links to other resources.
3. **Fetch all of a type of resource "GET /api/v2/resolved/{resource}<?page=-1>"**: The endpoint fetches all the resources of a type from the SWAPI API and resolves all links to other resources.


### Rate Limiting
The service is rate limited to 60 requests per minute. If you exceed this limit, you will get a 429 status code.
It also applies a concurrent request limit of 5 requests. If you exceed this limit, you will get a 429 status code also.
The limits are applied per IP address and nothing is done to distinguish between different users on the same IP address or if a user is behind a proxy.

### Technical Details
1. The service caches the resources fetched from the SWAPI API until the service is restarted.
2. The first calls made to the resolvable endpoints are slower than the non-resolvable endpoints because the service has to resolve the links to other resources sometimes recursively.
3. **Resolving links**: The service resolves the links to other resources by fetching the linked resources from the SWAPI API and replacing the links with the fetched resources.
The following table shows the relationship between resources and from this we can see that there are two kinds of resources:

|          | people | planets | films  | species | vehicles | starships |
|----------|--------|-------|--------|---------|----------|-----------|
| people   |        | X     | X      | X       | X        | X         |
| planets  | X      |       | X      |         |          |           |
| vehicles | X      |       | X      |         |          |           |
| starships| X      |       | X      |         |          |           |
| species  | X      |       | X      |         |          |           |
| films    | X      |       | X      | X       | X        | X         |


    - Aggregate resources: The `films` and the `people` resources are aggregate resources. They are linked to other resources. 
    - Leaf resources: The `species`, `starships`, `vehicles`, `planets` resources are leaf resources. The service resolves these links by fetching the resources and replacing the links with the fetched resources.

When resolving resources, therefore, we resolve only the aggregate resources recursively and for the leaf resource we resolve them with a single fetch and add only minimal data, e.g. data with only `name` and `id`.
To prevent infinite recursion, if an aggregate resource is linked to another aggregate resource, we only resolve the first level of the linked resource.

examples of resolved people resource:
```json
{
  "films": [
      {
          "id": "1",
          "title": "A New Hope"
      }
  ],
  "homeworld": {
      "name": "Tatooine",
      "id": "1"
  },
  "gender": "male",
  "skin_color": "fair",
  "edited": "2014-12-20T21:17:56.891000Z",
  "created": "2014-12-09T13:50:51.644000Z",
  "mass": "77",
  "vehicles": [
      {
          "name": "Sand Crawler",
          "id": "1"
      }
  ],
  "url": "https://swapi.py4e.com/api/people/1/",
  "hair_color": "blond",
  "birth_year": "19BBY",
  "eye_color": "blue",
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
  "name": "Luke Skywalker",
  "height": "172"
}
```