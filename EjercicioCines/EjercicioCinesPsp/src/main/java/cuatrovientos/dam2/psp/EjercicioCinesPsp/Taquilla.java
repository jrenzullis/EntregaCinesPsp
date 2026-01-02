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
            while (cine.hayAsientos()) {
               
                Cola colaAAtender = null;
                for (Cola c : colas) {
                    if (c.getPersonas() > 0) {
                        colaAAtender = c;
                        break;
                    }
                }

                if (colaAAtender != null) {
                    
                    int tiempoVenta = rand.nextInt(tMax - tMin + 1) + tMin;
                    Thread.sleep(tiempoVenta * 10); 

                    if (cine.venderEntrada()) {
                        colaAAtender.taquillaAtiende();
                    }
                } else {
                    Thread.sleep(50); 
                }
            }
        } catch (InterruptedException e) { }
    }

}
