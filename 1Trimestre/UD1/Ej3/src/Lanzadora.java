import java.lang.ProcessBuilder.Redirect;

public class Lanzadora {
    public static void main(String[] args) {
        try {
            Lanzadora launcher = new Lanzadora();
            launcher.launch(1, 22);
            launcher.launch(10, 250);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void launch (Integer n1, Integer n2){
        System.out.println("Comenza en: " + n1 + " hasta " + n2);
        //Obtener la ruta de la clase 
        String classPath = System.getProperty("java.class.path");
        String className= "App";
        ProcessBuilder pb = new ProcessBuilder("java", "-cp" , classPath, className, n1.toString(), n2.toString());
        pb.redirectOutput(Redirect.INHERIT);
        try {
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
