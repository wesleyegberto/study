package exceptions;

public class MinhaExceptionVMError extends VirtualMachineError {
	public MinhaExceptionVMError() {
		super("Error of memory.");
	}

	public static void main(String[] args) {
		MinhaExceptionVMError t = new MinhaExceptionVMError();

		throw new MinhaExceptionVMError();
	}
}
