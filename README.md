# NinjaOne Backend Interview Project

The project is configured to use an in-memory H2 database that is volatile. This API contains all the main features' 
described in the given documentation for the test.
## Starting the Application

Run the `BackendInterviewProjectApplication` class with the `prod` or build the app with `gradle build` and then execute
the docker-compose on the project's root directory, after that the endpoints can be better visualized at the Swagger main
page, the application is configured to use port 8080

* [Swagger Link](http://localhost:8080/app/swagger-ui/index.html)

You should see results after running the app.

## Using the API 

In order to use the application you must first authenticate yourself, this can be done via the default Credential witch is
already in the project, just use the following Credentials:
```json
{
  "username": "user",
  "password": "string"
}
```

Just input those credentials at the `/authenticate` endpoint in Authentication API Tag and store the received token, 
public routes don't need authentication, but with private end-point authentication is mandatory.

After that you can freely use the API.

### Suggestions

Feel free to remove or repurpose the existing Sample Repository, Entity, Controller, and Service. 
