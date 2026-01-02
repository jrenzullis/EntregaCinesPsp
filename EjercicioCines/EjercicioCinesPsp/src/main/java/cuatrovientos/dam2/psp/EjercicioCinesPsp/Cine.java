package cuatrovientos.dam2.psp.EjercicioCinesPsp;

public class Cine {
    private int asientosDisponibles;
    private int clientesAtendidos = 0;
    private int clientesSinEntrada = 0;

    public Cine(int asientos) {
        this.asientosDisponibles = asientos;
    }

    // Compruebo si me quedan asientos libres de forma segura entre hilos
    public synchronized boolean hayAsientos() {
        return asientosDisponibles > 0;
    }

    // Gestiono la venta asegurando que solo un hilo modifique los datos a la vez
    public synchronized boolean venderEntrada() {
        if (asientosDisponibles > 0) {
            asientosDisponibles--; // Resto un asiento del total disponible
            clientesAtendidos++;   // Sumo uno a mi cuenta de ventas con exito
            return true;
        }
        return false;
    }

    // Registro que un cliente se ha tenido que ir por falta de espacio en colas
    public synchronized void clienteRechazado() {
        clientesSinEntrada++;
    }

    // Imprimo por pantalla el resumen con todo lo ocurrido en la simulacion
    public void mostrarResultados(long tiempoMs) {
        System.out.println("\n--- RESULTADOS DE LA SIMULACION ---");
        System.out.println("Clientes con entrada: " + clientesAtendidos);
        System.out.println("Clientes que se fueron (cola llena): " + clientesSinEntrada);
        System.out.println("Asientos que quedaron vacios: " + asientosDisponibles);
        System.out.println("Tiempo total de ejecucion: " + (tiempoMs / 1000.0) + " segundos.");
    }
}