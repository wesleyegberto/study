package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import br.com.bsetechnology.gui.JButtonColumn;

@SuppressWarnings("serial")
public class TableButton extends JFrame {
	JTable table;

	public TableButton() {
		table = new JTable();

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(handlerEditar);

		String[] columnNames = { "", "Nome", "Data" };
		Object[][] data = new Object[][] { { btnEditar, "José Antonio", "10/08/2013" },
				{ "asd", "Odair Jozé", "05/07/2013" }, { btnEditar, "Milton Santons", "10/10/2013" },
				{ btnEditar, "Andre Santons", "10/10/2013" }};

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setModel(model);

		getContentPane().add(scrollPane);
		JButtonColumn.addJButtonAtCol(table, 0);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {			
		}
		
		TableButton frame = new TableButton();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private ActionListener handlerEditar = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
			edita();
		}
	};
	private ActionListener handlerSalvar = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
			salva();
		}
	};

	public void edita() {
		System.out.println("Edite at (" + table.getSelectedRow() + ", " + table.getSelectedColumn() + ")");

		if(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()) instanceof JButton) {
			JButton button = (JButton) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
			button.removeActionListener(handlerEditar);
			button.addActionListener(handlerSalvar);
			button.setText("Salvar");
		}
	}

	public void salva() {
		System.out.println("Save at (" + table.getSelectedRow() + ", " + table.getSelectedColumn() + ")");

		if(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()) instanceof JButton) {
			JButton button = (JButton) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
			button.removeActionListener(handlerSalvar);
			button.addActionListener(handlerEditar);
			button.setText("Editar");
		}
	}
}
