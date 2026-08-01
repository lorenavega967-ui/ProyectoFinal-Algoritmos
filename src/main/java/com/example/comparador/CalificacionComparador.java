package com.example.comparador;

import com.example.model.Producto;

import java.util.Comparator;

/*
   Comparator encargado de ordenar productos
   por calificación promedio de mayor a menor.
 */

public class CalificacionComparador implements Comparator<Producto> {

    /**
     * Compara dos productos según su calificación.
     
      - p1 Primer producto.
      - p2 Segundo producto.
      - retorna el  resultado de la comparación.
     */
    @Override
    public int compare(Producto p1, Producto p2) {

        return Double.compare(
                p2.getCalificacionPromedio(),
                p1.getCalificacionPromedio()
        );

    }

}