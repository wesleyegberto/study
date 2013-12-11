package sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.WebRowSet;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.FilteredRowSetImpl;
import com.sun.rowset.WebRowSetImpl;

public class RowSetTest {
	public static void main(String[] args) throws SQLException, IOException {
		MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
		ds.setServerName("127.0.0.1");
		ds.setPort(3306);
		ds.setUser("root");
		ds.setPassword("123456");
		ds.setDatabaseName("vm_visualconfig");

		Connection con = ds.getConnection();
		Statement stmt = con.createStatement();		
		ResultSet rs = null;
		
		// Load a CachedRowSet
		rs = stmt.executeQuery("SELECT * FROM ACESSO_PERFIL");
		CachedRowSet crs = new CachedRowSetImpl();
		crs.populate(rs);
		rs.close();
		// Load a FilteredRowSet
		rs = stmt.executeQuery("SELECT * FROM ACESSO_PERFIL");
		FilteredRowSet frs = new FilteredRowSetImpl();
		frs.populate(rs);
		rs.close();
		// Load a WebRowSet
		rs = stmt.executeQuery("SELECT * FROM ACESSO_PERFIL");
		WebRowSet wrs = new WebRowSetImpl();
		wrs.populate(rs);
		rs.close();
		
		// Close the connection to use RowSet disconnected
		con.close();

		// Each RowSet works as a DataSet of .Net
		System.out.print("CachedRowSet: ");
		System.out.println(frs.size());
		while(crs.next()) {
			System.out.print(" " + crs.getString("DESCRICAO"));
		}

		System.out.print("\n\nFilterRowSet: ");
		System.out.println(frs.size());
		while(frs.next()) {
			System.out.print(" " + frs.getString("DESCRICAO"));
		}
		
		System.out.print("\n\nWebRowSet: ");
		// Can export to XML to the given Writer
		//wrs.writeXml(System.out);
		System.out.println(wrs.size());
		while(wrs.next()) {
			System.out.print(" " + wrs.getString("DESCRICAO"));
		}
		
	}
}
