import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import javax.sound.sampled.Line;

public class Server {

	public static Socket misocket;
	public static BufferedReader buffederReader;
	public static BufferedWriter buffederWriter;

	public static void main(String[] args) {

		try {
			ServerSocket servidor = new ServerSocket(9999);

			while (true) {

				misocket = servidor.accept();

				buffederReader = new BufferedReader(new InputStreamReader(misocket.getInputStream()));
				buffederWriter = new BufferedWriter(new OutputStreamWriter(misocket.getOutputStream()));

				// Menu que se envia al cliente
				menu();

				// Leemos la opcion que a elgido el cliente
				// todo: mirar a ver como leer un int
				String opcion = buffederReader.readLine();
				int numeroOpcion = Integer.parseInt(opcion);
				System.out.println(numeroOpcion);

				if (numeroOpcion == 1) {
					mostrarFicheros();

				} else if (numeroOpcion == 2) {

					// Falta la opcion de salir
				} else if (numeroOpcion == 3) {
					misocket.close();
				} else {
					buffederWriter.write("==      Opcion incorrecta           ==");
					buffederWriter.newLine();
					buffederWriter.flush();
				}

			}

			// misocket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void mostrarFicheros() throws IOException {
		File carpeta = new File(".\\Ficheros");
		String[] listado = carpeta.list();
		for (int i = 0; i < listado.length; i++) {
			buffederWriter.write(listado[i]);
			buffederWriter.newLine();
			buffederWriter.flush();
		}
	}

	private static void menu() throws IOException {
		buffederWriter.write("======================================");
		buffederWriter.newLine();
		buffederWriter.flush();
		buffederWriter.write("== OPCIONES FICHEROS CARLOS - LAURA ==");
		buffederWriter.newLine();
		buffederWriter.flush();
		buffederWriter.write("==  1 - Listar Ficheros             ==");
		buffederWriter.newLine();
		buffederWriter.flush();
		buffederWriter.write("==  2 - Mostrar Contenido           ==");
		buffederWriter.newLine();
		buffederWriter.flush();
		buffederWriter.write("==  3 - Salir                        ==");
		buffederWriter.newLine();
		buffederWriter.flush();
		buffederWriter.write("======================================");
		buffederWriter.newLine();
		buffederWriter.flush();
		buffederWriter.write("==  Elige una de las opcionces      ==");
		buffederWriter.newLine();
		buffederWriter.flush();

		/*
		 * List<String> menu = new ArrayList<String>();
		 * 
		 * menu.add("======================================");
		 * menu.add("== OPCIONES FICHEROS CARLOS - LAURA ==");
		 * menu.add("==  1 - Listar Ficheros             ==");
		 * menu.add("==  2 - Mostrar Contenido           ==");
		 * // falta la opcion de salir (supongo que sera un hilo)
		 * menu.add("======================================");
		 * menu.add("==  Elige una de las opcionces      ==");
		 * 
		 * for (int i = 0; i < menu.size(); i++) {
		 * buffederWriter.write(menu.get(i));
		 * buffederWriter.newLine();
		 * buffederWriter.flush();
		 * }
		 */

	}
}
