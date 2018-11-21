package gcc.demos.services.graphql;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AddressRepository {

	private static int id = 0;
	
	private static final ConcurrentHashMap<String, Address> addresses = new ConcurrentHashMap<String, Address>();
	
	static {
		Address address = new Address("14", "", "Wibble Road", "Hampshire", "14311", "England");
		address.setId(String.valueOf(id++));
		addresses.put(address.getId(), address);

		address = new Address("The Hovel", "", "Wobble Road", "Old Hampshire", "43354", "Someland");
		address.setId(String.valueOf(id++));		
		addresses.put(address.getId(), address);
	}
	
	public List<Address> allAddresses() {
		return new ArrayList<Address>(addresses.values());
	}
	
	public Address addressWithId(String id) {
		return addresses.values().stream()
				.filter(address -> address.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
	
	public Address save(Address address) {
		if (address.getId() == "")
			address.setId(UUID.randomUUID().toString());
		addresses.put(address.getId(), address);
		return address;
	}
	
}
