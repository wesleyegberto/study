package interface_abstractclass.interfaces.multi_heranca;

public interface ISubSuper extends ISuper1, ISuper2 // Com interface pode-se
// estender mais de uma
// interface
{ // Sem implementar os m�todos
	String msg = "Sub interface estendendo interface super 1 e 2";

	// public String getMsg1(); //� permitido Override de m�todos nas interfaces

	public String get2Msg();
}
