package unidad8.colecciones;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Colecciones {

	static void eliminarLasDeLongitudPar(Set<String> conjunto) {

		conjunto.removeIf(p -> (p.length() % 2 == 0));

//		Iterator<String> i= conjunto.iterator();
//		while(i.hasNext())
//			if(i.next().length()%2==0)
//				i.remove();
	}

	static boolean contieneImpares(Set<Integer> numeros) {
//		Una manera de hacerlo:
//		
//		Predicate<Integer> n = new Predicate<Integer>() {
//			@Override
//			public boolean test(Integer t) {
//				if (t % 2 == 1)
//					return true;
//				return false;
//			};
//		};
//		for (int x : numeros)
//			if (n.test(x))
//				return true;
//		return false;

//		Otra manera:

//		Iterator<Integer> i= numeros.iterator();
//		while(i.hasNext()) {
//			if(i.next()%2==1)
//				return true;
//		}
//		return false;

//		Otra:

		for (int n : numeros) {
			if (n % 2 != 0)
				return true;
		}
		return false;
	}

	static boolean valoresUnicos(Map<String, String> mapa) {

		List<String> lista = new ArrayList<>();
		Set<String> set = new HashSet<>();
		for (String s : lista)
			if (!set.add(s))
				return false;
		return true;

//		Otra forma:
//		List<String> lista = new ArrayList<>();
//		Set<String> set = new HashSet<>(lista);
//		return lista.size()==set.size();

//		Otra forma:
//		return mapa.values().stream().distinct().count()==mapa.values().size();

	}

	static boolean algunaSeRepiteAlMenos3Veces(List<String> lista) {

		Map<String, Integer> mapa = new TreeMap<>();
		for (String palabra : lista)
			if (mapa.containsKey(palabra))
				mapa.put(palabra, mapa.get(palabra) + 1);
			else
				mapa.put(palabra, 1);
		return mapa.values().stream().anyMatch(n -> n >= 3);
	}

	static void negativosAbajoPositivosArriba(LinkedList<Integer> numeros) {
		PriorityQueue<Integer> pila = new PriorityQueue<>();
		numeros.stream().forEach(n -> {
			if (n < 0) {
				pila.offer(n);
			}
		});
//		Iterator <Integer> i= numeros.iterator();
//		int n=0;
//		while(i.hasNext()) {
//			n=i.next();
//			if(n<0)
//			 pila.push(n);
//		}
		numeros.removeAll(pila);
		pila.forEach(num -> numeros.offer(num));
		System.out.println("Tope -> " + numeros);

	}

	static int moda(ArrayList<Integer> lista) {
		Map<Integer, Integer> mapa = new HashMap<>();
		lista.stream().distinct().forEach(n -> mapa.put(n, 0));
		for (int n : lista)
			mapa.put(n, mapa.get(n) + 1);
//		mapa.entrySet().forEach(n -> {if (n.getValue() == mapa.values().stream().max(Integer::compare).get()) {System.out.println(n.getKey());}});
//		for(Entry<Integer,Integer> e:mapa.entrySet())
//			if(e.getValue()==mapa.values().stream().max(Integer::compare).get())
//				return (int)e.getKey();
		int max = 0;
		for (int i : mapa.values())
			if (i > max)
				max = i;
		for (Entry<Integer, Integer> e : mapa.entrySet())
			if (e.getValue() == max)
				return e.getKey();
		return 0;
	}

	public static void eliminarSiMayoresEncima(Deque<Integer> pila) {
		Deque<Integer> pila2 = new ArrayDeque<>();
		while (!pila.isEmpty()) {
			if (pila2.isEmpty())
				pila2.push(pila.pop());
			int n = pila.pop();
			if (n >= pila2.peek())
				pila2.push(n);
		}
		while (!pila2.isEmpty())
			pila.push(pila2.poll());
	}

	public static Map<String, Integer> interseccion(Map<String, Integer> mapa1, Map<String, Integer> mapa2) {
		Map<String, Integer> mapa = new LinkedHashMap<>();
//		mapa1.entrySet().forEach(n-> {if(mapa2.entrySet().contains(n)) {mapa.put(n.getKey(), n.getValue());}});

		for (Entry<String, Integer> n : mapa1.entrySet())
			if (mapa2.entrySet().contains(n))
				mapa.put(n.getKey(), n.getValue());
		return mapa;
	}

	public static int ValorMenosRepetido(Map<String, Integer> mapa) {
		Map<Integer, Integer> mapa1 = new TreeMap<>();
		for (Entry<String, Integer> n : mapa.entrySet()) {
			if (mapa1.get(n.getValue()) != null)
				mapa1.put(n.getValue(), mapa1.get(n.getValue()) + 1);
			else
				mapa1.put(n.getValue(), 1);
		}
		for (Entry<Integer, Integer> n : mapa1.entrySet())
			if (n.getValue() == 1)
				return n.getKey();
		return 0;
	}

}
