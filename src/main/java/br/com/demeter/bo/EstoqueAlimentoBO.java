package br.com.demeter.bo;

import br.com.demeter.dao.EstoqueAlimentoDAO;
import br.com.demeter.to.EstoqueAlimentoTO;

import java.sql.SQLException;
import java.util.List;

public class EstoqueAlimentoBO {

	private EstoqueAlimentoDAO estoqueAlimentoDAO = new EstoqueAlimentoDAO();

	/*
	 * Chama o m�todo da ClasseDao que busca todos alimentos do usu�rio que est�
	 * logado na platarforma web.
	 */
	public List<EstoqueAlimentoTO> listarTodos(int idUsuarioLogado) {
		return estoqueAlimentoDAO.listarTodos(idUsuarioLogado);
	}

	/**
	 * M�todo para editar um alimento
	 * 
	 * @param estoqueAlimento
	 * @param idUsuarioLogado
	 */
	public void editar(EstoqueAlimentoTO estoqueAlimento, int idUsuarioLogado) {
		EstoqueAlimentoTO receitaById = validaAlimento(estoqueAlimento,
				estoqueAlimentoDAO.listarPorId(estoqueAlimento.getIdAlimento(), idUsuarioLogado));
		estoqueAlimentoDAO.editarAlimento(receitaById);
	}

	/*
	 * M�todo para validar quais campos do alimento foram atualizados, e mantendo os
	 * que n�o foram atualizados.
	 */
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

	/**
	 * M�todo para inserir um alimento validando se ele j� existe e se j� est� no
	 * estoque.
	 * 
	 * @param estoqueAlimentoTO
	 * @param idUsuarioLogado
	 */
	public void inserirAlimento(EstoqueAlimentoTO estoqueAlimentoTO, int idUsuarioLogado) {
		try {
			if (!estoqueAlimentoDAO.isAlimentoCadastrado(estoqueAlimentoTO)) {
				estoqueAlimentoDAO.inserirAlimento(estoqueAlimentoTO, idUsuarioLogado);
			}
			if (!estoqueAlimentoDAO.isAlimentoEstoque(estoqueAlimentoTO, idUsuarioLogado)) {
				estoqueAlimentoDAO.inserirAlimentoEstoque(estoqueAlimentoTO, idUsuarioLogado);
			}
			if ((estoqueAlimentoDAO.isAlimentoCadastrado(estoqueAlimentoTO))
					&& (estoqueAlimentoDAO.isAlimentoEstoque(estoqueAlimentoTO, idUsuarioLogado))) {
				editar(estoqueAlimentoTO, idUsuarioLogado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
