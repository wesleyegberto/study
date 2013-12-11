package anotacao;

public class AnotacaoOverride {
	public void metodo() {
	}
	
	public static void main(String[] args) {
		
	}
}

class TesteAnotacaoOverrideDrive extends AnotacaoOverride {
	@Override
	public void metodo() {
		System.out.println("Ola");
	}
}
