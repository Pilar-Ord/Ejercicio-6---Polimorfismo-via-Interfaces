
public class PasarelaPago extends Integracion implements
        Consultable, Transaccional, Documentable, Auditable {

    private String medioPago;

    public PasarelaPago(String identificador, String nombre,
                        String estadoConexion, double latenciaPromedio,
                        String urlBase, String parametrosConfiguracion,
                        String medioPago) {

        super(identificador, nombre, "Pago", estadoConexion,
              latenciaPromedio, urlBase, parametrosConfiguracion,
              "Cobros y reintegros");

        this.medioPago = medioPago;
    }

    public String getMedioPago() {
        return medioPago;
    }

    @Override
    public boolean consultarEstado() {
        return estadoConexion.equalsIgnoreCase("Activa");
    }

    @Override
    public double consultarCotizacion(String origen, String destino,
                                     double monto) {
        return monto;
    }

    @Override
    public boolean procesarPago(double monto) {

        if (consultarEstado() && monto > 0) {
            return true;
        }

        return false;
    }

    @Override
    public boolean reintegrar(double monto) {

        if (consultarEstado() && monto > 0) {
            return true;
        }

        return false;
    }

    @Override
    public String generarDocumento() {
        return "Comprobante de pago de " + nombre;
    }

    @Override
    public String generarAuditoria() {
        return "Auditoría de la pasarela: " + identificador;
    }

    @Override
    public String toString() {
        return super.toString() +
               " | Medio de pago: " + medioPago;
    }
}
