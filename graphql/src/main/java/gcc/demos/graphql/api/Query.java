package gcc.demos.graphql.api;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import gcc.demos.graphql.model.Address;
import gcc.demos.graphql.model.Person;
import gcc.demos.graphql.repository.PersonRepository;

public class Query implements GraphQLQueryResolver {

	private final PersonRepository personRepository;
	
	public Query (PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public List<Person> people() {
		return personRepository.allPeople();
	}
	
	public List<Address> addresses() {
		return personRepository.allAddresses();
	}
	
	public Person person(String id) {
		return personRepository.personWithId(id);
	}

	public Address address(String id) {
		return personRepository.addressWithId(id);
	}
	
}
