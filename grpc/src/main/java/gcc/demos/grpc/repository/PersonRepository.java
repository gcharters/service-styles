package gcc.demos.grpc.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import gcc.demos.grpc.Address;
import gcc.demos.grpc.Person;

public class PersonRepository {
	
	private static final ConcurrentHashMap<String, Person> people = new ConcurrentHashMap<String, Person>();
	private static final ConcurrentHashMap<String, Address> addresses = new ConcurrentHashMap<String, Address>();

	static {
		
		Address address = Address.newBuilder()
				                 .setNameOrNumber("14")
				                 .setLine2("Wibble Road")
				                 .setState("Hampshire")
				                 .setZip("143111")
				                 .setCountry("England")
				                 .setId(UUID.randomUUID().toString())
				                 .build();
		
		Person person = Person.newBuilder()
						      .setFirstName("Bob")
						      .setLastName("Bobson")
						      .setDob("31-12-1999")
						      .setId(UUID.randomUUID().toString())
						      .setAddressId(address.getId())
						      .build();	

		addresses.put(address.getId(), address);
		people.put(person.getId(), person);
		
		System.out.println("Person id: " + person.getId());
		System.out.println("Address id: " + address.getId());

		
		address = Address.newBuilder()
				                 .setNameOrNumber("The Hovel")
				                 .setLine2("Wobble Road")
				                 .setState("Old Hampshire")
				                 .setZip("433544")
				                 .setCountry("Someland")
				                 .setId(UUID.randomUUID().toString())
				                 .build();
		
		person = Person.newBuilder()
						      .setFirstName("Jane")
						      .setLastName("Janely")
						      .setDob("30-12-1999")
						      .setId(UUID.randomUUID().toString())
						      .setAddressId(address.getId())
						      .build();	

		addresses.put(address.getId(), address);
		people.put(person.getId(), person);

		System.out.println("Person id: " + person.getId());
		System.out.println("Address id: " + address.getId());
	}
	
	public List<Person> allPeople() {
		return new ArrayList<Person>(people.values());
	}
	
	public Person save(Person person) {
		if (person.getId() == "") {
			Person newPerson = Person.newBuilder()
								     .setFirstName(person.getFirstName())
								     .setLastName(person.getLastName())
								     .setDob(person.getDob())
								     .setId(UUID.randomUUID().toString())
								     .setAddressId(person.getAddressId())
								     .build();	
			person = newPerson;
		}
		people.put(person.getId(), person);
		return person;
	}
	
	public List<Address> allAddresses() {
		return new ArrayList<Address>(addresses.values());
	}
	
	public Address addressWithId(String id) {
		System.out.println("looking for id: " + id);
		return addresses.values().stream()
				.filter(address -> address.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
	public Address save(Address address) {
		if (address.getId() == "") {
			Address newAddress = Address.newBuilder()
							            .setNameOrNumber(address.getNameOrNumber())
							            .setLine2(address.getLine2())
							            .setState(address.getState())
							            .setZip(address.getZip())
							            .setCountry(address.getCountry())
							            .setId(UUID.randomUUID().toString())
							            .build();
			address = newAddress;
		}
		addresses.put(address.getId(), address);
		return address;
	}

	public Person personWithId(String id) {
		return people.values().stream()
				.filter(person -> person.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
	
}
