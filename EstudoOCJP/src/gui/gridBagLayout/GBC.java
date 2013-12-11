package gui.gridBagLayout;

import java.awt.GridBagConstraints;

public class GBC extends GridBagConstraints {
	private static final long serialVersionUID = 22899906856158437L;

	public GBC() {
	}

	public GBC(int gridx, int gridy, int gridwidth, int gridheight) {
		this();
		setGrid(gridx, gridy, gridwidth, gridheight);
	}

	public GBC(int gridx, int gridy, int gridwidth, int gridheight, int fill, int anchor) {
		this(gridx, gridy, gridwidth, gridheight);
		setFill(fill);
		setAnchor(anchor);
	}

	// Configura a posição e tamanho
	public GBC setGrid(int valuePosition, int valueSize) {
		setGrid(valuePosition, valuePosition, valueSize, valueSize);
		return this;
	}

	public GBC setGrid(int gridx, int gridy, int gridwidth, int gridheight) {
		super.gridx = gridx;
		super.gridy = gridy;
		super.gridwidth = gridwidth;
		super.gridheight = gridheight;
		return this;
	}

	// Configurar o peso
	public GBC setWeight(double weightx, double weighty) {
		super.weightx = weightx;
		super.weighty = weighty;
		return this;
	}

	// Configura o preenchimento
	public GBC setFill(int fill) {
		super.fill = fill;
		return this;
	}

	// Configura a posição dentro da célula, recomendado que utilize constantes
	public GBC setAnchor(int anchor) {
		super.anchor = anchor;
		return this;
	}

	// Configura o preenchimento interno
	public GBC setIpad(int ipadx, int ipady) {
		super.ipadx = ipadx;
		super.ipady = ipady;
		return this;
	}

	// Configura o preenchimento externo
	public GBC setInsets(int distance) {
		this.insets = new java.awt.Insets(distance, distance, distance, distance);
		return this;
	}

	public GBC setInsets(int left, int top, int right, int bottom) {
		super.insets.left = left;
		super.insets.right = right;
		super.insets.top = top;
		super.insets.bottom = bottom;
		return this;
	}

}
