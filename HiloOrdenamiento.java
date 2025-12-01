// Hilo que ejecuta un algoritmo continuamente durante el tiempo límite
public class HiloOrdenamiento extends Thread {
    
    private final TareaOrdenamiento.TipoAlgoritmo algoritmo; // qué algoritmo usar
    private final GeneradorColecciones generador;
    private final EstadisticasAlgoritmo estadisticas; // guarda los resultados
    private final long tiempoLimiteMillis;
    private final int[] tiposColecciones; // los 4 tipos de colecciones
    private volatile boolean debeDetenerse; // para parar el hilo
    
    public HiloOrdenamiento(TareaOrdenamiento.TipoAlgoritmo algoritmo, 
                           long tiempoLimiteMillis,
                           int[] tiposColecciones) {
        this.algoritmo = algoritmo;
        this.tiempoLimiteMillis = tiempoLimiteMillis;
        this.tiposColecciones = tiposColecciones;
        this.generador = new GeneradorColecciones();
        this.estadisticas = new EstadisticasAlgoritmo(algoritmo.toString());
        this.debeDetenerse = false;
    }
    
    @Override
    public void run() {
        long tiempoInicio = System.currentTimeMillis();
        int indiceColeccion = 0;
        
        // sigue ordenando hasta que se acabe el tiempo
        while (!debeDetenerse) {
            long tiempoActual = System.currentTimeMillis();
            long tiempoTranscurrido = tiempoActual - tiempoInicio;
            
            // verifica si ya pasó el tiempo
            if (tiempoTranscurrido >= tiempoLimiteMillis) {
                break;
            }
            
            // genera una colección y la ordena
            int tipoColeccion = tiposColecciones[indiceColeccion];
            int[] datos = generador.generarPorTipo(tipoColeccion);
            
            // mide cuánto tarda en ordenar
            long inicio = System.currentTimeMillis();
            ordenar(datos);
            long fin = System.currentTimeMillis();
            
            estadisticas.registrarOrdenamiento(fin - inicio);
            
            indiceColeccion++;
            if (indiceColeccion >= tiposColecciones.length) {
                estadisticas.marcarComoCompleto();
                indiceColeccion = 0;
            }
        }
    }
    
    public void detener() {
        debeDetenerse = true;
    }
    
    public EstadisticasAlgoritmo getEstadisticas() {
        return estadisticas;
    }
    
    private int[] ordenar(int[] datos) {
        switch (algoritmo) {
            case BUBBLE_SORT:
                return AlgoritmosOrdenamiento.bubbleSort(datos);
            case INSERTION_SORT:
                return AlgoritmosOrdenamiento.insertionSort(datos);
            case SELECTION_SORT:
                return AlgoritmosOrdenamiento.selectionSort(datos);
            case MERGE_SORT:
                return AlgoritmosOrdenamiento.mergeSort(datos);
            case QUICK_SORT:
                return AlgoritmosOrdenamiento.quickSort(datos);
            case HEAP_SORT:
                return AlgoritmosOrdenamiento.heapSort(datos);
            default:
                return datos;
        }
    }
}
