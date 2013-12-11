package sql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceTest {
	public static void main(String[] args) {
		DataSource pds = getPooledDataSource();
		DataSource ds = getDataSource();
		Connection pooledConn = null, singleConn = null;
		
		try {
			pooledConn = pds.getConnection();
			System.out.println("Pooled Connected: " + !pooledConn.isClosed());
			
			singleConn = ds.getConnection();
			System.out.println("Single Connected: " + !singleConn.isClosed());
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(pooledConn != null) {
				try {
					pooledConn.close();
				} catch(SQLException e) {
				}
			}
			if(singleConn != null) {
				try {
					singleConn.close();
				} catch(SQLException e) {
				}
			}
			ds = null;
			pds = null;
		}

	}

	/**
	 * MysqlConnectionPoolDataSource é uma implementação concreta da DataSource.
	 * Não implementa pool de conexão, ela é utilizada para criar conexões físicas
	 */
	private static DataSource getPooledDataSource() {
		MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
		ds.setServerName("127.0.0.1");
		ds.setPort(3306);
		ds.setUser("root");
		ds.setPassword("123456");
		ds.setDatabaseName("vm_controlehoras");
		return ds;
	}

	/**
	 * Em toda requisição é criada uma nova conexão, igual ao DriverManager.getConnection
	 */
	private static DataSource getDataSource() {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setServerName("127.0.0.1");
		ds.setPort(3306);
		ds.setUser("root");
		ds.setPassword("123456");
		ds.setDatabaseName("vm_controlehoras");
		return ds;
	}

}
