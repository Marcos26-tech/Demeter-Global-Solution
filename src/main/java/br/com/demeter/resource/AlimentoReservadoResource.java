package br.com.demeter.resource;

import br.com.demeter.bo.AlimentoReservadoBO;
import br.com.demeter.to.AlimentoReservadoTO;

import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Path(value = "/reserva")
public class AlimentoReservadoResource {

    private AlimentoReservadoBO alimentoReservadoBO = new AlimentoReservadoBO();

    @POST
    @Path("/{idUsuarioLogado}/{idAlimentoReservado}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarReservaAlimento(AlimentoReservadoTO alimentoReservadoTO, @PathParam("idUsuarioLogado") int idUsuarioLogado,
                                             @PathParam("idAlimentoReservado") int idAlimentoReservado, @Context UriInfo uriInfo) {
        alimentoReservadoBO.reservarAlimento(alimentoReservadoTO, idUsuarioLogado, idAlimentoReservado);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(alimentoReservadoTO.getQuantidadeAlimentoReservado()));
        return Response.created(builder.build()).build();
    }

}
