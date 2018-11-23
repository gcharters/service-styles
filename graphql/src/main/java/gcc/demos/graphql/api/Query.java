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
	
	public List<Person> allPeople() {
		return personRepository.allPeople();
	}
	
	public List<Address> allAddresses() {
		return personRepository.allAddresses();
	}
	
	
}
