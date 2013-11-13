package br.com.caelum.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caelum.bean.ConnectionFactory;
import br.com.caelum.bean.Tarefa;

@Repository
public class JdbcTarefaDao {
	private static final String SQL_ADICIONA = "INSERT INTO Tarefa (id,descricao,finalizado,dataFinalizacao) VALUES (NULL,?,?,?)";
	private static final String SQL_ATUALIZA = "UPDATE Tarefa SET descricao = ?,finalizado = ?,dataFinalizacao = ? WHERE id = ?";
	private static final String SQL_FINALIZA = "UPDATE Tarefa SET finalizado = ?,dataFinalizacao = ? WHERE id = ?";
	private static final String SQL_REMOVE = "DELETE FROM Tarefa WHERE id = ?";
	private static final String SQL_LISTA = "SELECT * FROM Tarefa";
	private static final String SQL_SELECIONA_ID = "SELECT * FROM Tarefa WHERE id = ?";

	private Connection connection;

	@Autowired
	public JdbcTarefaDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean adiciona(Tarefa tarefa) {
		PreparedStatement stmt = null;

		try {
			connection = ConnectionFactory.getConnection();
			stmt = connection.prepareStatement(SQL_ADICIONA);

			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			if(tarefa.getDataFinalizacao() != null)
				stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));
			else
				stmt.setDate(3, null);

			if(stmt.execute()) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(stmt);
		}
		return false;
	}

	public List<Tarefa> lista() {
		PreparedStatement stmt = null;
		ResultSet rst = null;

		List<Tarefa> tarefas = null;
		try {
			connection = ConnectionFactory.getConnection();
			stmt = connection.prepareCall(SQL_LISTA);
			rst = stmt.executeQuery();

			Tarefa tarefa = null;
			Calendar cal = null;
			tarefas = new ArrayList<Tarefa>();
			while(rst.next()) {
				tarefa = new Tarefa();
				tarefa.setId(rst.getLong("id"));
				tarefa.setDescricao(rst.getString("descricao"));
				tarefa.setFinalizado(rst.getBoolean("finalizado"));
				if(rst.getDate("dataFinalizacao") != null) {
					cal = Calendar.getInstance();
					cal.setTime(rst.getDate("dataFinalizacao"));
				} else {
					cal = null;
				}
				tarefa.setDataFinalizacao(cal);
				tarefas.add(tarefa);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(stmt);
		}
		return tarefas;
	}

	public boolean remove(Tarefa tarefa) {
		PreparedStatement stmt = null;

		try {
			connection = ConnectionFactory.getConnection();
			stmt = connection.prepareStatement(SQL_REMOVE);

			stmt.setLong(1, tarefa.getId());

			if(stmt.execute()) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(stmt);
		}
		return false;
	}

	public Tarefa buscaPorId(long id) {
		PreparedStatement stmt = null;
		ResultSet rst = null;
		try {
			connection = ConnectionFactory.getConnection();
			stmt = connection.prepareCall(SQL_SELECIONA_ID);
			stmt.setLong(1, id);

			rst = stmt.executeQuery();
			if(rst.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rst.getLong("id"));
				tarefa.setDescricao(rst.getString("descricao"));
				tarefa.setFinalizado(rst.getBoolean("finalizado"));

				Calendar cal = null;
				if(rst.getDate("dataFinalizacao") != null) {
					cal = Calendar.getInstance();
					cal.setTime(rst.getDate("dataFinalizacao"));
				}
				tarefa.setDataFinalizacao(cal);
				return tarefa;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(stmt);
		}
		return null;
	}

	public boolean atualiza(Tarefa tarefa) {
		PreparedStatement stmt = null;

		try {
			connection = ConnectionFactory.getConnection();
			stmt = connection.prepareStatement(SQL_ATUALIZA);

			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			if(tarefa.getDataFinalizacao() != null)
				stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));
			else
				stmt.setDate(3, null);
			stmt.setLong(4, tarefa.getId());

			if(stmt.execute()) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(stmt);
		}
		return false;
	}

	public boolean finaliza(long id) {
		PreparedStatement stmt = null;

		try {
			connection = ConnectionFactory.getConnection();
			stmt = connection.prepareStatement(SQL_FINALIZA);

			stmt.setBoolean(1, true);
			stmt.setDate(2, new Date(System.currentTimeMillis()));
			stmt.setLong(3, id);

			if(stmt.execute()) {
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
