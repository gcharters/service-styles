package gcc.demos.services.graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLResolver;

public class PersonResolver implements GraphQLResolver<Person> {

	private PersonRepository personRepository;
	private AddressRepository addressRepository;
	
	public PersonResolver(PersonRepository personRepository, AddressRepository addressRepository) {
		this.personRepository = personRepository;
		this.addressRepository = addressRepository;
	}
	
	public List<Person> allPeople() {
		return personRepository.allPeople();
	}
	
	public Address getAddress(Person person) {
		return addressRepository.addressWithId(person.getId());
		
	}

}
