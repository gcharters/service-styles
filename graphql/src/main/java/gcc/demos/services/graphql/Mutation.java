package gcc.demos.services.graphql;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@ApplicationScoped
public class Mutation implements GraphQLMutationResolver {

	@Inject
	private  PersonRepository personRepository;
	
	public Person createPerson(String firstName, String lastName, String dob) {
		Person person = new Person(firstName, lastName, dob);
		return personRepository.save(person);
	}
	
	public Address createAddress(String nameOrNumber, String street, String line2, 
			                     String state, String zip, String country) {
		Address address = new Address(nameOrNumber, street, line2, state, zip, country);
		return personRepository.save(address);
	}
	
	public Person setAddressOfPerson(String personId, String addressId) {
		Person person = personRepository.personWithId(personId);
		person.setAddressId(addressId);
		return personRepository.save(person);
	}
	
	
}
