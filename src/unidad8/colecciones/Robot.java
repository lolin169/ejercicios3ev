package unidad8.colecciones;

public class Robot {
	private String nombre;
	private int tiempoProceso;
	private int crono;

	public Robot(String nombre, int tiempoProceso) {
		this.nombre = nombre;
		this.tiempoProceso = tiempoProceso;
	}

	public void asignar(String producto, String hora) {
		System.out.println(nombre + " - " + producto + " [" + hora + "]");
		crono = tiempoProceso;
	}

	public boolean procesar() {
		if (crono > 0)
			crono--;
		return crono == 0;
	}

}
