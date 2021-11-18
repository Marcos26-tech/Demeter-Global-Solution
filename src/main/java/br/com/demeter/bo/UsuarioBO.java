package br.com.demeter.bo;

import java.sql.SQLException;

import br.com.demeter.dao.UsuarioDAO;
import br.com.demeter.to.UsuarioTO;

public class UsuarioBO {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	/*
	 * M�todo para fazer login.
	 */
	public UsuarioTO login(String mail, String senha) {
		return usuarioDAO.login(mail, senha);
	}

	/*
	 * M�todo validando se um usuario pode se cadastrar ou n�o. Caso o email
	 * informado j� est� cadastrado no banco de dados, n�o ser� possivel realizar o
	 * cadastro. Al�m de validar se o usu�rio � do tipo entidade ou supermercado.
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
