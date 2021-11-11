package br.com.demeter.resource;

import br.com.demeter.bo.UsuarioBO;
import br.com.demeter.to.UsuarioTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

@Path(value = "/usuario")
public class UsuarioResource {


	private UsuarioBO usuarioBO = new UsuarioBO();


	@GET
	@Path("/login/{email}/{senha}")
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioTO login(@PathParam("email") String mail, @PathParam("senha") String senha) {
		return usuarioBO.login(mail, senha);
	}

	@POST
	@Path("/cadastro")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(UsuarioTO usuarioTO, @Context UriInfo uriInfo) {
		int resposta = usuarioBO.cadastrar(usuarioTO);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(resposta));
		return Response.created(builder.build()).build();
	}

}
