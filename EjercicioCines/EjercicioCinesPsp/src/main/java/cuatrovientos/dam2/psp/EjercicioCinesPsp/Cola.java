package cuatrovientos.dam2.psp.EjercicioCinesPsp;
import java.util.concurrent.Semaphore;

public class Cola {
	private final int id;
    public final Semaphore sem_espacio;   
    public final Semaphore sem_clientes;  
    private int contadorPersonas = 0;

    public Cola(int id, int capacidad) {
        this.id = id;
        this.sem_espacio = new Semaphore(capacidad);
        this.sem_clientes = new Semaphore(0);
    }

    public boolean clienteIntentaEntrar() {
        if (sem_espacio.tryAcquire()) { 
            synchronized (this) { contadorPersonas++; }
            sem_clientes.release(); 
            return true;
        }
        return false;
    }

    public void taquillaAtiende() throws InterruptedException {
        sem_clientes.acquire(); 
        synchronized (this) { contadorPersonas--; }
        sem_espacio.release(); 
    }
    
    public synchronized int getPersonas() { return contadorPersonas; }
}


