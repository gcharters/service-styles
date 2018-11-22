package gcc.demos.services.graphql;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;

import com.coxautodev.graphql.tools.SchemaParser;

import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLConfiguration;
import graphql.servlet.SimpleGraphQLHttpServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLHttpServlet {

	private static final long serialVersionUID = -5981232340854324385L;
	
	@Inject
	private Query query;
	
	@Inject 
	private Mutation mutation;
	
	@Inject
	PersonResolver personResolver;
	
	@Inject
	AddressResolver addressResolver;
	
	
    
    public GraphQLEndpoint() {
    }
    
    
    @Override
    protected GraphQLConfiguration getConfiguration() {
      return GraphQLConfiguration.with(createSchema()).build();
    }

    private /*static*/ GraphQLSchema createSchema() {
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(
/*                        new Query(personRepository),
                        new Mutation(personRepository),
                        new PersonResolver(personRepository),
                        new AddressResolver(personRepository)*/
                        query,
                        mutation, 
                        personResolver,
                        addressResolver
                        )
                .build()
                .makeExecutableSchema();
    } 
}