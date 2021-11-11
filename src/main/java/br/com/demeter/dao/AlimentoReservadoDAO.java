package br.com.demeter.dao;


import br.com.demeter.to.EstoqueAlimentoTO;
import br.com.demeter.to.UsuarioTO;

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

    public List<UsuarioTO> mostrarSupermercados(String regiao) {
        try {
            String sql = "SELECT U.NM_RAZAO_SOCIAL, U.ID_USUARIO " +
                    "FROM T_DEM_USUARIO U " +
                    "INNER JOIN T_DEM_USUARIO_ENDERECO E " +
                    "ON (U.ID_USUARIO = E.ID_USUARIO) " +
                    "WHERE U.TP_USUARIO = 'supermercado' AND E.NM_REGIAO = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, regiao);
            ResultSet rs = ps.executeQuery();
            List<UsuarioTO> listaSupermercados = new ArrayList<>();
            while (rs.next()) {
                UsuarioTO usuario = new UsuarioTO(rs.getInt("ID_USUARIO"),
                        rs.getString("NM_RAZAO_SOCIAL"));
                listaSupermercados.add(usuario);
            }
            ps.close();
            return listaSupermercados;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public UsuarioTO mostrarRegioaoUsuarioLogado(int idUsuarioLogado){
        try {
            String sql = " SELECT E.NM_REGIAO " +
                    "    FROM T_DEM_USUARIO_ENDERECO E " +
                    "    INNER JOIN T_DEM_USUARIO U " +
                    "    ON (U.ID_USUARIO = E.ID_USUARIO) " +
                    "    WHERE U.ID_USUARIO = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuarioLogado);
            ResultSet rs = ps.executeQuery();
            rs.next();
            UsuarioTO usuarioTO = new UsuarioTO(rs.getString("NM_REGIAO"));

            ps.close();
            return usuarioTO;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


//    public int reservarAlimento(AlimentoReservadoTO alimentoReservadoTO, int idUsuarioLogado, int idAlimentoReservado) throws SQLException {
//        String sql = "SELECT A.ID_ALIMENTO "ID", A.nm_alimento, EA.qt_alimento, ea.dt_alimento
//FROM T_DEM_ALIMENTO A
//INNER JOIN T_DEM_ESTOQUE_ALIMENTO EA
//ON (A.ID_alimento = EA.ID_alimento)
//INNER JOIN T_DEM_ESTOQUE E
//ON (EA.ID_ESTOQUE = E.ID_ESTOQUE)
//INNER JOIN T_DEM_USUARIO U
//ON (E.ID_USUARIO = U.ID_USUARIO)
//INNER JOIN T_DEM_USUARIO_ENDERECO END
//ON (U.ID_USUARIO = END.ID_USUARIO)
//WHERE END.NM_REGIAO = 'sul';";
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