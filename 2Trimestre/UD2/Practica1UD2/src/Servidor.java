import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Servidor {
    Servidor ser = new Servidor();

    static int puerto = 9999;

    public static void main(String[] args) {
        ServerSocket server;

        Socket socket = null;

        try {
            server = new ServerSocket(puerto);
            socket= server.accept();

            System.out.println("Cliente nuevo");

            String opciones = "Elige una opcion: \n 1.Lista de ficheros \n 2.Contenido del fichero \n 3. Salir";

            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            salida.writeUTF(opciones);
            salida.flush();


            while (true) {
                DataInputStream entrada = new DataInputStream(socket.getInputStream());
                String opcionElegida = entrada.readUTF();

                System.out.println("La opcion elegida a sido " + opcionElegida);

                String enviar = "";

                switch (opcionElegida) {
                    case "1":
                        ficherosLista(opciones,salida,enviar);
                        break;
                    case "2":
                        contenidoFichero (opciones,salida, entrada);
                        break;
                    case "3":
                        salir (server, socket,salida,entrada);
                        break;
                
                    default:
                        break;
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*METODO PARA LA OPCION DE SALIR DEL SERVIDOR*/
    private static void salir(ServerSocket server, Socket socket, DataOutputStream salida, DataInputStream entrada) {
        try {
            salida.writeUTF("Pulsa enter para salir del servidor");
            salida.flush();

            socket.close();

            server.close();

            entrada.close();

            salida.close();

        } catch (Exception e) {
           System.out.println(e.getMessage()); 
        }
    }

    /*METODO PARA MOSTRAR EL CONTENIDO DEL FICHERO*/
    private static void contenidoFichero(String opciones, DataOutputStream salida, DataInputStream entrada) {
        try {
            salida.writeUTF("¿Que archivo quieres ver?");
            salida.flush();

            String archivo = entrada.readUTF();
            try {
                byte[] contenidoArray = Files.readAllBytes(Paths.get("src/ficheros/"+archivo));
                String archivoContenido = new String(contenidoArray, StandardCharsets.UTF_8);

                salida.writeUTF("El contenido del fichero es: \n" + "   " + archivoContenido + "\n" + opciones);
                salida.flush();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*METODO PARA MOSTRAR LA LISTA DE FICHEROS*/
    private static void ficherosLista(String opciones, DataOutputStream salida, String enviar) {
        try {
            String rutaFicheros = "src/Ficheros/";
            File file = new File(rutaFicheros);
            File[] listaFicheros = file.listFiles();

           for (int i = 0; i < listaFicheros.length; i++) {
               enviar += "  ·" + listaFicheros[i].getName() + "\n";
           }

           salida.writeUTF("\n Los ficheros son: \n" + enviar + "\n" +opciones);
           salida.flush();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
