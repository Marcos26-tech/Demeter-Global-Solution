package br.com.demeter.resource;

import br.com.demeter.bo.EstoqueAlimentoBO;
import br.com.demeter.to.EstoqueAlimentoTO;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.List;

@Path(value = "/estoque")
public class EstoqueAlimentoResource {

	private EstoqueAlimentoBO estoqueAlimentoBO = new EstoqueAlimentoBO();


	@GET
	@Path("/{idUsuarioLogado}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EstoqueAlimentoTO> buscar(@PathParam("idUsuarioLogado") int idUsuarioLogado) {
		return estoqueAlimentoBO.listarTodos(idUsuarioLogado);
	}

	@PUT
	@Path("/editar/{idUsuarioLogado}/{idAlimento}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualiza(EstoqueAlimentoTO estoqueAlimento, @PathParam("idUsuarioLogado") int idUsuarioLogado,
							 @PathParam("idAlimento") int idAlimento) {
		estoqueAlimento.setIdAlimento(idAlimento);
		estoqueAlimentoBO.editar(estoqueAlimento, idUsuarioLogado);
		return Response.ok().build();
	}

	@POST
	@Path("/cadastrar/{idUsuarioLogado}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(EstoqueAlimentoTO estoqueAlimentoTO, @PathParam("idUsuarioLogado") int idUsuarioLogado,
							  @Context UriInfo uriInfo) {
		estoqueAlimentoBO.inserirAlimento(estoqueAlimentoTO, idUsuarioLogado);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(estoqueAlimentoTO.getIdAlimento()));
		return Response.created(builder.build()).build();
	}

}	