import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Cliente {

	public static void main(String[] args) throws IOException {

		// Cambiar look and feel
		try {
			
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Crear el fileChosser para seleccionar el archivo
		JFileChooser fileChosser = new JFileChooser();
		fileChosser.showOpenDialog(null);

		// Leer los bytes
		byte[] contenido = Files.readAllBytes(Paths.get(fileChosser.getSelectedFile().getAbsolutePath()));

		// Crear nuevo objeto
		Fichero fichero = new Fichero(fileChosser.getSelectedFile().getName(),
				fileChosser.getSelectedFile().getAbsolutePath().toString(),
				fileChosser.getSelectedFile().toString(), contenido);

		// Informar
		System.out.println("Enviar " +
				fileChosser.getSelectedFile().getName());
		try {

			// Abrir socket
			try (Socket socket = new Socket("192.168.50.41", 9999)) {
				// Enviar fichero
				ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
				flujoSalida.writeObject(fichero);
				// Cerrar object input
				flujoSalida.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
