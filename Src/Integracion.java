
import java.util.Objects;

public abstract class Integracion implements Comparable<Integracion> {

    protected String identificador;
    protected String nombre;
    protected String tipoServicio;
    protected String estadoConexion;
    protected double latenciaPromedio;
    protected String urlBase;
    protected String parametrosConfiguracion;
    protected String tipoOperacion;

    public Integracion(String identificador, String nombre,
                       String tipoServicio, String estadoConexion,
                       double latenciaPromedio, String urlBase,
                       String parametrosConfiguracion,
                       String tipoOperacion) {

        this.identificador = identificador;
        this.nombre = nombre;
        this.tipoServicio = tipoServicio;
        this.estadoConexion = estadoConexion;
        this.latenciaPromedio = latenciaPromedio;
        this.urlBase = urlBase;
        this.parametrosConfiguracion = parametrosConfiguracion;
        this.tipoOperacion = tipoOperacion;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public String getEstadoConexion() {
        return estadoConexion;
    }

    public double getLatenciaPromedio() {
        return latenciaPromedio;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public String getParametrosConfiguracion() {
        return parametrosConfiguracion;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setEstadoConexion(String estadoConexion) {
        this.estadoConexion = estadoConexion;
    }

    public void setLatenciaPromedio(double latenciaPromedio) {
        this.latenciaPromedio = latenciaPromedio;
    }

    @Override
    public int compareTo(Integracion otra) {
        return Double.compare(this.latenciaPromedio,
                             otra.latenciaPromedio);
    }

    @Override
    public String toString() {
        return "ID: " + identificador +
               " | Nombre: " + nombre +
               " | Tipo: " + tipoServicio +
               " | Estado: " + estadoConexion +
               " | Latencia: " + latenciaPromedio + " ms";
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Integracion otra = (Integracion) obj;

        return Objects.equals(identificador, otra.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }
}
