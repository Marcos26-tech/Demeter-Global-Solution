package br.com.demeter.resource;

import br.com.demeter.bo.EstoqueAlimentoBO;
import br.com.demeter.to.EstoqueAlimentoTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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


}