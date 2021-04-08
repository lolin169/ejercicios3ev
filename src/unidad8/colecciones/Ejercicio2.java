package unidad8.colecciones;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Ejercicio2 {

	public static void main(String[] args) {
		Random ran = new Random();
		List<Integer> lista = new ArrayList<>();
		for (int i = 0; i < 100; i++)
			lista.add(ran.nextInt(100) + 1);
		Iterator<Integer> i = lista.iterator();
		System.out.println("ArrayList: (Sin Ordenación y Repetidos)");
		while (i.hasNext())// Iteración 1
			System.out.print(i.next() + " ");
		System.out.println("\nHashSet: (Sin Ordenación(ordenados por tabla Hash) y Sin Repetir)");
		Set<Integer> conjunto = new HashSet<>();
		conjunto.addAll(lista);
		conjunto.forEach(numero -> System.out.print(numero + " "));// Iteración 2
		System.out.println("\nTreeSet: (Ordenados y Sin Repetir)");
		Set<Integer> conjunto2 = new TreeSet<>();
		conjunto2.addAll(lista);
		for (int num : conjunto2)
			System.out.print(num + " ");

	}

}
