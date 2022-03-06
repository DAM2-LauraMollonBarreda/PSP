import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {

	public static BufferedReader buffederReader;
	public static BufferedWriter buffederWriter;
	static Scanner sn = new Scanner(System.in);

	public static void main(String[] args) {

		try {
			// Socket socket = new Socket("192.168.50.61",9999);
			Socket socket = new Socket("192.168.1.15", 9999);

			// Para leer lo que dice el servidor
			buffederReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// Para escribir en el servidor
			buffederWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// Muestra el menu enviado por el servidor
			menu();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void opcion(Scanner sn) throws IOException {

		// Espera a que el usuario eliga un numeros
		String opcion = sn.nextLine();
		System.out.println("Has elegido la opcion: " + opcion);
		buffederWriter.write(opcion);
		buffederWriter.newLine();
		buffederWriter.flush();

		// Pasamo la opcion elegida a int
		int nOpcion = Integer.parseInt(opcion);

		// Si la opcion elegida es uno
		if (nOpcion == 1) {
			mostrarFicheros();

		} else if (nOpcion == 2) {


			System.out.println("Escribe el nombre completo del fichero que quieres mostrar");
			String fichero = sn.nextLine();
			System.out.println("Has elegido el fichero: " + fichero);
			buffederWriter.write(fichero);
			buffederWriter.newLine();
			buffederWriter.flush();

			String texto = buffederReader.readLine();
			// Repetir mientras no se llegue al final del fichero
			while (texto != "") {
				// Hacer lo que sea con la línea leída
				System.out.println(texto);
				// Leer la siguiente línea
				texto = buffederReader.readLine();
			}
			System.out.println("fuera");

		} else if (nOpcion == 3) {

		} else {
			System.out.println("Opcion incorrecta");
		}

	}

	private static void mostrarFicheros() throws IOException {

		System.out.println(buffederReader.readLine());
		System.out.println(buffederReader.readLine());
		System.out.println(buffederReader.readLine());
		System.out.println(buffederReader.readLine());
		System.out.println(buffederReader.readLine());

	}

	private static void menu() throws IOException {
		// Escibre lo que lee del servidor
		System.out.println(buffederReader.readLine());
		System.out.println(buffederReader.readLine());
		System.out.println(buffederReader.readLine());
		System.out.println(buffederReader.readLine());
		System.out.println(buffederReader.readLine());
		System.out.println(buffederReader.readLine());
		System.out.println(buffederReader.readLine());

		opcion(sn);

		/*
		 * String contenido;
		 * while ((contenido = buffederReader.readLine()) != null) {
		 * System.out.println(contenido);
		 * }
		 */
	}

}
