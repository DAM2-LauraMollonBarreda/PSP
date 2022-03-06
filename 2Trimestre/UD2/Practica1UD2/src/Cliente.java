import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    
    public static void main(String[] args) throws Exception {
        Scanner sn = new Scanner(System.in);
        try (Socket servidor = new Socket("localhost", 9999)){
            try {
                DataOutputStream salida =  new DataOutputStream(servidor.getOutputStream());
                DataInputStream entrada = new DataInputStream(servidor.getInputStream());

                while (true) {
                    String mensaje = entrada.readUTF();
                    System.out.println(mensaje);

                    String a = sn.nextLine();
                    salida.writeUTF(a);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        } 
    }
}
