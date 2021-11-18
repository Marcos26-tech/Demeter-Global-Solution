package br.com.demeter.dao;

import br.com.demeter.to.EstoqueAlimentoTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueAlimentoDAO {

	Connection con = ConnectionFactory.getConnection();

	/**
	 * Método para buscar todos os Estoques de alimentos pertencente a um usuário.
	 * 
	 * @param idUsuario
	 * @return Um Array com os estoques do usuário.
	 */
	public List<EstoqueAlimentoTO> listarTodos(int idUsuario) {
		try {

			String sql = "SELECT A.ID_ALIMENTO, A.nm_alimento, EA.qt_alimento, ea.dt_alimento, ea.id_estoque "
					+ "FROM T_DEM_ALIMENTO A " + "INNER JOIN T_DEM_ESTOQUE_ALIMENTO EA "
					+ "ON (A.ID_alimento = EA.ID_alimento) " + "INNER JOIN T_DEM_ESTOQUE E "
					+ "ON (EA.ID_ESTOQUE = E.ID_ESTOQUE) " + "WHERE E.id_usuario = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();

			List<EstoqueAlimentoTO> listaEstoqueAlimento = new ArrayList<>();
			while (rs.next()) {
				EstoqueAlimentoTO estoqueAlimentoTO = new EstoqueAlimentoTO(rs.getInt("qt_alimento"),
						rs.getInt("ID_ALIMENTO"), rs.getString("nm_alimento"), rs.getDate("dt_alimento"),
						rs.getInt("id_estoque"));
				listaEstoqueAlimento.add(estoqueAlimentoTO);
			}
			ps.close();
			return listaEstoqueAlimento;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Método para buscar um estoque por meio do Alimento e Usuario.
	 * 
	 * @param idAlimento
	 * @param idUsuario
	 * @return Estoque de alimento.
	 */
	public EstoqueAlimentoTO listarPorId(int idAlimento, int idUsuario) {
		try {

			String sql = "SELECT A.ID_ALIMENTO, A.nm_alimento, EA.qt_alimento, ea.dt_alimento, e.id_estoque "
					+ "FROM T_DEM_ALIMENTO A " + "INNER JOIN T_DEM_ESTOQUE_ALIMENTO EA "
					+ "ON (A.ID_alimento = EA.ID_alimento) " + "INNER JOIN T_DEM_ESTOQUE E "
					+ "ON (EA.ID_ESTOQUE = E.ID_ESTOQUE) " + "WHERE A.ID_ALIMENTO = ? AND ID_USUARIO = ?";

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

	/**
	 * Método de UPDATE, realizando uma edição de um alimento.
	 * 
	 * @param receitaById
	 */
	public void editarAlimento(EstoqueAlimentoTO receitaById) {
		try {

			String sql = "UPDATE T_DEM_ESTOQUE_ALIMENTO EA " + "SET EA.qt_alimento = ?, ea.dt_alimento = ? "
					+ "WHERE EA.ID_ALIMENTO = ? AND EA.ID_ESTOQUE = ?";

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

	/**
	 * Método para realizar a validação para saber se um alimento já está cadastrado
	 * no banco de dados
	 * 
	 * @param estoqueAlimentoTO
	 * @return
	 * @throws SQLException
	 */
	public boolean isAlimentoCadastrado(EstoqueAlimentoTO estoqueAlimentoTO) throws SQLException {

		String sql = "SELECT * FROM T_DEM_ALIMENTO WHERE nm_alimento like ?";
		PreparedStatement prepareStatement = con.prepareStatement(sql);
		prepareStatement.setString(1, "%" + estoqueAlimentoTO.getNomeAlimento() + "%");
		ResultSet resultSet = prepareStatement.executeQuery();
		return resultSet.next();
	}

	/**
	 * Método CREATE, para inserir um alimento no estoque de um usuário.
	 * 
	 * @param estoqueAlimentoTO
	 * @param idUsuarioLogado
	 * @throws SQLException
	 */
	public void inserirAlimento(EstoqueAlimentoTO estoqueAlimentoTO, int idUsuarioLogado) throws SQLException {

		String sql = "INSERT INTO T_DEM_ALIMENTO (id_alimento, nm_alimento) " + "VALUES (sq_dem_alimento.nextval, ?) ";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, estoqueAlimentoTO.getNomeAlimento());
		ps.executeUpdate();
	}

	/**
	 * Método CREATE, para inserir um alimento no estoque no usuário que está
	 * logado.
	 * 
	 * @param estoqueAlimentoTO
	 * @param idUsuarioLogado
	 * @throws SQLException
	 */
	public void inserirAlimentoEstoque(EstoqueAlimentoTO estoqueAlimentoTO, int idUsuarioLogado) throws SQLException {

		String sql = "INSERT INTO T_DEM_ESTOQUE_ALIMENTO (id_estoque_alimento, id_estoque, id_alimento, qt_alimento, dt_alimento) "
				+ "VALUES (sq_dem_estoque_alimento.nextval, ?, ?, ?, ?) ";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, getIdEstoque(estoqueAlimentoTO, idUsuarioLogado));
		ps.setInt(2, getIdAlimento(estoqueAlimentoTO));
		ps.setInt(3, estoqueAlimentoTO.getQuantidadeAlimento());
		ps.setDate(4, new java.sql.Date(estoqueAlimentoTO.getDataValidadeAlimento().getTime()));

		ps.executeUpdate();
	}

	/**
	 * Método para buscar o ID de um Estoque.
	 * 
	 * @param estoqueAlimentoTO
	 * @param idUsuarioLogado
	 * @return O Id de um estoque.
	 * @throws SQLException
	 */
	private int getIdEstoque(EstoqueAlimentoTO estoqueAlimentoTO, int idUsuarioLogado) throws SQLException {

		String sql = "SELECT ID_ESTOQUE " + "FROM T_DEM_ESTOQUE " + "WHERE  ID_USUARIO = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idUsuarioLogado);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			estoqueAlimentoTO.setIdEstoque(rs.getInt("ID_ESTOQUE"));
		}
		return estoqueAlimentoTO.getIdEstoque();
	}

	/**
	 * Método para buscar o ID de um alimento
	 * 
	 * @param estoqueAlimentoTO
	 * @return O id do alimento.
	 * @throws SQLException
	 */
	private int getIdAlimento(EstoqueAlimentoTO estoqueAlimentoTO) throws SQLException {

		String sql = "SELECT ID_ALIMENTO " + "FROM T_DEM_ALIMENTO " + "WHERE  NM_ALIMENTO = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, estoqueAlimentoTO.getNomeAlimento());
		ResultSet rs = ps.executeQuery();

		rs.next();
		estoqueAlimentoTO.setIdAlimento(rs.getInt("ID_ALIMENTO"));

		return estoqueAlimentoTO.getIdAlimento();
	}

	/**
	 * Método para validar se um alimento está cadastrado no estoque do usuário
	 * logado.
	 * 
	 * @param estoqueAlimentoTO
	 * @param idUsuarioLogado
	 * @return Um boolean, Caso True = Está cadastrado, Caso false = Não está
	 *         cadastrado.
	 * @throws SQLException
	 */
	public boolean isAlimentoEstoque(EstoqueAlimentoTO estoqueAlimentoTO, int idUsuarioLogado) throws SQLException {

		String sql = "SELECT * FROM T_DEM_ESTOQUE_ALIMENTO WHERE ID_ALIMENTO = ? AND ID_ESTOQUE = ?";
		PreparedStatement prepareStatement = con.prepareStatement(sql);
		prepareStatement.setInt(1, getIdAlimento(estoqueAlimentoTO));
		prepareStatement.setInt(2, getIdEstoque(estoqueAlimentoTO, idUsuarioLogado));
		ResultSet resultSet = prepareStatement.executeQuery();
		return resultSet.next();
	}
}
