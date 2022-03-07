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

    private static final Servidor s = new Servidor();

    
    private static final int n = 9999;
   
    public static void main(String[] args) {

        ServerSocket servidor;
        
        Socket socket = null;

        try {
            
            servidor = new ServerSocket(n);
            socket = servidor.accept();

            System.out.println("Nuevo Cliente.");

            String menu = "Elige una opcion:"
                        +"\n 1. Listado de los ficheros."
                        +"\n 2. Ver Info fichero."
                        +"\n 3. SALIR";

            DataOutputStream f_salida = new DataOutputStream(socket.getOutputStream());
            f_salida.writeUTF(menu);
            f_salida.flush();

            while (true) {

                DataInputStream f_entrada = new DataInputStream(socket.getInputStream());
                String o = f_entrada.readUTF();

                System.out.println("Opción: " + o);
                String l_enviar = "";
                
                switch (o) {

                    case "1":

                        listado(menu, f_salida, l_enviar);
                        break;

                    case "2":

                        ver(menu, f_salida, f_entrada);
                        break;

                    case "3":

                        salida(servidor, socket, f_salida, f_entrada);
                        break;

                    default:
                        break;
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    private static void ver(String menu, DataOutputStream f_salida, DataInputStream f_entrada) {

        try {

            f_salida.writeUTF("¿Què archivo quieres ver? (nombre_archivo + .txt)");
            f_salida.flush();

            String msj = f_entrada.readUTF();

            try {

                byte[] c = Files.readAllBytes(Paths.get("src\\ficheros\\" + msj));

                String string = new String(c, StandardCharsets.UTF_8);

                f_salida.writeUTF("\nContenido: \n" + string + "\n\n" + menu);
                f_salida.flush();
    
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private static void listado(String menu, DataOutputStream f_salida, String l_enviar) {

        try {

            String sDirectorio = "src/ficheros/";
            File f = new File(sDirectorio);
            File[] ficheros = f.listFiles();

            for (int x = 0; x < ficheros.length; x++) {
                l_enviar = l_enviar + "- " + ficheros[x].getName() + "\n";
            }

            f_salida.writeUTF("\n\nFicheros existentes: \n" + l_enviar + "\n\n" + menu);
            f_salida.flush();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void salida(ServerSocket servidor, Socket socket, DataOutputStream f_salida, DataInputStream f_entrada) {

        try {

            f_salida.writeUTF("Enter para salir.");
            f_salida.flush();

            socket.close();

            servidor.close();

            f_entrada.close();
            
            f_salida.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
