package gcc.demos.grpc.person;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Empty;
import gcc.demos.grpc.Person;
import gcc.demos.grpc.Address;
import gcc.demos.grpc.PersonAndAddress;
import gcc.demos.grpc.AgeInYearsResponse;
import gcc.demos.grpc.PersonServiceGrpc;
import gcc.demos.grpc.repository.PersonRepository;


class PersonServiceImpl extends PersonServiceGrpc.PersonServiceImplBase {

    private static PersonRepository repository = new PersonRepository();

	@Override
    public void getPeople(Empty request,
            StreamObserver<Person> responseObserver) {
		
		for (Person person : repository.allPeople()) {
			responseObserver.onNext(person);
		}
	    responseObserver.onCompleted();
	      
	}
	  
    @Override
    public void setPerson(Person req, StreamObserver<Person> responseObserver) {

      responseObserver.onNext(repository.save(req));
      responseObserver.onCompleted();

      System.out.println("setPerson called!!!!!!!!!!!!");
    }

    @Override
    public void getPerson(Person req, StreamObserver<Person> responseObserver) {

      responseObserver.onNext(repository.personWithId(req.getId()));
      responseObserver.onCompleted();

      System.out.println("getPerson called!!!!!!!!!!!!");
    }

    @Override
    public void getAddress(Person req,
        StreamObserver<Address> responseObserver) {

        responseObserver.onNext(repository.addressWithId(req.getAddressId()));
        responseObserver.onCompleted();
    }

    @Override
    public void setPersonAndAddress(PersonAndAddress req,
        StreamObserver<PersonAndAddress> responseObserver) {

        Address address = repository.save(req.getAddress());

        Person person = req.getPerson();
        Person tmpPerson = Person.newBuilder()
                                 .setFirstName(person.getFirstName())
                                 .setLastName(person.getLastName())
                                 .setDob(person.getDob())
                                 .setAddressId(address.getId())
                                 .build();	

        Person newPerson = repository.save(tmpPerson);

        PersonAndAddress pwa = PersonAndAddress.newBuilder()
                                               .setPerson(newPerson)
                                               .setAddress(address)
                                               .build();

        responseObserver.onNext(pwa);
        responseObserver.onCompleted();
    }

    @Override
    public void getPersonAndAddress(Person req,
        StreamObserver<PersonAndAddress> responseObserver) {

        Person person = repository.personWithId(req.getId());
        Address address = repository.addressWithId(req.getAddressId());

        PersonAndAddress pwa = PersonAndAddress.newBuilder()
            .setPerson(person)
            .setAddress(address)
            .build();

        responseObserver.onNext(pwa);
        responseObserver.onCompleted();
    }

    @Override
    public void getAgeInYears(Person req,
        StreamObserver<AgeInYearsResponse> responseObserver) {

        int ageYears = 42;
        AgeInYearsResponse age = AgeInYearsResponse.newBuilder()
                                                   .setAge(ageYears)
                                                   .build();
        responseObserver.onNext(age);
        responseObserver.onCompleted();
    }

  }