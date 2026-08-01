package com.example.ui.panels;

import com.example.servicio.ProductoService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/* 
- Panel principal de bienvenida del sistema.
- Presenta información general del proyecto,los integrantes y las principales funcionalidades de la aplicación.
 */

public class PanelInicio extends JPanel {

    // ===========
    // COLORES
    // ===========

    private static final Color COLOR_PRIMARIO =
            new Color(35, 32, 80);

    private static final Color COLOR_ACENTO =
            new Color(109, 94, 252);

    private static final Color COLOR_FONDO =
            new Color(246, 247, 255);

    private static final Color COLOR_PANEL =
            Color.WHITE;

    private static final Color COLOR_SECUNDARIO =
            new Color(105, 109, 142);


    // ===============
    // SERVICIO
    // ===============

    private final ProductoService productoService;


    // =================
    // CONSTRUCTOR
    // ===============

    public PanelInicio(
            ProductoService productoService
    ) {

        this.productoService =
                productoService;

        inicializar();
    }


    // ================
    // INICIALIZAR
    // ================

    private void inicializar() {

        setLayout(
                new BorderLayout()
        );

        setBackground(
                COLOR_FONDO
        );

        setBorder(
                new EmptyBorder(
                        30,
                        30,
                        30,
                        30
                )
        );

        add(
                crearContenido(),
                BorderLayout.CENTER
        );
    }


    // ===============
    // CONTENIDO
    // ===============

    private JPanel crearContenido() {

        JPanel contenedor =
                new JPanel(
                        new BorderLayout(
                                0,
                                25
                        )
                );

        contenedor.setOpaque(false);

        /*
         * Información principal.
         */
        contenedor.add(
                crearPanelCentral(),
                BorderLayout.CENTER
        );


        /*
         * Pie.
         */
        contenedor.add(
                crearPie(),
                BorderLayout.SOUTH
        );


        return contenedor;
    }



    // =========================================================
    // PANEL CENTRAL
    // =========================================================

    private JPanel crearPanelCentral() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0,
                                25
                        )
                );

        panel.setBackground(
                COLOR_PANEL
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        229,
                                        231,
                                        235
                                )
                        ),
                        new EmptyBorder(
                                35,
                                40,
                                35,
                                40
                        )
                )
        );


        /*
         * Mensaje principal.
         */
        JPanel bienvenida =
                new JPanel();

        bienvenida.setLayout(
                new BoxLayout(
                        bienvenida,
                        BoxLayout.Y_AXIS
                )
        );

        bienvenida.setOpaque(false);


        JLabel mensaje =
                new JLabel(
                        "<html><div style='text-align:center;'>"
                                + "Bienvenidos a nuestro proyecto"
                                + "</div></html>"
                );

        mensaje.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        mensaje.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        27
                )
        );

        mensaje.setForeground(
                COLOR_ACENTO
        );


        bienvenida.add(
                mensaje
        );


        bienvenida.add(
                Box.createVerticalStrut(
                        12
                )
        );


        JLabel descripcion =
                new JLabel(
                        "<html><div style='text-align:center;'>"
                                + "Aplicación desarrollada para la gestión, "
                                + "búsqueda, ordenamiento y análisis "
                                + "del catálogo de productos."
                                + "</div></html>"
                );

        descripcion.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        descripcion.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        descripcion.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        15
                )
        );

        descripcion.setForeground(
                COLOR_SECUNDARIO
        );


        bienvenida.add(
                descripcion
        );


        panel.add(
                bienvenida,
                BorderLayout.NORTH
        );


        /*
         * Integrantes.
         */
        panel.add(
                crearPanelIntegrantes(),
                BorderLayout.CENTER
        );


        /*
         * Funcionalidades.
         */
        panel.add(
                crearPanelFunciones(),
                BorderLayout.SOUTH
        );


        return panel;
    }


    // ==================
    // INTEGRANTES
    // ==================

    private JPanel crearPanelIntegrantes() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                25,
                                0
                        )
                );

        panel.setOpaque(false);


        panel.add(
                crearTarjetaIntegrante(
                        "LORENA VEGA"
                )
        );


        panel.add(
                crearTarjetaIntegrante(
                        "GABRIEL BARRIA"
                )
        );


        return panel;
    }


    // ==========================
    // TARJETA INTEGRANTE
    // =========================

    private JPanel crearTarjetaIntegrante(
            String nombre
    ) {

        JPanel tarjeta =
                new JPanel(
                        new GridBagLayout()
                );

        tarjeta.setBackground(
                new Color(
                        249,
                        250,
                        251
                )
        );

        tarjeta.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        229,
                                        231,
                                        235
                                )
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );


        JLabel icono =
                new JLabel(
                        "👤"
                );

        icono.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        28
                )
        );


        JLabel nombreLabel =
                new JLabel(
                        nombre
                );

        nombreLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        nombreLabel.setForeground(
                COLOR_PRIMARIO
        );


        JLabel rol =
                new JLabel(
                        "Integrante del proyecto"
                );

        rol.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        rol.setForeground(
                COLOR_SECUNDARIO
        );


        JPanel textos =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        textos.setOpaque(false);

        textos.add(
                nombreLabel
        );

        textos.add(
                rol
        );


        JPanel contenido =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                0
                        )
                );

        contenido.setOpaque(false);

        contenido.add(
                icono
        );

        contenido.add(
                textos
        );


        tarjeta.add(
                contenido
        );


        return tarjeta;
    }


    // ======================
    // FUNCIONALIDADES
    // ======================

    private JPanel crearPanelFunciones() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                0
                        )
                );

        panel.setOpaque(false);


        panel.add(
                crearFuncion(
                        "Ordenamiento",
                        "Merge, Quick y Heap Sort"
                )
        );


        panel.add(
                crearFuncion(
                        "Búsquedas",
                        "Lineal y Binaria"
                )
        );


        panel.add(
                crearFuncion(
                        "Productos",
                        "Gestión del catálogo"
                )
        );


        panel.add(
                crearFuncion(
                        "Dashboard",
                        "Análisis y gráficos"
                )
        );


        return panel;
    }


    // ==============================
    // TARJETA DE FUNCIÓN
    // =============================

    private JPanel crearFuncion(
            String titulo,
            String descripcion
    ) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        229,
                                        231,
                                        235
                                )
                        ),
                        new EmptyBorder(
                                15,
                                12,
                                15,
                                12
                        )
                )
        );


        JLabel lblTitulo =
                new JLabel(
                        titulo
                );

        lblTitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        lblTitulo.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        lblTitulo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        lblTitulo.setForeground(
                COLOR_ACENTO
        );


        JLabel lblDescripcion =
                new JLabel(
                        "<html><div style='text-align:center;'>"
                                + descripcion
                                + "</div></html>"
                );

        lblDescripcion.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        lblDescripcion.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        lblDescripcion.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        lblDescripcion.setForeground(
                COLOR_SECUNDARIO
        );


        panel.add(
                lblTitulo
        );

        panel.add(
                Box.createVerticalStrut(
                        5
                )
        );

        panel.add(
                lblDescripcion
        );


        return panel;
    }


    // ====================
    // PIE
    // ===================

    private JPanel crearPie() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setOpaque(false);


        JLabel texto =
                new JLabel(
                        "Proyecto Final • Algoritmos y Estructuras de datos"
                );

        texto.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        texto.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        texto.setForeground(
                COLOR_SECUNDARIO
        );


        panel.add(
                texto,
                BorderLayout.CENTER
        );


        return panel;
    }
}
