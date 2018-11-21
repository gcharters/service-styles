package gcc.demos.services.graphql;


import com.coxautodev.graphql.tools.GraphQLMutationResolver;

public class Mutation implements GraphQLMutationResolver {

	private final PersonRepository personRepository;
	
	public Mutation(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public Person createPerson(String firstName, String lastName, String dob) {
		Person person = new Person(firstName, lastName, dob);
		return personRepository.save(person);
	}
	
	public Address createAddress(String nameOrNumber, String street, String line2, 
			                     String state, String zip, String country) {
		Address address = new Address(nameOrNumber, street, line2, state, zip, country);
		return personRepository.save(address);
	}
	
	
}
