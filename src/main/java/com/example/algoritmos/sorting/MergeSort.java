package com.example.algoritmos.sorting;

import com.example.interfaces.SortingAlgoritmo;
import com.example.model.Producto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Implementación del algoritmo Merge Sort.
 
 - Merge Sort utiliza la estrategia "Divide y Vencerás",
   dividiendo la lista en partes más pequeñas hasta que
   cada una contenga un solo elemento. Posteriormente,
   las listas se fusionan de forma ordenada.

 - Complejidad:
    - Mejor caso: O(n log n)
    - Caso promedio: O(n log n)
    - Peor caso: O(n log n)
 */

public class MergeSort implements SortingAlgoritmo {

    
    // Devuelve el nombre del algoritmo.

    @Override
    public String getNombre() {
        return "Merge Sort";
    }

    /**
     * Ordena la lista utilizando Merge Sort.
     - productos Lista de productos.
     - comparator Criterio de comparación.
     */

    @Override
    public void sort(List<Producto> productos,Comparator<Producto> comparator) {

        if (productos == null || productos.size() <= 1) {
            return;
        }

        mergeSort(productos, 0, productos.size() - 1, comparator);

    }

    
    // Divide recursivamente la lista.
    
    private void mergeSort(List<Producto> lista,
                           int izquierda,
                           int derecha,
                           Comparator<Producto> comparator) {

        if (izquierda >= derecha) {
            return;
        }

        int centro = (izquierda + derecha) / 2;

        mergeSort(lista, izquierda, centro, comparator);

        mergeSort(lista, centro + 1, derecha, comparator);

        merge(lista, izquierda, centro, derecha, comparator);

    }

    
    // Fusiona dos sublistas ordenadas.
     
    private void merge(List<Producto> lista,
                       int izquierda,
                       int centro,
                       int derecha,
                       Comparator<Producto> comparator) {

        List<Producto> temporal = new ArrayList<>();

        int i = izquierda;
        int j = centro + 1;

        while (i <= centro && j <= derecha) {

            if (comparator.compare(lista.get(i), lista.get(j)) <= 0) {

                temporal.add(lista.get(i));
                i++;

            } else {

                temporal.add(lista.get(j));
                j++;

            }

        }

        while (i <= centro) {

            temporal.add(lista.get(i));
            i++;

        }

        while (j <= derecha) {

            temporal.add(lista.get(j));
            j++;

        }

        for (int k = 0; k < temporal.size(); k++) {

            lista.set(izquierda + k, temporal.get(k));

        }

    }

}