# perlib-server

The application provides a book collection management via REST API methods for all CRUD operations.

## Tech Stack

Spring Boot, H2 DB, Gradle, Docker

## API methods

The following methods are available:

- __POST /api/books__ - add new book using the request body data (request body is described below)
- __GET /api/books/{id}__ - get data for a book with the given ID
- __PUT /api/books/{id}__ - update a book with the given ID and using the request body data (request body is described below)
- __DELETE /api/books/{id}__ - delete a book with the given ID (returns HTTP 204)
- __GET /api/books__ - get book list using the search parameters (request parameters are described below)
- __GET /api/languages__ - get possible values for the parameter "language"

Request body example for the POST and the PUT method is (all parameters are mandatory):  
`{
    "name":"Hello World",
    "author": "John Doe",
    "language":"ENGLISH"
}`

Value for the parameter "language" must be one of the "name" parameter values returned in the GET languages response.

Request parameters for the GET books (search) method are:
- _name_ - LIKE operator will be applied (_%value%_)
- _author_ - LIKE operator will be applied (_%value%_)
- _language_ - EQUAL operator will be applied
- _size_ - number of books per page, a default value is 10
- _page_ - page number, starts from 0, and that is a default value
- _sorts_ - sort by value (multiple values possible) in the format _attribute,direction_ (e.g. name,asc), a default value is _id,asc_

All search parameters are optional, and in that case the default values will be used for the paging parameters.
The default values for the "size" and the "page" are defined in the application.yaml.

The response body example for one book (also part of the search method response):  
`{
    "id": 1,
    "name":"Hello World",
    "author": "John Doe",
    "language":"ENGLISH"
    "createdDate": "2021-12-12T11:24:38.071572",
    "lastModifiedDate": "2021-12-15T11:24:38.071646"
}`

The Swagger UI page and the OpenAPI description (generated without any customization and partially incorrect) are available at _/swagger-ui/index.html_ and at _/v3/api-docs_ respectively.

Also the application shutdown is available via the Spring Actuator endpoint (_POST /actuator/shutdown_, without request body).

__Security and authorization__: GET methods are permitted without authorization, while for all others the predefined username and password (application.yaml) must be provided via the Basic Authorization.

## Starting the application

The application can be started either standalone i.e. using the JAR file, or in a Docker container.  
Details for both options are described in the separate sections below.  
After starting the API methods are available at http://localhost:8080/perlib/  
The book data will be saved in the _database_ directory, and log files in the _logs_ directory (relative to the current working directory).
This is true for the both options.

 ### Standalone

To build the fat JAR file (_perlib-server-*.jar_) execute the command:
`$ ./gradlew clean bootJar`  
To start the JAR file execute the command:
`$ java -jar ./build/libs/perlib-server-*.jar`

 ### Docker

To build the image execute the command:
`$ ./gradlew clean dockerBuild`  
To create a container run _runDocker.sh_:
`$ ./runDocker.sh`  
To start an existing container execute the command:
`$ docker start perlib-server`

NOTE: The Docker image uses the layers from the fat JAR in order to improve build and start time of the image and the container.
 All dependencies are in one layer, whereas the code and the configuration is in another layer.  
 By executing the task _dockerBuild_ the following steps will be done: execute the _bootJar_ task, unpack the JAR, 
 delete the current (stopped) container, delete and the old image, build the new image.