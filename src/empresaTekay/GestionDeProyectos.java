package empresaTekay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionDeProyectos {

    public static void obtenerDatosYEjecutar() {
        Scanner sc = new Scanner(System.in);


        System.out.print("Ingrese el número de proyectos: ");
        int n = sc.nextInt();

        int[] beneficios = new int[n];
        int[] recursos = new int[n];

        System.out.println("Ingrese el beneficio y los recursos necesarios para cada proyecto:");
        for (int i = 0; i < n; i++) {
            System.out.print("Proyecto " + (i + 1) + " - Beneficio: ");
            beneficios[i] = sc.nextInt();
            System.out.print("Proyecto " + (i + 1) + " - Recursos necesarios: ");
            recursos[i] = sc.nextInt();
        }

        System.out.print("Ingrese la cantidad total de recursos disponibles: ");
        int R = sc.nextInt();

        // Crear objetos de las soluciones
        SolucionGenerica solucionGen = new SolucionGenerica();
        SolucionOptimizada solucionOpt = new SolucionOptimizada();

        // Lista para los proyectos seleccionados
        List<Integer> proyectosSeleccionadosGenerica = new ArrayList<>();
        List<Integer> proyectosSeleccionadosOpt = new ArrayList<>();

        System.out.println("\nEjecutando solución genérica...");
        int beneficioGenerico = solucionGen.optimizarProyectos(beneficios, recursos, R, proyectosSeleccionadosGenerica);
        System.out.println("Beneficio máximo (solución genérica): " + beneficioGenerico);
        System.out.print("Proyectos seleccionados (solución genérica): Proyecto ");
        imprimirProyectosSeleccionados(proyectosSeleccionadosGenerica);

        System.out.println("\nEjecutando solución optimizada...");
        int beneficioOptimizado = solucionOpt.optimizarProyectosOptimizado(beneficios, recursos, R, proyectosSeleccionadosOpt);
        System.out.println("Beneficio máximo (solución optimizada): " + beneficioOptimizado);
        System.out.print("Proyectos seleccionados (solución optimizada): Proyecto ");
        imprimirProyectosSeleccionados(proyectosSeleccionadosOpt);
    }

    // Método para imprimir los proyectos seleccionados de manera más legible
    private static void imprimirProyectosSeleccionados(List<Integer> proyectosSeleccionados) {
        if (proyectosSeleccionados.isEmpty()) {
            System.out.println("Ningún proyecto seleccionado.");
        } else {
            for (int i = 0; i < proyectosSeleccionados.size(); i++) {
                System.out.print((proyectosSeleccionados.get(i) + 1)); // Sumamos 1 para hacer el número más comprensible
                if (i < proyectosSeleccionados.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        obtenerDatosYEjecutar();
    }
}

