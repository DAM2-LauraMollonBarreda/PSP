public class App {
    public static void main(String[] args) throws Exception {
        Numero numeros = new Numero();
        Numero2 numeros2 = new Numero2(numeros);
        // Letras letras = new Letras();

        numeros.start();
        numeros2.start();
        // letras.start();
        System.out.println("Finalizado el trabajo");
    }

}

class Numero extends Thread {

    public void run() {
        // for (int r = 0; r <26; r++) {
        for (int i = 0; i < 61; i++) {
            System.out.println(i + " hilo:" + getName());
        }

        // }
    }
}

class Letras extends Thread {
    public void run() {
        for (int i = 0; i < 26; i++) {
            for (char caracter = 'a'; caracter <= 'z'; caracter++) {
                System.out.println(caracter + " ");
            }
            for (char caracter = 'A'; caracter <= 'Z'; caracter++) {
                System.out.println(caracter + " ");
            }
        }
    }
}

class Numero2 extends Thread {
    private Thread t;
    public Numero2(Thread t){
        super();
        this.t=t;
    }
    public void run() {
        // for (int r = 0; r <26; r++) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        for (int i = 0; i < 61; i++) {
            System.out.println(i + " hilo:" + getName());
        }

        // }
    }
}
