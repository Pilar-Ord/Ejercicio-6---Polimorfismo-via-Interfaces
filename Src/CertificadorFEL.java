
public class CertificadorFEL extends Integracion implements
        Consultable, Documentable, Auditable {

    private String certificador;
    private String nitEmisor;

    public CertificadorFEL(String identificador, String nombre,
                          String estadoConexion,
                          double latenciaPromedio,
                          String urlBase,
                          String parametrosConfiguracion,
                          String certificador,
                          String nitEmisor) {

        super(identificador, nombre, "FEL", estadoConexion,
              latenciaPromedio, urlBase, parametrosConfiguracion,
              "Generacion y certificacion de documentos");

        this.certificador = certificador;
        this.nitEmisor = nitEmisor;
    }

    public String getCertificador() {
        return certificador;
    }

    public String getNitEmisor() {
        return nitEmisor;
    }

    @Override
    public boolean consultarEstado() {
        return estadoConexion.equalsIgnoreCase("Activa");
    }

    @Override
    public double consultarCotizacion(String origen, String destino, double monto) {
        return monto;
    }

    @Override
    public String generarDocumento() {
        return "Documento FEL generado por: " + certificador
                + " | NIT emisor: " + nitEmisor;
    }

    @Override
    public String generarAuditoria() {
        return "Auditoria FEL de la integracion: " + identificador;
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Certificador: " + certificador
                + " | NIT: " + nitEmisor;
    }
}
