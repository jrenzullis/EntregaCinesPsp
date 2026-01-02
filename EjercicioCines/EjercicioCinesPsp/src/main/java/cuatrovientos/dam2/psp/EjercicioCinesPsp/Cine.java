package cuatrovientos.dam2.psp.EjercicioCinesPsp;

public class Cine {

	private int asientosDisponibles;
    private final int totalAsientos;
    private int clientesAtendidos = 0;
    private int clientesSinEntrada = 0;
    private long tiempoInicioVenta;

    public Cine(int asientos) {
        this.asientosDisponibles = asientos;
        this.totalAsientos = asientos;
    }

   
    public synchronized boolean venderEntrada() {
        if (asientosDisponibles > 0) {
            asientosDisponibles--;
            clientesAtendidos++;
            return true;
        }
        return false;
    }

    public synchronized void clienteSeFue() {
        clientesSinEntrada++;
    }

    public void mostrarResultados(long tiempoFinal) {
        System.out.println("\n--- RESULTADOS DE LA SIMULACIÓN ---");
        System.out.println("Clientes con entrada: " + clientesAtendidos);
        System.out.println("Clientes sin entrada (cola llena o cine lleno): " + clientesSinEntrada);
        System.out.println("Asientos vacíos: " + asientosDisponibles);
        System.out.println("Tiempo total de venta: " + (tiempoFinal / 1000) + " segundos simulados.");
    }

    public synchronized int getAsientosDisponibles() { return asientosDisponibles; }
}

