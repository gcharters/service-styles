package gcc.demos.services.graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLResolver;

public class AddressResolver implements GraphQLResolver<Address> {

	private PersonRepository personRepository;
	
	public AddressResolver(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public List<Address> allAddresses() {
		return personRepository.allAddresses();
	}

}
