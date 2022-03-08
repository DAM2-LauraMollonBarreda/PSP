import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFileChooser;

public class Cliente {

	public static void main(String[] args) throws IOException {

		// Abrir y crear el filechooser
		JFileChooser escogerArchivo = new JFileChooser();
		escogerArchivo.showOpenDialog(null);

		// Meter en un array de byte el contenido del archivo
		byte[] contenido = Files.readAllBytes(Paths.get(escogerArchivo.getSelectedFile().getAbsolutePath()));

		// Crear nuevo archivo recogiendo todos sus datos
		Archivo archivo = new Archivo(escogerArchivo.getSelectedFile().getName(),
				escogerArchivo.getSelectedFile().getAbsolutePath().toString(),
				escogerArchivo.getSelectedFile().toString(), contenido);

		System.out.println("Enviando archivo al servidor " + escogerArchivo.getSelectedFile().getName());

		// Crear un nuevo socket y abrirlo
		try (Socket socket = new Socket("localhost", 9999)) {
			// Enviar fichero al servidor
			ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
			salida.writeObject(archivo);

			salida.close();
		}

	}

}
