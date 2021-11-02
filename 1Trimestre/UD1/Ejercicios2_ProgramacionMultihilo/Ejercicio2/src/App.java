import java.util.ArrayList;

/*Crea una aplicación que conste de 2 hilos; el primero el hilo principal de la
aplicación Java. El hilo principal deberá lanzar un nuevo hilo encargado de
imprimir por consola los siguientes mensajes con un intervalo de 4 segundos
entre cada uno de ellos (Mensajes: “Programas”, “Procesos”, “Servicios”,
“Hilos”). El hilo principal debe quedar a la espera hasta que termine,
mostrando cada segundo que está esperando por la finalización del hilo hijo.
La ejecución del programa debe durar 16s ya que son 4 mensajes y 4s de
espera por cada uno.
Para poder reducir la duración de la ejecución el programa debe aceptar por
parámetro (método main) el tiempo de espera máximo que el hilo principal
esperará a la ejecución del hilo secundario, una vez superado ese tiempo el
hilo principal debe interrumpir la ejecución del hilo secundario y a partir de
ese momento el hilo secundario mostrará los mensajes restantes sin esperas
entre la impresión de los mensajes para finalizar la ejecución del hilo cuanto
antes. El hilo principal debe mostrar por consola el mensaje de finalización de
la ejecución del programa. Puedes imprimir los mensajes que consideres
oportunos para verificar la correcta ejecución del programa. Calcula el tiempo
de ejecución del hilo principal y muéstralo por consola. Incluye el nombre del
hilo que imprime por consola cada vez que muestres un mensaje de salida.*/




public class App {
    public static void main(String[] args) throws Exception {
        long TIncio,Tfin,tiempo;
        TIncio= System.currentTimeMillis();
        int numeroSg = Integer.valueOf(args[0]);
        
        Hilo hilo = new Hilo();
        
        hilo.start();
        int[] segundos = {1,2,3,4};
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {                 
            Thread.sleep(1000);
            System.out.println(segundos[j]);

            }
        }
        System.out.println("Programa terminado");
    }
}

class Hilo extends Thread{
    String[] palabras = {"Programas","Procesos","Servicios","Hilos"};
    public void run(){
        try {
            Thread.sleep(20);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        for (int i = 0; i < palabras.length; i++) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(palabras[i]);
           
        }
    }
}
