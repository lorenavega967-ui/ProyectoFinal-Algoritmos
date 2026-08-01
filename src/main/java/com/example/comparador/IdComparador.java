package com.example.comparador;

import com.example.model.Producto;

import java.util.Comparator;

/*
 Comparator encargado de ordenar los productos según su identificador único.
 */

public class IdComparador implements Comparator<Producto> {

    /*
     * Compara dos productos utilizando su ID.

    - p1 Primer producto.
    - p2 Segundo producto.
    - retorna elresultado de la comparación.
     */
    
    @Override
    public int compare(Producto p1, Producto p2) {

        return Integer.compare(
                p1.getId(),
                p2.getId()
        );

    }

}