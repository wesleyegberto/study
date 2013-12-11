/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 11 de Jul de 2013
 * 
 */
package connection.table;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.com.bsetechnology.sql.ConnectionFactory;
import br.com.bsetechnology.sql.TypeDatabase;
import br.com.bsetechnology.sql.Connection;

public class JTableFormTest extends JFrame {
	private JTable table;
	private ResultSetTableModel tableModel;
	private Connection connection;
	private JButton btnSair;

	private String datasource = "127.0.0.1";
	private int port = 3306;
	private String user = "root";
	private String password = "1234abc@";
	private String database = "EJ";

	public JTableFormTest() {
		super("Teste do JTable");
		super.setSize(700, 400);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		try {
			connection = ConnectionFactory.getConnection(TypeDatabase.MYSQL, datasource, port, user, password, database);

		} catch(ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Driver de conexão não encontrado.\nDetalhes: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao efetuar conexão.\nDetalhes: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}

		try {
			tableModel = new ResultSetTableModel(connection, "SELECT * FROM Tb_Produto_Camisa");
			table = new JTable(tableModel);
			// Objeto que permite reordenar os registros
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
			table.setRowSorter(sorter);
		} catch(SQLException e) {
			if(tableModel != null)
				tableModel.close();
			JOptionPane.showMessageDialog(null, "Erro ao consultar dados.\nDetalhes: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		add(new JScrollPane(table), BorderLayout.CENTER);

		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Deseja sair?") == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		add(btnSair, BorderLayout.SOUTH);
		super.setVisible(true);
	}

	public static void main(String[] args) {
		new JTableFormTest();
	}
}
