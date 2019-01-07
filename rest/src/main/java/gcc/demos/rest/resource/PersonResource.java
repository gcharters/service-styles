package gcc.demos.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import gcc.demos.rest.repository.PersonRepository;
import gcc.demos.rest.model.Address;
import gcc.demos.rest.model.Person;

import java.util.List;
import javax.inject.Inject;
import javax.enterprise.context.RequestScoped;


@Path("/person")
@RequestScoped
public class PersonResource {

    //@Inject
    PersonRepository personRepo = new PersonRepository();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> people() {
        return personRepo.allPeople();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person personWithId(@PathParam("id") String id) {
        return personRepo.personWithId(id);
    }
    
    @GET
    @Path("/{id}/address")
    @Produces(MediaType.APPLICATION_JSON)
    public Address addressOfPersonWithId(@PathParam("id") String id) {
    	Person person = personRepo.personWithId(id);
    	return personRepo.addressWithId(person.getAddressId());
    }    
    
    @POST
    @Path("/{personId}/address/{addressId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Person setAddressOfPerson(@PathParam("personId") String personId, @PathParam("addressId") String addressId) {
		Person person = personRepo.personWithId(personId);
		person.setAddressId(addressId);
		return personRepo.save(person);
    }

}