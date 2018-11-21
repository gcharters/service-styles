package gcc.demos.services.graphql;

import javax.servlet.annotation.WebServlet;

import com.coxautodev.graphql.tools.SchemaParser;

import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLConfiguration;
import graphql.servlet.SimpleGraphQLHttpServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLHttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5981232340854324385L;
	
	private static final PersonRepository personRepository;
    private static final AddressRepository addressRepository;
	
    static {
        personRepository = new PersonRepository();
        addressRepository = new AddressRepository();
    }
    
    public GraphQLEndpoint() {
        //buildSchema();
    }
    
    
    @Override
    protected GraphQLConfiguration getConfiguration() {
      return GraphQLConfiguration.with(createSchema()).build();
    }
    
	/*
    public GraphQLEntryPoint(PostRepository postRepository, AuthorRepository authRepository, CommentRepository commentRepository) {
        super(buildSchema(postRepository,
                          authRepository,
                          commentRepository));
    }*/

    private static GraphQLSchema createSchema() {
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(
                        new Query(personRepository, addressRepository),
                        new Mutation(personRepository, addressRepository),
                        new PersonResolver(personRepository, addressRepository),
                        new AddressResolver(addressRepository)
                        )
                //.scalars(Scalars.dateTime)
                .build()
                .makeExecutableSchema();
    } 
}