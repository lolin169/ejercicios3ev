package unidad8.ficheros;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Ejercicio2 {

	public static void main(String[] args) {
		leerarchivo("Archivos\\El Quijote UTF-8.txt", true);
		leerarchivo("Archivos\\El Quijote UTF-8.txt", false);
	}

	private static void leerarchivo(String path, boolean b) {
		try (FileInputStream archivo = new FileInputStream(path)) {
			if (!b) {
				long t0 = System.currentTimeMillis();
				while (archivo.read() != -1);
				long t1 = System.currentTimeMillis();
				archivo.close();
				System.out.println("Leer el archivo SIN búffer tarda " + (t1 - t0) / 1000f + " segundos");
			} else {
				BufferedInputStream in = new BufferedInputStream(archivo);
				long t0 = System.currentTimeMillis();
				while (in.read() != -1);
				long t1 = System.currentTimeMillis();
				in.close();
				System.out.println("Leer el archivo CON búffer tarda " + (t1 - t0) / 1000f + " segundos");
			}
		} catch (IOException e) {
			System.out.println("Error" + e.getMessage());
		}
	}
}
