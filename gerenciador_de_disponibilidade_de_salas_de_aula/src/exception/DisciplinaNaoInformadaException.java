package exception;

public class DisciplinaNaoInformadaException extends Exception{

	public DisciplinaNaoInformadaException(String msg) {
		super(msg);
	}
	public DisciplinaNaoInformadaException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
