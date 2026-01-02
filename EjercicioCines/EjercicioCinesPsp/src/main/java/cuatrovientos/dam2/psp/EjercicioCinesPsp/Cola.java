package cuatrovientos.dam2.psp.EjercicioCinesPsp;
import java.util.concurrent.Semaphore;

public class Cola {
    private final int id;
    public final Semaphore sem_espacio;   
    public final Semaphore sem_clientes;  
    private int contadorPersonas = 0;

    public Cola(int id, int capacidad) {
        this.id = id;
        // Inicializo los permisos segun la capacidad maxima de mi cola
        this.sem_espacio = new Semaphore(capacidad);
        // Empiezo con cero clientes esperando ser atendidos
        this.sem_clientes = new Semaphore(0);
    }

    // Intento meter a un cliente si tengo hueco libre en mi semaforo
    public boolean clienteIntentaEntrar() {
        if (sem_espacio.tryAcquire()) { 
            // Sumo uno a mi contador de forma segura
            synchronized (this) { contadorPersonas++; }
            // Libero un permiso para avisar a la taquilla de que hay alguien
            sem_clientes.release(); 
            return true;
        }
        return false;
    }

    // Gestiono la atencion del cliente esperando a que el semaforo me de paso
    public void taquillaAtiende() throws InterruptedException {
        // Me quedo bloqueado aqui hasta que llegue un cliente
        sem_clientes.acquire(); 
        // Resto uno a mi contador cuando el cliente ya es atendido
        synchronized (this) { contadorPersonas--; }
        // Libero un hueco en mi espacio para que pueda entrar otra persona
        sem_espacio.release(); 
    }
    
    // Devuelvo el numero actual de personas que tengo esperando
    public synchronized int getPersonas() { return contadorPersonas; }
}