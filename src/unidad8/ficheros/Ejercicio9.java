package unidad8.ficheros;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import unidad7.Libro;
import unidad7.Publicación;
import unidad7.Revista;

public class Ejercicio9 {

	public static void main(String[] args) {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("archivos\\Biblioteca.obj")))) {
			ArrayList<Publicación> publicaciones = new ArrayList<>();
			publicaciones.add(new Libro(03, "Saber educar", 1995, "J.L.Rodríguez"));
			publicaciones.add(new Libro(02, "Aprende a relajarte", 2005, "M.D.Álvarez"));
			publicaciones.add(new Revista(01, "Culebrones", 2021, 02, 15, 243));
			publicaciones.add(new Publicación(02, "El Barco Antiguo", 2003));
			publicaciones.add(new Libro(07, "El Patio", 1995, "Carlos De andrés"));
			if (publicaciones.size() > 0) {
				for (Publicación p : publicaciones)
					out.writeObject(p);
			}
			out.writeObject(new Libro(04, "Prueba Entrada/Salida", 2021, "Juan Antonio Díaz"));
			System.out.println("Archivo .obj creado correctamente");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (ObjectInputStream lector = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("archivos\\Biblioteca.obj")))) {
			Object aux;
			boolean eof = false;
			while (!eof) {
				try {
					aux = lector.readObject();
					if (aux instanceof Publicación)
						System.out.println(aux);
				} catch (EOFException e) {
					eof = true;
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
