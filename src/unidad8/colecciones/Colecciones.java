package unidad8.colecciones;

import java.util.Iterator;
import java.util.Set;

public class Colecciones {

	static void eliminarLasDeLongitudPar(Set<String> conjunto) {

		conjunto.removeIf(p -> (p.length() % 2 == 0));

//		Iterator<String> i= conjunto.iterator();
//		while(i.hasNext())
//			if(i.next().length()%2==0)
//				i.remove();
	}

}
