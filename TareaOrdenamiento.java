public class TareaOrdenamiento extends Thread {
    
    private final int[] datos;
    private final TipoAlgoritmo algoritmo;
    private int[] resultado;
    private Exception error;
    private boolean completado;
    
    public enum TipoAlgoritmo {
        BUBBLE_SORT,
        INSERTION_SORT,
        SELECTION_SORT,
        MERGE_SORT,
        QUICK_SORT,
        HEAP_SORT
    }
    
    public TareaOrdenamiento(int[] datos, TipoAlgoritmo algoritmo) {
        this.datos = clonarArreglo(datos);
        this.algoritmo = algoritmo;
        this.completado = false;
    }
    
    @Override
    public void run() {
        try {
            switch (algoritmo) {
                case BUBBLE_SORT:
                    resultado = AlgoritmosOrdenamiento.bubbleSort(datos);
                    break;
                case INSERTION_SORT:
                    resultado = AlgoritmosOrdenamiento.insertionSort(datos);
                    break;
                case SELECTION_SORT:
                    resultado = AlgoritmosOrdenamiento.selectionSort(datos);
                    break;
                case MERGE_SORT:
                    resultado = AlgoritmosOrdenamiento.mergeSort(datos);
                    break;
                case QUICK_SORT:
                    resultado = AlgoritmosOrdenamiento.quickSort(datos);
                    break;
                case HEAP_SORT:
                    resultado = AlgoritmosOrdenamiento.heapSort(datos);
                    break;
            }
            completado = true;
        } catch (Exception e) {
            error = e;
            completado = true;
        }
    }
    
    public int[] obtenerResultado() throws Exception {
        if (!completado) {
            throw new IllegalStateException("Tarea no completada");
        }
        if (error != null) {
            throw error;
        }
        return resultado;
    }
    
    public boolean estaCompletado() {
        return completado;
    }
    
    public TipoAlgoritmo getAlgoritmo() {
        return algoritmo;
    }
    
    private int[] clonarArreglo(int[] original) {
        int[] clon = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            clon[i] = original[i];
        }
        return clon;
    }
}
