package unidad8.colecciones;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Ejercicio9 {
	static Scanner teclado = new Scanner(System.in);
	static Queue<Robot> libres = new LinkedList<>();
	static Queue<Robot> ocupados = new LinkedList<>();
	static Queue<String> productos = new LinkedList<>();
	static LocalTime hora;

	public static void main(String[] args) {

		while (entrada());
		while (!productos.isEmpty()) {
			hora = hora.plusSeconds(1);
			comprobarocupados();
			String producto = productos.poll();
			if (libres.isEmpty())
				productos.offer(producto);
			else {
				Robot p = libres.poll();
				p.asignar(producto, hora.toString());
				ocupados.offer(p);
			}
		}
	}

	private static void comprobarocupados() {
		Iterator<Robot> i = ocupados.iterator();
		while (i.hasNext()) {
			Robot r = i.next();
			if (r.procesar()) {
				libres.offer(r);
				i.remove();
			}
		}
	}

	private static boolean entrada() {
		String ent = teclado.nextLine();
		Scanner entrada = new Scanner(ent);
		if (ent.contains(";")) {
			entrada.useDelimiter(";");
			while (entrada.hasNext()) {
				Scanner robot = new Scanner(entrada.next());
				robot.useDelimiter("-");
				libres.offer(new Robot(robot.next(), robot.nextInt()));
				robot.close();
			}
		} else if (ent.contains(":")) {
			Scanner horas = new Scanner(ent);
			horas.useDelimiter(":");
			hora = LocalTime.of(horas.nextInt(), horas.nextInt(), horas.nextInt());
			horas.close();
		} else if (ent.equals("fin")) {
			entrada.close();
			return false;
		} else {
			productos.offer(ent);
		}
		entrada.close();
		return true;
	}

//  ROB-15;SS2-10;NX8000-3
//	8:00:00
//	disco duro
//	procesador
//	memoria RAM
//	placa base
//	fin
}
