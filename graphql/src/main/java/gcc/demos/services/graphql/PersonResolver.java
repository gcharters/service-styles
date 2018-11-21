package gcc.demos.services.graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLResolver;

public class PersonResolver implements GraphQLResolver<Person> {

	private PersonRepository personRepository;
	
	public PersonResolver(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public List<Person> allPeople() {
		return personRepository.allPeople();
	}

	public Address address(Person person) {
		return personRepository.addressWithId(person.getAddressId());
		
	}

}
