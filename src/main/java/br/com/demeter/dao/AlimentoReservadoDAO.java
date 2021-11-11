package br.com.demeter.dao;


import br.com.demeter.to.AlimentoReservadoTO;
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
                    "FROM T_DEM_USUARIO_ENDERECO E " +
                    "INNER JOIN T_DEM_USUARIO U " +
                    "ON (U.ID_USUARIO = E.ID_USUARIO) " +
                    "WHERE U.ID_USUARIO = ?";
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

    public boolean validaEstoque(AlimentoReservadoTO alimentoReservadoTO) {
        try {
            String sql = "SELECT EA.QT_ALIMENTO " +
                    "FROM T_DEM_ESTOQUE_ALIMENTO EA " +
                    "WHERE EA.ID_ESTOQUE = ? AND EA.ID_ALIMENTO = ? AND EA.QT_ALIMENTO >= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, alimentoReservadoTO.getIdEstoque());
            ps.setInt(2, alimentoReservadoTO.getIdAlimento());
            ps.setInt(3, alimentoReservadoTO.getQuantidadeAlimentoReservado());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                alimentoReservadoTO.setQuantidadeExistenteEstoque(resultSet.getInt("QT_ALIMENTO"));
                return true;
            } else return false;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void criaReservaAlimento(AlimentoReservadoTO alimentoReservadoTO, int idUsuarioLogado) {

        try {
            String sql = "INSERT INTO T_DEM_RESERVA_ALIMENTO (id_reserva, id_usuario, dt_reserva) " +
                    "VALUES (sq_dem_reserva_alimento.nextval, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuarioLogado);
            ps.setDate(2, new java.sql.Date(alimentoReservadoTO.getDataReservaAlimento().getTime()));
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void reservaAlimento(AlimentoReservadoTO alimentoReservadoTO) {
        try {
            String sql = " INSERT INTO T_DEM_ALIMENTO_RESERVADO (id_alimento_reservado, id_estoque_alimento, " +
                    "id_reserva, id_alimento, qt_alimento) " +
                    "VALUES (sq_dem_alimento_reservado.nextval, ?, sq_dem_reserva_alimento.currval, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, getIdEstoqueAlimento(alimentoReservadoTO));
            ps.setInt(2, alimentoReservadoTO.getIdAlimento());
            ps.setInt(3, alimentoReservadoTO.getQuantidadeAlimentoReservado());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private int getIdEstoqueAlimento(AlimentoReservadoTO alimentoReservadoTO) throws SQLException {

        String sql = "SELECT ID_ESTOQUE_ALIMENTO " +
                "FROM T_DEM_ESTOQUE_ALIMENTO " +
                "WHERE ID_ESTOQUE = ? AND ID_ALIMENTO = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, alimentoReservadoTO.getIdEstoque());
        ps.setInt(2, alimentoReservadoTO.getIdAlimento());
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            alimentoReservadoTO.setIdEstoqueAlimento(rs.getInt("ID_ESTOQUE_ALIMENTO"));
        }
        return alimentoReservadoTO.getIdEstoqueAlimento();
    }

    public void subtraiQuantidadeAlimento(AlimentoReservadoTO alimentoReservadoTO) {
        try {

            String sql = "UPDATE T_DEM_ESTOQUE_ALIMENTO EA " +
                    "SET EA.qt_alimento = ? " +
                    "WHERE EA.ID_ALIMENTO = ? AND EA.ID_ESTOQUE = ?";


            int valor = alimentoReservadoTO.getQuantidadeExistenteEstoque() - alimentoReservadoTO.getQuantidadeAlimentoReservado();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, valor);
            ps.setInt(2, alimentoReservadoTO.getIdAlimento());
            ps.setInt(3, alimentoReservadoTO.getIdEstoque());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    private int getQuantidadeExistente(AlimentoReservadoTO alimentoReservadoTO) throws SQLException{
//
//        String sql = "SELECT EA.QT_ALIMENTO " +
//                "FROM T_DEM_ESTOQUE_ALIMENTO EA " +
//                "WHERE EA.ID_ESTOQUE = ? AND EA.ID_ALIMENTO = ?";
//
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setInt(1, alimentoReservadoTO.getIdEstoque());
//        ps.setInt(2, alimentoReservadoTO.getIdAlimento());
//        ResultSet rs = ps.executeQuery();
//
//        while (rs.next()) {
//            alimentoReservadoTO.setIdEstoqueAlimento(rs.getInt("ID_ESTOQUE_ALIMENTO"));
//        }
//        return alimentoReservadoTO.getIdEstoqueAlimento();
//    }
}