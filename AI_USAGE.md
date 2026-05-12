# Declaración de Uso de IA (Examen Lógica de Programación BBVA)

## 1. Herramienta(s) de IA utilizada(s)
Se utilizó **Gemini** (chat conversacional) funcionando como un asistente de estilo *Pair Programming* enfocado exclusivamente en la revisión del código y la automatización de la suite de pruebas unitarias.

## 2. Prompts utilizados
Los prompts ingresados fueron directos para autocompletar tareas secundarias mientras desarrollaba la lógica central en VSCode:
* *"Tengo esta lógica principal usando una estructura Queue y poll() / add() para simular la fila del banco. Revísala y dime si ves algún posible Memory Leak o ciclo infinito."*
* *"Ayúdame a generar el archivo `pom.xml` para incluir las dependencias de JUnit 5. No quiero configurarlo desde cero."*
* *"Escríbeme los métodos de prueba unitaria básicos en JUnit cubriendo los casos mandatorios del PDF, basándote en la clase `BankQueueSimulator` que acabo de programar."*
* *"Añade un caso de prueba de estrés (Stress Testing) dentro del archivo de test para validar el performance de la cola."*

## 3. ¿Qué partes generó la IA?
* El archivo de dependencias de Maven (`pom.xml`).
* El boilerplate y la estructura de la clase de pruebas `BankQueueSimulatorTest.java`.
* La generación de los métodos de simulación de estrés y bordes (Empty Array, Null) usando `assertThrows`.

## 4. ¿Qué partes modifiqué/escribí manualmente?
* **Lógica Principal (`BankQueueSimulator.java`):** El diseño y escritura del algoritmo basado en `Queue` (FIFO) fue desarrollado personalmente en VSCode. Consideré que una Cola (implementada con un `LinkedList`) era la estructura de datos que imitaba a la perfección el comportamiento del contexto bancario solicitado en el examen.
* **Estructura Interna:** Decidí usar un arreglo estático `int[]` de dos posiciones dentro de la Queue para amarrar el "Id del cliente" a sus "Operaciones restantes", en lugar de crear un Objeto de Dominio por separado, priorizando la rapidez. 
* Personalización manual de las pruebas unitarias para cuadrar con los nombres exactos de los métodos desarrollados.

## 5. ¿Qué errores detecté?
* **Excepciones de Índice:** Al inicio, detecté que si se insertaba un `alexIndex` fuera de la longitud del arreglo provocaba peticiones inválidas. Decidí controlar este caso generando un arrojo manual de una excepción defensiva (`IllegalArgumentException`) antes de procesar el arreglo.

## 6. ¿Qué validaciones realicé?
* **Casos Core:** Validé uno a uno todos los escenarios descritos en el examen logrando una equivalencia asertiva 100%.
* **Integridad de Datos:** Garantizar que si se inyecta un array nulo, la ejecución no explote con un NullPointerException, devolviendo de manera controlada un tiempo de `0`.

## 7. Qué decisiones técnicas tomé personalmente
* Tomé la decisión de apoyarme en la IA para el montaje del entorno de Testeo (JUnit configuration), permitiéndome destinar el 100% de mi tiempo de análisis a la codificación de la algoritmia central. Elegí la estructura genérica `Queue` como modelo principal gracias a mi entendimiento tradicional de las Listas de Tareas en Sistemas Operativos.

---

## BONUS: ¿Por qué el color de BBVA es azul?
En la psicología del color dentro del sector financiero, el tono azul tradicionalmente transmite valores de **seguridad, confianza y solidez institucional**. Puntualmente, la tonalidad azul marino / brillante adoptada por el Grupo BBVA reafirma adicionalmente su enfoque hacia la **innovación tecnológica y digital**, elementos clave para su actual identidad en el mercado.
