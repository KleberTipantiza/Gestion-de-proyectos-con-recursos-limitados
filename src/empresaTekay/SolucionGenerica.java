package empresaTekay;

import java.util.ArrayList;
import java.util.List;

public class SolucionGenerica {

    public int optimizarProyectos(int[] beneficios, int[] recursos, int R, List<Integer> proyectosSeleccionados) {
        // Medición del tiempo de ejecución
        long startTime = System.nanoTime();

        // Medición del uso de memoria antes de la ejecución
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        int n = beneficios.length;
        int[][] dp = new int[n + 1][R + 1];

        // Llenar la tabla dp
        for (int i = 1; i <= n; i++) {
            for (int r = 0; r <= R; r++) {
                dp[i][r] = dp[i - 1][r];

                if (recursos[i - 1] <= r) {
                    dp[i][r] = Math.max(dp[i][r], dp[i - 1][r - recursos[i - 1]] + beneficios[i - 1]);
                }
            }
        }

        // Reconstruir los proyectos seleccionados
        int r = R;
        for (int i = n; i > 0; i--) {
            if (dp[i][r] != dp[i - 1][r]) {
                proyectosSeleccionados.add(i - 1); // Agregar el proyecto (índice base 0)
                r -= recursos[i - 1];
            }
        }

        // Medición del uso de memoria después de la ejecución
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = memoryAfter - memoryBefore;

        // Medición del tiempo de ejecución
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        System.out.println("Tiempo de ejecución (solución genérica): " + duration + " nanosegundos");
        System.out.println("Memoria utilizada (solución genérica): " + memoryUsed + " bytes");

        return dp[n][R];
    }
}
