public class App {
    public static void main(String[] args) throws Exception {
        App addition = new App();
        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);

        int suma = addition.add(n1, n2);
        System.out.println("La suma de " +n1 +" y " +n2+ " es " +suma);
        System.out.flush();
        

    }

    public int add (int n1, int n2){
        int suma = 0 ;
        for (int i = n1; i <= n2; i++) {
            suma = suma + n1;
			n1 = n1 + 1; 
        }
        return suma;
    }
}
