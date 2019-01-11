# A simple REST service on Open Liberty

The service has two resource types: `Person` & `Address`

The `Person` type has a reference to the `Address` via the `addressId`.

## Build and run the service

```
mvn clean install liberty:run
```

Visit: `http://localhost:9080/openapi/ui` to launch the Liberty OpenAPI UI page.  This page lets you create requests for all the REST methods or each resource type.  If you have CURL you can use the commands, below, to also drive the API.

## GET Resources

### GET all People

```curl -X GET "http://localhost:9080/v1/person" -H "accept: application/json"```

Response Body:

```JSON
[
  {
    "addressId": "488c9e84-0fa5-444f-ae4e-55ff710b87eb",
    "dob": "30-12-1999",
    "firstName": "Jane",
    "id": "bd105b60-38b2-473e-af3f-7556bd72a70d",
    "lastName": "Janely"
  },
  ...
]
```

### GET a specific Person

```curl -X GET "http://localhost:9080/v1/person/bd105b60-38b2-473e-af3f-7556bd72a70d" -H "accept: application/json"```

Where `bd105b60-38b2-473e-af3f-7556bd72a70d` is the id of the Person being retrieved.

Response Body:

```JSON
{
  "addressId": "488c9e84-0fa5-444f-ae4e-55ff710b87eb",
  "dob": "30-12-1999",
  "firstName": "Jane",
  "id": "bd105b60-38b2-473e-af3f-7556bd72a70d",
  "lastName": "Janely"
}
```

### GET a Person with a matching lastName

```curl -X GET "http://localhost:9080/v1/person/filter?lastName=Bobson" -H "accept: application/json"```

Response Body:

```JSON
[
  {
    "addressId": "890d800a-a58a-4463-8058-1563448be707",
    "dob": "31-12-1999",
    "firstName": "Bob",
    "id": "fa77558d-f112-408c-a085-00369590ca93",
    "lastName": "Bobson"
  }
]
```

### GET all Addresses

```curl -X GET "http://localhost:9080/v1/address" -H "accept: application/json"```

Response Body:

```JSON
[
  {
    "country": "England",
    "id": "1dacc76e-43f4-4a09-8a4b-008890e94883",
    "line2": "",
    "nameOrNumber": "14",
    "state": "Hampshire",
    "street": "Wibble Road",
    "zip": "14311"
  },
  ...
]
```

### GET a specific Address

```curl -X GET "http://localhost:9080/v1/address/1dacc76e-43f4-4a09-8a4b-008890e94883" -H "accept: application/json"```

Where `1dacc76e-43f4-4a09-8a4b-008890e94883` is the id of the Address being retrieved.

Response Body:

```JSON
{
  "country": "England",
  "id": "1dacc76e-43f4-4a09-8a4b-008890e94883",
  "line2": "",
  "nameOrNumber": "14",
  "state": "Hampshire",
  "street": "Wibble Road",
  "zip": "14311"
}
```

### GET the Address of a Person

```curl -X GET "http://localhost:9080/v1/person/bd105b60-38b2-473e-af3f-7556bd72a70d/address" -H "accept: application/json"```

Where `bd105b60-38b2-473e-af3f-7556bd72a70d` is the id of the Person whose Address is being retrieved.

Response Body:

```JSON
{
  "country": "Someland",
  "id": "488c9e84-0fa5-444f-ae4e-55ff710b87eb",
  "line2": "",
  "nameOrNumber": "The Hovel",
  "state": "Old Hampshire",
  "street": "Wobble Road",
  "zip": "43354"
}
```

## Creating Resources

### Create a Person

```curl -X POST "http://localhost:9080/v1/person" -H "accept: application/json" -H "Content-Type: application/json" -d "{\"firstName\":\"Dave\",\"lastName\":\"Davison\",\"dob\":\"31-11-200\"}"```

Response Body:

```JSON
{
  "addressId": "",
  "dob": "31-11-200",
  "firstName": "Dave",
  "id": "9b659aae-0d6e-44a3-85f2-b35b8f803013",
  "lastName": "Davison"
}
```

### Create Address

```curl -X POST "http://localhost:9080/v1/address" -H "accept: application/json" -H "Content-Type: application/json" -d "{\"nameOrNumber\":\"A House\",\"street\":\"A Street\",\"state\":\"A State\",\"zip\":\"54321\",\"country\":\"A Country\"}"```

Response Body:

```JSON
{
  "country": "A Country",
  "id": "f34c5ecc-833e-488c-8617-9d660b3757db",
  "line2": "",
  "nameOrNumber": "A House",
  "state": "A State",
  "street": "A Street",
  "zip": "54321"
}
```

### PUT (Update) Person to add Address

```curl -X PUT "http://localhost:9080/v1/person/9b659aae-0d6e-44a3-85f2-b35b8f803013" -H "accept: application/json" -H "Content-Type: application/json" -d "{\"addressId\":\"f34c5ecc-833e-488c-8617-9d660b3757db\",\"dob\":\"31-11-200\",\"firstName\":\"Dave\",\"lastName\":\"Davison\"}"```

Where `9b659aae-0d6e-44a3-85f2-b35b8f803013` is the id of the Person to be updated and `f34c5ecc-833e-488c-8617-9d660b3757db` is the id of the Person's new Address.

```JSON
{
  "addressId": "f34c5ecc-833e-488c-8617-9d660b3757db",
  "dob": "31-11-200",
  "firstName": "Dave",
  "id": "9b659aae-0d6e-44a3-85f2-b35b8f803013",
  "lastName": "Davison"
}
```
