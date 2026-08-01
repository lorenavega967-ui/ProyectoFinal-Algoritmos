package com.example;

import com.example.algoritmos.sorting.HeapSort;
import com.example.algoritmos.sorting.MergeSort;
import com.example.algoritmos.sorting.QuickSort;
import com.example.comparador.CalificacionComparador;
import com.example.comparador.PrecioComparador;
import com.example.model.Producto;
import com.example.servicio.ProductoService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class PruebaRendimiento {

    public static void main(String[] args) {

        ProductoService service = new ProductoService();

        // Generar exactamente 50 productos
        service.generarProductos(50);

        System.out.println("==============================================");
        System.out.println("PRUEBAS DE ORDENAMIENTO");
        System.out.println("==============================================");

        probarOrdenamiento(
                "Merge Sort",
                new MergeSort(),
                new PrecioComparador(),
                service
        );

        probarOrdenamiento(
                "Quick Sort",
                new QuickSort(),
                new PrecioComparador(),
                service
        );

        probarOrdenamiento(
                "Heap Sort",
                new HeapSort(),
                new PrecioComparador(),
                service
        );

        System.out.println();

        probarOrdenamiento(
                "Merge Sort",
                new MergeSort(),
                new CalificacionComparador(),
                service
        );

        probarOrdenamiento(
                "Quick Sort",
                new QuickSort(),
                new CalificacionComparador(),
                service
        );

        probarOrdenamiento(
                "Heap Sort",
                new HeapSort(),
                new CalificacionComparador(),
                service
        );

        System.out.println();
        System.out.println("==============================================");
        System.out.println("PRUEBAS DE BUSQUEDA");
        System.out.println("==============================================");

        pruebaBusqueda(service);
    }

    private static void probarOrdenamiento(
            String nombre,
            com.example.interfaces.SortingAlgoritmo algoritmo,
            Comparator<Producto> comparador,
            ProductoService service) {

        List<Producto> copia = new ArrayList<>(service.getProductos());

        service.getProductos().clear();
        service.getProductos().addAll(copia);

        long tiempo = service.ordenar(algoritmo, comparador);

        System.out.printf(
                "%-12s -> %-18s : %10d ns (%.6f ms)%n",
                nombre,
                comparador.getClass().getSimpleName(),
                tiempo,
                tiempo / 1_000_000.0
        );
    }

    private static void pruebaBusqueda(ProductoService service) {

        Random random = new Random();

        long inicioExisten = System.nanoTime();

        for (int i = 0; i < 10; i++) {

            int id = random.nextInt(50) + 1;

            service.buscarPorId(id);
        }

        long finExisten = System.nanoTime();

        long inicioNo = System.nanoTime();

        for (int i = 0; i < 10; i++) {

            int id = random.nextInt(500) + 100;

            service.buscarPorId(id);
        }

        long finNo = System.nanoTime();

        System.out.println();
        System.out.println("Búsqueda Binaria");
        System.out.println("----------------------------");

        System.out.println("10 IDs existentes : "
                + (finExisten - inicioExisten)
                + " ns");

        System.out.println("10 IDs inexistentes : "
                + (finNo - inicioNo)
                + " ns");

        long inicioNombre = System.nanoTime();

        for (int i = 0; i < 10; i++) {

            service.buscarPorNombre("Laptop");
        }

        long finNombre = System.nanoTime();

        long inicioNombreNo = System.nanoTime();

        for (int i = 0; i < 10; i++) {

            service.buscarPorNombre("ProductoXYZ");
        }

        long finNombreNo = System.nanoTime();

        System.out.println();
        System.out.println("Búsqueda Lineal");
        System.out.println("----------------------------");

        System.out.println("10 búsquedas con resultados : "
                + (finNombre - inicioNombre)
                + " ns");

        System.out.println("10 búsquedas sin resultados : "
                + (finNombreNo - inicioNombreNo)
                + " ns");
    }

}