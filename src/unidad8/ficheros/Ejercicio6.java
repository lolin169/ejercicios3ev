package unidad8.ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Ejercicio6 {
	static Charset codificacion;
	static File f;

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Escoge el archivo:\n" + "1.-El Quijote UTF-8.txt\n" + "2.-El Quijote ISO-8859.txt");
		int eleccion = teclado.nextInt();
		teclado.close();
		if (eleccion == 1)
			abrir("archivos\\El Quijote UTF-8.txt");
		else if (eleccion == 2)
			abrir("archivos\\El Quijote ISO-8859-1.txt");
		else
			System.out.println("Elección errónea");
	}

	static void abrir(String path) {
		f = new File(path);
		codifica(f);
		try (BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(f), codificacion))) {
			String linea;
			while ((linea = lector.readLine()) != null) {
				System.out.println(linea);
			}
			System.out.println(codificacion);
		} catch (IOException e1) {
		}
	}

	static void codifica(File f) {
		if (f.getName().contains("ISO"))
			codificacion = StandardCharsets.ISO_8859_1;
		else if (f.getName().contains("UTF"))
			codificacion = StandardCharsets.UTF_8;
	}
}