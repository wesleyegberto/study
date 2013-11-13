package br.com.caelum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caelum.bean.ConnectionFactory;
import br.com.caelum.bean.Usuario;

@Repository
public class JdbcUsuarioDao {
	private Connection connection;

	@Autowired
	public JdbcUsuarioDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean existeUsuario(Usuario usuario) {
		PreparedStatement stmt = null;
		ResultSet rst = null;
		try {
			connection = ConnectionFactory.getConnection();
			stmt = connection.prepareCall("SELECT * FROM Usuario WHERE login = ? AND senha = ?");
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());

			rst = stmt.executeQuery();
			if(rst.next()) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(stmt);
		}
		return false;
	}

}
