package com.example.ui.panels;

import com.example.algoritmos.sorting.HeapSort;
import com.example.algoritmos.sorting.MergeSort;
import com.example.algoritmos.sorting.QuickSort;
import com.example.comparador.CalificacionComparador;
import com.example.comparador.IdComparador;
import com.example.comparador.PrecioComparador;
import com.example.interfaces.SortingAlgoritmo;
import com.example.model.Producto;
import com.example.servicio.ProductoService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;

/* Vista para ejecutar y comparar los algoritmos de ordenamiento. */
public class PanelAlgoritmos extends JPanel {

    private static final Color COLOR_PRIMARIO = new Color(35, 32, 80);
    private static final Color COLOR_ACENTO = new Color(109, 94, 252);
    private static final Color COLOR_ACENTO_HOVER = new Color(84, 70, 220);
    private static final Color COLOR_FONDO = new Color(246, 247, 255);
    private static final Color COLOR_PANEL = Color.WHITE;
    private static final Color COLOR_BORDE = new Color(226, 229, 244);
    private static final Color COLOR_TEXTO_SECUNDARIO = new Color(105, 109, 142);
    private static final Color COLOR_EXITO = new Color(18, 143, 102);
    private static final Color COLOR_ERROR = new Color(213, 64, 84);

    private final ProductoService productoService;
    private JComboBox<String> comboAlgoritmo;
    private JComboBox<String> comboCriterio;
    private JButton btnEjecutar;
    private JButton btnComparar;
    private JLabel lblEstado;
    private JLabel lblTiempo;
    private JLabel lblCantidad;
    private JLabel lblMejorAlgoritmo;
    private JLabel lblMejorTiempo;
    private JLabel lblCriterioInfo;
    private DefaultCategoryDataset datasetTiempos;

    public PanelAlgoritmos(ProductoService productoService) {
        this.productoService = productoService;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(0, 18));
        setBackground(COLOR_FONDO);
        setBorder(new EmptyBorder(26, 30, 26, 30));
        add(crearEncabezado(), BorderLayout.NORTH);
        add(crearContenido(), BorderLayout.CENTER);
        registrarEventos();
    }

    private JPanel crearEncabezado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JPanel textos = new JPanel();
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.setOpaque(false);

        JLabel titulo = new JLabel("Algoritmos de ordenamiento");
        titulo.setForeground(COLOR_PRIMARIO);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 27));

        JLabel descripcion = new JLabel("Ejecuta, compara y visualiza el rendimiento con datos reales de tu catálogo.");
        descripcion.setForeground(COLOR_TEXTO_SECUNDARIO);
        descripcion.setFont(new Font("SansSerif", Font.PLAIN, 13));

        textos.add(titulo);
        textos.add(Box.createVerticalStrut(5));
        textos.add(descripcion);
        panel.add(textos, BorderLayout.WEST);

        JLabel etiqueta = new JLabel("  ANÁLISIS DE RENDIMIENTO  ");
        etiqueta.setOpaque(true);
        etiqueta.setBackground(new Color(235, 232, 255));
        etiqueta.setForeground(COLOR_ACENTO_HOVER);
        etiqueta.setFont(new Font("SansSerif", Font.BOLD, 11));
        etiqueta.setBorder(new EmptyBorder(7, 9, 7, 9));
        panel.add(etiqueta, BorderLayout.EAST);
        return panel;
    }

    private JPanel crearContenido() {
        JPanel panel = new JPanel(new BorderLayout(0, 18));
        panel.setOpaque(false);
        panel.add(crearPanelConfiguracion(), BorderLayout.NORTH);
        panel.add(crearPanelResultados(), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelConfiguracion() {
        JPanel panel = crearTarjetaBase(new GridBagLayout(), new EmptyBorder(17, 20, 17, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 7, 4, 7);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 0;

        gbc.gridx = 0;
        panel.add(crearEtiquetaCampo("Algoritmo"), gbc);
        gbc.gridx = 1;
        comboAlgoritmo = crearCombo(new String[]{"Merge Sort", "Quick Sort", "Heap Sort"});
        panel.add(comboAlgoritmo, gbc);

        gbc.gridx = 2;
        panel.add(crearEtiquetaCampo("Ordenar por"), gbc);
        gbc.gridx = 3;
        comboCriterio = crearCombo(new String[]{"Precio", "Calificación", "ID"});
        panel.add(comboCriterio, gbc);

        gbc.gridx = 4;
        btnEjecutar = crearBoton("▶  Ejecutar", COLOR_ACENTO);
        panel.add(btnEjecutar, gbc);
        gbc.gridx = 5;
        btnComparar = crearBoton("⚡  Comparar", new Color(42, 161, 132));
        panel.add(btnComparar, gbc);
        return panel;
    }

    private JLabel crearEtiquetaCampo(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(COLOR_PRIMARIO);
        etiqueta.setFont(new Font("SansSerif", Font.BOLD, 12));
        return etiqueta;
    }

    private JComboBox<String> crearCombo(String[] opciones) {
        JComboBox<String> combo = new JComboBox<>(opciones);
        combo.setPreferredSize(new Dimension(160, 34));
        combo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        combo.setBackground(Color.WHITE);
        combo.setForeground(COLOR_PRIMARIO);
        combo.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));
        combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return combo;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(137, 34));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("SansSerif", Font.BOLD, 12));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(6, 13, 6, 13));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseEntered(java.awt.event.MouseEvent e) {
                boton.setBackground(color.equals(COLOR_ACENTO) ? COLOR_ACENTO_HOVER : new Color(24, 130, 105));
            }
            @Override public void mouseExited(java.awt.event.MouseEvent e) { boton.setBackground(color); }
        });
        return boton;
    }

    private JPanel crearPanelResultados() {
        JPanel principal = new JPanel(new BorderLayout(0, 18));
        principal.setOpaque(false);

        JPanel tarjetas = new JPanel(new GridLayout(1, 3, 14, 0));
        tarjetas.setOpaque(false);
        lblCantidad = crearValor("0");
        lblEstado = crearValor("Listo para ejecutar");
        lblTiempo = crearValor("--");
        tarjetas.add(crearTarjetaResultado("PRODUCTOS EN CATÁLOGO", lblCantidad, new Color(237, 241, 255)));
        tarjetas.add(crearTarjetaResultado("ÚLTIMA EJECUCIÓN", lblEstado, new Color(240, 253, 248)));
        tarjetas.add(crearTarjetaResultado("TIEMPO DE EJECUCIÓN", lblTiempo, new Color(255, 248, 235)));
        principal.add(tarjetas, BorderLayout.NORTH);
        principal.add(crearPanelComparacion(), BorderLayout.CENTER);
        return principal;
    }

    private JPanel crearPanelComparacion() {
        JPanel panel = crearTarjetaBase(new BorderLayout(0, 14), new EmptyBorder(19, 20, 18, 20));

        JPanel encabezado = new JPanel();
        encabezado.setLayout(new BoxLayout(encabezado, BoxLayout.Y_AXIS));
        encabezado.setOpaque(false);
        JLabel titulo = new JLabel("Comparación visual de tiempos");
        titulo.setForeground(COLOR_PRIMARIO);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        JLabel descripcion = new JLabel("Los valores se actualizan al comparar los tres algoritmos con el mismo criterio.");
        descripcion.setForeground(COLOR_TEXTO_SECUNDARIO);
        descripcion.setFont(new Font("SansSerif", Font.PLAIN, 12));
        encabezado.add(titulo);
        encabezado.add(Box.createVerticalStrut(4));
        encabezado.add(descripcion);
        panel.add(encabezado, BorderLayout.NORTH);

        JPanel contenido = new JPanel(new BorderLayout(0, 12));
        contenido.setOpaque(false);
        JPanel resumen = new JPanel(new GridLayout(1, 4, 10, 0));
        resumen.setOpaque(false);
        lblMejorAlgoritmo = crearValor("Sin ejecutar");
        lblMejorTiempo = crearValor("--");
        lblCriterioInfo = crearValor("Seleccione un criterio");
        resumen.add(crearTarjetaResultado("MEJOR ALGORITMO", lblMejorAlgoritmo, new Color(240, 253, 248)));
        resumen.add(crearTarjetaResultado("MEJOR TIEMPO", lblMejorTiempo, new Color(237, 241, 255)));
        resumen.add(crearTarjetaResultado("ALGORITMOS", crearValor("Merge · Quick · Heap"), new Color(249, 247, 255)));
        resumen.add(crearTarjetaResultado("CRITERIO", lblCriterioInfo, new Color(255, 248, 235)));
        contenido.add(resumen, BorderLayout.NORTH);
        contenido.add(crearGraficaTiempos(), BorderLayout.CENTER);
        panel.add(contenido, BorderLayout.CENTER);
        return panel;
    }

    private ChartPanel crearGraficaTiempos() {
        datasetTiempos = new DefaultCategoryDataset();
        JFreeChart grafica = ChartFactory.createBarChart(
                null, "Algoritmo", "Tiempo (ms)", datasetTiempos,
                PlotOrientation.VERTICAL, false, true, false);
        grafica.setBackgroundPaint(COLOR_PANEL);

        CategoryPlot plot = grafica.getCategoryPlot();
        plot.setBackgroundPaint(new Color(252, 252, 255));
        plot.setRangeGridlinePaint(COLOR_BORDE);
        plot.setOutlineVisible(false);
        plot.setNoDataMessage("Presiona «Comparar» para ver los tiempos de ejecución.");
        plot.setNoDataMessageFont(new Font("SansSerif", Font.PLAIN, 13));
        plot.setNoDataMessagePaint(COLOR_TEXTO_SECUNDARIO);
        plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 11));
        plot.getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 11));
        plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 11));
        plot.getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));
        plot.getRangeAxis().setStandardTickUnits(org.jfree.chart.axis.NumberAxis.createStandardTickUnits());

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, COLOR_ACENTO);
        renderer.setDrawBarOutline(false);
        renderer.setMaximumBarWidth(0.16);
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelFont(new Font("SansSerif", Font.BOLD, 10));
        renderer.setDefaultItemLabelPaint(COLOR_PRIMARIO);
        DecimalFormat formato = new DecimalFormat("0.000000");
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2} ms", formato));
        renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator("{1}: {2} ms", formato));

        ChartPanel chartPanel = new ChartPanel(grafica);
        chartPanel.setPreferredSize(new Dimension(650, 230));
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setBackground(COLOR_PANEL);
        chartPanel.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));
        return chartPanel;
    }

    private JPanel crearTarjetaBase(LayoutManager layout, EmptyBorder padding) {
        JPanel panel = new JPanel(layout);
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOR_BORDE), padding));
        return panel;
    }

    private JPanel crearTarjetaResultado(String titulo, JLabel valor, Color fondo) {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 2));
        panel.setBackground(fondo);
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOR_BORDE), new EmptyBorder(9, 10, 9, 10)));
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 10));
        lblTitulo.setForeground(COLOR_TEXTO_SECUNDARIO);
        valor.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblTitulo);
        panel.add(valor);
        return panel;
    }

    private JLabel crearValor(String texto) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setForeground(COLOR_PRIMARIO);
        return label;
    }

    private void registrarEventos() {
        btnEjecutar.addActionListener(e -> ejecutarOrdenamiento());
        btnComparar.addActionListener(e -> compararAlgoritmos());
    }

    private void ejecutarOrdenamiento() {
        List<Producto> productos = productoService.getProductos();
        if (productos == null || productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primero debes generar los productos.", "Catálogo vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            SortingAlgoritmo algoritmo = obtenerAlgoritmo();
            Comparator<Producto> comparador = obtenerComparador();
            lblCantidad.setText(String.valueOf(productos.size()));
            long tiempo = productoService.ordenar(algoritmo, comparador);
            lblEstado.setText(algoritmo.getNombre());
            lblEstado.setForeground(COLOR_EXITO);
            lblTiempo.setText(String.format("%d ns (%.6f ms)", tiempo, tiempo / 1_000_000.0));
        } catch (Exception ex) {
            lblEstado.setText("Error");
            lblEstado.setForeground(COLOR_ERROR);
            JOptionPane.showMessageDialog(this, "No fue posible ejecutar el algoritmo.\n\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void compararAlgoritmos() {
        List<Producto> productos = productoService.getProductos();
        if (productos == null || productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primero debes generar los productos.", "Catálogo vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Comparator<Producto> comparador = obtenerComparador();
            long tiempoMerge = productoService.ordenar(new MergeSort(), comparador);
            long tiempoQuick = productoService.ordenar(new QuickSort(), comparador);
            long tiempoHeap = productoService.ordenar(new HeapSort(), comparador);
            long mejorTiempo = Math.min(tiempoMerge, Math.min(tiempoQuick, tiempoHeap));
            String mejorAlgoritmo = mejorTiempo == tiempoMerge ? "Merge Sort" : mejorTiempo == tiempoQuick ? "Quick Sort" : "Heap Sort";

            actualizarGrafica(tiempoMerge, tiempoQuick, tiempoHeap);
            lblMejorAlgoritmo.setText(mejorAlgoritmo);
            lblMejorAlgoritmo.setForeground(COLOR_EXITO);
            lblMejorTiempo.setText(String.format("%.6f ms", mejorTiempo / 1_000_000.0));
            lblCriterioInfo.setText((String) comboCriterio.getSelectedItem());
            lblCantidad.setText(String.valueOf(productos.size()));
            lblEstado.setText("Comparación realizada");
            lblEstado.setForeground(COLOR_EXITO);
            lblTiempo.setText(String.format("Mejor: %.6f ms", mejorTiempo / 1_000_000.0));

            JOptionPane.showMessageDialog(this,
                    "Comparación completada.\n\n"
                            + "Merge Sort: " + tiempoMerge + " ns\n"
                            + "Quick Sort: " + tiempoQuick + " ns\n"
                            + "Heap Sort: " + tiempoHeap + " ns\n\n"
                            + "Mejor algoritmo: " + mejorAlgoritmo,
                    "Resultados", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No fue posible comparar los algoritmos.\n\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void actualizarGrafica(long merge, long quick, long heap) {
        datasetTiempos.clear();
        datasetTiempos.addValue(merge / 1_000_000.0, "Tiempo", "Merge Sort");
        datasetTiempos.addValue(quick / 1_000_000.0, "Tiempo", "Quick Sort");
        datasetTiempos.addValue(heap / 1_000_000.0, "Tiempo", "Heap Sort");
    }

    private SortingAlgoritmo obtenerAlgoritmo() {
        String seleccionado = (String) comboAlgoritmo.getSelectedItem();
        return switch (seleccionado) {
            case "Quick Sort" -> new QuickSort();
            case "Heap Sort" -> new HeapSort();
            default -> new MergeSort();
        };
    }

    private Comparator<Producto> obtenerComparador() {
        String seleccionado = (String) comboCriterio.getSelectedItem();
        return switch (seleccionado) {
            case "Calificación" -> new CalificacionComparador();
            case "ID" -> new IdComparador();
            default -> new PrecioComparador();
        };
    }
}
