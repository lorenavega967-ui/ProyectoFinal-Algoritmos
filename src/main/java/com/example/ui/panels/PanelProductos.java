package com.example.ui.panels;

import com.example.model.Producto;
import com.example.servicio.ProductoService;
import com.example.ui.ProductoTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

/*
 Panel encargado de la gestión y visualización de los productos del sistema.

- Este panel permite:
     - Generar productos.
     - Visualizar los productos generados.
     - Consultar la cantidad de productos.
     - Actualizar la información mostrada.

- La lógica de negocio permanece dentro de ProductoService. 
- Este panel solamente se encarga de la interacción con el usuario.
 */
public class PanelProductos extends JPanel {

    // =====================
    // COLORES
    // =====================

    /* Color principal */
    private static final Color COLOR_PRIMARIO =
            new Color(35, 32, 80);

    /* Color utilizado para botones principales. */
    private static final Color COLOR_ACENTO =
            new Color(109, 94, 252);

    /* Color de fondo.*/
    private static final Color COLOR_FONDO =
            new Color(246, 247, 255);

    /* Color de los paneles.*/
    private static final Color COLOR_PANEL =
            Color.WHITE;

    /* Color utilizado para bordes. */
    private static final Color COLOR_BORDE =
            new Color(226, 229, 244);

    /* Color utilizado para textos secundarios. */
    private static final Color COLOR_TEXTO_SECUNDARIO =
            new Color(105, 109, 142);

    /* Color utilizado para selección. */
    private static final Color COLOR_SELECCION =
            new Color(235, 232, 255);


    // =================
    // SERVICIO
    // ==================

    /*
     * Servicio encargado de administrar los productos.
     */
    private final ProductoService productoService;


    // =====================
    // COMPONENTES
    // =====================

    /* Tabla donde se muestran los productos. */
    private JTable tablaProductos;

    /* Modelo de la tabla. */
    private ProductoTableModel tableModel;

    /* Botón para generar productos.*/
    private JButton btnGenerar;

    /* Botón para actualizar la tabla. */
    private JButton btnActualizar;

    /* Etiqueta que muestra la cantidad de productos. */
    private JLabel lblCantidad;

    /*Etiqueta que muestra el estado de la operación. */
    private JLabel lblEstado;


    // ===================
    // CONSTRUCTOR
    // ====================

    /**
     * Constructor del panel.
     - productoService Servicio de productos compartido por la aplicación.
     */
    public PanelProductos(
            ProductoService productoService
    ) {

        /* Guardar la referencia al servicio. */
        this.productoService =
                productoService;

        /* Inicializar la interfaz. */
        inicializarComponentes();
    }


    // =====================
    // INICIALIZACIÓN
    // =====================

    /* Configura los componentes del panel. */
    private void inicializarComponentes() {

        /* Configuración general del panel. */
        setLayout(
                new BorderLayout(
                        0,
                        15
                )
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


        /* Crear modelo de tabla. */
        tableModel =
                new ProductoTableModel();


        /* Crear las diferentes secciones. */
        add(
                crearEncabezado(),
                BorderLayout.NORTH
        );

        add(
                crearPanelTabla(),
                BorderLayout.CENTER
        );

        add(
                crearPanelInferior(),
                BorderLayout.SOUTH
        );


        /* Actualizar la información inicial.*/
        actualizarVista();
    }


    // ====================
    // ENCABEZADO
    // ====================

    /* Crea el encabezado del módulo. */
    private JPanel crearEncabezado() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setOpaque(false);


        // -----------------
        // TÍTULOS
        // -----------------

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
                        "Gestión de productos"
                );

        titulo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        25
                )
        );

        titulo.setForeground(
                COLOR_PRIMARIO
        );


        JLabel descripcion =
                new JLabel(
                        "Genera y administra el catálogo de productos."
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


        textos.add(
                titulo
        );

        textos.add(
                descripcion
        );


        panel.add(
                textos,
                BorderLayout.WEST
        );


        // -----------------
        // BOTONES
        // -----------------

        JPanel acciones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        acciones.setOpaque(false);


        btnGenerar =
                crearBotonPrincipal(
                        "＋  Generar productos"
                );


        btnActualizar =
                crearBotonSecundario(
                        "↻  Actualizar"
                );


        acciones.add(
                btnGenerar
        );

        acciones.add(
                btnActualizar
        );


        panel.add(
                acciones,
                BorderLayout.EAST
        );


        /*Registrar eventos.*/
        btnGenerar.addActionListener(
                e -> generarProductos()
        );

        btnActualizar.addActionListener(
                e -> actualizarVista()
        );


        return panel;
    }


    // ================
    // TABLA
    // ===============

    /* Crea el panel que contiene la tabla.*/
    private JPanel crearPanelTabla() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                COLOR_PANEL
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                COLOR_BORDE
                        ),
                        new EmptyBorder(
                                12,
                                12,
                                12,
                                12
                        )
                )
        );


        // ---------------------------
        // ENCABEZADO DE LA TABLA
        // ---------------------------

        JPanel encabezado =
                new JPanel(
                        new BorderLayout()
                );

        encabezado.setOpaque(false);


        JLabel titulo =
                new JLabel(
                        "Catálogo de productos"
                );

        titulo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        titulo.setForeground(
                COLOR_PRIMARIO
        );


        JLabel ayuda =
                new JLabel(
                        "Productos disponibles en el sistema"
                );

        ayuda.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        ayuda.setForeground(
                COLOR_TEXTO_SECUNDARIO
        );


        encabezado.add(
                titulo,
                BorderLayout.WEST
        );

        encabezado.add(
                ayuda,
                BorderLayout.EAST
        );


        panel.add(
                encabezado,
                BorderLayout.NORTH
        );


        // -----------------------------
        // CREACIÓN DE LA TABLA
        // -----------------------------

        tablaProductos =
                new JTable(
                        tableModel
                );


        configurarTabla();


        JScrollPane scrollPane =
                new JScrollPane(
                        tablaProductos
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        COLOR_BORDE
                )
        );


        /* nota: La tabla se coloca en CENTER para que
           JScrollPane pueda ocupar todo el espacio isponible del panel.
         */
        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        return panel;
    }


    // ==========================
    // CONFIGURACIÓN DE TABLA
    // ==========================

    /*Configura la apariencia y comportamiento de la tabla.
     */
    private void configurarTabla() {

        /* Altura de las filas.*/
        tablaProductos.setRowHeight(
                34
        );


        /* Permitir que la tabla ocupe todoel espacio disponible.*/
        tablaProductos.setFillsViewportHeight(
                true
        );


        /*Selección de una sola fila.*/
        tablaProductos.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        /* Fuente.*/
        tablaProductos.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );


        /* Color de fondo. */
        tablaProductos.setBackground(
                Color.WHITE
        );


        /* Color del texto. */
        tablaProductos.setForeground(
                COLOR_PRIMARIO
        );


        /* Eliminar líneas verticales. */
        tablaProductos.setShowVerticalLines(
                false
        );


        /* Color de líneas horizontales. */
        tablaProductos.setGridColor(
                COLOR_BORDE
        );


        // ----------------------
        // ENCABEZADO
        // ----------------------

        JTableHeader encabezado =
                tablaProductos.getTableHeader();

        encabezado.setPreferredSize(
                new Dimension(
                        0,
                        38
                )
        );

        encabezado.setBackground(
                COLOR_PRIMARIO
        );

        encabezado.setForeground(
                Color.WHITE
        );

        encabezado.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        encabezado.setReorderingAllowed(
                false
        );


        // -------------------------------
        // ORDENAMIENTO VISUAL
        // -------------------------------

        /*
        Permite ordenar visualmente la tabla al pulsar sobre el encabezado.
        
        Esto es solamente una función visual de JTable 
        y no reemplaza los algoritmos de ordenamiento del proyecto.
         */
        tablaProductos.setAutoCreateRowSorter(
                true
        );


        // -------------------
        // RENDERER
        // -------------------

        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer() {

                    @Override
                    public Component
                    getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column
                    ) {

                        Component componente =
                                super.getTableCellRendererComponent(
                                        table,
                                        value,
                                        isSelected,
                                        hasFocus,
                                        row,
                                        column
                                );


                        if (isSelected) {

                            componente.setBackground(
                                    COLOR_SELECCION
                            );

                            componente.setForeground(
                                    COLOR_ACENTO
                            );

                        } else {

                            componente.setBackground(
                                    Color.WHITE
                            );

                            componente.setForeground(
                                    COLOR_PRIMARIO
                            );
                        }


                        /*
                         * Centrar columnas numéricas.
                         */
                        if (column == 0
                                || column == 2
                                || column == 4
                                || column == 5) {

                            setHorizontalAlignment(
                                    SwingConstants.CENTER
                            );

                        } else {

                            setHorizontalAlignment(
                                    SwingConstants.LEFT
                            );
                        }


                        return componente;
                    }
                };


        tablaProductos.setDefaultRenderer(
                Object.class,
                renderer
        );


        // -----------------
        // ANCHOS
        // -----------------

        /*
        Los anchos son preferencias.
        JScrollPane permitirá que la tabla
        utilice el espacio disponible.
         */
        if (tablaProductos.getColumnCount() >= 6) {

            tablaProductos
                    .getColumnModel()
                    .getColumn(0)
                    .setPreferredWidth(70);

            tablaProductos
                    .getColumnModel()
                    .getColumn(1)
                    .setPreferredWidth(240);

            tablaProductos
                    .getColumnModel()
                    .getColumn(2)
                    .setPreferredWidth(110);

            tablaProductos
                    .getColumnModel()
                    .getColumn(3)
                    .setPreferredWidth(150);

            tablaProductos
                    .getColumnModel()
                    .getColumn(4)
                    .setPreferredWidth(90);

            tablaProductos
                    .getColumnModel()
                    .getColumn(5)
                    .setPreferredWidth(110);
        }
    }


    // ============================
    // PANEL INFERIOR
    // ============================

    /* Crea el panel inferior con informacióndel catálogo */
    private JPanel crearPanelInferior() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                COLOR_PANEL
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                COLOR_BORDE
                        ),
                        new EmptyBorder(
                                12,
                                15,
                                12,
                                15
                        )
                )
        );


        // -----------------------
        // ESTADO
        // -----------------------

        lblEstado =
                new JLabel(
                        "Sistema listo."
                );

        lblEstado.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        lblEstado.setForeground(
                COLOR_TEXTO_SECUNDARIO
        );


        // ----------------------
        // CANTIDAD
        // ----------------------

        JPanel cantidad =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                5,
                                0
                        )
                );

        cantidad.setOpaque(false);


        JLabel textoCantidad =
                new JLabel(
                        "Productos:"
                );

        textoCantidad.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        textoCantidad.setForeground(
                COLOR_TEXTO_SECUNDARIO
        );


        lblCantidad =
                new JLabel(
                        "0"
                );

        lblCantidad.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );

        lblCantidad.setForeground(
                COLOR_ACENTO
        );


        cantidad.add(
                textoCantidad
        );

        cantidad.add(
                lblCantidad
        );


        panel.add(
                lblEstado,
                BorderLayout.WEST
        );

        panel.add(
                cantidad,
                BorderLayout.EAST
        );


        return panel;
    }


    // ==============================
    // GENERAR PRODUCTOS
    // ==============================

    /* Genera los productos utilizando el servicio. */
    private void generarProductos() {

        try {

            /*Generar los productos.
             Se mantiene la lógica dentro del servicio.
             */
            productoService.generarProductos(
                    50
            );


            /* Actualizar la tabla. */
            actualizarVista();


            /* Actualizar estado.*/
            lblEstado.setText(
                    "Los productos fueron generados correctamente."
            );


        } catch (Exception ex) {

            /* Mostrar el error al usuario.*/
            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible generar los productos."
                            + "\n\nDetalle: "
                            + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );


            /* Mostrar detalles en consola durante el desarrollo.
             */
            ex.printStackTrace();
        }
    }


    // =============================
    // ACTUALIZAR VISTA
    // =============================

    /* Actualiza la tabla con los productos
       actualmente almacenados en el servicio.
     */
    private void actualizarVista() {

        /* Obtener los productos del servicio. */
        List<Producto> productos =
                productoService.getProductos();


        /*  Actualizar el modelo de tabla. */
        tableModel.setProductos(
                productos
        );


        /*  Actualizar contador.  */
        lblCantidad.setText(
                String.valueOf(
                        productos.size()
                )
        );
    }


    // ==================
    // BOTONES
    // ==================

    /*
      Crea un botón principal.
     - texto Texto del botón.
     */
    private JButton crearBotonPrincipal(
            String texto
    ) {

        JButton boton =
                new JButton(
                        texto
                );

        boton.setPreferredSize(
                new Dimension(
                        180,
                        35
                )
        );

        boton.setBackground(
                COLOR_ACENTO
        );

        boton.setForeground(
                Color.WHITE
        );

        boton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        boton.setFocusPainted(
                false
        );

        boton.setBorder(
                BorderFactory.createEmptyBorder()
        );

        boton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        return boton;
    }


    /*
       Crea un botón secundario.
     - texto Texto del botón.

     */
    private JButton crearBotonSecundario(
            String texto
    ) {

        JButton boton =
                new JButton(
                        texto
                );

        boton.setPreferredSize(
                new Dimension(
                        120,
                        35
                )
        );

        boton.setBackground(
                Color.WHITE
        );

        boton.setForeground(
                COLOR_PRIMARIO
        );

        boton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        boton.setFocusPainted(
                false
        );

        boton.setBorder(
                BorderFactory.createLineBorder(
                        COLOR_BORDE
                )
        );

        boton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        return boton;
    }
}
