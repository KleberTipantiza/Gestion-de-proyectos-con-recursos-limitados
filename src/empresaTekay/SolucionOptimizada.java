package empresaTekay;

import java.util.ArrayList;
import java.util.List;

public class SolucionOptimizada {

    public int optimizarProyectosOptimizado(int[] beneficios, int[] recursos, int R, List<Integer> proyectosSeleccionados) {
        // Medición del tiempo de ejecución
        long startTime = System.nanoTime();

        // Medición del uso de memoria antes de la ejecución
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        int n = beneficios.length;
        int[][] dp = new int[n + 1][R + 1];

        // Llenamos la matriz dp usando programación dinámica
        for (int i = 1; i <= n; i++) {
            for (int r = 0; r <= R; r++) {
                if (recursos[i - 1] <= r) {
                    dp[i][r] = Math.max(dp[i - 1][r], dp[i - 1][r - recursos[i - 1]] + beneficios[i - 1]);
                } else {
                    dp[i][r] = dp[i - 1][r];
                }
            }
        }

        // Recorrer la matriz dp para determinar los proyectos seleccionados
        int r = R;
        for (int i = n; i > 0; i--) {
            if (dp[i][r] != dp[i - 1][r]) {
                proyectosSeleccionados.add(i - 1); // Agregar el índice del proyecto (basado en 0)
                r -= recursos[i - 1]; // Reducir los recursos disponibles
            }
        }

        // Medición del uso de memoria después de la ejecución
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = memoryAfter - memoryBefore;

        // Medición del tiempo de ejecución
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        System.out.println("Tiempo de ejecución (solución optimizada): " + duration + " nanosegundos");
        System.out.println("Memoria utilizada (solución optimizada): " + memoryUsed + " bytes");

        return dp[n][R]; // Retornamos el beneficio máximo
    }
}

