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

	/**
	 * Lista todos alimentos
	 * 
	 * @return Array do tipo de um EstoqueAlimentoTO.
	 */
	public List<EstoqueAlimentoTO> listarTodos() {
		return alimentoReservadoDAO.listarTodos();
	}

	/**
	 * Mostra todos os usu�rios que s�o do tipo supermercado, por regi�o.
	 * 
	 * @param idUsuarioLogado
	 * @return
	 */
	public List<UsuarioTO> mostrarSupermercados(int idUsuarioLogado) {
		UsuarioTO regiaoUsuario = alimentoReservadoDAO.mostrarRegioaoUsuarioLogado(idUsuarioLogado);
		return alimentoReservadoDAO.mostrarSupermercados(regiaoUsuario.getRegiaoUsuario());
	}

	/**
	 * Mostra todos os Estoques de alimentos pertencente a um usu�rio.
	 * 
	 * @param idSupermercado
	 * @return Um array do tipo EstoqueAlimentoTO
	 */
	public List<EstoqueAlimentoTO> mostrarAlimentos(int idSupermercado) {
		return estoqueAlimentoDAO.listarTodos(idSupermercado);
	}

	/**
	 * M�todo validando se � possivel reservar um alimento, caso seja, retorna um
	 * true.
	 * 
	 * @param alimentoReservadoTO
	 * @param idUsuarioLogado
	 * @return Boolean, True caso seja possivel reservar o alimento, False caso n�o
	 *         seja.
	 */
	public boolean reservarAlimento(AlimentoReservadoTO alimentoReservadoTO, int idUsuarioLogado) {
		if (alimentoReservadoDAO.validaEstoque(alimentoReservadoTO)) {
			alimentoReservadoDAO.criaReservaAlimento(alimentoReservadoTO, idUsuarioLogado);
			alimentoReservadoDAO.reservaAlimento(alimentoReservadoTO);
			alimentoReservadoDAO.subtraiQuantidadeAlimento(alimentoReservadoTO);
			return true;
		} else
			return false;
	}

}
