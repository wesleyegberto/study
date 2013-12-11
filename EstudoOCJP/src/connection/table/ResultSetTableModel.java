/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 11 de Jul de 2013
 * 
 */
package connection.table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.AbstractTableModel;

import br.com.bsetechnology.sql.Connection;

/**
 * ResultSetTableModel -Conterá os registro para serem carregados em um JTable.
 * -Permite um controle maior sobre os dados -Permite fornecer dados de qualquer
 * fonte (List, ResultSet, array, etc)
 */
public class ResultSetTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -6805468907217608785L;

	private Connection connection;
	private Statement statmnt;
	private ResultSet resultSet;
	private int totalRows;
	private String cmdSql;

	/**
	 * @param con
	 *            Conexão com o banco de dados
	 * @param sql
	 *            Comando SQL para consulta dos registros
	 * @throws SQLException
	 *             se ocorrer algum erro
	 */
	public ResultSetTableModel(Connection con, String sql) throws SQLException {
		this.connection = con;
		this.cmdSql = sql;
		if(!loadResultSet())
			throw new SQLException("Dados não carregado.");
	}

	/**
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return totalRows;
	}

	/**
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		if(connection == null)
			return 0;

		try {
			return resultSet.getMetaData().getColumnCount();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int col) {
		try {
			return Class.forName(resultSet.getMetaData().getColumnClassName(col + 1));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return Object.class;
	}

	@Override
	public String getColumnName(int col) {
		try {
			return resultSet.getMetaData().getColumnName(col + 1);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	/**
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int row, int col) {
		if(connection == null)
			throw new IllegalStateException("Connection does not exists");

		try {
			// Soma 1 pois o ResultSet inicia a linha e coluna do 1
			// e os argumentos poderão ser 0 pois o JTable a linha e coluna
			// iniciam do 0
			resultSet.absolute(row + 1);
			return resultSet.getObject(col + 1);
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Carrega o ResultSet que conterá os registro do JTable.throw new
	 * SQLException("Data do not loaded");
	 * 
	 * @return true se carregou com sucesso
	 * @throws SQLException
	 *             se ocorrer algum erro de conexão ou script SQL
	 */
	private boolean loadResultSet() throws SQLException {
		if(connection == null)
			throw new IllegalStateException("Connection does not exist");

		statmnt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		this.resultSet = statmnt.executeQuery(cmdSql);

		if(resultSet != null && !resultSet.isClosed()) {
			// move para o ultimo registro para obeter o total
			resultSet.last();
			this.totalRows = resultSet.getRow();
			// notifica o JTable que a utiliza que o modelo de estrutura foi
			// alterado
			super.fireTableStructureChanged();
			return true;
		}
		return false;
	}

	/**
	 * Fecha os recursos utilizados.
	 */
	public void close() {
		try {
			resultSet.close();
			statmnt.close();
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
