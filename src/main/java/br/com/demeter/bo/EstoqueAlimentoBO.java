package br.com.demeter.bo;

import br.com.demeter.dao.EstoqueAlimentoDAO;
import br.com.demeter.to.EstoqueAlimentoTO;

import java.sql.SQLException;
import java.util.List;

public class EstoqueAlimentoBO {

    private EstoqueAlimentoDAO estoqueAlimentoDAO = new EstoqueAlimentoDAO();

    public List<EstoqueAlimentoTO> listarTodos(int idUsuarioLogado) {
        return estoqueAlimentoDAO.listarTodos(idUsuarioLogado);
    }

	public void editar(EstoqueAlimentoTO estoqueAlimento, int idUsuarioLogado) {
		EstoqueAlimentoTO receitaById = validaAlimento(estoqueAlimento, estoqueAlimentoDAO.listarPorId(estoqueAlimento.getIdAlimento(), idUsuarioLogado));
        estoqueAlimentoDAO.editar(receitaById);
	}

    private EstoqueAlimentoTO validaAlimento(EstoqueAlimentoTO request, EstoqueAlimentoTO receitaDoBanco) {
    	EstoqueAlimentoTO estoqueAlimentoTO = new EstoqueAlimentoTO();
        estoqueAlimentoTO.setIdAlimento(request.getIdAlimento());
        estoqueAlimentoTO.setIdEstoque(receitaDoBanco.getIdEstoque());
        if (request.getQuantidadeAlimento() == 0) {
            estoqueAlimentoTO.setQuantidadeAlimento(receitaDoBanco.getQuantidadeAlimento());
        } else {
            estoqueAlimentoTO.setQuantidadeAlimento(request.getQuantidadeAlimento());
        }
        if (request.getDataValidadeAlimento() == null) {
            estoqueAlimentoTO.setDataValidadeAlimento(receitaDoBanco.getDataValidadeAlimento());
        } else {
            estoqueAlimentoTO.setDataValidadeAlimento(request.getDataValidadeAlimento());
        }

        return estoqueAlimentoTO;
    }

    public void inserirAlimento(EstoqueAlimentoTO estoqueAlimentoTO, int idUsuarioLogado) {
        try {
            estoqueAlimentoDAO.inserirAlimento(estoqueAlimentoTO, idUsuarioLogado);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
