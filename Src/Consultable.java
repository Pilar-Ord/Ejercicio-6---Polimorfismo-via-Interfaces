public interface Consultable {

    boolean consultarEstado();

    double consultarCotizacion(String origen, String destino, double monto);

}
