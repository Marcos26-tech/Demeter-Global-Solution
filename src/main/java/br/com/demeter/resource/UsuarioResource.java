package br.com.demeter.resource;

import br.com.demeter.bo.UsuarioBO;
import br.com.demeter.to.UsuarioTO;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(value = "/usuario")
public class UsuarioResource {


	private UsuarioBO usuarioBO = new UsuarioBO();


	@GET
	@Path("/login/{email}/{senha}")
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioTO login(@PathParam("email") String mail, @PathParam("senha") String senha) {
		return usuarioBO.login(mail, senha);
	}
	
	
	@GET
    @Path("/cadastro/{email}")
   	@Consumes(MediaType.APPLICATION_JSON)
    public boolean isCadastrado(@PathParam("email") String email){
    	return usuarioBO.isCadastrado(email);
	 }
	
	 @POST
	 @Path("/cadastro")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response cadastrar(UsuarioTO usuarioTO) {
	 int result = usuarioBO.cadastrar(usuarioTO);
	 return Response.ok().build();
	    	
	    }

	}
