/*
 * Copyright 2015 The gRPC Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gcc.demos.grpc.person;

import gcc.demos.grpc.PersonServiceGrpc;
import gcc.demos.grpc.Person;
import gcc.demos.grpc.Address;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.protobuf.Empty;

/**
 * A simple client that requests a greeting from the {@link PersonServer}.
 */
public class PersonClient {
  private static final Logger logger = Logger.getLogger(PersonClient.class.getName());

  private final ManagedChannel channel;
  private final PersonServiceGrpc.PersonServiceBlockingStub blockingStub;

  /** Construct client connecting to Person server at {@code host:port}. */
  public PersonClient(String host, int port) {
    this(ManagedChannelBuilder.forAddress(host, port)
        // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
        // needing certificates.
        .usePlaintext()
        .build());
  }

  /** Construct client for accessing Person server using the existing channel. */
  PersonClient(ManagedChannel channel) {
    this.channel = channel;
    blockingStub = PersonServiceGrpc.newBlockingStub(channel);
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  /** Get the person. */
  public void call(String op, String id) {
    logger.info("Calling operation " + op + " with Id: " + id);


    switch (op) {
      case "getPerson": {
        Person request = Person.newBuilder().setId(id).build();
        Person response;
        try {
          response = blockingStub.getPerson(request);
        } catch (StatusRuntimeException e) {
          logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
          return;
        }
        logger.info("Person: " + response.getFirstName());
      }
      break;
      case "getPeople": {
        Iterator<Person> response;
        try {
          response = blockingStub.getPeople(Empty.newBuilder().build());
        } catch (StatusRuntimeException e) {
          logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
          return;
        }

        while (response.hasNext()) {
          Person person = response.next();
          System.out.println("ID: " + person.getId());
          System.out.println("First Name: " + person.getFirstName());
          System.out.println("Last Name: " + person.getLastName());
          System.out.println("Address Id: " + person.getAddressId());

        }
      }
    }

  }

  /**
   * Person server. If provided, the first element of {@code args} is the id of the person to retrieve.
   */
  public static void main(String[] args) throws Exception {
    PersonClient client = new PersonClient("localhost", 50051);
    try {
      /* Access a service running on the local machine on port 50051 */
      String op = "getPerson";
      String id = "";
      if (args.length > 0) {
        op = args[0]; /* Use the arg as the id f provided */
      }
      if (args.length > 1) {
        id = args[1];
      }
      client.call(op, id);
    } finally {
      client.shutdown();
    }
  }
}
