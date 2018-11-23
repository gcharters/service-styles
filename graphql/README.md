# A simple GraphQL service on Open Liberty

The service has two data types: `Person` & `Address`

The `Person` type has a reference to the `Address` via the `addressId`.

## Build and run the service

```
mvn clean install liberty:run-server
```

Visit: `http://localhost:9080/` to launch the GraphiQL page.

## Querying

### Query People

In GraphiQL, clear the text in the left pane and enter:

```JavaScript
query {
  allPeople {
    id
    firstName
    lastName
    dob
  }
}
```
Press the play button and you should see:

```JSON
{
  "data": {
    "allPeople": [
      {
        "id": "10ae29ca-bf05-4b58-9070-31f4c40e9202",
        "firstName": "Bob",
        "lastName": "Bobson",
        "dob": "31-12-1999"
      },
      . . .
    ]
  }
}
```

### Query Addresses

In GraphiQL, clear the text in the left pane and enter:

```JavaScript
query {
  allAddresses {
    id
    nameOrNumber
    street
    state
    zip
    country
  }
}
```
Press the play button and you should see:

```JSON
{
  "data": {
    "allAddresses": [
      {
        "id": "e7d882fc-fc39-4b07-a82d-56985da1d59f",
        "nameOrNumber": "The Hovel",
        "street": "Wobble Road",
        "state": "Old Hampshire",
        "zip": "43354",
        "country": "Someland"
      },
      . . .
    ]
  }
}
```

### Query Person & Address

In GraphiQL, clear the text in the left pane and enter:

```JavaScript
query {
  allPeople {
    id
    firstName
    lastName
    address {
      id
      nameOrNumber
      street
      zip
    }
  }
}

```
Press the play button and you should see:

```JSON
{
  "data": {
    "allPeople": [
      {
        "id": "10ae29ca-bf05-4b58-9070-31f4c40e9202",
        "firstName": "Bob",
        "lastName": "Bobson",
        "address": {
          "id": "d13d6383-0116-4c3a-83b7-5e78b79d2d65",
          "nameOrNumber": "14",
          "street": "Wibble Road",
          "zip": "14311"
        }
      },
      . . .
    ]
  }
}
```

## Mutating (updating)

### Create Person

In GraphiQL, clear the text in the left pane and enter:

```JavaScript
mutation {
  createPerson(firstName: "Sally", lastName: "Sallyson", dob: "12-12-2012") {
    id
    firstName
    lastName
    dob
  }
}
```
Press the play button and you should see:

```JSON
{
  "data": {
    "createPerson": {
      "id": "0e8a1b52-1d0c-4e18-aaf1-4c835acfb675",
      "firstName": "Sally",
      "lastName": "Sallyson",
      "dob": "12-12-2012"
    }
  }
}
```

### Create Address

In GraphiQL, clear the text in the left pane and enter:

```JavaScript
mutation {
  createAddress(nameOrNumber: "A House", street: "A Street", zip: "12345", country: "A Country") {
    id
    nameOrNumber
    street
    zip
    country
  }
}
```
Press the play button and you should see:

```JSON
{
  "data": {
    "createAddress": {
      "id": "c3527006-d411-4f16-ae0d-badaa28aa539",
      "nameOrNumber": "A House",
      "street": "A Street",
      "zip": "12345",
      "country": "A Country"
    }
  }
}
```

### Set the Address of a Person

In GraphiQL, clear the text in the left pane and enter:

```JavaScript
mutation {
  setAddressOfPerson(personId: "0e8a1b52-1d0c-4e18-aaf1-4c835acfb675", addressId: "c3527006-d411-4f16-ae0d-badaa28aa539") {
    id
    firstName
    lastName
    address {
      id
      nameOrNumber
      street
      zip
    }
  }
}
```
Press the play button and you should see:

```JSON
{
  "data": {
    "setAddressOfPerson": {
      "id": "0e8a1b52-1d0c-4e18-aaf1-4c835acfb675",
      "firstName": "Sally",
      "lastName": "Sallyson",
      "address": {
        "id": "c3527006-d411-4f16-ae0d-badaa28aa539",
        "nameOrNumber": "A House",
        "street": "A Street",
        "zip": "12345"
      }
    }
  }
}
```
