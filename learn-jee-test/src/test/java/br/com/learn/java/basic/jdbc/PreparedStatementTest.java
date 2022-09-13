package br.com.learn.java.basic.jdbc;

/**
 * PreparedStatement estende a interface Statement<br/>
 * Possui métodos para vincular vários tipos de objetos , incluindo arquivos e matrizes<br/>
 * Protege contra injeção
 * <p>Usa pré-compilação: <br> 
 * 		Assim que o banco de dados receber uma consulta, ele verificará o cache antes de pré-compilar a consulta. Conseqüentemente, se não estiver armazenado em cache, o mecanismo de banco de dados o salvará para o próximo uso.<br/>
 * 		Além disso, esse recurso agiliza a comunicação entre o banco de dados e a JVM por meio de um protocolo binário não SQL. Ou seja, há menos dados nos pacotes, então a comunicação entre os servidores fica mais rápida.
 * </p>
 * Fornece uma execução em lote<br/>
 * Fornece uma maneira fácil de armazenar e recuperar arquivos usando os tipos de dados BLOB e  CLOB<br/>
 * Na mesma linha, ajuda a armazenar listas convertendo java.sql.Array em um SQL Array<br/>
 * Implementa métodos como getMetadata() que contêm informações sobre o resultado retornado
 */
public class PreparedStatementTest {

}
