[ ![Codeship Status for marcelomalcher/vrspotippos](https://codeship.com/projects/93b7daa0-0e8e-0134-c5b4-0a3e61dce168/status?branch=master)](https://codeship.com/projects/156392)

# VRSpotippos

## What you need to build

This `vrspotippos` project was built with the following:
 
 * Java 8
 * Gradle 2.13
 * Spring-Boot
 * Embedded Jetty
 
## Building

To build it, use Gradle Wrapper or have Gradle installed and run the following command:

```
> ./gradlew clean build 
```

## Running

You might run the project direct from Gradle using:

```
> ./gradlew bootRun
```

Or you might build the project and run using the `java` command:

```
> java -jar build/libs/vrspotippos-0.0.1.jar
```

### On initialization

This project downloads the provinces and properties during initialization and store the data 
using the respective repositories. 

  Important! Internet access is required to proper download the data.

### Storage

For the sake of simplicity, the repositories were built in memory using hash maps.

### Checking service status with health endpoint 

While executing the project, you might want to check if it is up and running through its Health 
endpoint:

```
curl -v http://localhost:8080/health
                  
> GET /health HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.43.0
> Accept: */*

< HTTP/1.1 200 OK
< Date: Tue, 07 Jun 2016 05:40:14 GMT
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 27
< Server: Jetty(9.2.16.v20160414)

VRSpotippos up and running.
```

## Operations

### Creates a property

* Request:

```http
POST /properties

curl -vX POST -d @property.json http://localhost:8080/properties -H "Content-Type: application/json"
```

* Responses:

- When the property is created successfully, it returns in the body the property as json, with 
all the new fields defined and inferred like id and provinces.
  - Important! If the property being sent to the endpoint contains an id and there is already a 
  property with the same id in the database, the property is replaced.

```
HTTP 201 - CREATED

curl -vX POST -d @create-no-id.json http://localhost:8080/properties -H "Content-Type: application/json"

> POST /properties HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.43.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 225

< HTTP/1.1 201 Created
< Date: Tue, 07 Jun 2016 13:25:25 GMT
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Server: Jetty(9.2.16.v20160414)
<
{
  "id":4001,
  "title":"Imóvel código 1, com 5 quartos e 4 banheiros",
  "price":1250000,
  "description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
  "x":222,
  "y":444,
  "beds":4,
  "baths":3,
  "provinces":[
    "Scavy"
  ],
  "squareMeters":210
}
```

- When the property being created is invalid as does not respect the constraints defined or if 
the json is invalid or absent, it returns a HTTP 400 and the body containing the error messages, 
if applicable.

```
HTTP 400 - BAD REQUEST

curl -vX POST -d @create-not-valid.json http://localhost:8080/properties -H "Content-Type: application/json"

> POST /properties HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.43.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 246

< HTTP/1.1 400 Bad Request
< Date: Tue, 07 Jun 2016 13:26:59 GMT
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Server: Jetty(9.2.16.v20160414)

{
   "timestamp":1465306019891,
   "status":400,
   "error":"Bad Request",
   "exception":"org.springframework.web.bind.MethodArgumentNotValidException",
   "errors":[
      {
         "codes":[
            "Range.property.baths",
            "Range.baths",
            "Range.java.lang.Integer",
            "Range"
         ],
         "arguments":[
            {
               "codes":[
                  "property.baths",
                  "baths"
               ],
               "arguments":null,
               "defaultMessage":"baths",
               "code":"baths"
            },
            4,
            1
         ],
         "defaultMessage":"Um imóvel em Spotippos deve ter no máximo 4 banheiros (baths) e no mínimo 1",
         "objectName":"property",
         "field":"baths",
         "rejectedValue":10,
         "bindingFailure":false,
         "code":"Range"
      },
      {
         "codes":[
            "Range.property.beds",
            "Range.beds",
            "Range.java.lang.Integer",
            "Range"
         ],
         "arguments":[
            {
               "codes":[
                  "property.beds",
                  "beds"
               ],
               "arguments":null,
               "defaultMessage":"beds",
               "code":"beds"
            },
            5,
            1
         ],
         "defaultMessage":"Um imóvel em Spotippos deve ter no máximo 5 quartos (beds), e no mínimo 1",
         "objectName":"property",
         "field":"beds",
         "rejectedValue":10,
         "bindingFailure":false,
         "code":"Range"
      },
      {
         "codes":[
            "Range.property.squareMeters",
            "Range.squareMeters",
            "Range.java.lang.Integer",
            "Range"
         ],
         "arguments":[
            {
               "codes":[
                  "property.squareMeters",
                  "squareMeters"
               ],
               "arguments":null,
               "defaultMessage":"squareMeters",
               "code":"squareMeters"
            },
            240,
            20
         ],
         "defaultMessage":"Um imóvel em Spotippos deve ter no máximo 240 metros quadrados e no mínimo 20",
         "objectName":"property",
         "field":"squareMeters",
         "rejectedValue":2000,
         "bindingFailure":false,
         "code":"Range"
      }
   ],
   "message":"Bad Request",
   "path":"/properties"
}
```

### Returns a property

* Request:

```http
GET /properties

curl -v http://localhost:8080/properties/{id}"
```

* Response:

- When the property is found

```
HTTP 200 - CREATED

curl -v http://localhost:8080/properties/4000

> GET /properties/4000 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.43.0
> Accept: */*

< HTTP/1.1 200 OK
< Date: Tue, 07 Jun 2016 13:40:44 GMT
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Server: Jetty(9.2.16.v20160414)

{
   "id":4000,
   "title":null,
   "price":null,
   "description":null,
   "x":1137,
   "y":845,
   "beds":5,
   "baths":4,
   "provinces":[
      "Jaby"
   ],
   "squareMeters":110
}
```

- When the property is not found

```
HTTP 404 - NOT FOUND

curl -v http://localhost:8080/properties/4001

> GET /properties/4001 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.43.0
> Accept: */*

< HTTP/1.1 404 Not Found
< Date: Tue, 07 Jun 2016 13:44:24 GMT
< Content-Length: 0
< Server: Jetty(9.2.16.v20160414)
```

### Searches  properties in defined region

* Request:

```http
GET /properties?ax={integer}&ay={integer}&bx={integer}&by={integer}

curl -v http://localhost:8080/properties?ax=0&ay=100&bx=100&by=100 -H "Content-Type: 
application/json"
```

* Response:

```
HTTP 200 - OK

curl -v 'http://localhost:8080/properties?ax=395&ay=800&bx=415&by=705'

> GET /properties?ax=395&ay=800&bx=415&by=705 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.43.0
> Accept: */*
> Content-Type: application/json

< HTTP/1.1 200 OK
< Date: Tue, 07 Jun 2016 13:48:38 GMT
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Server: Jetty(9.2.16.v20160414)

{
   "foundProperties":4,
   "properties":[
      {
         "id":1705,
         "title":null,
         "price":null,
         "description":null,
         "x":395,
         "y":798,
         "beds":5,
         "baths":4,
         "provinces":[
            "Gode"
         ],
         "squareMeters":196
      },
      {
         "id":1910,
         "title":null,
         "price":null,
         "description":null,
         "x":411,
         "y":737,
         "beds":5,
         "baths":4,
         "provinces":[
            "Gode",
            "Ruja"
         ],
         "squareMeters":164
      },
      {
         "id":3244,
         "title":null,
         "price":null,
         "description":null,
         "x":409,
         "y":791,
         "beds":4,
         "baths":3,
         "provinces":[
            "Gode",
            "Ruja"
         ],
         "squareMeters":89
      },
      {
         "id":3346,
         "title":null,
         "price":null,
         "description":null,
         "x":411,
         "y":742,
         "beds":5,
         "baths":4,
         "provinces":[
            "Gode",
            "Ruja"
         ],
         "squareMeters":192
      }
   ]
}
```

## Heroku