package gcc.demos.services.graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

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
