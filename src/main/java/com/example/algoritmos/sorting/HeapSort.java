package com.example.algoritmos.sorting;

import com.example.interfaces.SortingAlgoritmo;
import com.example.model.Producto;

import java.util.Comparator;
import java.util.List;

/*
 * Implementación del algoritmo Heap Sort.

 * Heap Sort organiza los elementos en una estructura
 * tipo Heap (árbol binario completo) para obtener
 * repetidamente el elemento de mayor prioridad y
 * colocarlo en su posición definitiva.

 - Complejidad:
    - Mejor caso: O(n log n)
    - Caso promedio: O(n log n)
    - Peor caso: O(n log n)
 */

public class HeapSort implements SortingAlgoritmo {

    
    // Devuelve el nombre del algoritmo.

    @Override
    public String getNombre() {
        return "Heap Sort";
    }

    /*
    Ordena una lista utilizando Heap Sort.
     - productos Lista de productos.
     - comparator Criterio de comparación.
     */

    @Override
    public void sort(List<Producto> productos,Comparator<Producto> comparator) {

        int n = productos.size();

        // Construye el Heap inicial.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(productos, n, i, comparator);
        }

        // Extrae el elemento raíz y reconstruye el Heap.
        for (int i = n - 1; i > 0; i--) {

            intercambiar(productos, 0, i);

            heapify(productos, i, 0, comparator);
        }

    }

    /**
     * Garantiza la propiedad del Heap para un nodo.
     
      - lista Lista de productos.
      - n Tamaño del Heap.
      - raiz Índice del nodo raíz.
      - comparator Criterio de comparación.
     */

    private void heapify(List<Producto> lista,int n,int raiz,Comparator<Producto> comparator) {

        int mayor = raiz;
        int izquierda = 2 * raiz + 1;
        int derecha = 2 * raiz + 2;

        if (izquierda < n &&
                comparator.compare(lista.get(izquierda), lista.get(mayor)) > 0) {

            mayor = izquierda;
        }

        if (derecha < n &&
                comparator.compare(lista.get(derecha), lista.get(mayor)) > 0) {

            mayor = derecha;
        }

        if (mayor != raiz) {

            intercambiar(lista, raiz, mayor);

            heapify(lista, n, mayor, comparator);
        }
    }

    /**
     * Intercambia dos elementos de la lista.
     
    - lista Lista de productos.
    - i Primer índice.
    - j Segundo índice.
     */
    
    private void intercambiar(List<Producto> lista, int i,int j) {

        Producto temporal = lista.get(i);

        lista.set(i, lista.get(j));

        lista.set(j, temporal);
    }

}