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

	public static Map<String, Integer> cuentaPares(List<String> lista) {
		Map<String, Integer> mapa = new HashMap<>();
		for (String p : lista) {
			for (int i = 0; i < p.length() - 1; i++) {
				String par = String.valueOf(p.charAt(i)) + String.valueOf(p.charAt(i + 1));
				if (!mapa.containsKey(par))
					mapa.put(par, 1);
				else
					mapa.put(par, mapa.get(par) + 1);
			}
		}
		return mapa;
	}

	public static int contarComunes(List<Integer> lista1, List<Integer> lista2) {

//	NO FUNCIONA PORQUE SI HAY UN NÚMERO REPETIDO LO METE POR PRIMERA VEZ IGUAL	
//		Set<Integer> set= new HashSet<>();
//		lista1.retainAll(lista2);
//		set.addAll(lista1);
//		set.addAll(lista2);
//		lista1.stream().distinct().forEach(n-> set.add(n));
//		lista2.stream().distinct().forEach(n->set.add(n));

		Map<Integer, Integer> mapa = new HashMap<>();
		for (int num : lista1) {
			for (int i = 0; i < lista2.size(); i++) {
				if (num == lista2.get(i))
					if (mapa.get(num) == null)
						mapa.put(num, 1);
					else
						mapa.put(num, mapa.get(num) + 1);
			}
		}
		mapa.values().removeIf(n -> n > 1);
		return mapa.size();
	}

	public static Deque<Integer> duplicar(Deque<Integer> numeros) {
//		Deque<Integer> pila2 = new ArrayDeque<>();
//		System.out.println("Tope -> " + numeros);
//		while (!numeros.isEmpty()) {
//			pila2.push(numeros.peek());
//			pila2.push(numeros.pop());
//		}
//		while (!pila2.isEmpty())
//			numeros.push(pila2.pop());
//		return numeros;
		
		Queue <Integer> aux= new LinkedList<>();
		while(!numeros.isEmpty()) {
			aux.offer(numeros.peek());
			aux.offer(numeros.pop());	
		}
		
		return (Deque<Integer>) aux;
	}

//	public static void main(String[] args) {
//
//		LinkedList<Integer> numeros = new LinkedList<>();
//		ArrayList<Integer> numeros1 = new ArrayList<>();
//		Deque<Integer> numeros3 = new LinkedList<>();
////		Map<String, Integer> mapa1 = new LinkedHashMap<>();
//		List<String> lista = new ArrayList<>();
//
////		mapa1.put("Juan",33);
////		mapa1.put("Hugo",29);
////		mapa1.put("Ana",45);
////		mapa1.put("Luis",47);
////		mapa1.put("Mario",33);
////		mapa1.put("Rosa",29);
////		mapa1.put("Carmen",33);
////		mapa1.put("Elena",59);
////		mapa1.put("Benito",33);
//
//		numeros3.push(7);
//		numeros3.push(6);
//		numeros3.push(2);
//		numeros3.push(9);
//		numeros3.push(5);
////		numeros1.add(2);
////		numeros1.add(12);
////		numeros1.add(14);
////		numeros1.add(9);
////		numeros1.add(7);
////		numeros1.add(17);
////		System.out.println("Tope -> " + numeros);
////		negativosAbajoPositivosArriba(numeros);
////		eliminarSiMayoresEncima(numeros);
////		System.out.println(ValorMenosRepetido(mapa1));
//		lista.add("banana");
//		lista.add("pera");
//		lista.add("melón");
//		lista.add("o");
//		lista.add("sandía");
//		System.out.println(cuentaPares(lista));
////		System.out.println(contarComunes(numeros,numeros1));
//		System.out.println(duplicar(numeros3));
//	}

}
