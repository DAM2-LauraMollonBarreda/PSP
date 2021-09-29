import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            // Lanzamos el proceso que permite ejecutar comandos cmd
            ProcessBuilder pb = new ProcessBuilder("C:\\Program Files (x86)\\Notepad++\\notepad++.exe", "D:\\Users\\damA\\Documents\\Hola.txt");
            Process p = pb.start();


            // Esperamos a que el proceso termine
            p.waitFor();
            System.out.println("Fin de la ejecución");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
