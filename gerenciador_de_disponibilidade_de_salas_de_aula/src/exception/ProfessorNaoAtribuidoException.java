package exception;

public class ProfessorNaoAtribuidoException extends Exception {
	private static final long serialVersionUID = -1072724250638521272L;
	public ProfessorNaoAtribuidoException(String msg) {
		super(msg);
	}
	public ProfessorNaoAtribuidoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
