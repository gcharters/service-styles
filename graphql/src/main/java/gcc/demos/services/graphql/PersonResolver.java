package gcc.demos.services.graphql;

import java.util.List;
import javax.inject.Inject;

import com.coxautodev.graphql.tools.GraphQLResolver;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonResolver implements GraphQLResolver<Person> {

	@Inject
	private PersonRepository personRepository;
	
	public List<Person> allPeople() {
		return personRepository.allPeople();
	}

	public Address address(Person person) {
		return personRepository.addressWithId(person.getAddressId());
		
	}

}
