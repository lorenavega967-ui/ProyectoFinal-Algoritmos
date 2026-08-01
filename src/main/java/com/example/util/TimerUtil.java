package com.example.util;

/*
 - Clase utilitaria encargada de medir el tiempo de ejecución de diferentes procesos del sistema.
 - La medición se realiza utilizando { System#nanoTime()},
   lo que permite obtener resultados en nanosegundos y es
   apropiado para medir el rendimiento de algoritmos.

 - clase centraliza la medición de tiempos para evitar
   duplicar código en los diferentes componentes del sistema.
 */
public class TimerUtil {

    /*Instante en nanosegundos en el que comienza la medición. */
    private long inicio;

    /*Instante en nanosegundos en el que finaliza la medición. */
    private long fin;

    /* Inicia la medición del tiempo.*/
    public void iniciar() {

        inicio = System.nanoTime();
    }

    /* Detiene la medición del tiempo. */
    public void detener() {

        fin = System.nanoTime();
    }

    /* Obtiene el tiempo transcurrido en nanosegundos.
       retorna en Tiempo transcurrido en nanosegundos.
     */
    public long getTiempoNano() {

        return fin - inicio;
    }

    /* 
    Obtiene el tiempo transcurrido en milisegundos.
    retorna en Tiempo transcurrido en milisegundos.
     */
    public double getTiempoMilisegundos() {

        return (fin - inicio) / 1_000_000.0;
    }

    /* Reinicia el temporizador.*/
    public void reiniciar() {

        inicio = 0;
        fin = 0;
    }
}