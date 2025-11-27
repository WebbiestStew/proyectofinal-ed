public class PruebaSistemaOrdenamiento {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║        SISTEMA DE ORDENAMIENTO PARALELO - INTEGRANTE B     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // Para empezar, usar un tamaño razonable para pruebas rápidas
        int[] datosOriginales = generarDatosAleatorios(1000);
        
        System.out.println("Datos generados: " + datosOriginales.length + " elementos\n");
        System.out.println("Primeros 20 elementos:");
        mostrarArreglo(copiarArreglo(datosOriginales, 20));
        System.out.println("\n");
        
        EjecutorOrdenamientoParalelo ejecutor = new EjecutorOrdenamientoParalelo();
        
        try {
            System.out.println("═".repeat(60));
            System.out.println("EJECUTANDO ALGORITMOS DE ORDENAMIENTO");
            System.out.println("═".repeat(60) + "\n");
            
            EjecutorOrdenamientoParalelo.ResultadoOrdenamiento[] resultados = 
                ejecutor.ejecutarTodosLosAlgoritmos(datosOriginales);
            
            System.out.println("\nRESULTADOS DE EJECUCIÓN:");
            System.out.println("─".repeat(60));
            
            // Mostrar resultados (usar enhanced for para simplicidad)
            for (EjecutorOrdenamientoParalelo.ResultadoOrdenamiento resultado : resultados) {
                System.out.println(formatearResultado(
                    resultado.getAlgoritmo().toString(), 
                    resultado.getTiempoMillis(),
                    verificarOrdenamiento(resultado.getArregloOrdenado())
                ));
            }
            
            System.out.println("\n\nPrimeros 20 elementos ordenados (usando Merge Sort):");
            mostrarArreglo(copiarArreglo(resultados[3].getArregloOrdenado(), 20));
            
            System.out.println("\n\n");
            String reporte = ComplejidadAlgoritmos.generarReporteCompleto();
            System.out.println(reporte);
            
            System.out.println("\n═".repeat(60));
            System.out.println("PRUEBA CON DIFERENTES TAMAÑOS DE DATOS");
            System.out.println("═".repeat(60) + "\n");
            
            int[] tamanios = {100, 1000, 5000, 10000};
            
            for (int i = 0; i < tamanios.length; i++) {
                int tamanio = tamanios[i];
                System.out.println("\nTamaño: " + tamanio + " elementos");
                System.out.println("─".repeat(40));
                
                int[] datos = generarDatosAleatorios(tamanio);
                
                try {
                    EjecutorOrdenamientoParalelo.ResultadoOrdenamiento resultado = 
                        ejecutor.ejecutarAlgoritmo(datos, TareaOrdenamiento.TipoAlgoritmo.QUICK_SORT);
                    
                    System.out.println("Quick Sort: " + resultado.getTiempoMillis() + " ms");
                    
                    resultado = ejecutor.ejecutarAlgoritmo(datos, TareaOrdenamiento.TipoAlgoritmo.MERGE_SORT);
                    System.out.println("Merge Sort: " + resultado.getTiempoMillis() + " ms");
                    
                } catch (Exception e) {
                    System.err.println("Error en prueba: " + e.getMessage());
                }
            }
            
        } catch (InterruptedException e) {
            System.err.println("Error durante la ejecución: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error durante la ejecución: " + e.getMessage());
        }
        
        System.out.println("\n\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              SISTEMA FINALIZADO CORRECTAMENTE              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
    
    private static int[] generarDatosAleatorios(int tamanio) {
        int[] datos = new int[tamanio];
        long semilla = System.nanoTime();
        
        for (int i = 0; i < tamanio; i++) {
            semilla = (semilla * 1103515245 + 12345) & 0x7fffffffL;
            datos[i] = (int)(semilla % 100000);
        }
        return datos;
    }
    
    private static boolean verificarOrdenamiento(int[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            if (arreglo[i] > arreglo[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    private static void mostrarArreglo(int[] arreglo) {
        System.out.print("[");
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print(arreglo[i]);
            if (i < arreglo.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }
    
    private static int[] copiarArreglo(int[] original, int cantidad) {
        int longitud = cantidad < original.length ? cantidad : original.length;
        int[] copia = new int[longitud];
        for (int i = 0; i < longitud; i++) {
            copia[i] = original[i];
        }
        return copia;
    }
    
    private static String formatearResultado(String algoritmo, long tiempo, boolean verificado) {
        String espacios = repetirCaracter(' ', 20 - algoritmo.length());
        String verificacion = verificado ? "✓" : "✗";
        return algoritmo + espacios + " | Tiempo: " + 
               formatearTiempo(tiempo) + " ms | Verificado: " + verificacion;
    }
    
    private static String formatearTiempo(long tiempo) {
        String tiempoStr = String.valueOf(tiempo);
        int espaciosNecesarios = 6 - tiempoStr.length();
        String espacios = repetirCaracter(' ', espaciosNecesarios);
        return espacios + tiempoStr;
    }
    
    private static String repetirCaracter(char c, int veces) {
        if (veces <= 0) return "";
        char[] resultado = new char[veces];
        for (int i = 0; i < veces; i++) {
            resultado[i] = c;
        }
        return new String(resultado);
    }
}
