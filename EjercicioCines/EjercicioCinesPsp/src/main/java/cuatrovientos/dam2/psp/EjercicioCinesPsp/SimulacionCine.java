package cuatrovientos.dam2.psp.EjercicioCinesPsp;
import java.util.*;

public class SimulacionCine {
	public static void main(String[] args) {
       
        int nAsientos = 200;
        int nTaquillas = 2;
        int nColas = 4;
        int maxPorCola = 10;
        int tiempoVentaMin = 20; 
        int tiempoVentaMax = 30;
        int tasaClientesMin = 10; 
        int tasaClientesMax = 15;
        int tiempoAperturaMinutos = 30;

        Cine cine = new Cine(nAsientos);
        List<Cola> colas = new ArrayList<>();
        for (int i = 0; i < nColas; i++) colas.add(new Cola(i, maxPorCola));

      
        for (int i = 0; i < nTaquillas; i++) {
            new Taquilla(i, cine, colas, tiempoVentaMin, tiempoVentaMax).start();
        }

       
        Random rand = new Random();
        long inicio = System.currentTimeMillis();
        
        System.out.println("Abriendo taquillas...");

        
        for (int minuto = 0; minuto < tiempoAperturaMinutos; minuto++) {
            if (cine.getAsientosDisponibles() <= 0) break;

            int clientesNuevos = rand.nextInt(tasaClientesMax - tasaClientesMin + 1) + tasaClientesMin;
            for (int i = 0; i < clientesNuevos; i++) {
                boolean entro = false;
               
                for (Cola c : colas) {
                    if (c.entrar(i)) {
                        entro = true;
                        break;
                    }
                }
                if (!entro) cine.clienteSeFue();
            }
            
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }

        cine.mostrarResultados(System.currentTimeMillis() - inicio);
        System.exit(0);
    }
	

}
