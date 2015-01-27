package boundary;

import entity.Inscription;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Stateless
@Path("inscriptions")
public class InscriptionsResources {
    
    @Inject
    Inscriptions inscriptions;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response inscriptions() {
        List<Inscription> liste = inscriptions.findAll();
        GenericEntity<List<Inscription>> resultat = new GenericEntity<List<Inscription>>(liste) {
        };
        return Response.ok(resultat).build();
    }
    
    @GET
    @Path("{id}")
    public Response find(@PathParam("id") long idInscription) {
        Inscription inscription = inscriptions.find(idInscription);
        if (inscription != null) {
            return Response.ok(inscription).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @POST
    public Response enregistre(Inscription request, @Context UriInfo info) {
        Inscription inscription = inscriptions.enregistre(request);
        long id = inscription.getId();
        URI uri = info.getAbsolutePathBuilder().path("/"+id).build();
        JsonObject confirmation = Json.createObjectBuilder().
                add("prix",inscription.getPrixTotal()).
                add("confirmation-id",inscription.getId()).build();
        return Response.created(uri).entity(confirmation).build();
    }
}
