package unidad8.colecciones;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Ejercicio8 {
	static Map<String, Set<String>> jugadores = new LinkedHashMap<>();
	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		while (juego());
		puntuacion();
		System.out.println("Fin del programa");
	}

	static void puntuacion() {
		for (Entry<String, Set<String>> jugador : jugadores.entrySet()) {
			int valor = 0;
			for (String carta : jugador.getValue())
				valor += equivale(carta);
			System.out.println(jugador.getKey() + ": " + valor);
		}
	}

	static int equivale(String carta) {
		int valor = 0;
		try {
			if (carta.length() < 2 || carta.length() > 3)
				throw new InputMismatchException("Entrada errónea");
			else if (carta.length() == 3) {
				valor = Integer.parseInt(carta.substring(0, 2)) * Palo.valueOf(carta.substring(2)).getValor();
			} else {
				for (char a : carta.toCharArray()) {
					if (a == 'Q' || a == 'J' || a == 'K' || a == 'A')
						valor = Rango.valueOf(String.valueOf(a)).getValor();
					else if ((a == 'C') || a == 'H' || a == 'S' || a == 'D')
						valor *= Palo.valueOf(String.valueOf(a)).getValor();
					else
						valor = Character.getNumericValue(a);
				}
			}
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		return valor;
	}

	static boolean juego() {
		String respuesta = teclado.nextLine();
		if (respuesta.equals("fin"))
			return false;
		Scanner carta = new Scanner(respuesta);
		if (respuesta.contains(":")) {
			String nombre = carta.next().replace(":", "");
			while (carta.hasNext()) {
				if (jugadores.get(nombre) == null) {
					Set<String> mano = new LinkedHashSet<>();
					jugadores.put(nombre, mano);
				}
				jugadores.get(nombre).add(carta.next());
			}
			carta.close();
		} else
			System.out.println("Datos de entrada erróneos");
		return true;
	}
}
