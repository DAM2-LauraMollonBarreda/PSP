import java.io.DataInput;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(9999);
        while (true) {
            
            Socket miSocket = servidor.accept();
            DataInputStream flujoEntrada = new DataInputStream(miSocket.getInputStream());
            String texto = flujoEntrada.readUTF();
            System.out.println(texto);
    
            miSocket.close();
        }


    }
}
