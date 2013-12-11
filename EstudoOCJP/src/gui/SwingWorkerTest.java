/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 28/04/2013
 * 
 *         Objetivo: Testar a classe utilitaria SwingWorker
 * 
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class SwingWorkerTest extends JFrame {
	private JLabel lblRotulo, lblRotulo2, lblNumber, lblRotulo1;
	private JTextField txfTexto, txfNumberMax;
	private JButton btnAction, btnCancel;
	private JProgressBar progressBar;
	private Worker action; // Worker para poder iniciar e cancelar

	public SwingWorkerTest() {
		super("Teste de SwingWorker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(400, 300);
		getContentPane().setLayout(new GridLayout(5, 2, 5, 5));

		this.lblRotulo2 = new JLabel("Max:");
		add(this.lblRotulo2);

		this.txfNumberMax = new JTextField();
		add(this.txfNumberMax);

		this.lblRotulo = new JLabel("Status:");
		// this.lblRotulo.setSize(140, 30);
		getContentPane().add(this.lblRotulo);

		this.lblNumber = new JLabel("0");
		getContentPane().add(this.lblNumber);

		this.lblRotulo1 = new JLabel("Mensagem: ");
		getContentPane().add(this.lblRotulo1);

		this.txfTexto = new JTextField();
		add(this.txfTexto);

		this.btnAction = new JButton("GO!");
		this.btnAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int n;
				progressBar.setValue(0);
				try {
					n = Integer.parseInt(txfNumberMax.getText());
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Entre com um numero valido!");
					return;
				}

				try {
					action = new Worker(n);
					action.addPropertyChangeListener(new PropertyChangeListener() {
						@Override
						public void propertyChange(PropertyChangeEvent e) {
							if(e.getPropertyName().equals("progress")) {
								progressBar.setValue((Integer) e.getNewValue());
							}
						}
					});
					action.execute(); // agenda/escalona a doInBackground() em
										// um thread trabalhadora
					btnAction.setEnabled(false);
					btnCancel.setEnabled(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		add(this.btnAction);

		this.btnCancel = new JButton("Cancel");
		this.btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				action.cancel(false);
				btnAction.setEnabled(true);
				btnCancel.setEnabled(false);
			}
		});
		this.btnCancel.setEnabled(false);
		add(this.btnCancel);

		this.progressBar = new JProgressBar();
		add(this.progressBar);

		setVisible(true);
	}

	public static void main(String[] args) {
		new SwingWorkerTest();
	}

	private class Worker extends SwingWorker<Integer, Integer> {
		int i;
		int max;

		Worker(int i) {
			this.max = i;
		}

		@Override
		protected Integer doInBackground() throws Exception // executa em uma
															// thread
															// trabalhadora
		{
			int i = 1;
			for(i = 1; i <= max; i++) {
				if(isCancelled()) {
					return i;
				}

				setProgress((i) * 100 / max);
				publish(i);
				Thread.sleep(1000);
			}
			return max;
		}

		@Override
		protected void done() // executa na EDT
		{
			int resul = 0;
			try {
				resul = get();
			} catch(Exception e) {
				resul = this.i;
			}

			if(isCancelled())
				lblNumber.setText("Cancelled at " + resul);
			else
				lblNumber.setText(resul + " - Done!");

			btnAction.setEnabled(true);
			btnCancel.setEnabled(false);
		}

		@Override
		protected void process(List<Integer> l) {
			ListIterator<Integer> iterator = l.listIterator();
			Integer i;
			while(iterator.hasNext()) {
				i = iterator.next();
				lblNumber.setText(i.toString());
				this.i = i;
			}
		}

	}
}
