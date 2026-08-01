package com.example.model;

/*
  - Clase que representa un producto dentro del catálogo de la tienda.
 
  - Cada objeto Producto almacena la información necesaria para realizar
    ordenamientos, búsquedas y análisis de rendimiento solicitados en el proyecto.
 */

public class Producto {

    
    private int id;
    private String nombre;
    private double precio; // precio del producto
    private String categoria; //categoria del producto
    private int stock; // cantidad disponible en el inventario 
    private double calificacionPromedio; // clasificacion promedio del producto

 
    public Producto() {
    }

    public Producto(int id,String nombre,double precio,String categoria,int stock,double calificacionPromedio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.stock = stock;
        this.calificacionPromedio = calificacionPromedio;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(double calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    
    //Devuelve una representación en texto del producto. 
    @Override
    public String toString() {
        return String.format(
                "Producto{id=%d, nombre='%s', precio=%.2f, categoria='%s', stock=%d, calificacion=%.1f}",
                id,
                nombre,
                precio,
                categoria,
                stock,
                calificacionPromedio
        );
    }

}