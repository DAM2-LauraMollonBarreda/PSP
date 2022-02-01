import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
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

			

				misocket = servidor.accept();

				buffederReader = new BufferedReader(new InputStreamReader(misocket.getInputStream()));
				buffederWriter = new BufferedWriter(new OutputStreamWriter(misocket.getOutputStream()));

				// Menu que se envia al cliente
				menu();

				

			

			// misocket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void opcion() throws IOException {
		
		while (true) {
			// Leemos la opcion que a elgido el cliente
			String opcion = buffederReader.readLine();
		//Pasamos la opcion elegida a int
		int numeroOpcion = Integer.parseInt(opcion);
		//Mostramos la opcion elegida por el cliente
		System.out.println("El usuario a elegido la opcion " +opcion);
			if (numeroOpcion == 1) {
				mostrarFicheros();
				menu();
	
			} else if (numeroOpcion == 2) {
				mostrarFicheros();


				String fichero = buffederReader.readLine();
				System.out.println("El usuario a elegido el fichero " +fichero);
				mostrarContenido(fichero);
			} else if (numeroOpcion == 3) {
				misocket.close();
			} else {
				buffederWriter.write("==      Opcion incorrecta           ==");
				buffederWriter.newLine();
				buffederWriter.flush();
			}			
		}

	}

	private static void mostrarContenido(String fichero) {
		String cadena; 
		try (FileReader f = new FileReader(".\\Ficheros\\"+fichero)) {
			BufferedReader b = new BufferedReader(f); 
			while((cadena = b.readLine())!=null) { 
				System.out.println(cadena);
				buffederWriter.write(cadena);
				buffederWriter.newLine();
				buffederWriter.flush();
			} 
			b.close();
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

		opcion();

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
