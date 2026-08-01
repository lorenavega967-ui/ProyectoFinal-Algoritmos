package com.example.comparador;

import com.example.model.Producto;

import java.util.Comparator;

/*
- Comparator encargado de ordenar productos por precio de menor a mayor.

 - Este comparador será utilizado por los algoritmos
   de ordenamiento para organizar el catálogo
   según el precio del producto.
 */

public class PrecioComparador implements Comparator<Producto> {

    /**
     * Compara dos productos según su precio.
     
     - p1 Primer producto.
     - p2 Segundo producto.
     retorna :
      - menor que 0 si p1 < p2
      - igual a 0 si son iguales
      - mayor que 0 si p1 > p2
     */
    @Override
    public int compare(Producto p1, Producto p2) {

        return Double.compare(
                p1.getPrecio(),
                p2.getPrecio()
        );

    }

}