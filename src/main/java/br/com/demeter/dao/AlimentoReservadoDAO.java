package br.com.demeter.dao;

import br.com.demeter.to.AlimentoReservadoTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlimentoReservadoDAO {

    Connection con = ConnectionFactory.getConnection();

    public int reservarAlimento(AlimentoReservadoTO alimentoReservadoTO, int idUsuarioLogado, int idAlimentoReservado) throws SQLException {
        String sql = "INSERT INTO T_DEM_USUARIO (id_usuario, nr_cnpj, nm_razao_social, ds_email, ds_senha, tp_usuario)"
                + " VALUES (sq_dem_usuario.nextval, ?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, alimentoReservadoTO.getCnpjUsuario());
        ps.setString(2, alimentoReservadoTO.getRazaoSocial());
        ps.setString(3, alimentoReservadoTO.getEmailUusario());
        ps.setString(4, alimentoReservadoTO.getSenhaUsuario());
        ps.setString(5, alimentoReservadoTO.getTipoUsuario());

        return ps.executeUpdate();
    }
}
