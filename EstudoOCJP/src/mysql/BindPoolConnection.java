package mysql;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class BindPoolConnection {
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/vm_controlehoras";
	static String user = "root";
	static String password = "123456";
	
	public static void main(String[] args) {
		Connection con = null;
		try {
			ComboPooledDataSource pooledDs = new ComboPooledDataSource();
			pooledDs.setDriverClass(driver);
			pooledDs.setJdbcUrl(url);
			pooledDs.setUser(user);
			pooledDs.setPassword(password);
			pooledDs.setDataSourceName("jdbc:myCon");
						
			con = pooledDs.getConnection();
			System.out.println("Connected: " + !con.isClosed());
			
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "javax.naming.spi.InitialContextFactory");
			Context ctx = new InitialContext(env);
			ctx.bind("jdbc:myCon", pooledDs);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(PropertyVetoException e) {
			e.printStackTrace();
		} catch(NamingException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
