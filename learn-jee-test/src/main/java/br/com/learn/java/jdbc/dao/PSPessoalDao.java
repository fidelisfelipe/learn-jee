package br.com.learn.java.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.learn.java.model.PessoaEntity;

public class PSPessoalDao {

	private final Connection connection;

	private String getTableName() {
		return PessoaEntity.TABLE_NAME;
	}

	public PSPessoalDao(Connection connection) {
		this.connection = connection;
	}

	public Optional<PessoaEntity> getById(int id) throws SQLException {
		String query = "SELECT id, name FROM " + getTableName() + " WHERE id = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.first()) {

			PessoaEntity result = new PessoaEntity(resultSet.getInt("id"), resultSet.getString("name"));

			return Optional.of(result);
		} else {
			return Optional.empty();
		}

	}

	public void insert(PessoaEntity personEntity) throws SQLException {

		String query = "INSERT INTO "+ getTableName() +"(id, name) VALUES( ?, ?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, personEntity.getId());
		preparedStatement.setString(2, personEntity.getName());
		preparedStatement.executeUpdate();

	}

	public void insert(List<PessoaEntity> personEntities) throws SQLException {
		String query = "INSERT INTO "+ getTableName() +"(id, name) VALUES( ?, ?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		for (PessoaEntity personEntity : personEntities) {
			preparedStatement.setInt(1, personEntity.getId());
			preparedStatement.setString(2, personEntity.getName());
			preparedStatement.addBatch();
		}
		preparedStatement.executeBatch();

	}

	public void update(PessoaEntity personEntity) throws SQLException {
		String query = "UPDATE "+ getTableName() +" SET name = ? WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, personEntity.getName());
		preparedStatement.setInt(2, personEntity.getId());
		preparedStatement.executeUpdate();
	}

	public void deleteById(int id) throws SQLException {
		String query = "DELETE FROM "+ getTableName() +" WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
	}

	public List<PessoaEntity> getAll() throws SQLException {
		String query = "SELECT id, name FROM "+ getTableName();

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<PessoaEntity> result = new ArrayList<>();
		while (resultSet.next()) {
			result.add(new PessoaEntity(resultSet.getInt("id"), resultSet.getString("name")));
		}
		return result;
	}
}
