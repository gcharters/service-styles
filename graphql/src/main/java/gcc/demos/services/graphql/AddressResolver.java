package gcc.demos.services.graphql;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.coxautodev.graphql.tools.GraphQLResolver;

@ApplicationScoped
public class AddressResolver implements GraphQLResolver<Address> {

	@Inject
	private PersonRepository personRepository;
	
	public List<Address> allAddresses() {
		return personRepository.allAddresses();
	}

}
