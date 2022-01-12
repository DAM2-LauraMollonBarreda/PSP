import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class Hilos implements Runnable {
    private List<Diario> listaDiario;
    private Path dir;
    private int rango_min;
    private int rango_max;
    private Datos datos;

    @Override
    public void run() {

        // Creamos un stream para contar el numero de archivos que contiene la carpeta
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {

            int i = 0;

            for (Path path : stream) {

                if ((i >= rango_min) && (i < rango_max)) {

                    // Abre los archivos, lee su contenido y los vuelve a cerrar
                    String content = new String(Files.readAllBytes(path));
                    // Deserializamos. Usamos Gson para parsear de json a la clase Diario
                    Gson gson = new Gson();
                    Diario diario = gson.fromJson(content, Diario.class);

                    datos.diario = diario;
                    datos.add_diario(listaDiario, diario);
                }
                i++;
            }
            // sino salta una excepcion
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Constructor
    public Hilos(List<Diario> lst_diario, Path dir, int rango_min, int rango_max, Datos datos) {
        this.listaDiario = lst_diario;
        this.dir = dir;
        this.rango_min = rango_min;
        this.rango_max = rango_max;
        this.datos = datos;
    }

}
