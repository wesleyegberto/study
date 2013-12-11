package mysql;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class CreatePoolConnection {
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/vm_controlehoras";
	static String user = "root";
	static String password = "123456";

	public static void main(String[] args) {
		Connection con[] = new Connection[3];
		try {
			// cria o pool de conexões
			ComboPooledDataSource pooledDs = new ComboPooledDataSource();
			pooledDs.setDriverClass(driver);
			pooledDs.setJdbcUrl(url);
			pooledDs.setUser(user);
			pooledDs.setPassword(password);

			// requisita uma Connection
			con[0] = pooledDs.getConnection();
			close(con[0]);
			con[1] = pooledDs.getConnection();
			con[2] = pooledDs.getConnection();
			con[0] = pooledDs.getConnection();

			// usa normalmente
			Statement stmt = con[0].createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SITUACAO");
			while(rs.next()) {
				System.out.print(rs.getString("SITUACAO") + " ");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(PropertyVetoException e) {
			e.printStackTrace();
		} finally {
		}

	}
	
	private static void close(Connection c) {
		if(c != null) {
			try {
				c.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}		
	}
}
