package br.unifieo.sd.servidornomes;

public class IllegalDataException extends Exception {
	private static final long serialVersionUID = 5414311035048275885L;

	public IllegalDataException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public IllegalDataException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public IllegalDataException(String arg0) {
		super(arg0);
	}

	public IllegalDataException(Throwable arg0) {
		super(arg0);
	}
	
}
