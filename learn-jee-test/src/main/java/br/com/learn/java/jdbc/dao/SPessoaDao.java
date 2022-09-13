package br.com.learn.java.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.learn.java.model.PessoaEntity;

public class SPessoaDao {

	private final Connection connection;

	public SPessoaDao(Connection connection) {
		this.connection = connection;
	}
	
	private String getTableName() {
		return PessoaEntity.TABLE_NAME;
	}

	public Optional<PessoaEntity> getById(int id) throws SQLException {
		String query = "SELECT id, name, FROM "+getTableName()+" WHERE id = '" + id + "'";

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);

		if (resultSet.first()) {
			PessoaEntity result = new PessoaEntity(resultSet.getInt("id"), resultSet.getString("name"));
			return Optional.of(result);
		} else {
			return Optional.empty();
		}
	}

	public void insert(PessoaEntity PessoaEntity) throws SQLException {
		String query = "INSERT INTO "+getTableName()+"(id, name) VALUES(" + PessoaEntity.getId() + ", '" + PessoaEntity.getName()
				+ "')";

		Statement statement = connection.createStatement();
		statement.executeUpdate(query);
	}

	public void insert(List<PessoaEntity> personEntities) throws SQLException {
		for (PessoaEntity PessoaEntity : personEntities) {
			insert(PessoaEntity);
		}
	}

	public void update(PessoaEntity PessoaEntity) throws SQLException {

		String query = "UPDATE "+getTableName()+" SET name = '" + PessoaEntity.getName() + "' WHERE id = " + PessoaEntity.getId();

		Statement statement = connection.createStatement();
		statement.executeUpdate(query);

	}

	public void deleteById(int id) throws SQLException {
		String query = "DELETE FROM "+getTableName()+" WHERE id = " + id;
		Statement statement = connection.createStatement();
		statement.executeUpdate(query);
	}

	public List<PessoaEntity> getAll() throws SQLException {
		String query = "SELECT id, name, FROM "+getTableName();

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		List<PessoaEntity> result = new ArrayList<>();
		while (resultSet.next()) {
			result.add(new PessoaEntity(resultSet.getInt("id"), resultSet.getString("name")));
		}
		return result;
	}
}
