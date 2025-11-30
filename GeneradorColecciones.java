public class GeneradorColecciones {
    
    private long semilla;
    
    public GeneradorColecciones() {
        this.semilla = System.nanoTime();
    }
    
    public int[] generar100Elementos() {
        return generarAleatorios(100, 100000);
    }
    
    public int[] generar50000Elementos() {
        return generarAleatorios(50000, 100000);
    }
    
    public int[] generar100000Elementos() {
        return generarAleatorios(100000, 100000);
    }
    
    public int[] generar100000Restringidos() {
        return generarAleatorios(100000, 5);
    }
    
    private int[] generarAleatorios(int cantidad, int valorMaximo) {
        int[] datos = new int[cantidad];
        
        for (int i = 0; i < cantidad; i++) {
            semilla = (semilla * 1103515245 + 12345) & 0x7fffffffL;
            datos[i] = ((int)(semilla % valorMaximo)) + 1;
        }
        
        return datos;
    }
    
    public String obtenerDescripcion(int tipo) {
        switch (tipo) {
            case 1:
                return "100 elementos aleatorios";
            case 2:
                return "50,000 elementos aleatorios";
            case 3:
                return "100,000 elementos aleatorios";
            case 4:
                return "100,000 elementos (1-5)";
            default:
                return "Desconocido";
        }
    }
    
    public int[] generarPorTipo(int tipo) {
        switch (tipo) {
            case 1:
                return generar100Elementos();
            case 2:
                return generar50000Elementos();
            case 3:
                return generar100000Elementos();
            case 4:
                return generar100000Restringidos();
            default:
                return new int[0];
        }
    }
}
