public class EjecutorOrdenamientoParalelo {
    
    private final int numeroHilos;
    
    public EjecutorOrdenamientoParalelo(int numeroHilos) {
        this.numeroHilos = numeroHilos;
    }
    
    public EjecutorOrdenamientoParalelo() {
        this(Runtime.getRuntime().availableProcessors());
    }
    
    public ResultadoOrdenamiento ejecutarAlgoritmo(int[] datos, TareaOrdenamiento.TipoAlgoritmo algoritmo) 
            throws InterruptedException, Exception {
        
        TareaOrdenamiento tarea = new TareaOrdenamiento(datos, algoritmo);
        long tiempoInicio = System.nanoTime();
        
        tarea.start();
        tarea.join();
        
        int[] arregloOrdenado = tarea.obtenerResultado();
        
        long tiempoFin = System.nanoTime();
        long duracion = (tiempoFin - tiempoInicio) / 1_000_000;
        
        return new ResultadoOrdenamiento(algoritmo, arregloOrdenado, duracion);
    }
    
    public ResultadoOrdenamiento[] ejecutarTodosLosAlgoritmos(int[] datos) 
            throws InterruptedException, Exception {
        
        TareaOrdenamiento.TipoAlgoritmo[] algoritmos = TareaOrdenamiento.TipoAlgoritmo.values();
        ResultadoOrdenamiento[] resultados = new ResultadoOrdenamiento[algoritmos.length];
        
        for (int i = 0; i < algoritmos.length; i++) {
            resultados[i] = ejecutarAlgoritmo(datos, algoritmos[i]);
        }
        
        return resultados;
    }
    
    public ResultadoOrdenamiento[] ejecutarTodosEnParalelo(int[] datos) 
            throws InterruptedException, Exception {
        
        TareaOrdenamiento.TipoAlgoritmo[] algoritmos = TareaOrdenamiento.TipoAlgoritmo.values();
        TareaOrdenamiento[] tareas = new TareaOrdenamiento[algoritmos.length];
        long[] tiemposInicio = new long[algoritmos.length];
        
        for (int i = 0; i < algoritmos.length; i++) {
            tareas[i] = new TareaOrdenamiento(datos, algoritmos[i]);
            tiemposInicio[i] = System.nanoTime();
            tareas[i].start();
        }
        
        ResultadoOrdenamiento[] resultados = new ResultadoOrdenamiento[algoritmos.length];
        
        for (int i = 0; i < tareas.length; i++) {
            tareas[i].join();
            long tiempoFin = System.nanoTime();
            long duracion = (tiempoFin - tiemposInicio[i]) / 1_000_000;
            
            int[] arregloOrdenado = tareas[i].obtenerResultado();
            resultados[i] = new ResultadoOrdenamiento(algoritmos[i], arregloOrdenado, duracion);
        }
        
        return resultados;
    }
    
    public static class ResultadoOrdenamiento {
        private final TareaOrdenamiento.TipoAlgoritmo algoritmo;
        private final int[] arregloOrdenado;
        private final long tiempoMillis;
        
        public ResultadoOrdenamiento(TareaOrdenamiento.TipoAlgoritmo algoritmo, 
                                    int[] arregloOrdenado, long tiempoMillis) {
            this.algoritmo = algoritmo;
            this.arregloOrdenado = arregloOrdenado;
            this.tiempoMillis = tiempoMillis;
        }
        
        public TareaOrdenamiento.TipoAlgoritmo getAlgoritmo() {
            return algoritmo;
        }
        
        public int[] getArregloOrdenado() {
            return arregloOrdenado;
        }
        
        public long getTiempoMillis() {
            return tiempoMillis;
        }
        
        @Override
        public String toString() {
            return algoritmo + " completado en " + tiempoMillis + " ms";
        }
    }
}
