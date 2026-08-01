package com.example.servicio;

import com.example.algoritmos.searching.BinarySearch;
import com.example.algoritmos.searching.LinearSearch;
import com.example.comparador.IdComparador;
import com.example.data.GeneradorProductos;
import com.example.interfaces.SearchingAlgoritmo;
import com.example.interfaces.SortingAlgoritmo;
import com.example.model.Producto;
import com.example.util.TimerUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
  - Servicio principal encargado de gestionar el catálogo
    de productos y coordinar las diferentes operaciones del sistema.

 - Esta clase representa la capa de servicio de la aplicación.
 - Su objetivo es separar la lógica de negocio de la interfaz gráfica.

 - Responsabilidades principales:
      - Generar productos.
      - Obtener el catálogo.
      - Ordenar productos.
      - Buscar productos por ID.
      - Buscar productos por nombre.
      - Medir tiempos de ejecución.
 */
public class ProductoService {

    // ==========
    // ATRIBUTOS
    // ==========

    
    // Lista que contiene el catálogo actual.
    private List<Producto> productos;

    // Generador encargado de crear productos.
    private final GeneradorProductos generador;

    // Algoritmo de búsqueda binaria.
    private final BinarySearch binarySearch;

    
    // Algoritmo de búsqueda lineal.
    private final LinearSearch linearSearch;

    /*
    Temporizador utilizado para medir el rendimiento de las operaciones.
     */
    private final TimerUtil timer;


    // ============
    // CONSTRUCTOR
    // ============


    // Inicializa el servicio y sus componentes.
     
    public ProductoService() {

        productos = new ArrayList<>();

        generador = new GeneradorProductos();

        binarySearch = new BinarySearch();

        linearSearch = new LinearSearch();

        timer = new TimerUtil();
    }


    // ========================
    // GENERACIÓN DE PRODUCTOS
    // ========================

    /*
     Genera una nueva colección de productos.
     - cantidad Cantidad de productos a generar.
     */
    public void generarProductos(int cantidad) {

        if (cantidad <= 0) {

            productos = new ArrayList<>();

            return;
        }

        productos =
                generador.generarProductos(cantidad);
    }


    // ==================
    // OBTENER PRODUCTOS
    // ==================

    /*
    Obtiene el catálogo actual.
    -retorna la Lista actual de productos.
     */
    public List<Producto> getProductos() {

        return productos;
    }


    /*
    Obtiene la cantidad actual de productos.
    - retorna el Número de productos existentes.
     */
    public int getCantidadProductos() {

        return productos.size();
    }


    // =============
    // ORDENAMIENTO
    // =============

    /*
    Ordena el catálogo utilizando el algoritmo y criterio especificados.
     
     - algoritmo Algoritmo de ordenamiento.
     - comparator Criterio de comparación.
     - retorna el Tiempo de ejecución en nanosegundos.
     */
    public long ordenar(
            SortingAlgoritmo algoritmo,
            Comparator<Producto> comparator) {

        
        //Validar parámetros.
         
        if (algoritmo == null ||
                comparator == null) {

            return 0;
        }

        if (productos.size() <= 1) {

            return 0;
        }


        // Reiniciar temporizador.
         
        timer.reiniciar();


        // Iniciar medición.
    
        timer.iniciar();


        // Ejecutar algoritmo seleccionado.
    
        algoritmo.sort(
                productos,
                comparator
        );


        
        // Detener medición.
    
        timer.detener();


    
        // Devolver tiempo.
        
        return timer.getTiempoNano();
    }


    // ========================
    // BÚSQUEDA BINARIA POR ID
    // ========================

    /*
    - Busca un producto mediante búsqueda binaria.
    - La búsqueda binaria requiere que la colección esté ordenada por ID.

     - id ID del producto.
     - retorna el Producto encontrado o null.
     */

    public Producto buscarPorId(int id) {

        // Verificar catálogo vacío.

        if (productos == null ||
                productos.isEmpty()) {

            return null;
        }


        /*
          Ordenar por ID antes de realizar
          la búsqueda binaria.
         */
        productos.sort(new IdComparador());

        /*
         Ejecutar búsqueda binaria.
         */
        return binarySearch.buscar(
                productos,
                id
        );
    }


    // =======================
    // BÚSQUEDA LINEAL POR ID
    // =======================

    /*
     * Busca un producto mediante búsqueda lineal.
     - id ID del producto.
     - retorna el Producto encontrado o null.
     */

    public Producto buscarPorIdLineal(int id) {

        if (productos == null ||
                productos.isEmpty()) {

            return null;
        }


        /*
          La búsqueda lineal no necesita
          que los productos estén ordenados.
         */
        return linearSearch.buscar(
                productos,
                id
        );
    }


    // ===================
    // BÚSQUEDA POR NOMBRE
    // ===================

    /*
    - Busca productos cuyo nombre contenga el texto proporcionado.
    - La búsqueda no distingue entre mayúsculas y minúsculas.
     - texto Texto que se desea buscar.
     - retorna la  Lista de productos encontrados.
     */

    public List<Producto> buscarPorNombre(
            String texto) {

        
        // Validar texto.
        if (texto == null ||
                texto.trim().isEmpty()) {

            return new ArrayList<>();
        }


        /*
         * Ejecutar búsqueda por nombre.
         */
        return linearSearch.buscarPorNombre(
                productos,
                texto.trim()
        );
    }


    // ==============================
    // TIEMPO DE LA ÚLTIMA OPERACIÓN
    // ==============================

    /*
     * Obtiene el tiempo de la última operación medida por el temporizador.
     - retorna el Tiempo en nanosegundos.
     */

    public long getTiempoNano() {

        return timer.getTiempoNano();
    }


    /*
     Obtiene el tiempo de la última operaciónen milisegundos.
     y retorna el Tiempo en milisegundos.
     */
    public double getTiempoMilisegundos() {

        return timer.getTiempoMilisegundos();
    }
}