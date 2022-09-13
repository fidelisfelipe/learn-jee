package br.com.learn.java.basic.jdbc;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import br.com.learn.java.jdbc.infra.DatasourceFactory;

public class DatasourceFactoryTest {

	@Test
	public void whenCreateConnectionAndTables_thenConnectionIsOpenAndTableIsCreated()
			throws SQLException, ClassNotFoundException {
		//given
		DatasourceFactory factory = new DatasourceFactory();
		Connection connection = factory.getConnection();
		//then
		assertFalse(connection.isClosed());
		//when
		assertTrue(factory.createTables());
		
	}
}
