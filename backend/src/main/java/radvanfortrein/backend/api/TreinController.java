package radvanfortrein.backend.api;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.CrossOrigin;

import io.swagger.annotations.Api;
import radvanfortrein.backend.model.Trein;
import radvanfortrein.backend.service.TreinService;

//@CrossOrigin(origins = "*")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("trein")
@Api(value = "Controller Trein objecten", produces = "application/json")
public class TreinController {
	
	@Autowired
	TreinService treinService;
	
	@GET // Retrieve/Read
	public Response apiGetAll() {
		return Response.ok(this.treinService.findAll()).build();
	}
	
	@GET // Retrieve/Read
	@Path("{naam}")
	public Response apiGetById(@PathParam("naam") String naam) {
		Optional<Trein> trein = this.treinService.findById(naam);

		if (trein.isPresent()) {
			return Response.ok(trein.get()).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
}
