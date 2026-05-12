package com.bbva;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BankQueueSimulator {

    /*
     * Calcula el tiempo total que le toma a Alex completar sus operaciones.
     * Esta solución está basada en una estructura de Datos Queue (Cola - FIFO)
     */
    public int timeRequiredToBuy(int[] transactions, int alexIndex) {
        if (transactions == null || transactions.length == 0) {
            return 0;
        }
        if (alexIndex < 0 || alexIndex >= transactions.length) {
            throw new IllegalArgumentException("El índice de Alex es inválido.");
        }

        // indice 0: La posición original del cliente 
        // indice 1: Las operaciones que le faltan (sus tickets)
        Queue<int[]> queue = new LinkedList<>();
        
        for (int i = 0; i < transactions.length; i++) {
            queue.add(new int[]{i, transactions[i]});
        }

        int time = 0;
        
        while (!queue.isEmpty()) {
            // poll() saca al cliente que está hasta el frente de la cola
            int[] currentClient = queue.poll();
            int personIndex = currentClient[0];
            int pendingOps = currentClient[1];

            // Realiza 1 operación (consume 1 unidad de tiempo)
            time++;
            pendingOps--;

            // Verificamos de inmediato si era Alex y ya terminó todo
            if (pendingOps == 0 && personIndex == alexIndex) {
                return time;
            }

            // Si aún tiene operaciones pendientes, regresa al final de la fila
            if (pendingOps > 0) {
                queue.add(new int[]{personIndex, pendingOps});
            }
        }

        return time;
    }

    public List<String> generateExecutionTrack(int[] transactions, int alexIndex) {
        List<String> track = new ArrayList<>();
        if (transactions == null || transactions.length == 0) return track;

        int[] copyQueue = new int[transactions.length];
        System.arraycopy(transactions, 0, copyQueue, 0, transactions.length);

        int time = 0;
        while (copyQueue[alexIndex] > 0) {
            for (int i = 0; i < copyQueue.length; i++) {
                if (copyQueue[i] > 0) {
                    copyQueue[i]--;
                    time++;
                    String person = (i == alexIndex) ? "Alex" : "Cliente " + i;
                    track.add("t=" + time + " | Atendiendo a: " + person + " | Faltan: " + copyQueue[i]);
                    if (i == alexIndex && copyQueue[i] == 0) {
                        break;
                    }
                }
            }
        }
        return track;
    }

    /**
     * Método principal para ejecutar y visualizar el comportamiento en consola.
     */
    public static void main(String[] args) {
        BankQueueSimulator simulator = new BankQueueSimulator();
        
        // Ejemplo de prueba manual
        int[] fila = {5, 1, 1, 1};
        int posicionDeAlex = 0;

        System.out.println("Iniciando simulación del banco...");
        System.out.println("Fila original: [5, 1, 1, 1]. Alex está en la posición 0.");
        
        int tiempoTotal = simulator.timeRequiredToBuy(fila, posicionDeAlex);
        
        System.out.println("\n-------------------------------------------");
        System.out.println("El tiempo total que tardó Alex fue de: " + tiempoTotal + " unidades.");
        System.out.println("-------------------------------------------\n");

        System.out.println("Historial de movimientos:");
        List<String> historial = simulator.generateExecutionTrack(fila, posicionDeAlex);
        for (String movimiento : historial) {
            System.out.println(movimiento);
        }
    }
}
