
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            // Lanzamos la calculadora creado un nuevo proceso en el SO de ese programa
            Process pCalc = new ProcessBuilder("mspaint").start();
            // Mostrar por consola el PID del proceso lanzado
            System.out.println("Lanzado proceso paint con java. PID: " + pCalc.pid());
            // vamos a lanzar el comando tasklist para ver los procesos lanzados en el SO
            // Comando a ejecutar
            String command = "notepad.exe";
            // Lanzamos el proceso que permite ejecutar el comando anterior
            Process pCommand = Runtime.getRuntime().exec(command);
            // Accedemos al buffer de lectura del proceso lanzado
            BufferedReader reader = new BufferedReader(new InputStreamReader(pCommand.getInputStream()));
            // leemos el resultado del comando
            // y mostramos por consola el proceso que corresponde con el PID del proceso
            // anterior
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (line.contains(Long.toString(pCommand.pid()))) {
                    System.out.println(line + "\n");
                }
            }
            // Esperamos a que el proceso del comando termine
            pCommand.waitFor();
            // Esperamos a que el proceso calc finalice
            pCalc.waitFor();
            // Mostrar que el programa va a finalizar
            System.out.println("Programa finalizado");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
