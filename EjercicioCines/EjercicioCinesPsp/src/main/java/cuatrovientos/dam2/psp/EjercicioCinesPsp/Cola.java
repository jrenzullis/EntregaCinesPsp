package cuatrovientos.dam2.psp.EjercicioCinesPsp;
import java.util.concurrent.Semaphore;

public class Cola {
	private final int id;
    private final Semaphore espacioLibre;
    private final Semaphore clientesEnCola;
    private int personasActuales = 0;

    public Cola(int id, int capacidadMaxima) {
        this.id = id;
        this.espacioLibre = new Semaphore(capacidadMaxima);
        this.clientesEnCola = new Semaphore(0);
    }

    public boolean entrar(int idCliente) {
        if (espacioLibre.tryAcquire()) {
            synchronized (this) { personasActuales++; }
            clientesEnCola.release();
            return true;
        }
        return false;
    }

    public void atender() throws InterruptedException {
        clientesEnCola.acquire();
        synchronized (this) { personasActuales--; }
        espacioLibre.release();
    }
}


