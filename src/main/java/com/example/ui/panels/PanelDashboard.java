package com.example.ui.panels;

import com.example.model.Producto;
import com.example.servicio.ProductoService;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 - Panel principal de estadísticas del sistema.
 - Muestra información general del catálogo y gráficos utilizando JFreeChart.
 - También permite actualizar las estadísticas cuando el catálogo cambia.
 */

public class PanelDashboard extends JPanel {

    // =========
    // COLORES
    // =========

    private static final Color COLOR_PRIMARIO =
            new Color(35, 32, 80);

    private static final Color COLOR_FONDO =
            new Color(246, 247, 255);

    private static final Color COLOR_PANEL =
            Color.WHITE;

    private static final Color COLOR_ACENTO =
            new Color(109, 94, 252);

    private static final Color COLOR_BORDE =
            new Color(226, 229, 244);

    private static final Color COLOR_TEXTO_SECUNDARIO =
            new Color(105, 109, 142);


    // ==========
    // SERVICIO
    // ==========

    /**
     * Servicio que contiene el catálogo de productos.
     */
    private final ProductoService productoService;


    // ===========================
    // ETIQUETAS DE ESTADÍSTICAS
    // ===========================

    private JLabel lblTotal;

    private JLabel lblCategorias;

    private JLabel lblMejor;

    private JLabel lblMayorPrecio;

    private JLabel lblStockTotal;

    private JLabel lblPrecioPromedio;

    private JLabel lblCalificacionPromedio;


    // ========================
    // CONTENEDOR DE GRÁFICOS
    // ========================

    private JPanel panelGraficos;


    // ============
    // CONSTRUCTOR
    // ============

    /**
     * Constructor del Dashboard.
     *
     * @param productoService servicio de productos.
     */
    public PanelDashboard(
            ProductoService productoService
    ) {

        this.productoService = productoService;

        inicializar();
    }


    // =================
    // INICIALIZACIÓN
    // =================

    /**
     * Inicializa todos los componentes.
     */
    private void inicializar() {

        setLayout(
                new BorderLayout(20, 20)
        );

        setBackground(
                COLOR_FONDO
        );

        setBorder(
                new EmptyBorder(
                        25,
                        25,
                        25,
                        25
                )
        );


        /*
         * Encabezado.
         */
        add(
                crearEncabezado(),
                BorderLayout.NORTH
        );


        /*
         * Contenido principal.
         */
        add(
                crearContenido(),
                BorderLayout.CENTER
        );


        /*
         * Cargar estadísticas inicialmente.
         */
        actualizarDatos();
    }


    // =============
    // ENCABEZADO
    // =============

    
     //Crea el encabezado del Dashboard.
 
    private JPanel crearEncabezado() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setOpaque(false);


        /*
         * Panel de textos.
         */
        JPanel textos =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        textos.setOpaque(false);


        JLabel titulo =
                new JLabel(
                        "Dashboard del sistema"
                );

        titulo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        26
                )
        );

        titulo.setForeground(
                COLOR_PRIMARIO
        );


        JLabel descripcion =
                new JLabel(
                        "Estadísticas generales y análisis del catálogo"
                );

        descripcion.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        descripcion.setForeground(
                COLOR_TEXTO_SECUNDARIO
        );


        textos.add(titulo);
        textos.add(descripcion);


        panel.add(
                textos,
                BorderLayout.WEST
        );


        /*
         * Botón actualizar.
         */
        JButton btnActualizar =
                new JButton(
                        "⟳  Actualizar"
                );

        btnActualizar.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        btnActualizar.setForeground(
                Color.WHITE
        );

        btnActualizar.setBackground(
                COLOR_ACENTO
        );

        btnActualizar.setFocusPainted(
                false
        );

        btnActualizar.setBorder(
                new EmptyBorder(
                        10,
                        18,
                        10,
                        18
                )
        );

        btnActualizar.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        btnActualizar.addActionListener(
                e -> actualizarDatos()
        );


        panel.add(
                btnActualizar,
                BorderLayout.EAST
        );


        return panel;
    }


    // =================
    // CONTENIDO
    // ================

    
     // Crea el contenido completo del Dashboard.

    private JPanel crearContenido() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                20,
                                20
                        )
                );

        panel.setOpaque(false);


        /*
         * Tarjetas superiores.
         */
        panel.add(
                crearTarjetas(),
                BorderLayout.NORTH
        );


        /*
         * Panel donde se cargarán los gráficos.
         */
        panelGraficos =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                20,
                                20
                        )
                );

        panelGraficos.setOpaque(false);


        panel.add(
                panelGraficos,
                BorderLayout.CENTER
        );


        return panel;
    }


    // =============
    // TARJETAS
    // ============

   
     // Crea las tarjetas estadísticas.

    private JPanel crearTarjetas() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                15,
                                15
                        )
                );

        panel.setOpaque(false);


        /*
         * Crear etiquetas.
         */
        lblTotal =
                crearValor();

        lblCategorias =
                crearValor();

        lblMejor =
                crearValor();

        lblMayorPrecio =
                crearValor();

        lblStockTotal =
                crearValor();

        lblPrecioPromedio =
                crearValor();

        lblCalificacionPromedio =
                crearValor();


        /*
         * Primera fila.
         */
        panel.add(
                crearTarjeta(
                        "PRODUCTOS",
                        lblTotal
                )
        );

        panel.add(
                crearTarjeta(
                        "CATEGORÍAS",
                        lblCategorias
                )
        );

        panel.add(
                crearTarjeta(
                        "MEJOR CALIFICADO",
                        lblMejor
                )
        );

        panel.add(
                crearTarjeta(
                        "MAYOR PRECIO",
                        lblMayorPrecio
                )
        );


        /*
         * Segunda fila.
         */
        panel.add(
                crearTarjeta(
                        "STOCK TOTAL",
                        lblStockTotal
                )
        );

        panel.add(
                crearTarjeta(
                        "PRECIO PROMEDIO",
                        lblPrecioPromedio
                )
        );

        panel.add(
                crearTarjeta(
                        "CALIFICACIÓN PROMEDIO",
                        lblCalificacionPromedio
                )
        );


        /*
         * Espacio visual.
         */
        JPanel espacio =
                new JPanel();

        espacio.setOpaque(false);

        panel.add(
                espacio
        );


        return panel;
    }


    /*
     Crea una tarjeta individual.
     - titulo título de la tarjeta.
     - valor valor mostrado.
     */
    private JPanel crearTarjeta(
            String titulo,
            JLabel valor
    ) {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        panel.setBackground(
                COLOR_PANEL
        );

        panel.setBorder(
                BorderFactory.createLineBorder(
                        COLOR_BORDE
                )
        );


        JLabel lblTitulo =
                new JLabel(
                        titulo,
                        SwingConstants.CENTER
                );

        lblTitulo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        lblTitulo.setForeground(
                COLOR_TEXTO_SECUNDARIO
        );


        panel.add(
                lblTitulo
        );

        panel.add(
                valor
        );


        return panel;
    }


    /*
     Crea una etiqueta para mostrar valores.
     */
    private JLabel crearValor() {

        JLabel lbl =
                new JLabel(
                        "-",
                        SwingConstants.CENTER
                );

        lbl.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );

        lbl.setForeground(
                COLOR_ACENTO
        );


        return lbl;
    }


    // ==========================
    // ACTUALIZACIÓN DE DATOS
    // ==========================

    /*
     - Actualiza las tarjetas y los gráficos.
     - Este método puede llamarse desde otros paneles cuando se generen nuevos productos.
     */
    public void actualizarDatos() {

        List<Producto> productos =
                productoService.getProductos();


        /*
         * Si no existen productos.
         */
        if (productos == null ||
                productos.isEmpty()) {

            lblTotal.setText("0");

            lblCategorias.setText("0");

            lblMejor.setText("-");

            lblMayorPrecio.setText("$0.00");

            lblStockTotal.setText("0");

            lblPrecioPromedio.setText("$0.00");

            lblCalificacionPromedio.setText("0.0");


            mostrarGraficosVacios();

            return;
        }


        // --------------------------
        // TOTAL DE PRODUCTOS
        // --------------------------
        lblTotal.setText(
                String.valueOf(
                        productos.size()
                )
        );


        // ----------------------
        // CATEGORÍAS
        // ----------------------

        long cantidadCategorias =
                productos.stream()
                        .map(
                                Producto::getCategoria
                        )
                        .distinct()
                        .count();


        lblCategorias.setText(
                String.valueOf(
                        cantidadCategorias
                )
        );


        // ---------------------
        // MEJOR CALIFICADO
        // ---------------------

        Producto mejor =
                productos.stream()
                        .max(
                                (a, b) ->
                                        Double.compare(
                                                a.getCalificacionPromedio(),
                                                b.getCalificacionPromedio()
                                        )
                        )
                        .orElse(null);


        lblMejor.setText(
                mejor == null
                        ? "-"
                        : mejor.getNombre()
        );


        // -------------------
        // MAYOR PRECIO
        // ------------------

        Producto caro =
                productos.stream()
                        .max(
                                (a, b) ->
                                        Double.compare(
                                                a.getPrecio(),
                                                b.getPrecio()
                                        )
                        )
                        .orElse(null);


        lblMayorPrecio.setText(
                caro == null
                        ? "$0.00"
                        : String.format(
                                "$ %.2f",
                                caro.getPrecio()
                        )
        );


        // -------------------
        // STOCK TOTAL
        // -------------------

        int stockTotal =
                productos.stream()
                        .mapToInt(
                                Producto::getStock
                        )
                        .sum();


        lblStockTotal.setText(
                String.valueOf(
                        stockTotal
                )
        );


        // --------------------
        // PRECIO PROMEDIO
        // -------------------

        double precioPromedio =
                productos.stream()
                        .mapToDouble(
                                Producto::getPrecio
                        )
                        .average()
                        .orElse(0);


        lblPrecioPromedio.setText(
                String.format(
                        "$ %.2f",
                        precioPromedio
                )
        );


        // ---------------------------
        // CALIFICACIÓN PROMEDIO
        // --------------------------

        double calificacionPromedio =
                productos.stream()
                        .mapToDouble(
                                Producto::getCalificacionPromedio
                        )
                        .average()
                        .orElse(0);


        lblCalificacionPromedio.setText(
                String.format(
                        "%.1f / 5.0",
                        calificacionPromedio
                )
        );


        /*
         * Actualizar gráficos.
         */
        actualizarGraficos(
                productos
        );
    }


    // ===================
    // GRÁFICOS
    // ==================

    /*
     * Actualiza todos los gráficos.
     - retorna los productos lista actual.
     */
    private void actualizarGraficos(
            List<Producto> productos
    ) {

        /*
         * Limpiar gráficos anteriores.
         */
        panelGraficos.removeAll();


        /*
         * Agregar gráfico de categorías.
         */
        panelGraficos.add(
                crearGraficoCategorias(
                        productos
                )
        );


        /*
         * Agregar gráfico de calificaciones.
         */
        panelGraficos.add(
                crearGraficoCalificaciones(
                        productos
                )
        );


        /*
         * Agregar gráfico de stock.
         */
        panelGraficos.add(
                crearGraficoStock(
                        productos
                )
        );


        /*
         * Agregar gráfico de precios.
         */
        panelGraficos.add(
                crearGraficoPrecios(
                        productos
                )
        );


        /*
         * Actualizar visualmente.
         */
        panelGraficos.revalidate();

        panelGraficos.repaint();
    }


    // ========================
    // GRÁFICO DE CATEGORÍAS
    // =======================

    /*
     * Crea el gráfico circular de productos por categoría.
     - productos productos actuales.
     - retorna el panel del gráfico.
     */
    private JPanel crearGraficoCategorias(
            List<Producto> productos
    ) {

        DefaultPieDataset<String> datos =
                new DefaultPieDataset<>();


        Map<String, Integer> categorias =
                new HashMap<>();


        for (Producto producto : productos) {

            String categoria =
                    producto.getCategoria();


            if (categoria == null ||
                    categoria.trim().isEmpty()) {

                categoria = "Sin categoría";
            }


            categorias.put(
                    categoria,
                    categorias.getOrDefault(
                            categoria,
                            0
                    ) + 1
            );
        }


        for (Map.Entry<String, Integer> entrada :
                categorias.entrySet()) {

            datos.setValue(
                    entrada.getKey(),
                    entrada.getValue()
            );
        }


        JFreeChart chart =
                ChartFactory.createPieChart(
                        "Productos por categoría",
                        datos,
                        true,
                        true,
                        false
                );


        PiePlot<?> plot =
                (PiePlot<?>) chart.getPlot();

        plot.setBackgroundPaint(
                Color.WHITE
        );


        ChartPanel chartPanel =
                new ChartPanel(
                        chart
                );


        chartPanel.setBorder(
                BorderFactory.createLineBorder(
                        COLOR_BORDE
                )
        );


        return chartPanel;
    }


    // ================================
    // GRÁFICO DE CALIFICACIONES
    // ================================

    /*
     * Crea un gráfico de barras con las calificaciones.
     - productos productos actuales.
     - retorna el panel del gráfico.
     */
    private JPanel crearGraficoCalificaciones(
            List<Producto> productos
    ) {

        DefaultCategoryDataset datos =
                new DefaultCategoryDataset();


        /*
         * Mostrar máximo 15 productos para evitar saturar el gráfico.
         */
        int limite =
                Math.min(
                        productos.size(),
                        15
                );


        for (int i = 0; i < limite; i++) {

            Producto producto =
                    productos.get(i);


            String nombre =
                    producto.getNombre();


            if (nombre == null ||
                    nombre.trim().isEmpty()) {

                nombre = "Producto " + (i + 1);
            }


            /*
             * Acortar nombres largos.
             */
            if (nombre.length() > 12) {

                nombre =
                        nombre.substring(
                                0,
                                12
                        ) + "...";
            }


            datos.addValue(
                    producto.getCalificacionPromedio(),
                    "Calificación",
                    nombre
            );
        }


        JFreeChart chart =
                ChartFactory.createBarChart(
                        "Calificación de productos",
                        "Producto",
                        "Calificación",
                        datos
                );


        CategoryPlot plot =
                chart.getCategoryPlot();


        plot.setBackgroundPaint(
                Color.WHITE
        );


        ChartPanel chartPanel =
                new ChartPanel(
                        chart
                );


        chartPanel.setBorder(
                BorderFactory.createLineBorder(
                        COLOR_BORDE
                )
        );


        return chartPanel;
    }


    // ======================
    // GRÁFICO DE STOCK
    // =====================

    /**
     * Crea un gráfico de stock por categoría.
     - productos productos actuales.
     - retorn el panel del gráfico.
     */
    private JPanel crearGraficoStock(
            List<Producto> productos
    ) {

        DefaultCategoryDataset datos =
                new DefaultCategoryDataset();


        Map<String, Integer> stockCategorias =
                new HashMap<>();


        for (Producto producto : productos) {

            String categoria =
                    producto.getCategoria();


            if (categoria == null ||
                    categoria.trim().isEmpty()) {

                categoria = "Sin categoría";
            }


            stockCategorias.put(
                    categoria,
                    stockCategorias.getOrDefault(
                            categoria,
                            0
                    ) + producto.getStock()
            );
        }


        for (Map.Entry<String, Integer> entrada :
                stockCategorias.entrySet()) {

            datos.addValue(
                    entrada.getValue(),
                    "Stock",
                    entrada.getKey()
            );
        }


        JFreeChart chart =
                ChartFactory.createBarChart(
                        "Stock por categoría",
                        "Categoría",
                        "Unidades",
                        datos
                );


        CategoryPlot plot =
                chart.getCategoryPlot();


        plot.setBackgroundPaint(
                Color.WHITE
        );


        ChartPanel chartPanel =
                new ChartPanel(
                        chart
                );


        chartPanel.setBorder(
                BorderFactory.createLineBorder(
                        COLOR_BORDE
                )
        );


        return chartPanel;
    }


    // ========================
    // GRÁFICO DE PRECIOS
    // ========================

    /**
     * Crea un gráfico de precios de los productos.
     - productos productos actuales.
     - retorna el panel del gráfico.
     */
    private JPanel crearGraficoPrecios(
            List<Producto> productos
    ) {

        DefaultCategoryDataset datos =
                new DefaultCategoryDataset();


        int limite =
                Math.min(
                        productos.size(),
                        15
                );


        for (int i = 0; i < limite; i++) {

            Producto producto =
                    productos.get(i);


            String nombre =
                    producto.getNombre();


            if (nombre == null ||
                    nombre.trim().isEmpty()) {

                nombre =
                        "Producto " + (i + 1);
            }


            if (nombre.length() > 12) {

                nombre =
                        nombre.substring(
                                0,
                                12
                        ) + "...";
            }


            datos.addValue(
                    producto.getPrecio(),
                    "Precio",
                    nombre
            );
        }


        JFreeChart chart =
                ChartFactory.createBarChart(
                        "Precio de productos",
                        "Producto",
                        "Precio ($)",
                        datos
                );


        CategoryPlot plot =
                chart.getCategoryPlot();


        plot.setBackgroundPaint(
                Color.WHITE
        );


        ChartPanel chartPanel =
                new ChartPanel(
                        chart
                );


        chartPanel.setBorder(
                BorderFactory.createLineBorder(
                        COLOR_BORDE
                )
        );


        return chartPanel;
    }


    // =====================
    // GRÁFICOS VACÍOS
    // =====================

    /**
     * Muestra gráficos informativos cuando todavía no existen productos.
     */
    private void mostrarGraficosVacios() {

        panelGraficos.removeAll();


        panelGraficos.add(
                crearPanelVacio(
                        "Productos por categoría",
                        "No existen productos para mostrar."
                )
        );


        panelGraficos.add(
                crearPanelVacio(
                        "Calificación de productos",
                        "Genera productos para mostrar estadísticas."
                )
        );


        panelGraficos.add(
                crearPanelVacio(
                        "Stock por categoría",
                        "No existe información de stock."
                )
        );


        panelGraficos.add(
                crearPanelVacio(
                        "Precio de productos",
                        "No existe información de precios."
                )
        );


        panelGraficos.revalidate();

        panelGraficos.repaint();
    }


    /*
     * Crea un panel informativo vacío.
     - titulo título.
     - mensaje mensaje.
     */
    private JPanel crearPanelVacio(
            String titulo,
            String mensaje
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                COLOR_PANEL
        );

        panel.setBorder(
                BorderFactory.createLineBorder(
                        COLOR_BORDE
                )
        );


        JLabel lblTitulo =
                new JLabel(
                        titulo,
                        SwingConstants.CENTER
                );

        lblTitulo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );


        JLabel lblMensaje =
                new JLabel(
                        mensaje,
                        SwingConstants.CENTER
                );

        lblMensaje.setForeground(
                COLOR_TEXTO_SECUNDARIO
        );


        panel.add(
                lblTitulo,
                BorderLayout.NORTH
        );


        panel.add(
                lblMensaje,
                BorderLayout.CENTER
        );


        return panel;
    }
}
