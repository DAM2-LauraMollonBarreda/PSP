import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		try (ServerSocket server = new ServerSocket(9999)) {
			// Entrar al socket
			Socket socket = server.accept();

			// Cojer el fichero que se envia desde el cliente
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

			// Recoje los datos del fiechero
			Archivo ficherRecogido = (Archivo) entrada.readObject();
			System.out.println("Se ha recibido el fichero con nombre: " + ficherRecogido.getArchivoNombre());

			// Ruta donde se guarda el fichero enviado
			File rutaArchivo = new File("src/ArchivosRecibidosDelCliente/" + ficherRecogido.getArchivoNombre());
			// Clonar el archivo en nueva ruta
			FileOutputStream salida = new FileOutputStream(rutaArchivo);
			// Recogemos el contenido del fichero en un array de bytes
			byte[] contenidoFichero = ficherRecogido.getContenido();

			// Escribe el contenido del fichero elegido, en el fichero creado en el servidor
			salida.write(contenidoFichero);
			salida.close();
		}
	}

}
