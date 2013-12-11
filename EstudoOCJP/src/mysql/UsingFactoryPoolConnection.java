package mysql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;

public class UsingFactoryPoolConnection {
	static String driver = "jdbc:mysql://localhost:3306/vm_controlehoras";
	static String user = "root";
	static String password = "123456";
	
	
	public static void main(String[] args) {
		Connection con = null;
		try {
			DataSource ds = DataSources.unpooledDataSource(driver, user, password);
			ds = DataSources.pooledDataSource(ds);
			
			con = ds.getConnection();
			System.out.println("Connected: " + !con.isClosed());
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null)
				try {
					con.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
}
