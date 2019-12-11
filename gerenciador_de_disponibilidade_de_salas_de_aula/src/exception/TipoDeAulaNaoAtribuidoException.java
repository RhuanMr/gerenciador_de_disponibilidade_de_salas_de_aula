package exception;

public class TipoDeAulaNaoAtribuidoException extends Exception {
	private static final long serialVersionUID = 6252358316681727422L;
		public TipoDeAulaNaoAtribuidoException(String msg) {
			super(msg);
		}
		public TipoDeAulaNaoAtribuidoException(String msg, Throwable cause) {
			super(msg, cause);
		}
}