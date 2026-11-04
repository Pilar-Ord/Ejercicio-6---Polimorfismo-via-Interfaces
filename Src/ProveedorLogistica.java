
public class ProveedorLogistica extends Integracion implements
        Consultable, Documentable, Auditable {

    private String proveedor;
    private String numeroGuia;

    public ProveedorLogistica(String identificador, String nombre,
                              String estadoConexion,
                              double latenciaPromedio,
                              String urlBase,
                              String parametrosConfiguracion,
                              String proveedor) {

        super(identificador, nombre, "Logistica", estadoConexion,
              latenciaPromedio, urlBase, parametrosConfiguracion,
              "Envios y rastreo");

        this.proveedor = proveedor;
        this.numeroGuia = "GUIA-" + identificador;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    @Override
    public boolean consultarEstado() {
        return estadoConexion.equalsIgnoreCase("Activa");
    }

    @Override
    public double consultarCotizacion(String origen, String destino,
                                     double peso) {

        if (peso <= 0) {
            return 0;
        }

        return peso * 15.00;
    }

    @Override
    public String generarDocumento() {
        return "Guia de envio: " + numeroGuia +
               " | Proveedor: " + proveedor;
    }

    @Override
    public String generarAuditoria() {
        return "Auditoria logistica de: " + identificador;
    }

    @Override
    public String toString() {
        return super.toString() +
               " | Proveedor: " + proveedor;
    }
}
