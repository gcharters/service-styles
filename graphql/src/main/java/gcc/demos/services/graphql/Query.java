package gcc.demos.services.graphql;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@ApplicationScoped
public class Query implements GraphQLQueryResolver {

	@Inject
	private PersonRepository personRepository;

	
	public List<Person> allPeople() {
		return personRepository.allPeople();
	}
	
	public List<Address> allAddresses() {
		return personRepository.allAddresses();
	}
	
	
}
