import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {

        // Le pasamos la ruta donde se encuentra la carpeta con todos los archivos
        Path dir = Paths.get("D:\\Users\\damA\\Documents\\PSP\\Practica1DatosClima\\items");

        int numeroHilos = Runtime.getRuntime().availableProcessors();
        int numeroFicheros = new File(dir.toString()).list().length;

        // Creamos un array list de Diario
        List<Diario> listaDiario = new ArrayList<>();

        Datos datos = new Datos();

        // El numero de ficheros que haya lo dividimos entre los hilos
        int rango = numeroFicheros / numeroHilos;
        int rango_min = 0;
        int rango_max = 0;

        // Creamos el thread
        Thread[] hilos = new Thread[numeroHilos];

        for (int i = 0; i < numeroHilos; i++) {
            rango_min = i * rango;
            rango_max = rango_min + rango;

            if (rango_max > numeroFicheros) {
                rango_max = numeroFicheros;
            }
            // Lanzamos los hilos
            hilos[i] = new Thread(new Hilos(listaDiario, dir, rango_min, rango_max, datos));
            hilos[i].start();
        }

        // Esperar a que todos los hilos acaben
        for (int i = 0; i < numeroHilos; i++) {

            hilos[i].join();
        }

        // Imprimimos la temperatura minima y maxima
        datos.sort(listaDiario);
        System.out.println(
                "---------------------------------------------------TEMPERATURAS MÁXIMAS-------------------------------------------------------\n");
        datos.mostrar_temperaturaMaxima(listaDiario);
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------------------------------- \n");
        System.out.println(
                "\n-------------------------------------------TEMPERATURAS MÍNIMAS--------------------------------------------- \n");
        datos.mostrar_temperaturaMinima(listaDiario);
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------------------------------------------- \n");

    }
}
