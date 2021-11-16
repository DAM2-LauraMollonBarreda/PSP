public class App {
    public static void main(String[] args) {

        Saldo saldo= new Saldo();

        Hilo hilo1=new Hilo(saldo);
        Hilo hilo2=new Hilo(saldo);
        Hilo hilo3=new Hilo(saldo);
        Hilo hilo4=new Hilo(saldo);

        Thread t1 = new Thread(hilo1);
        Thread t2 = new Thread(hilo2);
        Thread t3 = new Thread(hilo3);
        Thread t4 = new Thread(hilo4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}

class Hilo implements Runnable{
    
    Saldo saldo;


    @Override
    public void run() {
        saldo.RecibirCantidad( (int) (Math.random() * 1000) );
    }

    public Hilo(Saldo saldo){
        this.saldo=saldo;
    }

}


class Saldo {

    
    private int saldo;

    public Saldo() {
        super();
        this.saldo = 10_000;
    }

    public int getSaldo() {
        return saldo;
    }

    private void setSaldo(int saldo) {
        this.saldo += saldo;
        try {
            Thread.sleep((int)(Math.random()*1000));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public synchronized void RecibirCantidad(int cantidad){
        System.out.println();
        System.out.println(" - Quien " + Thread.currentThread().getName());
        System.out.println("   Cantidad: " + cantidad + "  ||  saldo inicial: " + getSaldo());
        setSaldo(cantidad);
        System.out.println("   Saldo final: " + getSaldo());
        System.out.println("");
        System.out.println("==============================================================");
    }
}
