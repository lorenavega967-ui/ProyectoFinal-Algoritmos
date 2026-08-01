package com.example.interfaces;

import com.example.model.Producto;

import java.util.Comparator;
import java.util.List;


 //Define el comportamiento que deberán implementar todos los algoritmos de ordenamiento del proyecto.
 
 //Con esta interfaz podremos intercambiar Merge Sort, Quick Sort y Heap Sort sin modificar el resto del sistema.
 
public interface SortingAlgoritmo {


    String getNombre();

    //Ordena una lista de productos utilizando el criterio recibido mediante un Comparator.

    void sort(List<Producto> productos,Comparator<Producto> comparator);

}