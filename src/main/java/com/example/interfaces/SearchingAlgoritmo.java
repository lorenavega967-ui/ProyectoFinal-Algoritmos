package com.example.interfaces;

import com.example.model.Producto;

import java.util.List;

/**
 - Define el comportamiento que deberán implementar
   todos los algoritmos de búsqueda del sistema.
 
 - Esta interfaz permite que cualquier algoritmo
   de búsqueda pueda ser utilizado de forma intercambiable por la aplicación.
 */
public interface SearchingAlgoritmo {

    
    // Devuelve el nombre del algoritmo.

    String getNombre();

    /*
    Busca un producto por su identificador.
     
     - productos Lista de productos.
     - id Identificador del producto.
     - Producto encontrado o null.
     */
    Producto buscar(List<Producto> productos, int id);

}