package gcc.demos.services.graphql;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PersonRepository {
	
	private static final ConcurrentHashMap<String, Person> people = new ConcurrentHashMap<String, Person>();
	
	static {
		Person person = new Person("Bob", "Bobson", "31-12-1999");
		person.setId(UUID.randomUUID().toString());
		people.put(person.getId(), person);
		
		person = new Person("Jane", "Janely", "30-12-1999");
		person.setId(UUID.randomUUID().toString());
		people.put(person.getId(), person);
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
	
}
