
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Servidor {
    static Servidor servidorClass = new Servidor();

    public static void main(String[] args) {
        ServerSocket server;
        Socket socket;
        try {
            server = new ServerSocket(9999);
            socket = server.accept();

            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            DataInputStream entrada = new DataInputStream(socket.getInputStream());

            System.out.println("Ha entrado un nuevo cliente");

            String opciones = "¿Que quieres hacer? \n 1.Listado de los ficheros \n 2. Ver contenido del fichero \n 3.Salir";

            salida.writeUTF(opciones);
            salida.flush();

            while (true) {
                String opcionElegida = entrada.readUTF();

                System.out.println("El usuario a elegido la opcion: " + opcionElegida);

                String enviar = "";
                switch (opcionElegida) {
                    case "1":
                        listadoFicheros(opciones, salida, enviar);
                        break;
                    case "2":
                        mostrarContenido(opciones, salida, entrada);
                        break;
                    case "3":
                        salir(server, socket, salida, entrada);
                        break;

                    default:
                        break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listadoFicheros(String opciones, DataOutputStream salida, String enviar) {
        try {
            File rutaArchivos = new File("src/ficheros/");
            File[] ficheros = rutaArchivos.listFiles();

            for (int i = 0; i < ficheros.length; i++) {
                enviar += "·" + ficheros[i].getName() + "\n";
            }

            salida.writeUTF("Estos son los ficheros que hay en la carpeta \n" + enviar + "\n" + opciones);
            salida.flush();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void mostrarContenido(String opciones, DataOutputStream salida, DataInputStream entrada) {
        try {
            salida.writeUTF("Escribe el nombre del archivo que quieres ver");
            salida.flush();

            String opcionElegidaUsu = entrada.readUTF();

            byte[] charContenido = Files.readAllBytes(Paths.get("src/ficheros/", opcionElegidaUsu));

            String contenidoString = new String(charContenido, StandardCharsets.UTF_8);

            salida.writeUTF("Este el contenido \n" + "    "+ contenidoString + "\n" + opciones);
            salida.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void salir(ServerSocket server, Socket socket, DataOutputStream salida, DataInputStream entrada) {
        try {
            salida.writeUTF("Pulsa enter para salir");
            salida.flush();

            socket.close();

            server.close();

            entrada.close();

            salida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
