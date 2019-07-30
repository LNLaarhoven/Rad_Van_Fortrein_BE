package radvanfortrein.backend.api;

import java.util.Optional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.CrossOrigin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import radvanfortrein.backend.model.Game;
import radvanfortrein.backend.service.GameService;

//@CrossOrigin(origins = "*")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("game")
@Api(value = "Controller Game objecten", produces = "application/json")
public class GameController {
	
	@Autowired
	GameService gameService;
	
	@POST
	@ApiOperation(
			value = "Create nieuwe game",
			response = Game.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Game toegevoegd"),
			@ApiResponse(code = 409, message = "Conflict: Verzoek create van game, terwijl Id al bekend is")
	})
	public Response apiCreate(Game game) {
		if (game.getId() != 0) {
			return Response.status(Response.Status.CONFLICT).build();
		}
		return Response.ok(this.gameService.save(game)).build();
	}
	
	@GET
	public Response apiGetAll() {
		return Response.ok((gameService.findAll())).build();
	}
	
	@GET
	@Path("{id}")
	public Response apiGetById(@PathParam("id") long id) {
		Optional<Game> game = gameService.findById(id);
		
		if (game.isPresent()) {
			return Response.ok((game.get())).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("{id}")
	public Response apiUpdate(@PathParam("id") long id, Game game) {
		if (game == null || game.getId() != id) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		Optional<Game> oldGame = this.gameService.findById(game.getId());
		if (oldGame.isPresent()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(this.gameService.save(game)).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response apiDeleteById(@PathParam("id") long id) {
		if (!gameService.findById(id).isPresent()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			gameService.deleteById(id);
			return Response.status(Response.Status.OK).build();
		}
	}

}
