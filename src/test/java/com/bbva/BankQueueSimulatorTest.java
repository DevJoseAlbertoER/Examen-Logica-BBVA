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

    // --- Tests Obligatorios ---

    @Test
    void testMandatoryCase1() {
        int[] transactions = {1, 2, 5};
        int alexIndex = 1;
        assertEquals(4, simulator.timeRequiredToBuy(transactions, alexIndex));
    }

    @Test
    void testMandatoryCase2() {
        int[] transactions = {5, 1, 1, 1};
        int alexIndex = 0;
        assertEquals(8, simulator.timeRequiredToBuy(transactions, alexIndex));
    }

    @Test
    void testMandatoryCase3() {
        int[] transactions = {1};
        int alexIndex = 0;
        assertEquals(1, simulator.timeRequiredToBuy(transactions, alexIndex));
    }

    @Test
    void testMandatoryCase4() {
        int[] transactions = {10, 10, 10};
        int alexIndex = 2;
        assertEquals(30, simulator.timeRequiredToBuy(transactions, alexIndex));
    }

    // --- Testing de Casos Borde y Random ---

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

    // --- Testing de Estrés (Stress Testing) adaptado para Queue ---
    @Test
    void testStressLargeArrayAndOperations() {
        int size = 1000; // Mil clientes para demostrar robustness
        int[] transactions = new int[size];
        for (int i = 0; i < size; i++) {
            transactions[i] = 100; // 100 operaciones por persona
        }
        int alexIndex = size - 1; // Alex es el último
        
        // Simulación completa Queue FIFO
        assertEquals(100000, simulator.timeRequiredToBuy(transactions, alexIndex));
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
