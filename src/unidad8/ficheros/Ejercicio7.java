package unidad8.ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import unidad8.colecciones.Palabras;

public class Ejercicio7 {

	public static void main(String[] args) {
		Palabras lemario = new Palabras();
		try (BufferedReader lector = new BufferedReader(new FileReader("archivos\\Lemario ES UTF-8.txt"))) {
			String linea;
			while ((linea = lector.readLine()) != null)
				lemario.añadePalabras(linea);
		} catch (IOException e) {
			System.out.println("Error en el archivo" + e.getMessage());
		}
		Set<String> conjunto = null;
		try (BufferedReader lector1 = new BufferedReader(new FileReader("archivos\\El Quijote UTF-8.txt"))) {
			conjunto = new TreeSet<>();
			String linea;
			while ((linea = lector1.readLine()) != null) {
				for (String l : linea.toLowerCase().split("\\P{L}+")) {
					if (!lemario.comprueba(l))
						conjunto.add(l);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Palabras añadidas al conjunto que tiene un tamaño de " + conjunto.size() + " palabras.");
//		AL SOBREPASAR EL LÍMITE DE LA CONSOLA, CREAMOS UN ARCHIVO DE  APOYO PARA VISUALIZAR LAS PALABRAS DEL CONJUNTO
//		try(PrintWriter escritor= new PrintWriter ( new FileWriter("archivos\\ejercicio7.txt"))){
//			conjunto.forEach(n->escritor.println(n));
//			System.out.println("Archivo de texto escrito correctamente.");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
