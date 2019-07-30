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
import radvanfortrein.backend.model.Station;
import radvanfortrein.backend.service.StationService;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("station")
@Api(value = "Controller Station objecten", produces = "application/json")
//@CrossOrigin(origins = "*")
public class StationController {
	
	@Autowired
	StationService stationService;
	
	@GET
	public Response apiGetAll() {
		return Response.ok(this.stationService.findAll()).build();
	}
	
	@GET
	@Path("{code}")
	public Response apiGetById(@PathParam("code") String code) {
		Optional<Station> station = this.stationService.findById(code);

		if (station.isPresent()) {
			return Response.ok(station.get()).build();			
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
}
