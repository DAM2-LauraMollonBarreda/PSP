public class App {
    public static void main(String[] args) {
        //Creamos la panaderia    
        Panaderia panaderia = new Panaderia();
        
        //Creamos un panadero para la panaderia
        Panadero panadero = new Panadero(panaderia);
        //Creamos los clientes para la panaderia
        Cliente cliente = new Cliente(panaderia);
        
        //Creamos el hilo panadero
        Thread hiloPanadero = new Thread(panadero);
        //Creamos el hilo cliente
        Thread hiloCliente = new Thread(cliente);
        
        //Empiza el panadero
        hiloPanadero.start();
        //Empiezan los clientes
        hiloCliente.start();
    }
}


class Panadero implements Runnable{
    
    private Panaderia panaderia;
    

    public Panadero(Panaderia panaderia) {
        this.panaderia = panaderia;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            panaderia.hacerBarras();
        }                    
        
    }    

}


class Cliente implements Runnable{
    
    Panaderia panaderia;


    public Cliente(Panaderia panaderia) {
        this.panaderia = panaderia;
    }


    @Override
    public void run() {
        while (true) {
            panaderia.cogerBarra(-1);
            
        }
    }
    
}



class Panaderia{
    
    private int nBarras;

    public Panaderia() {
        
    }

    public int getnBarras() {
        return nBarras;
    }

    public void setnBarras(int cantidad) {
        this.nBarras += cantidad;
    }
    
    
    public synchronized void cogerBarra(int cantidad){
        //Mientras las barras son 0
        while (nBarras==0) {
            try {
                //Despierta al panadero
                notifyAll();
                //Los clientes se esperan a que las barras esten hechas
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Resta una barra por que se le llama arriba
        setnBarras(cantidad);
        System.out.println(" Barras final: " + getnBarras());
        System.out.println("");
        System.out.println("==============================================================");
    }

    public synchronized void hacerBarras(){
        //Mientras las barras mayores cero
        while (nBarras>0) {
            try {               
                //Esperara a despertarse
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Hasta que las barras no sean 0, el panadero no hace las barras
        setnBarras(20);        
        //Duerme a los clientes
        notifyAll();

        System.out.println(" Barras final: " + getnBarras());
        System.out.println("");
        System.out.println("==============================================================");
    }

}
