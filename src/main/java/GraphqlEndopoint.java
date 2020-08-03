import entity.Person;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@GraphQLApi
@ApplicationScoped
public class GraphqlEndopoint {

    @Query("peoples")
    @Description("Get all peolpes")
    public List<Person> getAll() {
        return Person.getPeoples();
    }

    @Query("getPeronByName")
    @Description("Get a person with given name")
    public Person getByName(String name) {
        return Person.findByName(name);
    }
}
