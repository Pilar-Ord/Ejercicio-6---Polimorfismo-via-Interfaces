
import java.util.Scanner;

public class Vista {

    private Scanner teclado;

    public Vista() {
        teclado = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n===== SISTEMA DE INTEGRACIONES =====");
        System.out.println("1. Listar todas las integraciones");
        System.out.println("2. Buscar integracion por ID");
        System.out.println("3. Ordenar integraciones por latencia");
        System.out.println("4. Consultar estado de una integracion");
        System.out.println("5. Procesar pago");
        System.out.println("6. Solicitar reintegro");
        System.out.println("7. Generar documento");
        System.out.println("8. Generar auditoria");
        System.out.println("9. Salir");
        System.out.print("Seleccione una opcion: ");

        return teclado.nextInt();
    }

    public String leerIdentificador() {
        System.out.print("Ingrese el ID de la integracion: ");
        return teclado.next();
    }

    public double leerMonto() {
        System.out.print("Ingrese el monto: ");
        return teclado.nextDouble();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarIntegracion(Integracion integracion) {
        System.out.println(integracion.toString());
    }

    public void mostrarTitulo(String titulo) {
        System.out.println("\n===== " + titulo + " =====");
    }

    public void cerrarScanner() {
        teclado.close();
    }
}
