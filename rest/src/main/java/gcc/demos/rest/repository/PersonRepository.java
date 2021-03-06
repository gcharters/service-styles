package gcc.demos.rest.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import gcc.demos.rest.model.Address;
import gcc.demos.rest.model.Person;

public class PersonRepository {
	
	private static final ConcurrentHashMap<String, Person> people = new ConcurrentHashMap<String, Person>();
	private static final ConcurrentHashMap<String, Address> addresses = new ConcurrentHashMap<String, Address>();

	static {
		Person person = new Person("Bob", "Bobson", "31-12-1999");
		person.setId(UUID.randomUUID().toString());
		Address address = new Address("14", "Wibble Road", "", "Hampshire", "14311", "England");
		address.setId(UUID.randomUUID().toString());
		addresses.put(address.getId(), address);
		person.setAddressId(address.getId());
		people.put(person.getId(), person);
		
		System.out.println("Address id: " + address.getId());

		
		person = new Person("Jane", "Janely", "30-12-1999");
		person.setId(UUID.randomUUID().toString());
		address = new Address("The Hovel", "Wobble Road", "", "Old Hampshire", "43354", "Someland");
		address.setId(UUID.randomUUID().toString());		
		addresses.put(address.getId(), address);
		person.setAddressId(address.getId());
		people.put(person.getId(), person);

		System.out.println("Address id: " + address.getId());
	}
	
	public List<Person> allPeople() {
		return new ArrayList<Person>(people.values());
	}
	
	public Person save(Person person) {
		if (person.getId() == "")
			person.setId(UUID.randomUUID().toString());
		people.put(person.getId(), person);
		return person;
	}
	
	
	public Person update(Person person) {
		if (personWithId(person.getId()) == null)
			return null;
		
		people.put(person.getId(), person);
		return person;
	}
	

	public Person personWithId(String id) {
		return people.values().stream()
				.filter(person -> person.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
	
	public List<Person> personWithLastName(String lastName) {
		return people.values().stream()
				.filter(person -> person.getLastName().equals(lastName))
				.collect(Collectors.toList());
	}

	public Person delete(String personId) {
		return people.remove(personId);
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
		if (address.getId() == "")
			address.setId(UUID.randomUUID().toString());
		addresses.put(address.getId(), address);
		return address;
	}
	
}
