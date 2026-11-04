public interface Transaccional {

    boolean procesarPago(double monto);

    boolean reintegrar(double monto);

}
