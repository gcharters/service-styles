package gcc.demos.grpc.person;

import io.grpc.stub.StreamObserver;

import gcc.demos.grpc.Address;
import gcc.demos.grpc.AddressServiceGrpc;
import gcc.demos.grpc.repository.PersonRepository;


class AddressServiceImpl extends AddressServiceGrpc.AddressServiceImplBase {

    private static PersonRepository repository = new PersonRepository();
	  
    @Override
    public void setAddress(Address req, StreamObserver<Address> responseObserver) {

      responseObserver.onNext(repository.save(req));
      responseObserver.onCompleted();

      System.out.println("setAddress called!!!!!!!!!!!!");
    }

    @Override
    public void getAddress(Address req, StreamObserver<Address> responseObserver) {

      responseObserver.onNext(repository.addressWithId(req.getId()));
      responseObserver.onCompleted();

      System.out.println("getAddress called!!!!!!!!!!!!");
    }    
  }