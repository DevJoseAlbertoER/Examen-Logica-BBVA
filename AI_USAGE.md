# Declaración de Uso de IA (Examen TF Light 2026)

## 1. Herramienta(s) de IA utilizada(s)
Para auxiliar y acelerar el desarrollo del presente código, se utilizó un asistente local de inteligencia artificial conversacional **(Gemini)** mediante un agente de programación pareada (Pair Programming).

## 2. Prompts utilizados
Dado que el flujo de trabajo fue un entorno de programación en pareja, los prompts fueron interacciones fluidas de análisis. Algunos ejemplos clave:
* *"Por favor lee el contenido del examen técnico en el PDF y explícame el planteamiento lógico de la fila del banco"*
* *"El problema indica simulaciones donde cada turno toma 1 unidad de tiempo. Podemos hacer una simulación usando un ciclo repetitivo pero quiero proponer una estrategia optimizada O(N) para la métrica de rendimiento. ¿Me ayudas con la implementación de esta optimización en Java?"*
* *"Necesito generar un suite de Testing robusto usando JUnit para cubrir casos borde, de estrés con arrays grandes, y nulos, de manera que verifiquemos que el tiempo no excede los límites."*

## 3. ¿Qué partes generó la IA?
* La estructura base o "esqueleto" ("boilerplate") (Clases de POJO y métodos base de Java).
* Estructura inicial del archivo `pom.xml` para la configuración de las dependencias de JUnit 5.
* La escritura base de los métodos de las pruebas unitarias basadas en el documento del examen.

## 4. ¿Qué partes modifiqué manualmente?
* **Arquitectura de Solución:** Yo decidí implementar una clase estructurada que manejara por separado la visualización de la ejecución (`generateExecutionTrack`) y el sumador de tiempo, para mantener los principios SOLID.
* **Elección de variables lógicas:** Adaptación manual de los casos de estrés para probar el tiempo asintótico de validación de la cola.
* Inclusión de validaciones primarias defensivas (`if array == null` o vacío).

## 5. ¿Qué errores detecté?
* **Riesgo de Memory Leak o TLE (Time Limit Exceeded):** Como lo comento en la estrategia de optimización, el problema se presenta inicialmente como una simulación `round-robin`. Ejecutar una simulación literal reduciendo elemento por elemento en un caso hipotético con $10,000$ personas haciendo $10,000$ operaciones tomaría mucho ciclo de procesamiento. Detecté el problema y en lugar de la simulación manual, lo reemplacé por una suma matemática de distancias `Math.min()`.

## 6. ¿Qué validaciones realicé?
* **Pruebas Funcionales:** Validar mediante la suite de JUnit que los casos mandatorios se cumplan de manera precisa.
* **Pruebas de Estrés:** Generar un vector de 100,000 posiciones probando la optimización garantizando respuesta $< 1ms$.
* **Casos Borde:** Respuestas ante entradas Nullables, arreglos vacíos o un índice de usuario fuera de límites matemáticos (`IllegalArgumentException`).

## 7. Qué decisiones técnicas tomé personalmente
* Opté por una métrica analógica O(N) de pase único por el arreglo en vez de la manipulación de una Cola (Queue) tradicional para ahorrar costos de reasignación de memoria.

---

## BONUS: ¿Por qué el color de BBVA es azul?
El ecosistema y la paleta de colores de Identidad de las instituciones globales bancarias está apoyada fuertemente en la *Psicología del Color*. El Azul marino tradicionalmente comunica **seguridad, solidez institucional y confianza**, que son la base para un negocio financiero. Además, las variaciones eléctricas y cian más modernas que BBVA ha integrado en años recientes enfatizan valores de **innovación tecnológica y digitalización**.
