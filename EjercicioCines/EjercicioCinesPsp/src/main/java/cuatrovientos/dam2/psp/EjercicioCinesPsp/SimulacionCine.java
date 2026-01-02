package cuatrovientos.dam2.psp.EjercicioCinesPsp;
import java.util.*;

public class SimulacionCine {
	public static void main(String[] args) throws InterruptedException {
        
        int asientos = 200;
        int nTaquillas = 6;
        int nColas = 8;
        int capacidadCola = 10;
        int tVentaMin = 20, tVentaMax = 30; 

        Cine cine = new Cine(asientos);
        List<Cola> colas = new ArrayList<>();
        for (int i = 0; i < nColas; i++) colas.add(new Cola(i, capacidadCola));

        List<Taquilla> hilosTaquilla = new ArrayList<>();
        for (int i = 0; i < nTaquillas; i++) {
            Taquilla t = new Taquilla(i, cine, colas, tVentaMin, tVentaMax);
            hilosTaquilla.add(t);
            t.start();
        }

        Random rand = new Random();
        long inicio = System.currentTimeMillis();

        
        for (int m = 0; m < 30; m++) {
            if (!cine.hayAsientos()) break;
            
            int clientesEsteMinuto = rand.nextInt(15 - 10 + 1) + 10;
            for (int i = 0; i < clientesEsteMinuto; i++) {
                boolean entro = false;
               
                for (Cola c : colas) {
                    if (c.clienteIntentaEntrar()) {
                        entro = true;
                        break;
                    }
                }
                if (!entro) cine.clienteRechazado();
            }
            Thread.sleep(100); 
        }

       
        Thread.sleep(2000); 

        cine.mostrarResultados(System.currentTimeMillis() - inicio);
        System.exit(0);
    }
	

}
