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
	         while (cine.getAsientosDisponibles() > 0) {
	            
	             for (Cola cola : colas) {
	                 if (cola.entrar(-1)) { 
	                     int tiempoVenta = rand.nextInt(tMax - tMin + 1) + tMin;
	                     Thread.sleep(tiempoVenta * 100L); 

	                     if (cine.venderEntrada()) {
	                         cola.atender();
	                     }
	                     break;
	                 }
	             }
	         }
	     } catch (InterruptedException e) {
	    	 
	         Thread.currentThread().interrupt();
	     }
	 }

}
