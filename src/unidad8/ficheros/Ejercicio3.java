package unidad8.ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio3 {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		String path;
		if (args.length < 1)
			path = teclado.nextLine();
		else
			path = args[0];
		teclado.close();
		try (BufferedReader lector = new BufferedReader(new FileReader(path))) {
			String linea;
			int lineas = 0;
			int palabras = 0;
			int letras = 0;
			while ((linea = lector.readLine()) != null) {
				lineas++;
				for (String palabra : linea.split("\\P{L}+")) {
					palabras++;
					letras += palabra.length();
				}
			}
			System.out.println(
					"El archivo tiene " + lineas + " lineas, " + palabras + " palabras, " + letras + " letras.");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

//  archivos\El Quijote UTF-8.txt
