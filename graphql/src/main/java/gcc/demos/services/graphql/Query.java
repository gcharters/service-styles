package gcc.demos.services.graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

public class Query implements GraphQLQueryResolver {

	private final PersonRepository personRepository;
	private final AddressRepository addressRepository;
	
	public Query (PersonRepository personRepository, AddressRepository addressRepository) {
		this.personRepository = personRepository;
		this.addressRepository = addressRepository;
	}
	
	public List<Person> allPeople() {
		return personRepository.allPeople();
	}
	
	public List<Address> allAddresses() {
		return addressRepository.allAddresses();
	}
	
	
}
