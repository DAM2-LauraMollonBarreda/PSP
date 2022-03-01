import java.io.Serializable;

public class Fichero implements Serializable {
    
    private String nombre;
    private String path;
    private String fichero;
    private byte[] contenidoFichero;

    public Fichero(String nombre, String path, String fichero, byte[] contenidoFichero) {

        this.nombre = nombre;
        this.path = path;
        this.fichero = fichero;
        this.contenidoFichero = contenidoFichero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFichero() {
        return fichero;
    }

    public void setFichero(String fichero) {
        this.fichero = fichero;
    }

    public byte[] getContenidoFichero() {
        return contenidoFichero;
    }

    public void setContenidoFichero(byte[] contenidoFichero) {
        this.contenidoFichero = contenidoFichero;
    }

}
