package cuatrovientos.dam2.psp.EjercicioCinesPsp;
import java.util.*;

public class SimulacionCine {
    public static void main(String[] args) throws InterruptedException {
        
        // Configuro los parametros iniciales de mi cine y las colas
        int asientos = 200;
        int nTaquillas = 6;
        int nColas = 8;
        int capacidadCola = 10;
        int tVentaMin = 20, tVentaMax = 30; 

        // Creo el objeto cine y preparo la lista de colas disponibles
        Cine cine = new Cine(asientos);
        List<Cola> colas = new ArrayList<>();
        for (int i = 0; i < nColas; i++) colas.add(new Cola(i, capacidadCola));

        // Inicializo y pongo en marcha los hilos de mis taquillas
        List<Taquilla> hilosTaquilla = new ArrayList<>();
        for (int i = 0; i < nTaquillas; i++) {
            Taquilla t = new Taquilla(i, cine, colas, tVentaMin, tVentaMax);
            hilosTaquilla.add(t);
            t.start();
        }

        Random rand = new Random();
        long inicio = System.currentTimeMillis();

        // Simulo el paso de 30 minutos creando clientes constantemente
        for (int m = 0; m < 30; m++) {
            // Si ya no me quedan asientos, dejo de generar gente
            if (!cine.hayAsientos()) break;
            
            // Calculo cuantos clientes llegan en este minuto concreto
            int clientesEsteMinuto = rand.nextInt(15 - 10 + 1) + 10;
            for (int i = 0; i < clientesEsteMinuto; i++) {
                boolean entro = false;
               
                // Busco entre todas mis colas una que tenga sitio libre
                for (Cola c : colas) {
                    if (c.clienteIntentaEntrar()) {
                        entro = true; // El cliente ha logrado entrar en una cola
                        break;
                    }
                }
                // Si ninguna cola tenia sitio, registro que el cliente se ha ido
                if (!entro) cine.clienteRechazado();
            }
            // Hago una pequeña pausa para simular el paso del tiempo
            Thread.sleep(100); 
        }

        // Espero un tiempo prudencial para que las taquillas terminen su trabajo
        Thread.sleep(2000); 

        // Muestro por consola el balance final de mi simulacion
        cine.mostrarResultados(System.currentTimeMillis() - inicio);
        // Cierro el programa por completo
        System.exit(0);
    }
}