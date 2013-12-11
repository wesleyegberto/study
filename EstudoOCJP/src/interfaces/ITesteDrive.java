package interfaces;

public class ITesteDrive implements ITeste {
	public void falar(String texto1) {
		System.out.println(texto1);

	}

	public int atacar(int forca) {
		System.out.println("Ganhei!");
		return 0;
	}

	public int defender() {
		System.out.println("Perdeu!");
		return 0;
	}
}
