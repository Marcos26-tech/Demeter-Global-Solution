package br.com.demeter.resource;

import br.com.demeter.bo.UsuarioBO;
import br.com.demeter.to.UsuarioTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value = "/usuario")
public class UsuarioResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getString() {
		return "teste";
	}


	private UsuarioBO usuarioBO = new UsuarioBO();

	@GET
	@Path("/{email}/{senha}")
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioTO login(@PathParam("email") String mail, @PathParam("senha") String senha) {
		return usuarioBO.login(mail, senha);
	}

	}
