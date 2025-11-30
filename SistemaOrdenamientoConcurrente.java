public class SistemaOrdenamientoConcurrente {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     SISTEMA DE ORDENAMIENTO CONCURRENTE - PROYECTO FINAL   ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        System.out.println("Este programa ejecutará 6 algoritmos de ordenamiento de forma");
        System.out.println("concurrente durante el tiempo que especifiques.\n");
        
        System.out.println("Colecciones a procesar:");
        System.out.println("  1. 100 elementos aleatorios");
        System.out.println("  2. 50,000 elementos aleatorios");
        System.out.println("  3. 100,000 elementos aleatorios");
        System.out.println("  4. 100,000 elementos (números del 1 al 5)\n");
        
        int tiempoSegundos = leerTiempoDelUsuario();
        
        if (tiempoSegundos <= 0) {
            System.out.println("Tiempo inválido. El programa terminará.");
            return;
        }
        
        System.out.println("\n═".repeat(60));
        System.out.println("Iniciando ejecución concurrente por " + tiempoSegundos + " segundos...");
        System.out.println("═".repeat(60) + "\n");
        
        ejecutarOrdenamientoConcurrente(tiempoSegundos);
    }
    
    private static int leerTiempoDelUsuario() {
        System.out.print("Ingresa el tiempo de ejecución (en segundos): ");
        
        try {
            StringBuilder entrada = new StringBuilder();
            int caracter;
            
            while ((caracter = System.in.read()) != '\n' && caracter != -1) {
                if (caracter != '\r') {
                    entrada.append((char) caracter);
                }
            }
            
            String texto = entrada.toString().trim();
            return convertirAEntero(texto);
            
        } catch (Exception e) {
            System.out.println("Error al leer entrada: " + e.getMessage());
            return 30;
        }
    }
    
    private static int convertirAEntero(String texto) {
        if (texto == null || texto.length() == 0) {
            return 30;
        }
        
        int resultado = 0;
        boolean esNegativo = false;
        int inicio = 0;
        
        if (texto.charAt(0) == '-') {
            esNegativo = true;
            inicio = 1;
        }
        
        for (int i = inicio; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c < '0' || c > '9') {
                break;
            }
            resultado = resultado * 10 + (c - '0');
        }
        
        return esNegativo ? -resultado : resultado;
    }
    
    private static void ejecutarOrdenamientoConcurrente(int tiempoSegundos) {
        long tiempoMillis = tiempoSegundos * 1000L;
        
        int[] tiposColecciones = {1, 2, 3, 4};
        
        TareaOrdenamiento.TipoAlgoritmo[] algoritmos = TareaOrdenamiento.TipoAlgoritmo.values();
        HiloOrdenamiento[] hilos = new HiloOrdenamiento[algoritmos.length];
        
        for (int i = 0; i < algoritmos.length; i++) {
            hilos[i] = new HiloOrdenamiento(algoritmos[i], tiempoMillis, tiposColecciones);
        }
        
        long inicioEjecucion = System.currentTimeMillis();
        
        for (HiloOrdenamiento hilo : hilos) {
            hilo.start();
        }
        
        try {
            Thread.sleep(tiempoMillis);
        } catch (InterruptedException e) {
            System.out.println("Ejecución interrumpida.");
        }
        
        for (HiloOrdenamiento hilo : hilos) {
            hilo.detener();
        }
        
        for (HiloOrdenamiento hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("Error esperando a hilo: " + e.getMessage());
            }
        }
        
        long finEjecucion = System.currentTimeMillis();
        long tiempoRealMillis = finEjecucion - inicioEjecucion;
        
        System.out.println("\n✓ Ejecución completada en " + (tiempoRealMillis / 1000.0) + " segundos\n");
        
        EstadisticasAlgoritmo[] estadisticas = new EstadisticasAlgoritmo[hilos.length];
        for (int i = 0; i < hilos.length; i++) {
            estadisticas[i] = hilos[i].getEstadisticas();
        }
        
        ComparadorEstadisticas.imprimirResultados(estadisticas, tiposColecciones.length);
        
        System.out.println("\n" + ComplejidadAlgoritmos.generarReporteCompleto());
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  EJECUCIÓN FINALIZADA                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
