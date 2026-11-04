
import java.util.ArrayList;
import java.util.Collections;

public class Controlador {

    private Vista vista;
    private ArrayList<Integracion> integraciones;

    public Controlador() {
        vista = new Vista();
        integraciones = new ArrayList<Integracion>();

        cargarIntegraciones();
    }

    public void iniciar() {
        int opcion = 0;

        while (opcion != 9) {
            opcion = vista.mostrarMenu();

            switch (opcion) {
                case 1:
                    listarIntegraciones();
                    break;

                case 2:
                    buscarIntegracion();
                    break;

                case 3:
                    ordenarPorLatencia();
                    break;

                case 4:
                    consultarEstado();
                    break;

                case 5:
                    procesarPago();
                    break;

                case 6:
                    solicitarReintegro();
                    break;

                case 7:
                    generarDocumento();
                    break;

                case 8:
                    generarAuditoria();
                    break;

                case 9:
                    vista.mostrarMensaje("Saliendo del sistema...");
                    break;

                default:
                    vista.mostrarMensaje("Opcion no valida.");
            }
        }

        vista.cerrarScanner();
    }

    // Carga inicial de 10 integraciones diferentes
    private void cargarIntegraciones() {

        integraciones.add(new PasarelaPago(
                "P001", "PayPal", "Activa", 120.5,
                "https://api.paypal.com", "Token API", "Tarjeta"));

        integraciones.add(new PasarelaPago(
                "P002", "Stripe", "Activa", 95.3,
                "https://api.stripe.com", "Clave secreta", "Tarjeta"));

        integraciones.add(new PasarelaPago(
                "P003", "NeoNet", "Activa", 150.2,
                "https://api.neonet.com", "Credenciales", "Tarjeta"));

        integraciones.add(new PasarelaPago(
                "P004", "BAC Credomatic", "Inactiva", 210.8,
                "https://api.bac.com", "Usuario y clave", "Transferencia"));

        integraciones.add(new ProveedorLogistica(
                "L001", "Cargo Expreso", "Activa", 180.4,
                "https://api.cargoexpreso.com", "Token", "Cargo Expreso"));

        integraciones.add(new ProveedorLogistica(
                "L002", "Guatex", "Activa", 140.7,
                "https://api.guatex.com", "Credenciales", "Guatex"));

        integraciones.add(new ProveedorLogistica(
                "L003", "DHL", "Inactiva", 250.1,
                "https://api.dhl.com", "API Key", "DHL"));

        integraciones.add(new CertificadorFEL(
                "F001", "Infile", "Activa", 130.6,
                "https://api.infile.com", "Certificado", "Infile", "1234567-8"));

        integraciones.add(new CertificadorFEL(
                "F002", "Megaprint", "Activa", 160.9,
                "https://api.megaprint.com", "Certificado", "Megaprint", "8765432-1"));

        integraciones.add(new CertificadorFEL(
                "F003", "G4S FEL", "Inactiva", 300.5,
                "https://api.g4s.com", "Certificado", "G4S", "1122334-5"));
    }

    // Opcion 1
    public void listarIntegraciones() {
        vista.mostrarTitulo("Integraciones registradas");

        for (Integracion integracion : integraciones) {
            vista.mostrarMensaje(integracion.toString());
        }
    }

    // Opcion 2
    public void buscarIntegracion() {
        String id = vista.leerIdentificador();
        Integracion encontrada = buscarPorID(id);

        if (encontrada != null) {
            vista.mostrarMensaje("Integracion encontrada:");
            vista.mostrarMensaje(encontrada.toString());
            vista.mostrarMensaje("URL: " + encontrada.getUrlBase());
            vista.mostrarMensaje("Configuracion: "
                    + encontrada.getParametrosConfiguracion());
        } else {
            vista.mostrarMensaje("No se encontro la integracion.");
        }
    }

    // Sobrecarga: permite buscar usando un ID o un objeto
    public Integracion buscarPorID(String id) {
        for (Integracion integracion : integraciones) {
            if (integracion.getIdentificador().equalsIgnoreCase(id)) {
                return integracion;
            }
        }

        return null;
    }

    public Integracion buscarPorID(Integracion integracion) {
        return buscarPorID(integracion.getIdentificador());
    }

    // Opcion 3
    public void ordenarPorLatencia() {
        Collections.sort(integraciones);

        vista.mostrarTitulo("Integraciones ordenadas por latencia");

        for (Integracion integracion : integraciones) {
            vista.mostrarMensaje(integracion.toString());
        }
    }

    // Opcion 4
    public void consultarEstado() {
        String id = vista.leerIdentificador();
        Integracion integracion = buscarPorID(id);

        if (integracion != null && integracion instanceof Consultable) {
            Consultable consultable = (Consultable) integracion;

            if (consultable.consultarEstado()) {
                vista.mostrarMensaje("La integracion esta activa.");
            } else {
                vista.mostrarMensaje("La integracion esta inactiva.");
            }

        } else {
            vista.mostrarMensaje("Integracion no encontrada o no consultable.");
        }
    }

    // Opcion 5
    public void procesarPago() {
        String id = vista.leerIdentificador();
        Integracion integracion = buscarPorID(id);

        if (integracion instanceof Transaccional) {
            double monto = vista.leerMonto();
            Transaccional transaccion = (Transaccional) integracion;

            if (transaccion.procesarPago(monto)) {
                vista.mostrarMensaje("Pago procesado correctamente.");
            } else {
                vista.mostrarMensaje("No fue posible procesar el pago.");
            }

        } else {
            vista.mostrarMensaje(
                    "Esta integracion no permite procesar pagos.");
        }
    }

    // Opcion 6
    public void solicitarReintegro() {
        String id = vista.leerIdentificador();
        Integracion integracion = buscarPorID(id);

        if (integracion instanceof Transaccional) {
            double monto = vista.leerMonto();
            Transaccional transaccion = (Transaccional) integracion;

            if (transaccion.reintegrar(monto)) {
                vista.mostrarMensaje("Reintegro realizado correctamente.");
            } else {
                vista.mostrarMensaje("No fue posible realizar el reintegro.");
            }

        } else {
            // Si es logistica u otro servicio, se ignora sin causar errores.
            vista.mostrarMensaje(
                    "Esta integracion no admite reintegros. Operacion ignorada.");
        }
    }

    // Opcion 7
    public void generarDocumento() {
        String id = vista.leerIdentificador();
        Integracion integracion = buscarPorID(id);

        if (integracion instanceof Documentable) {
            Documentable documento = (Documentable) integracion;
            vista.mostrarMensaje(documento.generarDocumento());
        } else {
            vista.mostrarMensaje("Esta integracion no genera documentos.");
        }
    }

    // Opcion 8
    public void generarAuditoria() {
        String id = vista.leerIdentificador();
        Integracion integracion = buscarPorID(id);

        if (integracion instanceof Auditable) {
            Auditable auditoria = (Auditable) integracion;
            vista.mostrarMensaje(auditoria.generarAuditoria());
        } else {
            vista.mostrarMensaje("Esta integracion no permite auditorias.");
        }
    }
}
