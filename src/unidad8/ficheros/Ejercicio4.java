package unidad8.ficheros;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio4 {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		String path;
		int lineas;
		int palabras;
		int letras;
		if (args.length < 1)
			path = teclado.nextLine();
		else
			path = args[0];
		teclado.close();
		try (BufferedReader lector = new BufferedReader(new FileReader(path))) {
			String linea;
			lineas = 0;
			palabras = 0;
			letras = 0;
			while ((linea = lector.readLine()) != null) {
				lineas++;
				for (String palabra : linea.split("\\P{L}+")) {
					palabras++;
					letras += palabra.length();
				}
			}
			System.out.println(
					"El archivo tiene " + lineas + " lineas, " + palabras + " palabras, " + letras + " letras.");
			DataOutputStream out = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream("ejercicio4.bin")));
			out.writeUTF(path);
			out.writeInt(lineas);
			out.writeInt(palabras);
			out.writeInt(letras);
			out.close();
			System.out.println("Archivo binario creado y escrito correctamente");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

//    archivos\El Quijote UTF-8.txt
