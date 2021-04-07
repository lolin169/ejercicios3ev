package unidad8.colecciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Ejercicio1 {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		List<String> lista = new ArrayList<>();
		Set<String> conjunto = new LinkedHashSet<>();
		int n = Integer.parseInt(in.readLine());

		for (int i = 0; i < n; i++) {
			String nombre = in.readLine();
			conjunto.add(nombre);
			if (!lista.contains(nombre))
				lista.add(nombre);
		}

		lista.forEach(nom -> System.out.println(nom));
		System.out.println("-----------------");
		conjunto.forEach(nom -> System.out.println(nom));
	}
}
