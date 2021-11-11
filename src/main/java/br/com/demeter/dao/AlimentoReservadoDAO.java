package br.com.demeter.dao;

import br.com.demeter.to.AlimentoReservadoTO;
import br.com.demeter.to.EstoqueAlimentoTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlimentoReservadoDAO {

    Connection con = ConnectionFactory.getConnection();

    public List<EstoqueAlimentoTO> listarTodos(){
        try {
        String sql = "SELECT A.ID_ALIMENTO, A.nm_alimento, EA.qt_alimento, ea.dt_alimento " +
                "FROM T_DEM_ALIMENTO A " +
                "INNER JOIN T_DEM_ESTOQUE_ALIMENTO EA " +
                "ON (A.ID_alimento = EA.ID_alimento) " +
                "INNER JOIN T_DEM_ESTOQUE E " +
                "ON (EA.ID_ESTOQUE = E.ID_ESTOQUE) ";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<EstoqueAlimentoTO> listaEstoqueAlimento = new ArrayList<>();
            while (rs.next()) {
                EstoqueAlimentoTO estoqueAlimentoTO = new EstoqueAlimentoTO(rs.getInt("qt_alimento"),
                        rs.getInt("ID_ALIMENTO"), rs.getString("nm_alimento"), rs.getDate("dt_alimento"));
                listaEstoqueAlimento.add(estoqueAlimentoTO);
            }
            ps.close();
            return listaEstoqueAlimento;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

//    public int reservarAlimento(AlimentoReservadoTO alimentoReservadoTO, int idUsuarioLogado, int idAlimentoReservado) throws SQLException {
//        String sql = "INSERT INTO T_DEM_USUARIO (id_usuario, nr_cnpj, nm_razao_social, ds_email, ds_senha, tp_usuario)"
//                + " VALUES (sq_dem_usuario.nextval, ?, ?, ?, ?, ?)";
//
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setLong(1, alimentoReservadoTO.getCnpjUsuario());
//        ps.setString(2, alimentoReservadoTO.getRazaoSocial());
//        ps.setString(3, alimentoReservadoTO.getEmailUusario());
//        ps.setString(4, alimentoReservadoTO.getSenhaUsuario());
//        ps.setString(5, alimentoReservadoTO.getTipoUsuario());
//
//        return ps.executeUpdate();
//    }
}