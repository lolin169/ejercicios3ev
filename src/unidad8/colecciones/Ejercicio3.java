package unidad8.colecciones;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Ejercicio3 {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		String linea = teclado.nextLine();
		Set<String> norepeat = new TreeSet<>();
		Set<String> repeat = new TreeSet<>();
		for (String palabra : linea.split("\\P{L}+")) {
			if (!norepeat.add(palabra))
				repeat.add(palabra);
		}
		norepeat.removeAll(repeat);
		System.out.println("Palabras  NO repetidas: " + norepeat);
		System.out.println("Palabras  repetidas: " + repeat);
		teclado.close();
	}

}
