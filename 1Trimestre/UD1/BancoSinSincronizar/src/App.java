import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static void main(String[] args) {

        int numeroDeCuentas = 100;

        Banco b = new Banco();
        for (int i = 0; i < numeroDeCuentas; i++) {
            EjecucionTransferencias r = new EjecucionTransferencias(b, i, 2000);
            Thread t = new Thread(r);
            t.start();

        }

    }

}

class Banco {

    private final double[] cuentas;
   private final Lock cierreBanco = new ReentrantLock();

    public Banco() {
        super();
        cuentas = new double[100];

        for (int i = 0; i < cuentas.length; i++) {
            cuentas[i] = 2000;
        }
    }

    public  void transferencia(int origen, int destino, double cantidad) {
        cierreBanco.lock();
        // controlamos q la cuenta de origen tenga suficiente dinero
       try {
            if (cuentas[origen] < cantidad) {
                System.out.println("-------------------------------------------------------------------------------------NO HAS SALDO EN " + origen);
                // No hay saldo;
                return;

            }
            System.out.println(Thread.currentThread());

            cuentas[origen] -= cantidad;

            System.out.printf("%10.2f de %d para %d%n", cantidad, origen, destino);

            cuentas[destino] += cantidad;

            System.out.printf("   Saldo total:  %10.2f%n ", getSaldoTotal());
        } finally {
            cierreBanco.unlock();
        }
    }

    public double getSaldoTotal() {

        double sumaCuentas = 0;

        for (double a : cuentas) {

            sumaCuentas += a;

        }

        return sumaCuentas;

    }
}

class EjecucionTransferencias implements Runnable {

    private Banco banco;
    private int deLaCuenta;
    private double cantidadMax;

    public EjecucionTransferencias(Banco banco, int deLaCuenta, double cantidadMax) {
        super();
        this.banco = banco;
        this.deLaCuenta = deLaCuenta;
        this.cantidadMax = cantidadMax;
    }

    @Override
    public void run() {

        while (true) {

            int paraLaCuenta = (int) (100 * Math.random());

            double cantidad = cantidadMax * Math.random();

            banco.transferencia(deLaCuenta, paraLaCuenta, cantidad);

            try {
                Thread.sleep((int) (Math.random() * 10));
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

        }

    }

}
