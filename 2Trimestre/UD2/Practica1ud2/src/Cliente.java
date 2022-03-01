import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente{

    public static void main(String[] args) throws Exception{
        
        try (Socket servidor = new Socket("localhost", 9999)) {

            try {
                
                DataOutputStream f_salida = new DataOutputStream(servidor.getOutputStream());
                f_salida = new DataOutputStream(servidor.getOutputStream());
                
                DataInputStream f_entrada = new DataInputStream(servidor.getInputStream());

                Scanner sc = new Scanner(System.in);

                while (true) {

                    String msj = f_entrada.readUTF();
                    System.out.println(msj);
                    
                    String a = sc.nextLine();
                    f_salida.writeUTF(a);
                }


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            
        }
        

    }

}