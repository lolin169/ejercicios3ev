package unidad8.ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import unidad6.Elemento;
import unidad6.Entrenador;
import unidad6.Pokemon;
import unidad6.PokemonRepetidoException;

public class TorneoV2 {
	static Scanner teclado = null;
	static BufferedReader ejercicio = null;
	private static ArrayList<Entrenador> entrenadores = new ArrayList<Entrenador>();

	public static void main(String[] args) {

		String respuesta = "";
		try {
			if (args.length < 1)
				teclado = new Scanner(System.in);
			else {
				ejercicio = new BufferedReader(new FileReader(args[0]));
				teclado = new Scanner(ejercicio);
			}
			respuesta = teclado.nextLine();
			while (!respuesta.equalsIgnoreCase("torneo") && !respuesta.equalsIgnoreCase("fin")) {
				Scanner linea = new Scanner(respuesta);
				String nombreent = linea.next();
				String nombre = linea.next();
				String elemento = linea.next();
				int salud = linea.nextInt();
				if (linea.hasNext()) {
					System.out.println("Error, demasiados datos en la línea");
				} else {
					linea.close();
					Entrenador nuevoe = new Entrenador(nombreent);
					if (entrenadores.contains(nuevoe)) {
						entrenadores.get(entrenadores.indexOf(nuevoe)).addPokemon(addPok(nombre, elemento, salud));
					} else {
						nuevoe.addPokemon(addPok(nombre, elemento, salud));
						entrenadores.add(nuevoe);
					}
				}
				respuesta = teclado.nextLine();
			}
			respuesta = teclado.nextLine();
			while (respuesta != null && (!respuesta.equalsIgnoreCase("fin") || respuesta.equalsIgnoreCase("torneo"))) {
				Elemento ele = Elemento.valueOf(respuesta.toUpperCase());
				for (Entrenador e : entrenadores)
					contieneElemento(e, ele);
				respuesta = teclado.nextLine();
			}

			Collections.sort(entrenadores);
			for (Entrenador e : entrenadores)
				System.out.println(e.toString());
			System.out.println("Fin.");
		} catch (InputMismatchException e) {
			System.out.println("Error, el dato no es un número entero.");
		} catch (IllegalArgumentException e) {
			System.out.println("Error, el dato no es un elemento correcto.[" + e.getMessage() + "]");
		} catch (NoSuchElementException e) {
			System.out.println("Error, número de datos inferior.");
		} catch (PokemonRepetidoException e) {
			System.out.println(
					"El pokemon introducido ya estaba en la colección de éste entrenador.[" + e.getMessage() + "]");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} finally {
			if (ejercicio != null)
				try {
					ejercicio.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public static Pokemon addPok(String nombre, String elemento, int salud) {
		try {
			Elemento ele = Elemento.valueOf(elemento.toUpperCase());
			return new Pokemon(nombre, ele, salud);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(elemento);
		}
	}

	public static void contieneElemento(Entrenador e, Elemento elemento) {
		boolean contiene = false;
		for (Pokemon p : e.getPokemons()) {
			if (p.getElemento().equals(elemento))
				contiene = true;
		}
		if (contiene) {
			e.setInsignias(1);
		} else {
			Iterator<Pokemon> i = e.getPokemons().iterator();
			while (i.hasNext()) {
				Pokemon p = i.next();
				p.setSalud(-10);
				if (p.getSalud() <= 0) {
					i.remove();
				}
			}
		}
	}
}
