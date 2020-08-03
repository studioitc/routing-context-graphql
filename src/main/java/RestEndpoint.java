import entity.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/peoples")
public class RestEndpoint {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeoples() {
        return Response.ok(Person.getPeoples()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/byname")
    public Response getByName(@QueryParam("name") String name) {
        return Response.ok(Person.findByName(name)).build();
    }
}
