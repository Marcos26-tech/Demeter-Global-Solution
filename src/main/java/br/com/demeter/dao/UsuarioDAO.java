package br.com.demeter.dao;

import br.com.demeter.to.UsuarioTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    Connection con = ConnectionFactory.getConnection();

    public UsuarioTO login(String email, String senha) {

        String sql = "SELECT id_usuario, nr_cnpj, nm_razao_social, ds_email, ds_senha, tp_usuario"
                + " FROM T_DEM_USUARIO WHERE ds_email like ? and ds_senha like ?";
        PreparedStatement prepareStatement = null;
        try {
            prepareStatement = con.prepareStatement(sql);
            prepareStatement.setString(1, "%" + email + "%");
            prepareStatement.setString(2, "%" + senha + "%");
            ResultSet resultSet = prepareStatement.executeQuery();
            resultSet.next();

            UsuarioTO usuarioTO = new UsuarioTO();

            usuarioTO.setIdUsuario(resultSet.getInt("id_usuario"));
            usuarioTO.setCnpjUsuario(resultSet.getLong("nr_cnpj"));
            usuarioTO.setRazaoSocial(resultSet.getString("nm_razao_social"));
            usuarioTO.setEmailUusario(resultSet.getString("ds_email"));
            usuarioTO.setTipoUsuario(resultSet.getString("tp_usuario"));

            con.close();

            return usuarioTO;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
