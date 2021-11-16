import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            Socket socket = new Socket("192.168.50.70", 9999);
            DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream());
            
            System.out.println("Escribe lo que quieres enviar");
            String pide = sc.nextLine();
            flujoSalida.writeUTF(pide);
            flujoSalida.close();
        }

    }
}