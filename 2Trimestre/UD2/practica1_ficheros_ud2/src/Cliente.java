import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws Exception {
        try (Socket server = new Socket("localhost",9999)) {
            Scanner sn = new Scanner(System.in);

            //Creamos un data output para sacar los datos al servidor
            DataOutputStream salida = new DataOutputStream(server.getOutputStream());

            //Creamos un data input para cojer los datos del servidor
            DataInputStream entrada = new DataInputStream(server.getInputStream());

            while (true) {
                //Mostramos el mensaje que recojemos del servidor
                System.out.println(entrada.readUTF());

                String respuesta = sn.nextLine();
                salida.writeUTF(respuesta);
            }
        }
        
    }
}
