package gcc.demos.graphql.resolver;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLResolver;

import gcc.demos.graphql.model.Address;
import gcc.demos.graphql.repository.PersonRepository;

public class AddressResolver implements GraphQLResolver<Address> {

	private PersonRepository personRepository;
	
	public AddressResolver(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public List<Address> allAddresses() {
		return personRepository.allAddresses();
	}

}
