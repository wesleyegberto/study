package sql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceDeployedTest {
		
	private static DataSource getDataSource() {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setServerName("127.0.0.1");
		ds.setPort(3306);
		ds.setUser("root");
		ds.setPassword("123456");
		ds.setDatabaseName("vm_controlehoras");
		
		return ds;
	}
	
	private static boolean registerDataSource(DataSource ds) {
		Context ctx = null;
		try {
			ctx = new InitialContext();
			ctx.bind("java:comp/env/jdbc/MyConnection", ds);
			return true;
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static DataSource rentrieveDataSource() {
		DataSource ds = null;
		Context ctx = null;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("jdbc/myDataSource");
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public static void main(String[] args) {
		registerDataSource(getDataSource());

		DataSource ds = rentrieveDataSource();
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
				}
			}
		}

	}
}
