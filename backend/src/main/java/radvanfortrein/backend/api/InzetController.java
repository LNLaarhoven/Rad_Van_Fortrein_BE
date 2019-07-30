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
import radvanfortrein.backend.model.Inzet;
import radvanfortrein.backend.service.InzetService;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("inzet")
@Api(value = "Controller Inzet objecten", produces = "application/json")
//@CrossOrigin(origins = "*")
public class InzetController {
	
	@Autowired
	InzetService inzetService;
	
	@POST
	@ApiOperation(
			value = "Create nieuwe inzet",
			response = Inzet.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Inzet toegevoegd"),
			@ApiResponse(code = 409, message = "Conflict: Verzoek create van inzet, terwijl id al bekend is")
	})
	public Response apiCreate(Inzet inzet) {
		if (inzet.getId() != 0) {
			return Response.status(Response.Status.CONFLICT).build();
		}
		return Response.ok(this.inzetService.save(inzet)).build();
	}
	
	@GET
	public Response apiGetAll() {
		return Response.ok(this.inzetService.findAll()).build();
	}
	
	@GET
	@Path("{id}")
	public Response apiGetById(@PathParam("id") long id) {
		Optional<Inzet> inzet = this.inzetService.findById(id);
		if (inzet.isPresent()) {
			return Response.ok(inzet.get()).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("{id}")
	public Response apiUpdate(@PathParam("id") long id, Inzet inzet) {
		if (inzet == null || inzet.getId() != id) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		Optional<Inzet> oldInzet = this.inzetService.findById(inzet.getId());
		if (!oldInzet.isPresent()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(this.inzetService.save(inzet)).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response apiDeleteById(@PathParam("id") long id) {
		if (!this.inzetService.findById(id).isPresent()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			this.inzetService.deleteById(id);
			return Response.status(Response.Status.OK).build();
		}
	}
}
