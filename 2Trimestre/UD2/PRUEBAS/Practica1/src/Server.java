

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static ServerSocket misocket;
	public static BufferedReader buffederReader;
	public static BufferedWriter buffederWriter;

	public Server(ServerSocket serverSocket) {
		this.misocket = serverSocket;
	}

	public void arrancaServidor() {
		try {
			while (!misocket.isClosed()) {
				Socket socket = misocket.accept();
				ManejadorCliente cliente = new ManejadorCliente(socket);
				System.out.println("Cliente conectado: " + cliente.getNombreUsuario() + " desde " +cliente.getIp());
				Thread thread = new Thread(cliente);
				thread.start();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void closeSeverSocket() {
		try {
			if (misocket != null) {

				misocket.close();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		ServerSocket severSocket = new ServerSocket(9999);
		Server server = new Server(severSocket);
		server.arrancaServidor();
		// Menu que se envia al cliente
		menu();


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

	

	}

}
