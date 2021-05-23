package unidad8.ficheros;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ejercicio8 {

	public static void main(String args[]) {
		Scanner teclado = new Scanner(System.in);
		File ar = new File("archivos\\torneo.txt");
		String entrada = teclado.nextLine();
		Scanner datos = new Scanner(entrada);
		try (PrintWriter escritor = new PrintWriter(new FileWriter(ar))) {
			while (!entrada.equals("torneo")) {
				escritor.println(datos.next("\\p{L}+") + " " + datos.next("\\p{L}+") + " " + datos.next("\\p{L}+") + " "
						+ datos.nextInt());
				if (datos.hasNext())
					System.out.println("error de datos de entrada");
				entrada = teclado.nextLine();
				datos = new Scanner(entrada);
			}
			datos.close();
			if (entrada.equals("torneo")) {
				escritor.println("torneo");
				String elemento = teclado.nextLine();
				while (!elemento.equals("fin")) {
					escritor.println(elemento);
					elemento = teclado.nextLine();
				}
				teclado.close();
				escritor.println("fin");
			} else {
				System.out.println("Datos de entrada incorrectos");
				ar.delete();
				System.out.println("Torneo borrado, intenta meter los datos bien la próxima vez.");
			}

		} catch (IOException e) {
			ar.delete();
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			System.out.println("Datos de entrada incorrectos");
			ar.delete();
			System.out.println("Torneo borrado, intenta meter los datos bien la próxima vez.");
		}
	}
}
//archivos\\torneo.txt
//Ash Charizard fuego 100
//Brock Squirtle agua 38
//Ash Pikachu electricidad 10
//torneo
//fuego
//electricidad
//fin
//---------------------------------- 
//archivos\\torneo.txt
//Misty Blastoise agua 18
//Clemont Pikachu electricidad 22
//Millo Kadabra psíquico 90
//torneo
//fuego
//electricidad
//fuego
//fin
