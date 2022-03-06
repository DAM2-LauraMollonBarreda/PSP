

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private Socket socket;
	private static BufferedReader buffederReader;
	private static BufferedWriter buffederWriter;
	private String nombreUsuario;
	static Scanner sn = new Scanner(System.in);

	public Client(Socket socket, String nombreUsuario) {
		this.socket = socket;
		this.nombreUsuario = nombreUsuario;
		try {
			this.buffederReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.buffederWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mandaMensaje () {
		try {
			buffederWriter.write(nombreUsuario);
			buffederWriter.newLine();
			buffederWriter.flush();
			Scanner sc = new Scanner (System.in);
			
			while(socket.isConnected()) {
				String mensaje = sc.nextLine();
				
				buffederWriter.write(nombreUsuario +": "+  mensaje);
				buffederWriter.newLine();
				buffederWriter.flush();
				
			}
			
			
			
		} catch (IOException e) {
			cierraTodo(socket, buffederReader, buffederWriter);
			// TODO: handle exception
		}
		
	}

	private void cierraTodo(Socket socket, BufferedReader buffederReader, BufferedWriter buffederWriter) {
		try {
			if (buffederReader != null) {
				buffederReader.close();
			}
			if (buffederWriter != null) {
				buffederWriter.close();
			}
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	


	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		System.out.print("Introduce tu nick para entrar al chat: ");
		String nick = sc.nextLine();
		try {
			Socket socket = new Socket("192.168.1.15", 9999);
			Client cliente = new Client (socket, nick);

			cliente.mandaMensaje();
			menu();


		} catch (IOException e) {
			// TODO: handle exception
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
