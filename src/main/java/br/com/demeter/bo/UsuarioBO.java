package br.com.demeter.bo;

import java.sql.SQLException;

import br.com.demeter.dao.UsuarioDAO;
import br.com.demeter.to.UsuarioTO;

public class UsuarioBO {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	/*
	 * Método para fazer login.
	 */
	public UsuarioTO login(String mail, String senha) {
		return usuarioDAO.login(mail, senha);
	}

	/*
	 * Método validando se um usuario pode se cadastrar ou não. Caso o email
	 * informado já está cadastrado no banco de dados, não será possivel realizar o
	 * cadastro. Além de validar se o usuário é do tipo entidade ou supermercado.
	 */
	public int cadastrar(UsuarioTO usuarioTO) {
		int cadastrar = 0;
		try {
			if (!usuarioDAO.isCadastrado(usuarioTO.getEmailUusario())) {
				cadastrar = usuarioDAO.cadastrarUsuario(usuarioTO);
				usuarioDAO.cadastrarRegiao(usuarioTO);
				if (usuarioTO.getTipoUsuario().equals("supermercado"))
					usuarioDAO.cadastrarEstoque();
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return cadastrar;
	}

}
