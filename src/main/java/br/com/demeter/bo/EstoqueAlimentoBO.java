package br.com.demeter.bo;

import br.com.demeter.dao.EstoqueAlimentoDAO;
import br.com.demeter.to.EstoqueAlimentoTO;
import br.com.fiap.resource.to.UltimaCeiaTO;

import java.util.List;

public class EstoqueAlimentoBO {

    private EstoqueAlimentoDAO estoqueAlimentoDAO = new EstoqueAlimentoDAO();

    public List<EstoqueAlimentoTO> listarTodos(int idUsuarioLogado) {
        return estoqueAlimentoDAO.listarTodos(idUsuarioLogado);
    }

	public void editar(EstoqueAlimentoTO estoque) {
		var receitaById = validaUltimaCeia(uct, ucdao.listarPorId(uct.getId()));
		ucdao.editar(receitaById);
	}

}
