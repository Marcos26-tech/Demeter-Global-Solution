package br.com.demeter.bo;

import java.sql.SQLException;

import br.com.demeter.dao.UsuarioDAO;
import br.com.demeter.to.UsuarioTO;


public class UsuarioBO {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();


    public UsuarioTO login(String mail, String senha){
        return usuarioDAO.login(mail, senha);
    }
    
	public boolean isCadastrado(String email) {

		try {

			if (usuarioDAO.isCadastrado(email)) {
				return true;
			} else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	
public int cadastrar(UsuarioTO usuarioTO) {
    	
    	int cadastrar = 0;
		try {
			cadastrar = usuarioDAO.cadastrarUsuario(usuarioTO);
			usuarioDAO.cadastrarRegiao(usuarioTO);
			if (usuarioTO.getTipoUsuario().equals("supermercado")) usuarioDAO.cadastrarEstoque();
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
    	return cadastrar;
    }

}
