package com.example.ui.panels;

import com.example.model.Producto;
import com.example.servicio.ProductoService;
import com.example.ui.ProductoTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.List;


/*
Panel encargado de realizar búsquedas dentro del catálogo de productos.
 *
-Permite buscar:
  - ID mediante búsqueda binaria.
  - ID mediante búsqueda lineal.
  - Nombre mediante búsqueda secuencial.

- Además muestra:
  - Cantidad de resultados.
  - Tiempo de ejecución.
  - Algoritmo utilizado.
 */
public class PanelBusqueda extends JPanel {


    // ========
    // COLORES
    // ========

    private static final Color COLOR_PRIMARIO =
            new Color(35, 32, 80);

    private static final Color COLOR_ACENTO =
            new Color(109, 94, 252);

    private static final Color COLOR_ACENTO_HOVER =
            new Color(84, 70, 220);

    private static final Color COLOR_FONDO =
            new Color(246, 247, 255);

    private static final Color COLOR_PANEL =
            Color.WHITE;

    private static final Color COLOR_BORDE =
            new Color(226, 229, 244);

    private static final Color COLOR_TEXTO_SECUNDARIO =
            new Color(105, 109, 142);

    private static final Color COLOR_EXITO =
            new Color(22, 163, 74);

    private static final Color COLOR_ERROR =
            new Color(220, 38, 38);


    // =========
    // SERVICIO
    // =========
    private final ProductoService productoService;


    // ============
    // COMPONENTES
    // ============

    private JComboBox<String> comboTipoBusqueda;

    private JTextField txtBusqueda;

    private JButton btnBuscar;

    private JButton btnLimpiar;

    private JLabel lblResultado;

    private JLabel lblTiempo;

    private JLabel lblAlgoritmo;

    private JTable tabla;

    private ProductoTableModel modelo;


    // ============
    // CONSTRUCTOR
    // ============

    public PanelBusqueda(
            ProductoService productoService
    ) {

        this.productoService =
                productoService;

        inicializar();
    }


    // ===============
    // INICIALIZACIÓN
    // ===============

    private void inicializar() {

        setLayout(
                new BorderLayout(
                        0,
                        20
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

        add(
                crearTitulo(),
                BorderLayout.NORTH
        );

        add(
                crearCentro(),
                BorderLayout.CENTER
        );

        configurarEventosTeclado();
    }


    // =======
    // TÍTULO
    // =======

    private JPanel crearTitulo() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setOpaque(false);


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
                        "Búsqueda de productos"
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
                        "Busca productos por ID o nombre y analiza el rendimiento."
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


        return panel;
    }


    // =======
    // CENTRO
    // =======

    private JPanel crearCentro() {

        JPanel principal =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        principal.setOpaque(false);


        principal.add(
                crearPanelBusqueda(),
                BorderLayout.NORTH
        );


        principal.add(
                crearPanelResultados(),
                BorderLayout.CENTER
        );


        return principal;
    }


    // ==================
    // PANEL DE BÚSQUEDA
    // ==================

    private JPanel crearPanelBusqueda() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                15,
                                10
                        )
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
                                18,
                                20,
                                18,
                                20
                        )
                )
        );


        // ==========
        // CONTROLES
        // ==========
        
        JPanel controles =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                5
                        )
                );

        controles.setOpaque(false);


        // -----------------
        // TIPO DE BÚSQUEDA
        // -----------------

        JLabel lblTipo =
                new JLabel(
                        "Tipo de búsqueda:"
                );

        lblTipo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );


        comboTipoBusqueda =
                new JComboBox<>(
                        new String[]{
                                "Buscar por ID Binaria",
                                "Buscar por ID Lineal",
                                "Buscar por Nombre"
                        }
                );

        comboTipoBusqueda.setPreferredSize(
                new Dimension(
                        205,
                        35
                )
        );


        // ---------
        // CAMPO
        // ---------

        JLabel lblBuscar =
                new JLabel(
                        "Buscar:"
                );

        lblBuscar.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );


        txtBusqueda =
                new JTextField();

        txtBusqueda.setPreferredSize(
                new Dimension(
                        200,
                        35
                )
        );


        // -------------
        // BOTÓN BUSCAR
        // -------------

        btnBuscar =
                crearBoton(
                        "🔎 Buscar"
                );


        // --------------
        // BOTÓN LIMPIAR
        // --------------

        btnLimpiar =
                new JButton(
                        "✕ Limpiar"
                );

        btnLimpiar.setPreferredSize(
                new Dimension(
                        120,
                        35
                )
        );

        btnLimpiar.setBackground(
                Color.WHITE
        );

        btnLimpiar.setForeground(
                COLOR_PRIMARIO
        );

        btnLimpiar.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        btnLimpiar.setFocusPainted(
                false
        );

        btnLimpiar.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        btnLimpiar.setBorder(
                BorderFactory.createLineBorder(
                        COLOR_BORDE
                )
        );


        controles.add(
                lblTipo
        );

        controles.add(
                comboTipoBusqueda
        );

        controles.add(
                lblBuscar
        );

        controles.add(
                txtBusqueda
        );

        controles.add(
                btnBuscar
        );

        controles.add(
                btnLimpiar
        );


        panel.add(
                controles,
                BorderLayout.CENTER
        );


        // =============
        // INFORMACIÓN
        // =============

        JPanel informacion =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                15,
                                0
                        )
                );

        informacion.setOpaque(false);


        lblAlgoritmo =
                crearEtiquetaInformacion(
                        "Algoritmo: --"
                );


        lblTiempo =
                crearEtiquetaInformacion(
                        "Tiempo: --"
                );


        informacion.add(
                lblAlgoritmo
        );

        informacion.add(
                lblTiempo
        );


        panel.add(
                informacion,
                BorderLayout.SOUTH
        );


        // =========
        // EVENTOS
        // =========

        btnBuscar.addActionListener(
                e -> buscar()
        );


        btnLimpiar.addActionListener(
                e -> limpiar()
        );


        return panel;
    }


    // ================
    // BOTÓN PRINCIPAL
    // ================

    private JButton crearBoton(
            String texto
    ) {

        JButton boton =
                new JButton(
                        texto
                );

        boton.setPreferredSize(
                new Dimension(
                        125,
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
                BorderFactory.createEmptyBorder(
                        5,
                        12,
                        5,
                        12
                )
        );

        boton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        boton.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e
                    ) {

                        boton.setBackground(
                                COLOR_ACENTO_HOVER
                        );
                    }


                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent e
                    ) {

                        boton.setBackground(
                                COLOR_ACENTO
                        );
                    }
                }
        );


        return boton;
    }


    // ========================
    // ETIQUETA DE INFORMACIÓN
    // ========================

    private JLabel crearEtiquetaInformacion(
            String texto
    ) {

        JLabel label =
                new JLabel(
                        texto
                );

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        label.setForeground(
                COLOR_TEXTO_SECUNDARIO
        );

        return label;
    }


    // ====================
    // PANEL DE RESULTADOS
    // ====================

    private JPanel crearPanelResultados() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0,
                                10
                        )
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
                                15,
                                15,
                                15,
                                15
                        )
                )
        );


        // ============
        // ENCABEZADO
        // ============

        JPanel encabezado =
                new JPanel(
                        new BorderLayout()
                );

        encabezado.setOpaque(false);


        JLabel titulo =
                new JLabel(
                        "Resultados de búsqueda"
                );

        titulo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        titulo.setForeground(
                COLOR_PRIMARIO
        );


        lblResultado =
                new JLabel(
                        "Esperando búsqueda..."
                );

        lblResultado.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        lblResultado.setForeground(
                COLOR_TEXTO_SECUNDARIO
        );


        encabezado.add(
                titulo,
                BorderLayout.WEST
        );

        encabezado.add(
                lblResultado,
                BorderLayout.EAST
        );


        panel.add(
                encabezado,
                BorderLayout.NORTH
        );


        // ========
        // TABLA
        // ========

        modelo =
                new ProductoTableModel();


        tabla =
                new JTable(
                        modelo
                );


        configurarTabla();


        JScrollPane scroll =
                new JScrollPane(
                        tabla
                );

        scroll.setBorder(
                BorderFactory.createLineBorder(
                        COLOR_BORDE
                )
        );


        panel.add(
                scroll,
                BorderLayout.CENTER
        );


        return panel;
    }


    // ==================
    // CONFIGURAR TABLA
    // ==================

    private void configurarTabla() {

        tabla.setRowHeight(
                30
        );

        tabla.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        tabla.getTableHeader().setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        tabla.getTableHeader().setBackground(
                COLOR_PRIMARIO
        );

        tabla.getTableHeader().setForeground(
                Color.WHITE
        );

        tabla.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tabla.setGridColor(
                COLOR_BORDE
        );

        tabla.setShowVerticalLines(
                false
        );


        /*
         * Alineación centrada para algunas columnas.
         */
        DefaultTableCellRenderer centrado =
                new DefaultTableCellRenderer();

        centrado.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        if (tabla.getColumnCount() > 0) {

            tabla.getColumnModel()
                    .getColumn(0)
                    .setCellRenderer(
                            centrado
                    );
        }
    }


    // =============
    // BÚSQUEDA
    // =============

    private void buscar() {

        String texto =
                txtBusqueda
                        .getText()
                        .trim();


        // ----------------
        // VALIDACIÓN
        // ----------------

        if (texto.isEmpty()) {

            limpiarResultados();

            lblResultado.setText(
                    "Ingrese un dato para buscar."
            );

            lblResultado.setForeground(
                    COLOR_ERROR
            );

            txtBusqueda.requestFocus();

            return;
        }


        String tipo =
                String.valueOf(
                        comboTipoBusqueda
                                .getSelectedItem()
                );


        try {

            long inicio =
                    System.nanoTime();


            List<Producto> encontrados;


            // =====================
            // BÚSQUEDA POR NOMBRE
            // =====================

            if (tipo.equals(
                    "Buscar por Nombre"
            )) {

                encontrados =
                        productoService.buscarPorNombre(
                                texto
                        );


                lblAlgoritmo.setText(
                        "Algoritmo: Búsqueda secuencial por nombre"
                );


            }

            // ===================
            // BÚSQUEDA POR ID
            // ===================

            else {

                int id;


                try {

                    id =
                            Integer.parseInt(
                                    texto
                            );

                } catch (NumberFormatException ex) {

                    limpiarResultados();

                    lblResultado.setText(
                            "El ID debe ser un número entero."
                    );

                    lblResultado.setForeground(
                            COLOR_ERROR
                    );

                    txtBusqueda.requestFocus();

                    return;
                }


                Producto producto;


                // ------------
                // BINARIA
                // ------------

                if (tipo.contains(
                        "Binaria"
                )) {

                    producto =
                            productoService.buscarPorId(
                                    id
                            );


                    lblAlgoritmo.setText(
                            "Algoritmo: Búsqueda binaria"
                    );

                }

                // -----------
                // LINEAL
                // -----------

                else {

                    producto =
                            productoService.buscarPorIdLineal(
                                    id
                            );


                    lblAlgoritmo.setText(
                            "Algoritmo: Búsqueda lineal"
                    );
                }


                if (producto != null) {

                    encontrados =
                            List.of(
                                    producto
                            );

                } else {

                    encontrados =
                            Collections.emptyList();
                }
            }


            long fin =
                    System.nanoTime();


            long tiempo =
                    fin - inicio;


            // ===================
            // MOSTRAR RESULTADOS
            // ===================

            modelo.setProductos(
                    encontrados
            );


            lblTiempo.setText(
                    String.format(
                            "Tiempo: %d ns (%.6f ms)",
                            tiempo,
                            tiempo / 1_000_000.0
                    )
            );


            if (encontrados.isEmpty()) {

                lblResultado.setText(
                        "No se encontraron productos."
                );

                lblResultado.setForeground(
                        COLOR_ERROR
                );

            } else {

                lblResultado.setText(
                        "Encontrados: "
                                + encontrados.size()
                );

                lblResultado.setForeground(
                        COLOR_EXITO
                );
            }


        } catch (Exception ex) {

            limpiarResultados();


            lblResultado.setText(
                    "Error durante la búsqueda."
            );

            lblResultado.setForeground(
                    COLOR_ERROR
            );


            JOptionPane.showMessageDialog(
                    this,
                    "Ocurrió un error durante la búsqueda:\n\n"
                            + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );


            ex.printStackTrace();
        }
    }


    // ===========
    // LIMPIAR
    // ===========

    private void limpiar() {

        txtBusqueda.setText(
                ""
        );


        limpiarResultados();


        lblResultado.setText(
                "Esperando búsqueda..."
        );

        lblResultado.setForeground(
                COLOR_TEXTO_SECUNDARIO
        );


        lblAlgoritmo.setText(
                "Algoritmo: --"
        );


        lblTiempo.setText(
                "Tiempo: --"
        );


        txtBusqueda.requestFocus();
    }


    // =====================
    // LIMPIAR RESULTADOS
    // =====================

    private void limpiarResultados() {

        modelo.setProductos(
                Collections.emptyList()
        );
    }


    // =====================
    // ENTER PARA BUSCAR
    // =====================

    private void configurarEventosTeclado() {

        txtBusqueda.addKeyListener(
                new KeyAdapter() {

                    @Override
                    public void keyPressed(
                            KeyEvent e
                    ) {

                        if (e.getKeyCode()
                                == KeyEvent.VK_ENTER) {

                            buscar();
                        }
                    }
                }
        );
    }
}
