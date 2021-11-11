package br.com.demeter.bo;

import br.com.demeter.dao.AlimentoReservadoDAO;
import br.com.demeter.dao.EstoqueAlimentoDAO;
import br.com.demeter.to.AlimentoReservadoTO;
import br.com.demeter.to.EstoqueAlimentoTO;
import br.com.demeter.to.UsuarioTO;

import java.util.List;

public class AlimentoReservadoBO {

    private AlimentoReservadoDAO alimentoReservadoDAO = new AlimentoReservadoDAO();
    private EstoqueAlimentoDAO estoqueAlimentoDAO = new EstoqueAlimentoDAO();

    public List<EstoqueAlimentoTO> listarTodos() {
        return alimentoReservadoDAO.listarTodos();
    }

    public List<UsuarioTO> mostrarSupermercados(int idUsuarioLogado) {
        UsuarioTO regiaoUsuario = alimentoReservadoDAO.mostrarRegioaoUsuarioLogado(idUsuarioLogado);
        return alimentoReservadoDAO.mostrarSupermercados(regiaoUsuario.getRegiaoUsuario());
    }

    public List<EstoqueAlimentoTO> mostrarAlimentos(int idSupermercado) {
        return estoqueAlimentoDAO.listarTodos(idSupermercado);
    }

    public void reservarAlimento(AlimentoReservadoTO alimentoReservadoTO, int idUsuarioLogado) {
        if (alimentoReservadoDAO.validaEstoque(alimentoReservadoTO)){
            alimentoReservadoDAO.criaReservaAlimento(alimentoReservadoTO, idUsuarioLogado);
            alimentoReservadoDAO.reservaAlimento(alimentoReservadoTO);
            alimentoReservadoDAO.subtraiQuantidadeAlimento(alimentoReservadoTO);
        }

    }

}
