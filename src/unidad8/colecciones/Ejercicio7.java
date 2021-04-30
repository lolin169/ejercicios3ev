package unidad8.colecciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Ejercicio7 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Set<Integer> conjunto1 = new TreeSet<>();
		Set<Integer> conjunto2 = new TreeSet<>();
		String[] numeros = in.readLine().split(" ");
		try {
			int n = Integer.parseInt(numeros[0]);
			int m = Integer.parseInt(numeros[1]);
			numeros = in.readLine().split(" ");
			int i = 0;
			if ((n + m) < numeros.length)
				throw new IndexOutOfBoundsException("Demasiados números para los especificados");

			while (n > 0) {
				conjunto1.add(Integer.parseInt(numeros[i]));
				n--;
				i++;
			}
			while (m > 0) {
				conjunto2.add(Integer.parseInt(numeros[i]));
				--m;
				i++;
			}

//			if (numeros[i]!=null)
//				System.out.println("Demasiados números para los especificados");

		} catch (NumberFormatException e) {
			System.out.println("Datos de entrada erróneos");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Menos números de los especificados");
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
//		for (int num : conjunto1) {
//			if (conjunto2.contains(num))
//				System.out.print(num + " ");
//		}

		conjunto1.retainAll(conjunto2);
		conjunto1.stream().distinct().forEach(n -> System.out.print(n + " "));
	}
}
