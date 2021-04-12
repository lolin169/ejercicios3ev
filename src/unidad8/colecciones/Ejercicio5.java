package unidad8.colecciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Ejercicio5 {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static Queue<Integer> cola = new LinkedList<>();
	static Scanner linea = new Scanner(in);

	public static void main(String[] args) throws IOException {
		int N = linea.nextInt();
		int K = linea.nextInt();
		int X = linea.nextInt();
		linea = new Scanner(in.readLine());

		for (int i = 0; i < N; i++) {
			cola.add(linea.nextInt());
		}

		Iterator<Integer> i = cola.iterator();
		while (K != 0 && !cola.isEmpty()) {
			i.next();
			i.remove();
			K--;
		}

		if (cola.contains(X))
			System.out.println("true");
		else {
			int min;
			min = cola.peek();
			for (int a : cola) {
				if (min > a)
					min = a;
			}
			System.out.println(min);
		}
	}
}
