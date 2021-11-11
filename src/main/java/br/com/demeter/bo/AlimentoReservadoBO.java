package br.com.demeter.bo;

import br.com.demeter.dao.AlimentoReservadoDAO;
import br.com.demeter.to.AlimentoReservadoTO;
import br.com.demeter.to.EstoqueAlimentoTO;

import java.sql.SQLException;
import java.util.List;

public class AlimentoReservadoBO {

    private AlimentoReservadoDAO alimentoReservadoDAO = new AlimentoReservadoDAO();

    public List<EstoqueAlimentoTO> listarTodos() {
        return alimentoReservadoDAO.listarTodos();
    }

//    public void reservarAlimento(AlimentoReservadoTO alimentoReservadoTO, int idUsuarioLogado, int idAlimentoReservado) {
//        try {
//            alimentoReservadoDAO.reservarAlimento(alimentoReservadoTO, idUsuarioLogado, idAlimentoReservado);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
}
