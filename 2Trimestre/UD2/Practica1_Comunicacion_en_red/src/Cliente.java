import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	public static BufferedReader buffederReader;
	public static BufferedWriter buffederWriter;

	public static void main(String[] args) {

		Scanner sn = new Scanner(System.in);
		try {
			// Socket socket = new Socket("192.168.50.61",9999);
			Socket socket = new Socket("192.168.1.15", 9999);

			// Para leer lo que dice el servidor
			buffederReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// Para escribir en el servidor
			buffederWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// Muestra el menu enviado por el servidor
			menu();

			// Espera a que el usuario eliga un numeros
			String opcion = sn.nextLine();
			buffederWriter.write(opcion);
			buffederWriter.newLine();
			buffederWriter.flush();


			String contenido;
			while ((contenido = buffederReader.readLine()) != null) {
				System.out.println(contenido);
			}



		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		/*String contenido;
		while ((contenido = buffederReader.readLine()) != null) {
			System.out.println(contenido);
		}*/
	}

}
