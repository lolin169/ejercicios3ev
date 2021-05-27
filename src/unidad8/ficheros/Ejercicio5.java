package unidad8.ficheros;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Ejercicio5 {

	public static void main(String[] args) {
		PrintWriter escritor = null;
		boolean eof = false;
		try (DataInputStream lector = new DataInputStream(
				new BufferedInputStream(new FileInputStream("archivos\\ejercicio4.bin")))) {
			escritor = new PrintWriter(new FileWriter("archivos\\ejercicio5.txt"));
			while (!eof) {
				try {
					escritor.println("Ruta:" + lector.readUTF() + " - " + lector.readInt() + " lineas - "
							+ lector.readInt() + " palabras - " + lector.readInt() + " letras.");
				} catch (EOFException e) {
					eof = true;
					escritor.close();
					System.out.println("Archivo de texto creado correctamente");
				}
			}
		} catch (IOException e) {
			System.out.println("Error, el archivo no se encuentra o es erróneo");
		}

	}
}
