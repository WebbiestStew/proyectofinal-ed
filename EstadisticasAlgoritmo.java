// Guarda las estadísticas de cada algoritmo
public class EstadisticasAlgoritmo {
    
    private final String nombre;
    private int coleccionesOrdenadas; // cuántas colecciones ordenó
    private long tiempoTotalMillis; // tiempo total que tardó
    private boolean completoTodas; // si terminó los 4 tipos
    
    public EstadisticasAlgoritmo(String nombre) {
        this.nombre = nombre;
        this.coleccionesOrdenadas = 0;
        this.tiempoTotalMillis = 0;
        this.completoTodas = false;
    }
    
    public synchronized void registrarOrdenamiento(long tiempoMillis) {
        coleccionesOrdenadas++;
        tiempoTotalMillis += tiempoMillis;
    }
    
    public synchronized void marcarComoCompleto() {
        completoTodas = true;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getColeccionesOrdenadas() {
        return coleccionesOrdenadas;
    }
    
    public long getTiempoTotalMillis() {
        return tiempoTotalMillis;
    }
    
    public double getPromedioMillis() {
        if (coleccionesOrdenadas == 0) {
            return 0.0;
        }
        return (double) tiempoTotalMillis / coleccionesOrdenadas;
    }
    
    public boolean completoTodas() {
        return completoTodas;
    }
}
