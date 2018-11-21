package gcc.demos.services.graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLResolver;

public class AddressResolver implements GraphQLResolver<Address> {

	private AddressRepository addressRepository;
	
	public AddressResolver(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	public List<Address> allAddresses() {
		return addressRepository.allAddresses();
	}

}
