import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Principal extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1945532643929574176L;
	static Principal princ;
	JFrame janela;
	JMenuBar menu;
	JMenu mnuAcoes, mnuMalha, mnuExtra;
	JMenuItem mitAbstrato, mitCirculosAniCol, mitQuadros, mitCirculos, mitOrigCirculos, mitQuadrosCol, mitArc,
			mitCirculosCol, mitOrigCirculosCol, mitMalha, mitMalha3DCompletaCol, mitSobre, mitSair;

	public Principal() {
		setTitle("Desenhos"); // Define um título
		getContentPane().setBackground(Color.gray); // Define uma cor para o
													// forme
		setBounds(250, 200, 600, 600); // Define posição e tamanho (x, y,
										// largura, altura)
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		menu = new JMenuBar(); // Cria a barra de menu
		getContentPane().add(menu);
		setJMenuBar(menu); // Define mnBarra como padrão

		mnuAcoes = new JMenu("Criar");
		menu.add(mnuAcoes);

		mitAbstrato = new JMenuItem("Arte abstrata");
		mitAbstrato.addActionListener(this);
		mnuAcoes.add(mitAbstrato);

		mitCirculosAniCol = new JMenuItem("Circulos Aninhados e Coloridos");
		mitCirculosAniCol.addActionListener(this);
		mnuAcoes.add(mitCirculosAniCol);

		mitQuadros = new JMenuItem("Quadros");
		mitQuadros.addActionListener(this);
		mnuAcoes.add(mitQuadros);

		mitCirculos = new JMenuItem("Circulos");
		mitCirculos.addActionListener(this);
		mnuAcoes.add(mitCirculos);

		mitOrigCirculos = new JMenuItem("Como criam os circulos");
		mitOrigCirculos.addActionListener(this);
		mnuAcoes.add(mitOrigCirculos);

		mitQuadrosCol = new JMenuItem("Quadros Coloridos");
		mitQuadrosCol.addActionListener(this);
		mnuAcoes.add(mitQuadrosCol);

		mitCirculosCol = new JMenuItem("Circulos Coloridos");
		mitCirculosCol.addActionListener(this);
		mnuAcoes.add(mitCirculosCol);

		mitOrigCirculosCol = new JMenuItem("Como criam os circulos coloridos");
		mitOrigCirculosCol.addActionListener(this);
		mnuAcoes.add(mitOrigCirculosCol);

		mitArc = new JMenuItem("Arcos");
		mitArc.addActionListener(this);
		mnuAcoes.add(mitArc);

		mnuMalha = new JMenu("Malha 3D");
		menu.add(mnuMalha);

		mitMalha = new JMenuItem("Malha quadriculada");
		mitMalha.addActionListener(this);
		mnuMalha.add(mitMalha);

		mitMalha3DCompletaCol = new JMenuItem("Malha 3D completa colorida");
		mitMalha3DCompletaCol.addActionListener(this);
		mnuMalha.add(mitMalha3DCompletaCol);

		mnuExtra = new JMenu("Extra");
		menu.add(mnuExtra);

		mitSobre = new JMenuItem("Sobre");
		mitSobre.addActionListener(this);
		mnuExtra.add(mitSobre);

		mitSair = new JMenuItem("Sair");
		mitSair.addActionListener(this);
		mnuExtra.add(mitSair);
	}

	public static void main(String... x) {
		princ = new Principal();
		princ.janela = new Principal();
		princ.janela.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mitAbstrato) {
			ArteAbstrato panel = new ArteAbstrato();

			princ.janela = panel.mostrar();
		} else if(e.getSource() == mitCirculosAniCol) {
			CirculosCol panel = new CirculosCol();

			princ.janela = panel.mostrar();
		} else if(e.getSource() == mitQuadros) {
			CirculoEQuadrado panel = new CirculoEQuadrado(1);

			princ.janela = panel.mostrar();
		} else if(e.getSource() == mitCirculos) {
			CirculoEQuadrado panel = new CirculoEQuadrado(2);

			princ.janela = panel.mostrar();
		} else if(e.getSource() == mitOrigCirculos) {
			CirculoEQuadrado panel = new CirculoEQuadrado(3);

			princ.janela = panel.mostrar();
		} else if(e.getSource() == mitQuadrosCol) {
			CirculoEQuadrado panel = new CirculoEQuadrado(4);

			princ.janela = panel.mostrar();
		} else if(e.getSource() == mitCirculosCol) {
			CirculoEQuadrado panel = new CirculoEQuadrado(5);

			princ.janela = panel.mostrar();
		} else if(e.getSource() == mitOrigCirculosCol) {
			CirculoEQuadrado panel = new CirculoEQuadrado(6);

			princ.janela = panel.mostrar();
		} else if(e.getSource() == mitMalha) {
			MalhaQuad malhaQ = new MalhaQuad();

			princ.janela = malhaQ.mostrar();
		} else if(e.getSource() == mitMalha3DCompletaCol) {
			Malha3D malha = new Malha3D();

			princ.janela = malha.mostrar();
		} else if(e.getSource() == mitArc) {
			Arcos arc = new Arcos();

			princ.janela = arc.mostrar();
		} else if(e.getSource() == mitSobre) {
			javax.swing.JOptionPane.showMessageDialog(null, "Created by: Wesley - M4c4c0 L0k0\n" + "Date: 21/04/2011\n"
					+ "Hours: 20:02 to 21:08");
		} else if(e.getSource() == mitSair) {
			javax.swing.JOptionPane.showMessageDialog(null, "Obrigado!");
			System.exit(0);
		}
	}
}
