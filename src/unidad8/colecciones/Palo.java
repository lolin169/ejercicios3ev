package unidad8.colecciones;

public enum Palo {
	S(4), H(3), D(2), C(1);

	int valor;

	private Palo(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}
}
