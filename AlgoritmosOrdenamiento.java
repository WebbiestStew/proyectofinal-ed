public class AlgoritmosOrdenamiento {

    public static int[] bubbleSort(int[] arreglo) {
        int[] resultado = arreglo.clone();
        int n = resultado.length;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (resultado[j] > resultado[j + 1]) {
                    int temp = resultado[j];
                    resultado[j] = resultado[j + 1];
                    resultado[j + 1] = temp;
                }
            }
        }
        
        return resultado;
    }

    public static int[] insertionSort(int[] arreglo) {
        int[] resultado = arreglo.clone();
        int n = resultado.length;
        
        for (int i = 1; i < n; i++) {
            int clave = resultado[i];
            int j = i - 1;
            
            while (j >= 0 && resultado[j] > clave) {
                resultado[j + 1] = resultado[j];
                j--;
            }
            
            resultado[j + 1] = clave;
        }
        
        return resultado;
    }

    public static int[] selectionSort(int[] arreglo) {
        int[] resultado = arreglo.clone();
        int n = resultado.length;
        
        for (int i = 0; i < n - 1; i++) {
            int indiceMinimo = i;
            
            for (int j = i + 1; j < n; j++) {
                if (resultado[j] < resultado[indiceMinimo]) {
                    indiceMinimo = j;
                }
            }
            
            int temp = resultado[indiceMinimo];
            resultado[indiceMinimo] = resultado[i];
            resultado[i] = temp;
        }
        
        return resultado;
    }

    public static int[] mergeSort(int[] arreglo) {
        if (arreglo.length <= 1) {
            return arreglo.clone();
        }
        
        int[] resultado = arreglo.clone();
        mergeSortRecursivo(resultado, 0, resultado.length - 1);
        return resultado;
    }
    
    private static void mergeSortRecursivo(int[] arr, int izquierda, int derecha) {
        if (izquierda < derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            
            mergeSortRecursivo(arr, izquierda, medio);
            mergeSortRecursivo(arr, medio + 1, derecha);
            
            mezclar(arr, izquierda, medio, derecha);
        }
    }
    
    private static void mezclar(int[] arr, int izquierda, int medio, int derecha) {
        int n1 = medio - izquierda + 1;
        int n2 = derecha - medio;
        
        int[] izq = new int[n1];
        int[] der = new int[n2];
        
        for (int i = 0; i < n1; i++) {
            izq[i] = arr[izquierda + i];
        }
        for (int j = 0; j < n2; j++) {
            der[j] = arr[medio + 1 + j];
        }
        
        int i = 0, j = 0, k = izquierda;
        
        while (i < n1 && j < n2) {
            if (izq[i] <= der[j]) {
                arr[k] = izq[i];
                i++;
            } else {
                arr[k] = der[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            arr[k] = izq[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            arr[k] = der[j];
            j++;
            k++;
        }
    }

    public static int[] quickSort(int[] arreglo) {
        if (arreglo.length <= 1) {
            return arreglo.clone();
        }
        
        int[] resultado = arreglo.clone();
        quickSortRecursivo(resultado, 0, resultado.length - 1);
        return resultado;
    }
    
    private static void quickSortRecursivo(int[] arr, int bajo, int alto) {
        if (bajo < alto) {
            int indicePivote = particionar(arr, bajo, alto);
            
            quickSortRecursivo(arr, bajo, indicePivote - 1);
            quickSortRecursivo(arr, indicePivote + 1, alto);
        }
    }
    
    private static int particionar(int[] arr, int bajo, int alto) {
        int pivote = arr[alto];
        int i = bajo - 1;
        
        for (int j = bajo; j < alto; j++) {
            if (arr[j] < pivote) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        int temp = arr[i + 1];
        arr[i + 1] = arr[alto];
        arr[alto] = temp;
        
        return i + 1;
    }

    public static int[] heapSort(int[] arreglo) {
        int[] resultado = arreglo.clone();
        int n = resultado.length;
        
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(resultado, n, i);
        }
        
        for (int i = n - 1; i > 0; i--) {
            int temp = resultado[0];
            resultado[0] = resultado[i];
            resultado[i] = temp;
            
            heapify(resultado, i, 0);
        }
        
        return resultado;
    }
    
    private static void heapify(int[] arr, int n, int i) {
        int mayor = i;
        int izquierdo = 2 * i + 1;
        int derecho = 2 * i + 2;
        
        if (izquierdo < n && arr[izquierdo] > arr[mayor]) {
            mayor = izquierdo;
        }
        
        if (derecho < n && arr[derecho] > arr[mayor]) {
            mayor = derecho;
        }
        
        if (mayor != i) {
            int temp = arr[i];
            arr[i] = arr[mayor];
            arr[mayor] = temp;
            
            heapify(arr, n, mayor);
        }
    }
}
