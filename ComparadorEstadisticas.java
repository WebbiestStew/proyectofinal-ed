public class ComparadorEstadisticas {
    
    public static EstadisticasAlgoritmo[] ordenarPorEficiencia(EstadisticasAlgoritmo[] estadisticas) {
        EstadisticasAlgoritmo[] ordenado = clonarArreglo(estadisticas);
        int n = ordenado.length;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (esMenosEficiente(ordenado[j], ordenado[j + 1])) {
                    EstadisticasAlgoritmo temp = ordenado[j];
                    ordenado[j] = ordenado[j + 1];
                    ordenado[j + 1] = temp;
                }
            }
        }
        
        return ordenado;
    }
    
    private static boolean esMenosEficiente(EstadisticasAlgoritmo a, EstadisticasAlgoritmo b) {
        if (a.getColeccionesOrdenadas() != b.getColeccionesOrdenadas()) {
            return a.getColeccionesOrdenadas() < b.getColeccionesOrdenadas();
        }
        
        return a.getPromedioMillis() > b.getPromedioMillis();
    }
    
    private static EstadisticasAlgoritmo[] clonarArreglo(EstadisticasAlgoritmo[] original) {
        EstadisticasAlgoritmo[] copia = new EstadisticasAlgoritmo[original.length];
        for (int i = 0; i < original.length; i++) {
            copia[i] = original[i];
        }
        return copia;
    }
    
    public static void imprimirResultados(EstadisticasAlgoritmo[] estadisticas, int totalColecciones) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              RESULTADOS DE ORDENAMIENTO CONCURRENTE         ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        System.out.println("Total de colecciones a procesar: " + totalColecciones + "\n");
        
        System.out.println("┌─────────────────┬────────────┬─────────────┬──────────────┐");
        System.out.println("│ Algoritmo       │ Ordenadas  │ Promedio ms │ Completó     │");
        System.out.println("├─────────────────┼────────────┼─────────────┼──────────────┤");
        
        for (EstadisticasAlgoritmo est : estadisticas) {
            String nombre = ajustarLongitud(est.getNombre(), 15);
            String ordenadas = ajustarLongitud(String.valueOf(est.getColeccionesOrdenadas()), 10);
            String promedio = ajustarLongitud(formatearDecimal(est.getPromedioMillis()), 11);
            String completo = est.completoTodas() ? "Sí    " : "No    ";
            
            System.out.println("│ " + nombre + " │ " + ordenadas + " │ " + promedio + " │ " + completo + "      │");
        }
        
        System.out.println("└─────────────────┴────────────┴─────────────┴──────────────┘\n");
        
        System.out.println("═".repeat(60));
        System.out.println("RANKING DE EFICIENCIA (Más eficiente → Menos eficiente)");
        System.out.println("═".repeat(60) + "\n");
        
        EstadisticasAlgoritmo[] ranking = ordenarPorEficiencia(estadisticas);
        
        for (int i = 0; i < ranking.length; i++) {
            EstadisticasAlgoritmo est = ranking[i];
            System.out.println((i + 1) + ". " + est.getNombre());
            System.out.println("   └─ Colecciones ordenadas: " + est.getColeccionesOrdenadas());
            System.out.println("   └─ Tiempo promedio: " + formatearDecimal(est.getPromedioMillis()) + " ms");
            System.out.println("   └─ Completó todas: " + (est.completoTodas() ? "Sí" : "No"));
            System.out.println();
        }
        
        System.out.println("═".repeat(60));
        System.out.println("ALGORITMOS QUE COMPLETARON TODAS LAS COLECCIONES");
        System.out.println("═".repeat(60) + "\n");
        
        boolean hayCompletos = false;
        for (EstadisticasAlgoritmo est : estadisticas) {
            if (est.completoTodas()) {
                System.out.println("✓ " + est.getNombre());
                hayCompletos = true;
            }
        }
        
        if (!hayCompletos) {
            System.out.println("Ningún algoritmo completó todas las colecciones en el tiempo dado.");
        }
        
        System.out.println();
    }
    
    private static String ajustarLongitud(String texto, int longitud) {
        if (texto.length() >= longitud) {
            return texto.substring(0, longitud);
        }
        
        int espacios = longitud - texto.length();
        return texto + repetirCaracter(' ', espacios);
    }
    
    private static String formatearDecimal(double numero) {
        long parteEntera = (long) numero;
        long parteDecimal = (long) ((numero - parteEntera) * 100);
        return parteEntera + "." + (parteDecimal < 10 ? "0" : "") + parteDecimal;
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
