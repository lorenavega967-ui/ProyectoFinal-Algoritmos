package com.example.algoritmos.searching;

import com.example.interfaces.SearchingAlgoritmo;
import com.example.model.Producto;

import java.util.List;

/* Implementación del algoritmo de búsqueda binaria.  
 
 - La búsqueda binaria permite localizar un productom dentro de una lista previamente ordenada por ID.
 - Complejidad:
        * Mejor caso: O(1)
        * Caso promedio: O(log n)
        * Peor caso: O(log n)

  - Requisito: 
  La lista debe estar ordenada de forma ascendente utilizando el ID del producto.
 */

public class BinarySearch implements SearchingAlgoritmo {

    
    // Devuelve el nombre del algoritmo.
    @Override
    public String getNombre() {

        return "Búsqueda Binaria";
    }

    
     /* Busca un producto mediante su ID utilizando
     el algoritmo de búsqueda binaria.
     
     - productos Lista de productos ordenada por ID.
     - id Identificador del producto que se desea buscar.
     retorna el Producto encontrado o null si no existe.
      */ 

    @Override
    public Producto buscar(
            List<Producto> productos,
            int id) {

    
        //Valida que la lista exista y contenga al menos un elemento.
        
        if (productos == null || productos.isEmpty()) {

            return null;
        }

    
        // Límite inferior del rango de búsqueda.
    
        int izquierda = 0;

        
        //Límite superior del rango de búsqueda.
        
        int derecha = productos.size() - 1;


        
        //Continua mientras exista un rango válido de búsqueda.
        
        while (izquierda <= derecha) {

            /* Calcular la posición central.
              Esta fórmula evita posibles problema de desbordamiento de enteros.
             */
            int medio = izquierda + (derecha - izquierda) / 2;


            // Obtener el producto ubicado en la posición central.
        
            Producto productoMedio = productos.get(medio);


        
            // Obtener el ID del producto central.
            int idMedio = productoMedio.getId();


            
            //Comprobar si se encuentra el producto.
    
            if (id == idMedio) {

                return productoMedio;
            }


            /*
              Si el ID buscado es menor que el ID central,
              debemos continuar en la mitad izquierda.
             */
            if (id < idMedio) {

                derecha = medio - 1;

            } else {

                /*
                 Si el ID buscado es mayor que el ID central,
                 debemos continuar en la mitad derecha.
                 */
                izquierda = medio + 1;
            }
        }


    
        //Si llegamos aquí, el producto no fue encontrado.
        
        return null;
    }
}