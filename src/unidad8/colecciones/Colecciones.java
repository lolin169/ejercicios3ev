package unidad8.colecciones;

import java.util.Iterator;
import java.util.Set;
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

}
