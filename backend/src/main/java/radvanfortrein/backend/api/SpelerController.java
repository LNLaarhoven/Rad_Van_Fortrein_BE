package radvanfortrein.backend.api;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.CrossOrigin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import radvanfortrein.backend.model.Speler;
import radvanfortrein.backend.service.SpelerService;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("speler")
@Api(value = "Controller Speler objecten", produces = "application/json")
//@CrossOrigin(origins = "*")
public class SpelerController {

	@Autowired
	SpelerService spelerService;
	
	@POST
	@ApiOperation(
			value = "Create nieuwe speler",
			response = Speler.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Speler toegevoegd"),
			@ApiResponse(code = 409, message = "Conflict: Verzoek create van Speler, terwijl id al bekend is")
	})
	public Response apiCreate(Speler speler) {
		if (speler.getId() != 0) {
			return Response.status(Response.Status.CONFLICT).build();
		}

		return Response.ok(this.spelerService.save(speler)).build();
	}
	
	@GET // Retrieve/Read
	public Response apiGetAll() {
		return Response.ok(this.spelerService.findAll()).build();
	}
	
	@GET // Retrieve/Read
	@Path("{id}")
	public Response apiGetById(@PathParam("id") long id) {
		Optional<Speler> speler = this.spelerService.findById(id);

		if (speler.isPresent()) {
			return Response.ok(speler.get()).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@PUT // Update
	@Path("{id}")
	public Response apiUpdate(@PathParam("id") long id, Speler speler) {
		if (speler == null || speler.getId() != id)
			return Response.status(Response.Status.BAD_REQUEST).build();

		Optional<Speler> oldSpeler = this.spelerService.findById(speler.getId());
		if (!oldSpeler.isPresent()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(this.spelerService.save(speler)).build();
	}
	
	@DELETE // Delete
	@Path("{id}")
	public Response apiDeleteById(@PathParam("id") long id) {
		if (this.spelerService.findById(id).isPresent() == false) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			this.spelerService.deleteById(id);
			return Response.status(Response.Status.OK).build();
		}
	}
}
