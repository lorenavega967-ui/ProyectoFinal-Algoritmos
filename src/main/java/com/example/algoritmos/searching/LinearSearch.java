package com.example.algoritmos.searching;

import com.example.interfaces.SearchingAlgoritmo;
import com.example.model.Producto;

import java.util.ArrayList;
import java.util.List;

/*
 * Implementación del algoritmo de búsqueda lineal.
 
    La búsqueda lineal recorre los elementos de una lista
    de forma secuencial hasta encontrar el elemento buscado.
 
   - Este algoritmo no requiere que la lista esté ordenada.

  - Complejidad:
        * Mejor caso: O(1)
        * Caso promedio: O(n)
        * Peor caso: O(n)
 */

public class LinearSearch implements SearchingAlgoritmo {

    
     // Devuelve el nombre del algoritmo.
    
    @Override
    public String getNombre() {

        return "Búsqueda Lineal";
    }


    // ================
    // BÚSQUEDA POR ID
    // ================

    /*
     * Busca un producto mediante su ID utilizando
      una búsqueda secuencial.
     
     * productos Lista de productos.
       id Identificador que se desea buscar.
     
     * retorna el Producto encontrado o null si no existe.
     */

    @Override
    public Producto buscar(
            List<Producto> productos,
            int id) {

        
        // Validar que la lista exista y contenga elementos antes de comenzar la búsqueda.
        
        if (productos == null ||
                productos.isEmpty()) {

            return null;
        }


        
        // Recorrer los productos uno por uno.
        
        for (Producto producto : productos) {

            /*
             Comprobar si el ID del producto coincide con el ID buscado.
             */
            if (producto.getId() == id) {
                return producto;
            }
        }

        /*
        Si se termina el recorrido sin encontrar coincidencias, se devuelve null.
         */
        return null;
    }


    // ====================
    // BÚSQUEDA POR NOMBRE
    // ====================

    /**
     * Busca productos cuyo nombre contenga el texto proporcionado.
  
     - La búsqueda no distingue entre mayúsculas y minúsculas.
     - A diferencia de la búsqueda por ID, esta operación puede devolver varios productos.

    * productos Lista de productos.
    * texto Texto que se desea buscar.
    * retorna la Lista de productos encontrados.
     */

    public List<Producto> buscarPorNombre( List<Producto> productos,String texto) {


        //Lista donde almacenaremos los resultados.
        List<Producto> resultados = new ArrayList<>();
        
        // Validar los parámetros recibidos.
        
        if (productos == null ||
                productos.isEmpty() ||
                texto == null ||
                texto.trim().isEmpty()) {

            return resultados;
        }


        /*
          Normalizar el texto de búsqueda para
          realizar una comparación que no distinga
          entre mayúsculas y minúsculas.
         */
        String textoBusqueda = texto.trim().toLowerCase();


        /*
          Recorrer todos los productos.
         */
        for (Producto producto : productos) {

            /*
              Verificar que el producto y su nombre
              sean válidos antes de realizar la búsqueda.
             */
            if (producto == null ||
                    producto.getNombre() == null) {

                continue;
            }


            /*
              Obtener el nombre del producto.
             */
            String nombre = producto.getNombre();


            /*
             * Comprobar si el nombre contiene el texto buscado.
             */
            if (nombre
                    .toLowerCase()
                    .contains(textoBusqueda)) {

                /*
                 * Agregar el producto a los resultados.
                 */
                resultados.add(producto);
            }
        }


        /*
         * Devolver todos los productos encontrados.
         */
        return resultados;
    }
}