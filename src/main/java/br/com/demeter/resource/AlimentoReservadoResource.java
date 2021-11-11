package br.com.demeter.resource;

import br.com.demeter.bo.AlimentoReservadoBO;
import br.com.demeter.to.AlimentoReservadoTO;
import br.com.demeter.to.EstoqueAlimentoTO;
import br.com.demeter.to.UsuarioTO;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;


@Path(value = "/reserva")
public class AlimentoReservadoResource {

    private AlimentoReservadoBO alimentoReservadoBO = new AlimentoReservadoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstoqueAlimentoTO> buscar() {
        return alimentoReservadoBO.listarTodos();
    }

    @GET
    @Path("/{idUsuarioLogado}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsuarioTO> mostrarSupermercados(@PathParam("idUsuarioLogado") int idUsuarioLogado) {
        return alimentoReservadoBO.mostrarSupermercados(idUsuarioLogado);
    }

    @GET
    @Path("/alimentos/{idSupermercado}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstoqueAlimentoTO> mostrarAlimentos(@PathParam("idSupermercado") int idSupermercado) {
        return alimentoReservadoBO.mostrarAlimentos(idSupermercado);
    }

    @POST
    @Path("/reservar/{idUsuarioLogado}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response reservarAlimento(AlimentoReservadoTO alimentoReservadoTO, @PathParam("idUsuarioLogado") int idUsuarioLogado,
                                     @Context UriInfo uriInfo) {
        alimentoReservadoBO.reservarAlimento(alimentoReservadoTO, idUsuarioLogado);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(alimentoReservadoTO.getQuantidadeAlimentoReservado()));
        return Response.created(builder.build()).build();
    }

}
