package br.com.demeter.dao;

import br.com.demeter.to.EstoqueAlimentoTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueAlimentoDAO {
    public List<EstoqueAlimentoTO> listarTodos(int idUsuario) {
        try {
            Connection con = ConnectionFactory.getConnection();
            String sql = "SELECT A.ID_ALIMENTO, A.nm_alimento, EA.qt_alimento, ea.dt_alimento " +
                    "FROM T_DEM_ALIMENTO A " +
                    "INNER JOIN T_DEM_ESTOQUE_ALIMENTO EA " +
                    "ON (A.ID_alimento = EA.ID_alimento) " +
                    "INNER JOIN T_DEM_ESTOQUE E " +
                    "ON (EA.ID_ESTOQUE = E.ID_ESTOQUE) " +
                    "WHERE E.id_usuario = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            List<EstoqueAlimentoTO> listaEstoqueAlimento = new ArrayList<>();
            while (rs.next()) {
                EstoqueAlimentoTO estoqueAlimentoTO = new EstoqueAlimentoTO(rs.getInt("qt_alimento"),
                        rs.getInt("ID_ALIMENTO"), rs.getString("nm_alimento"), rs.getDate("dt_alimento"));
                listaEstoqueAlimento.add(estoqueAlimentoTO);
            }
            ps.close();
            return listaEstoqueAlimento;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public EstoqueAlimentoTO listarPorId(int idAlimento, int idUsuario) {
        try {
            Connection con = ConnectionFactory.getConnection();
            String sql = "SELECT A.ID_ALIMENTO, A.nm_alimento, EA.qt_alimento, ea.dt_alimento, e.id_estoque " +
                    "FROM T_DEM_ALIMENTO A " +
                    "INNER JOIN T_DEM_ESTOQUE_ALIMENTO EA " +
                    "ON (A.ID_alimento = EA.ID_alimento) " +
                    "INNER JOIN T_DEM_ESTOQUE E " +
                    "ON (EA.ID_ESTOQUE = E.ID_ESTOQUE) " +
                    "WHERE A.ID_ALIMENTO = ? AND ID_USUARIO = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlimento);
            ps.setInt(2, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EstoqueAlimentoTO estoqueAlimentoTO = new EstoqueAlimentoTO(rs.getInt("qt_alimento"),
                        rs.getInt("ID_ALIMENTO"), rs.getString("nm_alimento"), rs.getDate("dt_alimento"),
                        rs.getInt("id_estoque"));
                return estoqueAlimentoTO;
            }
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void editar(EstoqueAlimentoTO receitaById) {
        try {
            Connection con = ConnectionFactory.getConnection();
            String sql = "UPDATE T_DEM_ESTOQUE_ALIMENTO EA " +
                    "SET EA.qt_alimento = ?, ea.dt_alimento = ? " +
                    "WHERE EA.ID_ALIMENTO = ? AND EA.ID_ESTOQUE = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, receitaById.getQuantidadeAlimento());
            ps.setDate(2, new java.sql.Date(receitaById.getDataValidadeAlimento().getTime()));
            ps.setInt(3, receitaById.getIdAlimento());
            ps.setInt(4, receitaById.getIdEstoque());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inserirAlimento(EstoqueAlimentoTO estoqueAlimentoTO, int idUsuarioLogado) {
        try {
            Connection con = ConnectionFactory.getConnection();

            String sql = "INSERT INTO T_DEM_ALIMENTO (id_alimento, nm_alimento) " +
                    "VALUES (sq_dem_alimento.nextval, ?) ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, estoqueAlimentoTO.getNomeAlimento());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getCause();
        }
        
        inserirAlimentoEstoque(estoqueAlimentoTO, getIdEstoque(estoqueAlimentoTO, idUsuarioLogado), getIdAlimentoInserido(estoqueAlimentoTO));
    }

    private void inserirAlimentoEstoque(EstoqueAlimentoTO estoqueAlimentoTO, int idEstoque, int idAlimento) {
        try {
            Connection con = ConnectionFactory.getConnection();

            String sql = "INSERT INTO T_DEM_ESTOQUE_ALIMENTO (id_estoque_alimento, id_estoque, id_alimento, qt_alimento, dt_alimento) " +
                    "VALUES (sq_dem_estoque_alimento.nextval, ?, ?, ?, ?) ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idEstoque);
            ps.setInt(2, idAlimento);
            ps.setInt(3, estoqueAlimentoTO.getQuantidadeAlimento());
            ps.setDate(4, new java.sql.Date(estoqueAlimentoTO.getDataValidadeAlimento().getTime()));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getCause();
        }

    }

    public int getIdEstoque(EstoqueAlimentoTO estoqueAlimentoTO, int idUsuarioLogado) {
        try {
            Connection con = ConnectionFactory.getConnection();
            String sql = "SELECT ID_ESTOQUE " +
                    "FROM T_DEM_ESTOQUE " +
                    "WHERE  ID_USUARIO = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuarioLogado);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	estoqueAlimentoTO.setIdEstoque(rs.getInt("ID_ESTOQUE"));
            }
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return estoqueAlimentoTO.getIdEstoque();
    }
    
    public int getIdAlimentoInserido(EstoqueAlimentoTO estoqueAlimentoTO) {
    	try {
            Connection con = ConnectionFactory.getConnection();
            String sql = "SELECT ID_ALIMENTO " +
                    "FROM T_DEM_ALIMENTO " +
                    "WHERE  NM_ALIMENTO = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, estoqueAlimentoTO.getNomeAlimento());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	estoqueAlimentoTO.setIdAlimento(rs.getInt("ID_ALIMENTO"));
            }
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return estoqueAlimentoTO.getIdAlimento();
    	
    }
}
