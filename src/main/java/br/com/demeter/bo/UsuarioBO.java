package br.com.demeter.bo;

import br.com.demeter.dao.UsuarioDAO;
import br.com.demeter.to.UsuarioTO;


public class UsuarioBO {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();


    public UsuarioTO login(String mail, String senha){
        return usuarioDAO.login(mail, senha);

    }

}
