package cuatrovientos.dam2.psp.EjercicioCinesPsp;
import java.util.*;

public class Taquilla extends Thread {

    private final int id;
    private final Cine cine;
    private final List<Cola> colas;
    private final int tMin, tMax;
    private final Random rand = new Random();

    public Taquilla(int id, Cine cine, List<Cola> colas, int tMin, int tMax) {
        this.id = id;
        this.cine = cine;
        this.colas = colas;
        this.tMin = tMin;
        this.tMax = tMax;
    }

    @Override
    public void run() {
        try {
            // Me mantengo trabajando mientras mi cine tenga asientos libres
            while (cine.hayAsientos()) {
               
                // Busco en mi lista de colas alguna que tenga personas esperando
                Cola colaAAtender = null;
                for (Cola c : colas) {
                    if (c.getPersonas() > 0) {
                        colaAAtender = c;
                        break;
                    }
                }

                // Si he encontrado a alguien en una cola, procedo con la venta
                if (colaAAtender != null) {
                    
                    // Calculo un tiempo aleatorio y simulo lo que tardo en vender
                    int tiempoVenta = rand.nextInt(tMax - tMin + 1) + tMin;
                    Thread.sleep(tiempoVenta * 10); 

                    // Si logro confirmar la venta en el cine, saco al cliente de la cola
                    if (cine.venderEntrada()) {
                        colaAAtender.taquillaAtiende();
                    }
                } else {
                    // Si todas las colas estan vacias, descanso un poco antes de volver a mirar
                    Thread.sleep(50); 
                }
            }
        } catch (InterruptedException e) { 
            // Manejo la interrupcion si mi hilo se detiene inesperadamente
        }
    }
}