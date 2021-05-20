package unidad8.ficheros;

import java.io.File;
import java.util.Scanner;

public class Ejercicio1 {

	public static void main(String[] args) {
		File nombre;
		if (args.length == 1)
			nombre = new File(args[0]);
		else {
			Scanner teclado = new Scanner(System.in);
			nombre = new File(teclado.nextLine());
			teclado.close();
		}
		if (!nombre.exists())
			System.out.println("La ruta introducida no existe o es errónea");
		else {
			if (nombre.isDirectory())
				System.out.println("El nombre del DIRECTORIO es: " + nombre.getName());
			else
				System.out.println("El nombre del ARCHIVO es: " + nombre.getName() + "\nEl tamaño es de: "
						+ nombre.length() + " bytes");
			System.out.println("Los permisos son: " + (nombre.canExecute() ? "EJECUTABLE " : "")
					+ (nombre.canRead() ? "LECTURA " : "") + (nombre.canWrite() ? " ESCRITURA." : "."));
		}
	}
}
