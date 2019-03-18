gRPC Person Example

### Build

```
./gradlew installDist
```
### Start the server

```
./build/install/examples/bin/person-server
```

### Run the client

```
./build/install/examples/bin/person-client getPeople
```

You should see and output like:

```
INFO: Calling operation getPeople with Id: 
ID: 3c491b16-ea2d-41bd-9c73-cbf8d86a6164
First Name: Jane
Last Name: Janely
Address Id: ffe4dd79-67ae-4722-b0f3-2d6b0bed0f0c
ID: 20cb62d4-7cca-4131-b1de-ddf3992cb2bf
First Name: Bob
Last Name: Bobson
Address Id: 0b6fab38-1ce7-491b-917b-851e4a1554f8
```

or to get an individual person, call

```
./build/install/examples/bin/person-client getPerson <person id>
```

For example: 

```
./build/install/examples/bin/person-client getPerson 3c491b16-ea2d-41bd-9c73-cbf8d86a6164
```
And you should see an output like:

```
INFO: Calling operation getPerson with Id: 3c491b16-ea2d-41bd-9c73-cbf8d86a6164
Mar 18, 2019 2:16:02 PM gcc.demos.grpc.person.PersonClient call
INFO: Person: Jane

```
