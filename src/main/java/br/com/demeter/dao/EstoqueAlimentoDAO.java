package br.com.demeter.dao;

import br.com.demeter.to.EstoqueAlimentoTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
