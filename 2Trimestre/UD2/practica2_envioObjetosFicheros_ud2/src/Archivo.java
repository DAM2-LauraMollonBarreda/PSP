import java.io.Serializable;

public class Archivo implements Serializable {
    
    private String archivoNombre;
    private String ruta;
    private String archivo;
    private byte[] contenido;

    //Constructor
    public Archivo(String archivoNombre, String ruta, String archivo, byte[] contenido) {
        this.archivoNombre = archivoNombre;
        this.ruta = ruta;
        this.archivo = archivo;
        this.contenido = contenido;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    



}
