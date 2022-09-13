package br.com.learn.java.basic.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.learn.java.jdbc.infra.BasicConnectionPool;
import br.com.learn.java.jdbc.infra.ConnectionPool;

public class BasicConnectionPoolTest {

	private static ConnectionPool connectionPool;

	@BeforeAll
	public static void setUpBasicConnectionPoolInstance() throws SQLException {
		connectionPool = BasicConnectionPool.create("jdbc:h2:mem:test", "user", "password");
	}

	@Test
	public void givenBasicConnectionPoolInstance_whenCalledgetConnection_thenCorrect() throws Exception {
		assertTrue(connectionPool.getConnection().isValid(1));
	}

	@Test
	public void givenBasicConnectionPoolInstance_whenCalledreleaseConnection_thenCorrect() throws Exception {
		Connection connection = connectionPool.getConnection();
		assertTrue(connectionPool.releaseConnection(connection));
	}

	@Test
	public void givenBasicConnectionPoolInstance_whenCalledgetUrl_thenCorrect() {
		assertEquals(connectionPool.getUrl(), "jdbc:h2:mem:test");
	}

	@Test
	public void givenBasicConnectionPoolInstance_whenCalledgetUser_thenCorrect() {
		assertEquals(connectionPool.getUser(), "user");
	}

	@Test
	public void givenBasicConnectionPoolInstance_whenCalledgetPassword_thenCorrect() {
		assertEquals(connectionPool.getPassword(), "password");
	}

	@Test
	public void givenBasicConnectionPoolInstance_whenAskedForMoreThanMax_thenError() throws Exception {
		// this test needs to be independent so it doesn't share the same connection
		// pool as other tests

		String msgExpected = String.format("Tamanho máximo do pool atingido, %d conexões, sem conexões disponíveis!",
				20);

		RuntimeException current = Assertions.assertThrows(RuntimeException.class, () -> {

			ConnectionPool cp = BasicConnectionPool.create("jdbc:h2:mem:test", "user", "password");
			final int MAX_POOL_SIZE = 20;
			for (int i = 0; i < MAX_POOL_SIZE + 1; i++) {
				cp.getConnection();
			}

		});

		Assertions.assertEquals(msgExpected, current.getMessage());
	}

	@Test
	public void givenBasicConnectionPoolInstance_whenSutdown_thenEmpty() throws Exception {
		ConnectionPool cp = BasicConnectionPool.create("jdbc:h2:mem:test", "user", "password");
		assertEquals(((BasicConnectionPool) cp).getSize(), 10);

		((BasicConnectionPool) cp).shutdown();
		assertEquals(((BasicConnectionPool) cp).getSize(), 0);
	}
}