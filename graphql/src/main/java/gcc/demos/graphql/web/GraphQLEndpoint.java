package gcc.demos.graphql.web;

import javax.servlet.annotation.WebServlet;

import com.coxautodev.graphql.tools.SchemaParser;

import gcc.demos.graphql.api.Mutation;
import gcc.demos.graphql.api.Query;
import gcc.demos.graphql.repository.PersonRepository;
import gcc.demos.graphql.resolver.AddressResolver;
import gcc.demos.graphql.resolver.PersonResolver;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLConfiguration;
import graphql.servlet.SimpleGraphQLHttpServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLHttpServlet {

	private static final long serialVersionUID = -5981232340854324385L;
	
	private static final PersonRepository personRepository;
	
    static {
        personRepository = new PersonRepository();
    }
    
    public GraphQLEndpoint() {
    }
    
    
    @Override
    protected GraphQLConfiguration getConfiguration() {
      return GraphQLConfiguration.with(createSchema()).build();
    }

    private static GraphQLSchema createSchema() {
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(
                        new Query(personRepository),
                        new Mutation(personRepository),
                        new PersonResolver(personRepository),
                        new AddressResolver(personRepository)
                        )
                .build()
                .makeExecutableSchema();
    } 
}