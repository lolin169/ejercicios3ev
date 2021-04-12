package unidad8.colecciones;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Palabras {
	Map<Integer, Set<String>> mapa;

	public Palabras() {
		mapa = new TreeMap<>();
	}

	public Palabras(String palabras) {
		this();
		añadePalabras(palabras);
	}

	public void añadePalabras(String palabras) {
		for (String palabra : palabras.split("\\P{L}+"))
			añade(palabra);
	}

	public void añade(String palabra) {
		Set<String> palabras = mapa.get(palabra.length());
		if (mapa.get(palabra.length()) == null) {
			palabras = new TreeSet<>();
			mapa.put(palabra.length(), palabras);
		}
		palabras.add(palabra);
	}

	public Map<Integer, Set<String>> getMapa() {
		return mapa;
	}

	public boolean comprueba(String palabra) {
		try {
			return mapa.get(palabra.length()).contains(palabra);
		} catch (NullPointerException e) {
			return false;
		}
	}
}