/*Crea un programa que trabaje con hilos. El hilo principal deberá lanzar 2 hilos,
el primero escribirá por consola 15 veces “Hola “ cada 2 segundos. El segundo
hilo escribirá “ mundo!” y el retorno de carro otras 15 veces también cada 2
segundos. 
Si el hilo principal lanza el segundo con un pequeño retraso (unos
20ms) el texto se mostrará por consola sin percatarnos que lo están
escribiendo 2 hilos diferentes. Modifica el programa de manera que el hilo
principal interrumpa al primer hilo transcurridos 5s desde el arranque de los
dos hilos. La respuesta ante la interrupción debe consistir en la salida y
finalización de la ejecución del hilo interrumpido.*/



public class App {
    public static void main(String[] args) throws Exception  {
        Hola hola = new Hola();
        Mundo mundo = new Mundo();

        hola.start();
        mundo.start();

        
        Thread.sleep(5000);
        hola.interrupt();
        
          
        
        
    }
}


class Hola extends Thread{
    public void run(){
        for (int i = 0; i < 15; i++) {
            System.out.print("Hola");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Interrumpido");
                break;
            }
        }
    }
}

class Mundo extends Thread{
    public void run(){
        try {
            Thread.sleep(2);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        for (int i = 0; i < 15; i++) {
            System.out.println(" mundo!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
