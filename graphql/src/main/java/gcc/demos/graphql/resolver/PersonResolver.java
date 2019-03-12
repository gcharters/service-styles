package gcc.demos.graphql.resolver;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLResolver;

import gcc.demos.graphql.model.Address;
import gcc.demos.graphql.model.Person;
import gcc.demos.graphql.repository.PersonRepository;

public class PersonResolver implements GraphQLResolver<Person> {

	private PersonRepository personRepository;
	
	public PersonResolver(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Address address(Person person) {
		return personRepository.addressWithId(person.getAddressId());
		
	}

}
