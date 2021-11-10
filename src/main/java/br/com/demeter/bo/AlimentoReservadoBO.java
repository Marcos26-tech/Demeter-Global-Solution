package br.com.demeter.bo;

import br.com.demeter.dao.AlimentoReservadoDAO;
import br.com.demeter.to.AlimentoReservadoTO;

import java.sql.SQLException;

public class AlimentoReservadoBO {

    private AlimentoReservadoDAO alimentoReservadoDAO = new AlimentoReservadoDAO();

    public void reservarAlimento(AlimentoReservadoTO alimentoReservadoTO, int idUsuarioLogado, int idAlimentoReservado) {
        try {
            alimentoReservadoDAO.reservarAlimento(alimentoReservadoTO, idUsuarioLogado, idAlimentoReservado);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
