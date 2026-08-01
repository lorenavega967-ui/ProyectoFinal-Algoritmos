package com.example.algoritmos.sorting;

import com.example.interfaces.SortingAlgoritmo;
import com.example.model.Producto;

import java.util.Comparator;
import java.util.List;

/*
 Implementación del algoritmo Quick Sort.

 - Quick Sort utiliza la técnica "Divide y Vencerás".
   Selecciona un pivote y reorganiza la lista de forma
   que los elementos menores queden a la izquierda y
   los mayores a la derecha.

 - Posteriormente aplica el mismo proceso de forma
   recursiva sobre ambas particiones.

 - Complejidad:
     - Mejor caso: O(n log n)
     - Caso promedio: O(n log n)
     - Peor caso: O(n²)
 */

public class QuickSort implements SortingAlgoritmo {

    
    // Devuelve el nombre del algoritmo.

    @Override
    public String getNombre() {
        return "Quick Sort";
    }

    /*
     * Ordena la lista utilizando Quick Sort.
      - productos Lista de productos.
      - comparator Criterio de comparación.
     */

    @Override
    public void sort(List<Producto> productos,Comparator<Producto> comparator) {

        if (productos == null || productos.size() <= 1) {
            return;
        }

        quickSort(productos, 0, productos.size() - 1, comparator);

    }

    
    // Método recursivo principal.
    
    private void quickSort(List<Producto> lista,
                           int inicio,
                           int fin,
                           Comparator<Producto> comparator) {

        if (inicio < fin) {

            int indicePivote = partition(lista, inicio, fin, comparator);

            quickSort(lista, inicio, indicePivote - 1, comparator);

            quickSort(lista, indicePivote + 1, fin, comparator);

        }

    }

    
    // Reorganiza la lista utilizando un pivote.  retorna la Posición final del pivote.
    
    private int partition(List<Producto> lista,
                          int inicio,
                          int fin,
                          Comparator<Producto> comparator) {

        Producto pivote = lista.get(fin);

        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {

            if (comparator.compare(lista.get(j), pivote) <= 0) {

                i++;

                intercambiar(lista, i, j);

            }

        }

        intercambiar(lista, i + 1, fin);

        return i + 1;

    }

    /*
    Intercambia dos posiciones dentro de la lista.
     
      - lista Lista de productos.
      - i Primera posición.
      - j Segunda posición.
     */

    private void intercambiar(List<Producto> lista,int i,int j) {

        Producto temporal = lista.get(i);

        lista.set(i, lista.get(j));

        lista.set(j, temporal);

    }

}