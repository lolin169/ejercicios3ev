package unidad8.colecciones;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ejercicio6 {
	static Map<String, HashSet<Integer>> agenda = new HashMap<>();
	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("> ");
		while (ejecutar(teclado.nextLine()));
		System.out.println("Fin del programa");
		teclado.close();
	}

	static boolean ejecutar(String respuesta) {
		Scanner sc = new Scanner(respuesta);
		try {
			String comando;
			String[] cmd = new String[respuesta.length()];
			if (respuesta.contains(":")) {
				cmd = respuesta.split(":");
				comando = cmd[0].trim();
			} else
				comando = sc.next();
			switch (comando) {
			case "salir":
				sc.close();
				return false;
			case "buscar":
			case "contactos":
				mostrar(cmd[1].trim());
				break;
			case "eliminar:":
				eliminar(sc);
				break;
			default:
				añadir(respuesta);
				break;
			}
		} catch (NoSuchElementException e) {
			System.out.println("Datos de entrada inválidos");
			String[] a = respuesta.split(" ");
			eliminar(new Scanner(a[0]));
		} catch (Exception e) {
			System.out.println("Comando erróneo");
		}
		System.out.print("> ");
		sc.close();
		return true;
	}

	static void eliminar(Scanner sc) {
		String nombre = sc.next();
		if (sc.hasNext())
			System.out.println("Demasiados datos introducidos");
		else if (!agenda.containsKey(nombre))
			System.out.println("Contacto no existente en la agenda");
		else
			System.out.println("¿Desea ELIMINAR definitivamente el contacto " + nombre + "?");
		if (teclado.nextLine().equalsIgnoreCase("si")) {
			agenda.remove(nombre, agenda.get(nombre));
			System.out.println("El contacto \"" + nombre + "\" ha sido eliminado");
		} else
			System.out.println("Se ha cancelado el borrado");
	}

	static void mostrar(String nom) {
		try {
			if (!agenda.containsKey(nom))
				System.out.println("El contacto no existe");
			else if (agenda.get(nom).size() < 1)
				System.out.println(agenda.get(nom) + ": No hay números para este contacto todavía");
			else
				System.out.println((agenda.get(nom).size() == 1 ? "Teléfono: " : "Teléfonos: ")
						+ agenda.get(nom).toString().replace("[", "").replace("]", ""));
		} catch (Exception e) {
			if (agenda.size() == 0)
				System.out.println("Agenda vacía");
			else
				for (Entry<String, HashSet<Integer>> numeros : agenda.entrySet()) {
					if (numeros.getValue().size() < 1)
						System.out.println(numeros.getKey() + ": No hay números para este contacto todavía");
					else
						System.out
								.println("> " + numeros.toString().replace("=", ":").replace("[", "").replace("]", ""));
				}
		}
	}

	static void añadir(String respuesta) {
		String[] datos = respuesta.split(":");
		String nombre = datos[0].trim();
		String nu = datos[1].trim();
		String[] num = nu.split(" ");
		int i = 0;
		HashSet<Integer> numeros = new HashSet<>();
		if (agenda.get(nombre) == null)
			agenda.put(nombre, numeros);
		numeros = agenda.get(nombre);
		while (i < num.length) {
			int numero = Integer.parseInt(num[i]);
			if (contiene(agenda, numero))
				System.out.println("Número ya existente en la agenda");
			else if (!agenda.get(nombre).add(numero))
				System.out.println(nombre + " " + numero + ".Número ya existente en este contacto");
			i++;
		}
	}

	static boolean contiene(Map<String, HashSet<Integer>> agenda, int numero) {
		for (HashSet<Integer> numeros : agenda.values())
			return numeros.contains(numero);
		return false;
	}
}