import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {

		try {
			try (
				// Se crea el sevidor
				ServerSocket servidor = new ServerSocket(9999)) {
				File ruta = new File("src\\nombre\\");

				// Se crea la carpeta
				if (ruta.mkdir()) {
					System.out.println("Directorio creado <3");
				}

				// Aceptar entrada de socket
				Socket socket = servidor.accept();

				// Recibir el objeto
				ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());

				// Obtener el fichero con todos sus datos
				Fichero ficheroEnviado = (Fichero) flujoEntrada.readObject();
				System.out.println("Recibido " + ficheroEnviado.getNombre());

				// Clonar el archivo en nueva ruta
				FileOutputStream outputStream = new FileOutputStream("src\\nombre\\" + ficheroEnviado.getNombre());
				byte[] contenidoFichero = ficheroEnviado.getContenidoFichero();
				outputStream.write(contenidoFichero);

				// Cerrar el OutPut
				outputStream.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
