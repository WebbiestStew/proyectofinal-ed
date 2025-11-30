# Sistema de Ordenamiento Concurrente - Proyecto Final

## 📋 Descripción
Sistema que ejecuta 6 algoritmos de ordenamiento de forma concurrente durante un tiempo específico. Compara la eficiencia de cada algoritmo con diferentes tamaños de colecciones.

## 📦 Archivos del Proyecto

### Algoritmos
- `AlgoritmosOrdenamiento.java` - Los 6 algoritmos de ordenamiento
- `TareaOrdenamiento.java` - Wrapper para ejecutar algoritmos en hilos
- `HiloOrdenamiento.java` - Hilo que ordena colecciones continuamente

### Utilidades
- `GeneradorColecciones.java` - Genera las 4 colecciones requeridas
- `EstadisticasAlgoritmo.java` - Registra métricas de cada algoritmo
- `ComparadorEstadisticas.java` - Compara y ordena resultados por eficiencia
- `ComplejidadAlgoritmos.java` - Análisis teórico de complejidad

### Principal
- `SistemaOrdenamientoConcurrente.java` - Programa principal interactivo

## 🚀 Cómo Compilar

```bash
javac *.java
```

## ▶️ Cómo Ejecutar

```bash
java SistemaOrdenamientoConcurrente
```

El programa te pedirá el tiempo de ejecución en segundos. Por ejemplo:
- `30` para 30 segundos
- `60` para 1 minuto
- `120` para 2 minutos

## 📊 Colecciones que se Procesan

1. **100 elementos** - Números aleatorios hasta 100,000
2. **50,000 elementos** - Números aleatorios hasta 100,000
3. **100,000 elementos** - Números aleatorios hasta 100,000
4. **100,000 elementos restringidos** - Solo números del 1 al 5

## 🧵 Algoritmos Implementados

1. **Bubble Sort** - O(n²)
2. **Insertion Sort** - O(n²)
3. **Selection Sort** - O(n²)
4. **Merge Sort** - O(n log n)
5. **Quick Sort** - O(n log n)
6. **Heap Sort** - O(n log n)

## 📈 Qué Muestra el Programa

- ✅ Número de colecciones ordenadas por cada algoritmo
- ⏱️ Tiempo promedio de ejecución por colección
- 🎯 Qué algoritmos completaron todas las colecciones
- 🏆 Ranking de eficiencia (más eficiente → menos eficiente)
- 📊 Reporte de complejidad teórica

## 💡 Ejemplo de Uso

```
Ingresa el tiempo de ejecución (en segundos): 30

═══════════════════════════════════════════════════════════
Iniciando ejecución concurrente por 30 segundos...
═══════════════════════════════════════════════════════════

✓ Ejecución completada en 30.05 segundos

╔════════════════════════════════════════════════════════════╗
║              RESULTADOS DE ORDENAMIENTO CONCURRENTE         ║
╚════════════════════════════════════════════════════════════╝

[Aquí se muestran los resultados...]
```

## 🔧 Notas Técnicas

- **Sin java.util**: Solo usa las clases básicas de Java (excepto System y Thread)
- **Thread-safe**: Cada algoritmo trabaja con su propia copia de datos
- **Concurrente**: Los 6 algoritmos se ejecutan simultáneamente
- **Medición precisa**: Usa `System.currentTimeMillis()` para mediciones

## 🎓 Para Principiantes

Este código está diseñado para ser fácil de entender:
- Variables y métodos con nombres en español
- Sin librerías complejas
- Comentarios mínimos pero claros
- Estructuras de datos básicas (arreglos)

## ⚠️ Recomendaciones

- Para pruebas rápidas: usa 10-30 segundos
- Para ver diferencias claras: usa 60+ segundos
- Los algoritmos O(n²) serán más lentos con colecciones grandes
- Los algoritmos O(n log n) completarán más colecciones
