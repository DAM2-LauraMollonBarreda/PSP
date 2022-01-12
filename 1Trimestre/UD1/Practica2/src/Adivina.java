import java.util.Scanner;

public class Adivina {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        System.out.println("¿Cuantos vais a jugar?");
        int jugadores= sn.nextInt();
        sn.close();

        Arbitro arbitro = new Arbitro(jugadores);

        System.out.println("NÚMERO A ADIVINAR: "+arbitro.numeroAdivinar);

        int identificador = 0;

        while (identificador<=jugadores) {
            Jugador j = new Jugador(identificador, arbitro);
            j.start();

            identificador++;
            
        }

        /*Jugador j1 = new Jugador(1, arbitro);
        Jugador j2 = new Jugador(2, arbitro);
        Jugador j3 = new Jugador(3, arbitro);

        j1.start();
        j2.start();
        j3.start();*/
    }

}

class Arbitro {
    private int jugadores;
    private int turno;
    public int numeroAdivinar;
    private boolean fin;

    public Arbitro(int numeroJugadores) {
        jugadores = numeroJugadores;
        turno = 1 + (int) (jugadores * Math.random());
        numeroAdivinar = 1 + (int) (10 * Math.random());
        fin = false;
    }

    public int siguiente_turno() {
        return turno;
    }

    public boolean finJuego() {
        return fin;
    }

    public synchronized void jugada(int jugador, int propuesta) {

        if (jugador == siguiente_turno()) {
            System.out.println("     Le toca a jugador " + jugador);
            System.out.println("Jugador " + jugador + " dice: " + propuesta);

            if (propuesta == numeroAdivinar) {
                System.out.println("Jugador " + jugador + " gana!!!");
                fin = true;
            } else

            if (turno == jugadores) {
                turno = 1;
            } else {
                turno++;
            }

        } else {
            System.out.println(jugador + " trata de hacer trampa!");
        }

    }

}

class Jugador extends Thread {
    Arbitro arbitro;
    int id;

    public Jugador(int idJugador, Arbitro elArbitro) {
        arbitro = elArbitro;
        id = idJugador;
    }

    public void run() {
        while (arbitro.finJuego() == false) { // hasta el fin del juego
            if (arbitro.siguiente_turno() == id) { // es nuestro turno
                int jugada = 1 + (int) (10 * Math.random());
                arbitro.jugada(id, jugada);
            } 
        } 
    } 
}
