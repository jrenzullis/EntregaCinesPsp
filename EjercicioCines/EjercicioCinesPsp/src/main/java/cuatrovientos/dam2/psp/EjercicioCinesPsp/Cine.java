package cuatrovientos.dam2.psp.EjercicioCinesPsp;

public class Cine {
	private int asientosDisponibles;
    private int clientesAtendidos = 0;
    private int clientesSinEntrada = 0;

    public Cine(int asientos) {
        this.asientosDisponibles = asientos;
    }

    public synchronized boolean hayAsientos() {
        return asientosDisponibles > 0;
    }

    public synchronized boolean venderEntrada() {
        if (asientosDisponibles > 0) {
            asientosDisponibles--;
            clientesAtendidos++;
            return true;
        }
        return false;
    }

    public synchronized void clienteRechazado() {
        clientesSinEntrada++;
    }

    public void mostrarResultados(long tiempoMs) {
        System.out.println("\n--- RESULTADOS DE LA SIMULACIÓN ---");
        System.out.println("Clientes con entrada: " + clientesAtendidos);
        System.out.println("Clientes que se fueron (cola llena): " + clientesSinEntrada);
        System.out.println("Asientos que quedaron vacíos: " + asientosDisponibles);
        System.out.println("Tiempo total de ejecución: " + (tiempoMs / 1000.0) + " segundos.");
    }
    
}

