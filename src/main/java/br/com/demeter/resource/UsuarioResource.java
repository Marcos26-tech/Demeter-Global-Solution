package br.com.demeter.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value = "/usuario")
public class UsuarioResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getString() {
		return "teste";
	}

}
