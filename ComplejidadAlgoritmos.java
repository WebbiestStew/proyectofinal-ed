public class ComplejidadAlgoritmos {
    
    public static class AnalisisComplejidad {
        private final String nombre;
        private final String mejorCaso;
        private final String casoPromedio;
        private final String peorCaso;
        private final String complejidadEspacial;
        private final String ventajas;
        private final String desventajas;
        
        public AnalisisComplejidad(String nombre, String mejorCaso, String casoPromedio, 
                                   String peorCaso, String complejidadEspacial, 
                                   String ventajas, String desventajas) {
            this.nombre = nombre;
            this.mejorCaso = mejorCaso;
            this.casoPromedio = casoPromedio;
            this.peorCaso = peorCaso;
            this.complejidadEspacial = complejidadEspacial;
            this.ventajas = ventajas;
            this.desventajas = desventajas;
        }
        
        public String getNombre() { return nombre; }
        public String getMejorCaso() { return mejorCaso; }
        public String getCasoPromedio() { return casoPromedio; }
        public String getPeorCaso() { return peorCaso; }
        public String getComplejidadEspacial() { return complejidadEspacial; }
        public String getVentajas() { return ventajas; }
        public String getDesventajas() { return desventajas; }
        
        @Override
        public String toString() {
            return String.format(
                "=== %s ===\n" +
                "Mejor caso: %s\n" +
                "Caso promedio: %s\n" +
                "Peor caso: %s\n" +
                "Complejidad espacial: %s\n" +
                "Ventajas: %s\n" +
                "Desventajas: %s\n",
                nombre, mejorCaso, casoPromedio, peorCaso, complejidadEspacial, ventajas, desventajas
            );
        }
    }
    
    public static AnalisisComplejidad getBubbleSort() {
        return new AnalisisComplejidad(
            "Bubble Sort",
            "O(n)",
            "O(n²)",
            "O(n²)",
            "O(1)",
            "Simple de implementar, estable, funciona bien con datos casi ordenados",
            "Muy ineficiente para arreglos grandes, requiere muchas comparaciones"
        );
    }
    
    public static AnalisisComplejidad getInsertionSort() {
        return new AnalisisComplejidad(
            "Insertion Sort",
            "O(n)",
            "O(n²)",
            "O(n²)",
            "O(1)",
            "Eficiente para arreglos pequeños o casi ordenados, estable, ordenamiento en lugar",
            "Ineficiente para grandes volúmenes de datos desordenados"
        );
    }
    
    public static AnalisisComplejidad getSelectionSort() {
        return new AnalisisComplejidad(
            "Selection Sort",
            "O(n²)",
            "O(n²)",
            "O(n²)",
            "O(1)",
            "Realiza un número mínimo de intercambios, simple de implementar",
            "Ineficiente para arreglos grandes, no es estable, mismo rendimiento sin importar el orden inicial"
        );
    }
    
    public static AnalisisComplejidad getMergeSort() {
        return new AnalisisComplejidad(
            "Merge Sort",
            "O(n log n)",
            "O(n log n)",
            "O(n log n)",
            "O(n)",
            "Garantiza O(n log n) en todos los casos, estable, predecible",
            "Requiere espacio adicional O(n), no es eficiente para arreglos pequeños"
        );
    }
    
    public static AnalisisComplejidad getQuickSort() {
        return new AnalisisComplejidad(
            "Quick Sort",
            "O(n log n)",
            "O(n log n)",
            "O(n²)",
            "O(log n)",
            "Muy rápido en la práctica, ordenamiento en lugar, buen uso de caché",
            "Peor caso O(n²) con pivote mal elegido, no es estable, recursivo"
        );
    }
    
    public static AnalisisComplejidad getHeapSort() {
        return new AnalisisComplejidad(
            "Heap Sort",
            "O(n log n)",
            "O(n log n)",
            "O(n log n)",
            "O(1)",
            "Garantiza O(n log n) en todos los casos, ordenamiento en lugar, no requiere recursión",
            "No es estable, constante más alta que Quick Sort, mal uso de caché"
        );
    }
    
    public static void imprimirTodosLosAnalisis() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ANÁLISIS DE COMPLEJIDAD - ALGORITMOS DE ORDENAMIENTO");
        System.out.println("=".repeat(60) + "\n");
        
        System.out.println(getBubbleSort());
        System.out.println(getInsertionSort());
        System.out.println(getSelectionSort());
        System.out.println(getMergeSort());
        System.out.println(getQuickSort());
        System.out.println(getHeapSort());
        
        System.out.println("=".repeat(60));
    }
    
    public static String generarReporteCompleto() {
        StringBuilder reporte = new StringBuilder();
        
        reporte.append("\n╔════════════════════════════════════════════════════════════╗\n");
        reporte.append("║   REPORTE TÉCNICO: ANÁLISIS DE ALGORITMOS DE ORDENAMIENTO  ║\n");
        reporte.append("╚════════════════════════════════════════════════════════════╝\n\n");
        
        reporte.append("1. ALGORITMOS CUADRÁTICOS O(n²)\n");
        reporte.append("─".repeat(60)).append("\n\n");
        reporte.append(getBubbleSort()).append("\n");
        reporte.append(getInsertionSort()).append("\n");
        reporte.append(getSelectionSort()).append("\n");
        
        reporte.append("\n2. ALGORITMOS LOGARÍTMICOS O(n log n)\n");
        reporte.append("─".repeat(60)).append("\n\n");
        reporte.append(getMergeSort()).append("\n");
        reporte.append(getQuickSort()).append("\n");
        reporte.append(getHeapSort()).append("\n");
        
        reporte.append("\n3. RESUMEN COMPARATIVO\n");
        reporte.append("─".repeat(60)).append("\n");
        reporte.append("┌─────────────────┬──────────┬─────────────┬──────────┐\n");
        reporte.append("│ Algoritmo       │ Mejor    │ Promedio    │ Peor     │\n");
        reporte.append("├─────────────────┼──────────┼─────────────┼──────────┤\n");
        reporte.append("│ Bubble Sort     │ O(n)     │ O(n²)       │ O(n²)    │\n");
        reporte.append("│ Insertion Sort  │ O(n)     │ O(n²)       │ O(n²)    │\n");
        reporte.append("│ Selection Sort  │ O(n²)    │ O(n²)       │ O(n²)    │\n");
        reporte.append("│ Merge Sort      │ O(n logn)│ O(n log n)  │ O(n logn)│\n");
        reporte.append("│ Quick Sort      │ O(n logn)│ O(n log n)  │ O(n²)    │\n");
        reporte.append("│ Heap Sort       │ O(n logn)│ O(n log n)  │ O(n logn)│\n");
        reporte.append("└─────────────────┴──────────┴─────────────┴──────────┘\n\n");
        
        reporte.append("4. RECOMENDACIONES DE USO\n");
        reporte.append("─".repeat(60)).append("\n");
        reporte.append("• Datos pequeños o casi ordenados: Insertion Sort\n");
        reporte.append("• Datos grandes, garantía de rendimiento: Merge Sort o Heap Sort\n");
        reporte.append("• Mejor rendimiento promedio: Quick Sort\n");
        reporte.append("• Mínima memoria adicional: Heap Sort o Quick Sort\n");
        reporte.append("• Estabilidad requerida: Merge Sort o Bubble Sort\n\n");
        
        return reporte.toString();
    }
}
