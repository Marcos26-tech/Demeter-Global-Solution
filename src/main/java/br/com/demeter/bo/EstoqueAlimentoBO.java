package br.com.demeter.bo;

import br.com.demeter.dao.EstoqueAlimentoDAO;
import br.com.demeter.to.EstoqueAlimentoTO;

import java.util.List;

public class EstoqueAlimentoBO {

    private EstoqueAlimentoDAO estoqueAlimentoDAO = new EstoqueAlimentoDAO();

    public List<EstoqueAlimentoTO> listarTodos(int idUsuarioLogado) {
        return estoqueAlimentoDAO.listarTodos(idUsuarioLogado);
    }
}
