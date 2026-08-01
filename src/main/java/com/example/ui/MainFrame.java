package com.example.ui;

import com.example.servicio.ProductoService;
import com.example.ui.panels.PanelAlgoritmos;
import com.example.ui.panels.PanelBusqueda;
import com.example.ui.panels.PanelDashboard;
import com.example.ui.panels.PanelInicio;
import com.example.ui.panels.PanelProductos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/* Ventana principal de la aplicación.

 * Esta clase representa el JFrame principal del sistema
 * y se encarga de coordinar los diferentes paneles de
 * la aplicación.

 - Los paneles disponibles son:
      - Inicio
      - Productos
      - Algoritmos
      - Búsqueda
      - Dashboard
 */
public class MainFrame extends JFrame {

    // ================
    // COLORES
    // ===============

    private static final Color COLOR_MENU =
            new Color(35, 32, 80);

    private static final Color COLOR_MENU_HOVER =
            new Color(75, 66, 156);

    private static final Color COLOR_FONDO =
            new Color(246, 247, 255);

    private static final Color COLOR_TEXTO =
            Color.WHITE;


    // ===================
    // CONSTANTES
    // ==================

    private static final String CARD_INICIO =
            "INICIO";

    private static final String CARD_PRODUCTOS =
            "PRODUCTOS";

    private static final String CARD_ALGORITMOS =
            "ALGORITMOS";

    private static final String CARD_BUSQUEDA =
            "BUSQUEDA";

    private static final String CARD_DASHBOARD =
            "DASHBOARD";


    // ====================
    // SERVICIO
    // ===================

    /*
     - Servicio central de productos.
     * Todos los paneles reciben la misma instancia
     * para trabajar sobre el mismo catálogo.
     */
    private final ProductoService productoService;


    // ==========================
    // COMPONENTES
    // ==========================

    /*Panel donde se muestran las diferentes vistas. */
    private JPanel panelContenido;

    /**
     * Botones del menú lateral.
     */
    private JButton btnInicio;

    private JButton btnProductos;

    private JButton btnAlgoritmos;

    private JButton btnBusqueda;

    private JButton btnDashboard;


    // ===========================
    // PANELES
    // ===========================

    private PanelInicio panelInicio;

    private PanelProductos panelProductos;

    private PanelAlgoritmos panelAlgoritmos;

    private PanelBusqueda panelBusqueda;

    private PanelDashboard panelDashboard;


    // =========================
    // CONSTRUCTOR
    // =========================

    /* Constructor principal.*/
    public MainFrame() {

        /* Crear una única instancia del servicio. */
        productoService =
                new ProductoService();

        /*Configurar la ventana. */
        configurarVentana();

        /* Inicializar los paneles. */
        inicializarPaneles();

        /*Construir la interfaz. */
        construirInterfaz();


        /* Mostrar inicialmente el panel de inicio. */
        mostrarPanel(CARD_INICIO);
    }


    // =================================
    // CONFIGURACIÓN DE VENTANA
    // =================================

    /* Configura las propiedades principales del JFrame.  */
    private void configurarVentana() {

        setTitle(
                "Sistema de Gestión de Productos"
        );


        setDefaultCloseOperation(
                JFrame.DO_NOTHING_ON_CLOSE
        );


        setSize(
                1200,
                750
        );


        setMinimumSize(
                new Dimension(
                        1000,
                        650
                )
        );


        setLocationRelativeTo(
                null
        );


        setLayout(
                new BorderLayout()
        );


        getContentPane().setBackground(
                COLOR_FONDO
        );


        /* Confirmar antes de cerrar la aplicación.  */
        addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowClosing(
                            WindowEvent e
                    ) {

                        confirmarSalida();
                    }
                }
        );
    }


    // ===============================
    // INICIALIZAR PANELES
    // ===============================

    /*Crea todas las vistas de la aplicación.
      - Todos los paneles reciben la misma instancia ProductoService.  */
    private void inicializarPaneles() {

        panelInicio =
                new PanelInicio(
                        productoService
                );


        panelProductos =
                new PanelProductos(
                        productoService
                );


        panelAlgoritmos =
                new PanelAlgoritmos(
                        productoService
                );


        panelBusqueda =
                new PanelBusqueda(
                        productoService
                );


        panelDashboard =
                new PanelDashboard(
                        productoService
                );
    }


    // ================================
    // CONSTRUIR INTERFAZ
    // ================================

    /* Construye el menú lateral y el área principal de contenido. */
    private void construirInterfaz() {

        /* Crear menú lateral. */
        JPanel menu =
                crearMenuLateral();


        /* Crear área de contenido. */
        panelContenido =
                new JPanel(
                        new BorderLayout()
                );


        panelContenido.setBackground(
                COLOR_FONDO
        );


        /* Agregar componentes principales.*/
        add(
                menu,
                BorderLayout.WEST
        );


        add(
                panelContenido,
                BorderLayout.CENTER
        );
    }


    // ========================
    // MENÚ LATERAL
    // ========================

    /* Crea el menú lateral de navegación. */
    private JPanel crearMenuLateral() {

        JPanel menu =
                new JPanel();


        menu.setLayout(
                new BorderLayout()
        );


        menu.setPreferredSize(
                new Dimension(
                        225,
                        0
                )
        );


        menu.setBackground(
                COLOR_MENU
        );


        /*
         * -----------------------
         * ENCABEZADO
         * -----------------------
         */

        JPanel encabezado =
                new JPanel();


        encabezado.setLayout(
                new BoxLayout(
                        encabezado,
                        BoxLayout.Y_AXIS
                )
        );


        encabezado.setBackground(
                COLOR_MENU
        );


        encabezado.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        20,
                        28,
                        20
                )
        );


        JLabel titulo =
                new JLabel(
                        "PRODUCTOS"
                );


        titulo.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        titulo.setForeground(
                COLOR_TEXTO
        );


        titulo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );


        JLabel subtitulo =
                new JLabel(
                        "Sistema de gestión"
                );


        subtitulo.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        subtitulo.setForeground(
                new Color(
                        209,
                        213,
                        219
                )
        );


        subtitulo.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );


        encabezado.add(
                titulo
        );


        encabezado.add(
                Box.createVerticalStrut(
                        5
                )
        );


        encabezado.add(
                subtitulo
        );


        menu.add(
                encabezado,
                BorderLayout.NORTH
        );


        /*
         * -----------------------
         * BOTONES
         * ----------------------
         */

        JPanel botones =
                new JPanel();


        botones.setLayout(
                new BoxLayout(
                        botones,
                        BoxLayout.Y_AXIS
                )
        );


        botones.setBackground(
                COLOR_MENU
        );


        botones.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );


        btnInicio =
                crearBotonMenu(
                        "⌂   Inicio"
                );


        btnProductos =
                crearBotonMenu(
                        "▣   Productos"
                );


        btnAlgoritmos =
                crearBotonMenu(
                        "↕   Algoritmos"
                );


        btnBusqueda =
                crearBotonMenu(
                        "⌕   Búsqueda"
                );


        btnDashboard =
                crearBotonMenu(
                        "▥   Dashboard"
                );


        botones.add(
                btnInicio
        );


        botones.add(
                Box.createVerticalStrut(
                        8
                )
        );


        botones.add(
                btnProductos
        );


        botones.add(
                Box.createVerticalStrut(
                        8
                )
        );


        botones.add(
                btnAlgoritmos
        );


        botones.add(
                Box.createVerticalStrut(
                        8
                )
        );


        botones.add(
                btnBusqueda
        );


        botones.add(
                Box.createVerticalStrut(
                        8
                )
        );


        botones.add(
                btnDashboard
        );


        menu.add(
                botones,
                BorderLayout.CENTER
        );


        /*
         * ------------------------
         * PIE DEL MENÚ
         * ------------------------
         */

        JPanel pie =
                new JPanel(
                        new BorderLayout()
                );


        pie.setBackground(
                COLOR_MENU
        );


        pie.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        20,
                        20,
                        20
                )
        );


        JLabel version =
                new JLabel(
                        "Sistema de algoritmos"
                );


        version.setForeground(
                new Color(
                        156,
                        163,
                        175
                )
        );


        version.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );


        pie.add(
                version,
                BorderLayout.WEST
        );


        menu.add(
                pie,
                BorderLayout.SOUTH
        );


        /*
         * Registrar acciones.
         */
        registrarEventosMenu();


        return menu;
    }


    // =======================================
    // CREAR BOTÓN DEL MENÚ
    // ======================================

    /* Crea un botón visual para el menú lateral. */
    private JButton crearBotonMenu(
            String texto
    ) {

        JButton boton =
                new JButton(
                        texto
                );


        boton.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        boton.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );


        boton.setPreferredSize(
                new Dimension(
                        205,
                        45
                )
        );


        boton.setHorizontalAlignment(
                SwingConstants.LEFT
        );


        boton.setBackground(
                COLOR_MENU
        );


        boton.setForeground(
                COLOR_TEXTO
        );


        boton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );


        boton.setFocusPainted(
                false
        );


        boton.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        18,
                        0,
                        10
                )
        );


        boton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        /* Efecto visual al pasar el mouse.*/
        boton.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e
                    ) {

                        boton.setBackground(
                                COLOR_MENU_HOVER
                        );
                    }


                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent e
                    ) {

                        boton.setBackground(
                                COLOR_MENU
                        );
                    }
                }
        );


        return boton;
    }


    // ============================
    // EVENTOS DEL MENÚ
    // ============================

    /* Registra los eventos de navegación. */
    private void registrarEventosMenu() {

        btnInicio.addActionListener(
                e -> mostrarPanel(
                        CARD_INICIO
                )
        );


        btnProductos.addActionListener(
                e -> mostrarPanel(
                        CARD_PRODUCTOS
                )
        );


        btnAlgoritmos.addActionListener(
                e -> mostrarPanel(
                        CARD_ALGORITMOS
                )
        );


        btnBusqueda.addActionListener(
                e -> mostrarPanel(
                        CARD_BUSQUEDA
                )
        );


        btnDashboard.addActionListener(
                e -> mostrarPanel(
                        CARD_DASHBOARD
                )
        );
    }


    // ========================
    // MOSTRAR PANEL
    // ========================

    /* Cambia el panel mostrado en el área central. */
    private void mostrarPanel(
            String nombre
    ) {

        /*
         * Limpiar contenido actual.
         */
        panelContenido.removeAll();


        /*
         * Seleccionar el panel correspondiente.
         */
        switch (nombre) {

            case CARD_PRODUCTOS:

                panelContenido.add(
                        panelProductos,
                        BorderLayout.CENTER
                );

                break;


            case CARD_ALGORITMOS:

                panelContenido.add(
                        panelAlgoritmos,
                        BorderLayout.CENTER
                );

                break;


            case CARD_BUSQUEDA:

                panelContenido.add(
                        panelBusqueda,
                        BorderLayout.CENTER
                );

                break;


            case CARD_DASHBOARD:

                panelContenido.add(
                        panelDashboard,
                        BorderLayout.CENTER
                );

                break;


            case CARD_INICIO:

            default:

                panelContenido.add(
                        panelInicio,
                        BorderLayout.CENTER
                );

                break;
        }


        /*
         * Actualizar interfaz.
         */
        panelContenido.revalidate();

        panelContenido.repaint();
    }


    // ============================
    // CONFIRMAR SALIDA
    // ============================

    /**
     * Solicita confirmación antes de cerrar
     * la aplicación.
     */
    private void confirmarSalida() {

        int respuesta =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Deseas salir de la aplicación?",
                        "Confirmar salida",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );


        if (respuesta ==
                JOptionPane.YES_OPTION) {

            dispose();

            System.exit(0);
        }
    }

}
