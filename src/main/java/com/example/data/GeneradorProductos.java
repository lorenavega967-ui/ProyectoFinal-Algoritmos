package com.example.data;

import com.example.model.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


 // Clase responsable de generar un catálogo de productos con datos aleatorios pero coherentes.
 
 // Esta clase será utilizada para crear los datos de prueba solicitados en el proyecto.

public class GeneradorProductos {

    
     // Generador de números aleatorios.
    private final Random random = new Random();

    
    //Lista de nombres disponibles.
    private static final String[] NOMBRES = {

            "Laptop HP", "Laptop Lenovo",
            "Laptop Dell", "Mouse Logitech",
            "Teclado Mecánico", "Monitor Samsung",
            "Monitor LG", "Smartphone Samsung",
            "Smartphone Xiaomi", "Tablet Lenovo",
            "Impresora Epson", "Auriculares Sony",
            "Audífonos JBL", "Silla Gamer",
            "Escritorio Gamer", "Camisa Azul",
            "Camisa Blanca", "Pantalón Jeans",
            "Zapatos Deportivos", "Chaqueta Impermeable",
            "Libro Java", "Libro Python",
            "Libro SQL", "Libro Redes",
            "Mochila Escolar", "Botella Térmica",
            "Cafetera Oster", "Microondas LG",
            "Licuadora Oster", "Ventilador",
            "Televisor LG", "Televisor Samsung",
            "Disco SSD", "Memoria USB",
            "Router TP-Link", "Cámara Web",
            "Micrófono USB", "Parlante Bluetooth",
            "Reloj Inteligente", "Consola Gamer"

    };

    
     //Categorías disponibles.
    private static final String[] CATEGORIAS = { "Electrónica","Ropa","Hogar","Libros","Accesorios"
    };

    
    //Genera una lista de productos.
    public List<Producto> generarProductos(int cantidad) {

        List<Producto> productos = new ArrayList<>();

        for (int i = 1; i <= cantidad; i++) {

            Producto producto = new Producto();

            producto.setId(i);

            producto.setNombre(
                    NOMBRES[random.nextInt(NOMBRES.length)]
            );

            producto.setCategoria(
                    CATEGORIAS[random.nextInt(CATEGORIAS.length)]
            );

            // Precio entre 15 y 2000 dólares
            producto.setPrecio(
                    Math.round((15 + random.nextDouble() * 1985) * 100.0) / 100.0
            );

            // Stock entre 1 y 100 unidades
            producto.setStock(
                    random.nextInt(100) + 1
            );

            // Calificación entre 1.0 y 5.0
            producto.setCalificacionPromedio(
                    Math.round((1 + random.nextDouble() * 4) * 10.0) / 10.0
            );

            productos.add(producto);

        }

        return productos;

    }

}