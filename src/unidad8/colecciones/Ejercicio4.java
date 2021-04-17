package unidad8.colecciones;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ejercicio4 {

	static Scanner teclado = new Scanner(System.in);
	static Palabras texto = new Palabras();

	public static void main(String[] args) {

		while (ejecutar(texto))
			;
		System.out.println("Fin del programa");

	}

	static boolean ejecutar(Palabras texto) {
		Scanner cmd = new Scanner(teclado.nextLine());
		try {
			switch (cmd.next("añadir:|lista|borrar|borrar:|fin")) {
			case "añadir:":
				texto.añadePalabras(cmd.nextLine());
				break;
			case "lista":
				listar(cmd);
				break;
			case "borrar":
			case "borrar:":
				borrar(cmd);
				break;
			case "fin":
				return false;
			}
		} catch (NoSuchElementException e) {
			System.out.println("Error de comando de entrada");
		} finally {
			cmd.close();
		}
		return true;
	}

	static void listar(Scanner cmd) {
		try {
			int n = cmd.nextInt();
			if (texto.getMapa().get(n) == null)
				System.out.println("No existen palabras con ese tamaño");
			else
				System.out.println(texto.getMapa().get(n));
		} catch (InputMismatchException e) {
			System.out.println("El dato de entrada no es un número entero");
		}
	}

	static void borrar(Scanner cmd) {
		texto.getMapa().clear();
		if (cmd.hasNext())
			texto.añadePalabras(cmd.nextLine().trim());
	}
}
