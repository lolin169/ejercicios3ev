package unidad8.colecciones;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Predicate;

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
	
	static boolean algunaSeRepiteAlMenos3Veces(List<String> lista){
		
		Map<String,Integer> mapa= new TreeMap<>();
		for(String palabra:lista)
			if(mapa.containsKey(palabra))
				mapa.put(palabra, mapa.get(palabra)+1);
			else
				mapa.put(palabra,1);
		return mapa.values().stream().anyMatch(n -> n >= 3);
	}

}
