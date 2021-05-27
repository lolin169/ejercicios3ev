package unidad8.ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Ejercicio10 {

	static Map<String, HashSet<Integer>> agenda = new TreeMap<>();
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
			if (respuesta.contains(":"))
				cmd = respuesta.split(":");
			else
				cmd = respuesta.split(" ");
			comando = cmd[0].trim();
			switch (comando) {
			case "salir":
				sc.close();
				return false;
			case "buscar":
				mostrar(cmd[1].trim());
				break;
			case "contactos":
				mostrar(null);
				break;
			case "eliminar":
				eliminar(cmd[1].trim());
				break;
			case "guardar":
				guardar(cmd[1].trim());
				break;
			case "cargar":
				cargar(cmd[1].trim());
				break;
			default:
				añadir(respuesta);
				break;
			}
		} catch (NoSuchElementException e) {
			System.out.println("Datos de entrada inválidos");
			String[] a = respuesta.split(" ");
			eliminar(a[0]);
		} catch (Exception e) {
			System.out.println("(" + respuesta + ")Comando erróneo");
		}
		System.out.print("> ");
		sc.close();
		return true;
	}

	private static void cargar(String ruta) {
		try (BufferedReader lector = new BufferedReader(new FileReader(ruta))) {
			String linea = "";
			while ((linea = lector.readLine()) != null) {
				if (linea.contains("-") && linea.contains("[") && linea.contains("]")) {
					String num = linea.substring(linea.indexOf("[") + 1, linea.indexOf("]"));
					String[] numeros = num.split(",");
					HashSet<Integer> set = new HashSet<>();
					for (String n : numeros)
						set.add(Integer.parseInt(n.trim()));
					agenda.put(linea.substring(0, linea.indexOf("-")), set);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado (" + ruta + ")");
		} catch (IOException e) {
			System.out.println("Archivo erróneo o dañado(" + ruta + ")");
		}

	}

	private static void guardar(String ruta) {
		BufferedReader lector = null;
		PrintWriter escritor = null;
		File archivo = new File(ruta);
		Map<String, String> recuperado = null;
		try {
			if (archivo.exists()) {
				lector = new BufferedReader(new FileReader(archivo));
				recuperado = new LinkedHashMap<>();
				lector.readLine();
				lector.readLine();
				String linea = "";
				while ((linea = lector.readLine()) != null) {
					String nombre = linea.substring(0, linea.indexOf("-"));
					if (!agenda.containsKey(nombre)) {
						recuperado.put(nombre, linea.substring(linea.indexOf("-") + 1, linea.indexOf("]") + 1));
					}
				}
			}
			escritor = new PrintWriter(new FileWriter(ruta));
			escritor.write("AGENDA\n-----------------\n");
			escribirAgenda(escritor, recuperado);
			System.out.println("Agenda escrita y guardada correctamente en el sistema.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (escritor != null)
				escritor.close();
		}
	}

	private static void escribirAgenda(PrintWriter escritor, Map<String, String> recuperado) {
		if (recuperado != null) {
			for (Entry<String, String> e : recuperado.entrySet())
				escritor.println(e.getKey() + "-" + e.getValue());
		}
		for (Entry<String, HashSet<Integer>> e : agenda.entrySet())
			escritor.println(e.getKey() + "-" + e.getValue());
	}

	static void eliminar(String nombre) {

		if (!agenda.containsKey(nombre))
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
			if (nom == null) {
				for (Entry<String, HashSet<Integer>> numeros : agenda.entrySet()) {
					if (numeros.getValue().size() < 1)
						System.out.println(numeros.getKey() + ": No hay números para este contacto todavía");
					else
						System.out
								.println("> " + numeros.toString().replace("=", ":").replace("[", "").replace("]", ""));
				}
			}

			else if (!agenda.containsKey(nom))
				System.out.println("El contacto no existe");
			else if (agenda.get(nom).size() < 1)
				System.out.println(agenda.get(nom) + ": No hay números para este contacto todavía");
			else
				System.out.println((agenda.get(nom).size() == 1 ? "Teléfono: " : "Teléfonos: ")
						+ agenda.get(nom).toString().replace("[", "").replace("]", ""));
		} catch (Exception e) {
			if (agenda.size() == 0)
				System.out.println("Agenda vacía");
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
