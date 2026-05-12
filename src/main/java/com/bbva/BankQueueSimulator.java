package com.bbva;

import java.util.ArrayList;
import java.util.List;

public class BankQueueSimulator {

    /**
     * Calcula el tiempo total que le toma a Alex completar sus operaciones utilizando
     * una estrategia matemática optimizada O(N) en lugar de una simulación paso a paso (O(N*M)),
     * logrando un mejor performance y menor consumo de memoria.
     *
     * @param transactions Arreglo con la cantidad de operaciones por cliente.
     * @param alexIndex El índice (posición) inicial de Alex en la fila.
     * @return El tiempo total transcurrido.
     */
    public int timeRequiredToBuy(int[] transactions, int alexIndex) {
        // Validaciones de seguridad (Casos base y bordes)
        if (transactions == null || transactions.length == 0) {
            return 0;
        }
        if (alexIndex < 0 || alexIndex >= transactions.length) {
            throw new IllegalArgumentException("El índice de Alex es inválido respecto al tamaño de la fila.");
        }

        int totalTime = 0;
        int alexOperations = transactions[alexIndex];

        // Lógica de optimización:
        // Todas las personas antes de Alex (a la izquierda) serán atendidas como máximo la misma
        // cantidad de veces que Alex. Todas las personas después de Alex (a la derecha) serán
        // atendidas como máximo las veces de Alex MENOS 1 (porque Alex terminará antes que ellos en esa iteración).
        for (int i = 0; i < transactions.length; i++) {
            if (i <= alexIndex) {
                totalTime += Math.min(transactions[i], alexOperations);
            } else {
                totalTime += Math.min(transactions[i], alexOperations - 1);
            }
        }

        return totalTime;
    }

    /**
     * Extrae el track de ejecución secuencial si se requiere visualizar el flujo.
     * Aunque no se utiliza para el conteo final por temas de performance, 
     * sirve para cumplir el punto "Track de ejecución" del requerimiento.
     */
    public List<String> generateExecutionTrack(int[] transactions, int alexIndex) {
        List<String> track = new ArrayList<>();
        if (transactions == null || transactions.length == 0) return track;

        // Copia para no mutar la original
        int[] queue = new int[transactions.length];
        System.arraycopy(transactions, 0, queue, 0, transactions.length);

        int time = 0;
        while (queue[alexIndex] > 0) {
            for (int i = 0; i < queue.length; i++) {
                if (queue[i] > 0) {
                    queue[i]--;
                    time++;
                    String person = (i == alexIndex) ? "Alex" : "Cliente " + i;
                    track.add("t=" + time + " | Atendiendo a: " + person + " | Le faltan: " + queue[i]);
                    // Si es Alex y acaba de terminar, rompemos aquí mismo.
                    if (i == alexIndex && queue[i] == 0) {
                        break;
                    }
                }
            }
        }
        return track;
    }
}
