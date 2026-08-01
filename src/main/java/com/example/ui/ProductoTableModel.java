package com.example.ui;

import com.example.model.Producto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/*
   - Modelo de datos utilizado por JTable para mostrar la información de los productos.
   - Esta clase conecta la lista de objetos Producto con la tabla de la interfaz gráfica.
 */
public class ProductoTableModel extends AbstractTableModel {

    // =========================
    // COLUMNAS
    // =========================

    private final String[] columnas = {
            "ID",
            "Nombre",
            "Precio",
            "Categoría",
            "Stock",
            "Calificación"
    };


    // ========================
    // DATOS
    // ========================

    /**
     * Lista de productos que actualmente
     * se muestran en la tabla.
     */
    private List<Producto> productos;


    // ==================================
    // CONSTRUCTOR
    // =================================

    public ProductoTableModel() {

        productos =
                new ArrayList<>();
    }


    // ===============================
    // ACTUALIZAR PRODUCTOS
    // ===============================

    /*
     * Actualiza los productos mostrados en la tabla.
     */
    public void setProductos(
            List<Producto> productos
    ) {

        if (productos == null) {

            this.productos =
                    new ArrayList<>();

        } else {

            /*
             * Creamos una copia para evitar
             * referencias externas.
             */
            this.productos =
                    new ArrayList<>(
                            productos
                    );
        }


        /*
         * Avisar a JTable que los datos
         * cambiaron.
         */
        fireTableDataChanged();
    }


    // ==============================
    // CANTIDAD DE FILAS
    // ==============================

    @Override
    public int getRowCount() {

        return productos.size();
    }


    // ================================
    // CANTIDAD DE COLUMNAS
    // ================================

    @Override
    public int getColumnCount() {

        return columnas.length;
    }


    // ===================================
    // NOMBRE DE COLUMNA
    // ===================================

    @Override
    public String getColumnName(
            int column
    ) {

        return columnas[column];
    }


    // ================================
    // VALOR DE CELDA
    // ================================

    @Override
    public Object getValueAt(
            int rowIndex,
            int columnIndex
    ) {

        /*
         * Obtener producto correspondiente
         * a la fila.
         */
        Producto producto =
                productos.get(
                        rowIndex
                );


        switch (columnIndex) {

            // ------------------
            // ID
            // ------------------

            case 0:

                return producto.getId();


            // -----------------------
            // NOMBRE
            // -----------------------

            case 1:

                return producto.getNombre();


            // ---------------------
            // PRECIO
            // ---------------------

            case 2:

                return String.format(
                        "$ %.2f",
                        producto.getPrecio()
                );


            // --------------------
            // CATEGORÍA
            // --------------------

            case 3:

                return producto.getCategoria();


            // ----------------------
            // STOCK
            // ----------------------

            case 4:

                return producto.getStock();


            // ----------------------
            // CALIFICACIÓN
            // ----------------------

            case 5:

                return String.format(
                        "%.1f",
                        producto.getCalificacionPromedio()
                );


            default:

                return null;
        }
    }


    // ===============================
    // TIPO DE COLUMNA
    // ===============================

    @Override
    public Class<?> getColumnClass(
            int columnIndex
    ) {

        switch (columnIndex) {

            case 0:
                return Integer.class;

            case 4:
                return Integer.class;

            default:
                return String.class;
        }
    }


    // =================================
    // CELDAS EDITABLES
    // ================================

    /**
     * La tabla solamente muestra información.
     * No permite modificar los productos
     * directamente desde la tabla.
     */
    @Override
    public boolean isCellEditable(
            int rowIndex,
            int columnIndex
    ) {

        return false;
    }


    // ===============================
    // OBTENER PRODUCTO
    // ===============================

    /*
    Obtiene el producto correspondiente a una determinada fila.
     */
    public Producto getProductoAt(
            int fila
    ) {

        if (fila < 0 ||
                fila >= productos.size()) {

            return null;
        }


        return productos.get(
                fila
        );
    }


    // ============================
    // LIMPIAR TABLA
    // ============================

    /**
     * Elimina todos los productos mostrados
     * actualmente en la tabla.
     */
    public void limpiar() {

        productos.clear();

        fireTableDataChanged();
    }
}