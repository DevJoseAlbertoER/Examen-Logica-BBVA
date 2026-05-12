package com.bbva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BankQueueSimulatorTest {

    private BankQueueSimulator simulator;

    @BeforeEach
    void setUp() {
        simulator = new BankQueueSimulator();
    }

    // --- Tests Obligatorios del Examen ---

    @Test
    void testMandatoryCase1() {
        int[] transactions = {1, 2, 5};
        int alexIndex = 1;
        // Explicación: [1, 2, 5] Alex está en index 1 (tiene 2 ops).
        // Turno 1: idx 0 resta 1 -> [0, 2, 5] (t=1)
        // Turno 2: Alex resta 1 -> [0, 1, 5] (t=2)
        // Turno 3: idx 2 resta 1 -> [0, 1, 4] (t=3)
        // Turno 4: Alex resta 1 -> [0, 0, 4] (t=4) -> Alex termina.
        assertEquals(4, simulator.timeRequiredToBuy(transactions, alexIndex));
    }

    @Test
    void testMandatoryCase2() {
        int[] transactions = {5, 1, 1, 1};
        int alexIndex = 0;
        // Explicación: Alex está en index 0 (tiene 5 ops).
        // Ronda 1 todos hacen una operacion (t=4). Fila queda [4, 0, 0, 0]
        // Las ultimas 4 operaciones las hace Alex solo (t=4). 4+4 = 8.
        assertEquals(8, simulator.timeRequiredToBuy(transactions, alexIndex));
    }

    @Test
    void testMandatoryCase3() {
        int[] transactions = {1};
        int alexIndex = 0;
        // Solo está Alex, tarda 1 turno.
        assertEquals(1, simulator.timeRequiredToBuy(transactions, alexIndex));
    }

    @Test
    void testMandatoryCase4() {
        int[] transactions = {10, 10, 10};
        int alexIndex = 2;
        // Alex está en index 2 (el último). Para terminar sus 10, los anteriores 
        // también harán 10. Son 3 personas x 10 operaciones = 30.
        assertEquals(30, simulator.timeRequiredToBuy(transactions, alexIndex));
    }

    // --- Testing de Casos Borde ---

    @Test
    void testEmptyArray() {
        int[] transactions = {};
        assertEquals(0, simulator.timeRequiredToBuy(transactions, 0));
    }

    @Test
    void testNullArray() {
        assertEquals(0, simulator.timeRequiredToBuy(null, 0));
    }

    @Test
    void testInvalidAlexIndex() {
        int[] transactions = {1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> {
            simulator.timeRequiredToBuy(transactions, 5);
        });
    }

    // --- Testing de Estrés (Stress Testing) ---
    @Test
    void testStressLargeArrayAndOperations() {
        int size = 100000; // Cien mil clientes
        int[] transactions = new int[size];
        for (int i = 0; i < size; i++) {
            transactions[i] = 100; // 100 operaciones por persona
        }
        int alexIndex = size - 1; // Alex es el último millón

        // El tiempo debe ser 100000 * 100 = 10,000,000
        // Como implementamos optimización O(N), esta prueba pasará instantáneamente
        // en lugar de tardar minutos simulando bucles.
        assertEquals(10000000, simulator.timeRequiredToBuy(transactions, alexIndex));
    }

    // --- Validación de Track de Ejecución ---
    @Test
    void testGenerateExecutionTrack() {
        int[] transactions = {1, 2};
        int alexIndex = 1;
        List<String> track = simulator.generateExecutionTrack(transactions, alexIndex);
        
        assertEquals(3, track.size());
        assertTrue(track.get(0).contains("t=1"));
        assertTrue(track.get(1).contains("t=2"));
        assertTrue(track.get(2).contains("t=3"));
    }
}
