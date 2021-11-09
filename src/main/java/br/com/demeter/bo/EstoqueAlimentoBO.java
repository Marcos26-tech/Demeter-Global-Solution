package br.com.demeter.bo;

import br.com.demeter.dao.EstoqueAlimentoDAO;
import br.com.demeter.to.EstoqueAlimentoTO;

import java.util.List;

public class EstoqueAlimentoBO {

    private EstoqueAlimentoDAO estoqueAlimentoDAO = new EstoqueAlimentoDAO();

    public List<EstoqueAlimentoTO> listarTodos(int idUsuarioLogado) {
        return estoqueAlimentoDAO.listarTodos(idUsuarioLogado);
    }

	public void editar(EstoqueAlimentoTO estoque, int idUsuarioLogado) {
		EstoqueAlimentoTO receitaById = validaAlimento(estoque, estoqueAlimentoDAO.listarPorId(estoque.getIdAlimento(), idUsuarioLogado));
        estoqueAlimentoDAO.editar(receitaById);
	}

    private EstoqueAlimentoTO validaAlimento(EstoqueAlimentoTO request, EstoqueAlimentoTO receitaDoBanco) {
    	EstoqueAlimentoTO alimento = new EstoqueAlimentoTO();
        alimento.setIdAlimento(request.getIdAlimento());
        alimento.setIdEstoque(receitaDoBanco.getIdEstoque());
        if (request.getQuantidadeAlimento() == 0) {
            alimento.setQuantidadeAlimento(receitaDoBanco.getQuantidadeAlimento());
        } else {
            alimento.setQuantidadeAlimento(request.getQuantidadeAlimento());
        }
        if (request.getDataValidadeAlimento() == null) {
            alimento.setDataValidadeAlimento(receitaDoBanco.getDataValidadeAlimento());
        } else {
            alimento.setDataValidadeAlimento(request.getDataValidadeAlimento());
        }

        return alimento;
    }

}
