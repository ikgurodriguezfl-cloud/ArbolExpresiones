package arbolE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class DialogoConfigArbol extends JDialog {

    private final ArbolRenderConfig config;
    private final Runnable onConfigChange;

    private JButton btnColorNodo;
    private JButton btnColorBorde;
    private JButton btnColorLinea;
    private JSlider sliderNodo;
    private JSlider sliderLinea;
    private JLabel lblNodo;
    private JLabel lblLinea;

    public DialogoConfigArbol(Frame parent, ArbolRenderConfig configInicial, Runnable onConfigChange) {
        super(parent, "Configuracion del arbol", false);
        this.config = (configInicial != null)
                ? configInicial
                : ArbolRenderConfig.defaultConfig();
        this.onConfigChange = onConfigChange;

        initUi();
        setSize(460, 320);
        setLocationRelativeTo(parent);
    }

    private void initUi() {
        JPanel panelContenido = new JPanel(new GridLayout(5, 1, 8, 8));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        btnColorNodo = crearBotonColor("Color del nodo", config.getColorNodo());
        btnColorBorde = crearBotonColor("Color del borde", config.getColorNodoBorde());
        btnColorLinea = crearBotonColor("Color de la linea", config.getColorLinea());

        panelContenido.add(btnColorNodo);
        panelContenido.add(btnColorBorde);
        panelContenido.add(btnColorLinea);

        JPanel panelSliderNodo = new JPanel(new BorderLayout(8, 0));
        panelSliderNodo.add(new JLabel("Grosor del nodo", SwingConstants.LEFT), BorderLayout.WEST);
        sliderNodo = new JSlider(10, 80, config.getAnchoBorde());
        lblNodo = new JLabel(String.valueOf(config.getAnchoBorde()));
        panelSliderNodo.add(sliderNodo, BorderLayout.CENTER);
        panelSliderNodo.add(lblNodo, BorderLayout.EAST);
        panelContenido.add(panelSliderNodo);

        JPanel panelSliderLinea = new JPanel(new BorderLayout(8, 0));
        panelSliderLinea.add(new JLabel("Grosor de linea", SwingConstants.LEFT), BorderLayout.WEST);
        sliderLinea = new JSlider(1, 20, config.getAnchoLinea());
        lblLinea = new JLabel(String.valueOf(config.getAnchoLinea()));
        panelSliderLinea.add(sliderLinea, BorderLayout.CENTER);
        panelSliderLinea.add(lblLinea, BorderLayout.EAST);
        panelContenido.add(panelSliderLinea);

        JButton btnCerrar = new JButton("Cerrar");
        JButton btnDefault = new JButton("Restablecer");

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnDefault);
        panelBotones.add(btnCerrar);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelContenido, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        btnColorNodo.addActionListener(e -> seleccionarColor(btnColorNodo, "Selecciona color del nodo", 1));
        btnColorBorde.addActionListener(e -> seleccionarColor(btnColorBorde, "Selecciona color del borde", 2));
        btnColorLinea.addActionListener(e -> seleccionarColor(btnColorLinea, "Selecciona color de la linea", 3));

        sliderNodo.addChangeListener(e -> {
            int valor = sliderNodo.getValue();
            lblNodo.setText(String.valueOf(valor));
            config.setAnchoBorde(valor);
            notificarCambio();
        });
        sliderLinea.addChangeListener(e -> {
            int valor = sliderLinea.getValue();
            lblLinea.setText(String.valueOf(valor));
            config.setAnchoLinea(valor);
            notificarCambio();
        });

        btnDefault.addActionListener(e -> aplicarDefault());

        btnCerrar.addActionListener(e -> dispose());
    }

    private JButton crearBotonColor(String titulo, Color color) {
        JButton boton = new JButton(titulo);
        boton.setBackground(color);
        boton.setOpaque(true);
        boton.setForeground(Color.BLACK);
        return boton;
    }

    private void seleccionarColor(JButton boton, String titulo, int tipoColor) {
        Color seleccionado = JColorChooser.showDialog(this, titulo, boton.getBackground());
        if (seleccionado != null) {
            boton.setBackground(seleccionado);
            if (tipoColor == 1) {
                config.setColorNodo(seleccionado);
            } else if (tipoColor == 2) {
                config.setColorNodoBorde(seleccionado);
            } else {
                config.setColorLinea(seleccionado);
            }
            notificarCambio();
        }
    }

    private void aplicarDefault() {
        ArbolRenderConfig base = ArbolRenderConfig.defaultConfig();
        config.setColorNodo(base.getColorNodo());
        config.setColorNodoBorde(base.getColorNodoBorde());
        config.setColorLinea(base.getColorLinea());
        config.setAnchoBorde(base.getAnchoBorde());
        config.setAnchoLinea(base.getAnchoLinea());

        btnColorNodo.setBackground(config.getColorNodo());
        btnColorBorde.setBackground(config.getColorNodoBorde());
        btnColorLinea.setBackground(config.getColorLinea());
        sliderNodo.setValue(config.getAnchoBorde());
        sliderLinea.setValue(config.getAnchoLinea());
        lblNodo.setText(String.valueOf(config.getAnchoBorde()));
        lblLinea.setText(String.valueOf(config.getAnchoLinea()));
        notificarCambio();
    }

    private void notificarCambio() {
        if (onConfigChange != null) {
            onConfigChange.run();
        }
    }
}