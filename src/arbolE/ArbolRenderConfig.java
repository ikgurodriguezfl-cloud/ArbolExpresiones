package arbolE;

import java.awt.Color;

public class ArbolRenderConfig {
    private Color colorNodo;
    private Color colorNodoBorde;
    private Color colorLinea;
    private int anchoBorde;
    private int anchoLinea;

    public ArbolRenderConfig(Color colorNodo, Color colorNodoBorde, Color colorLinea,
            int anchoBorde, int anchoLinea) {
        this.colorNodo = colorNodo;
        this.colorNodoBorde = colorNodoBorde;
        this.colorLinea = colorLinea;
        this.anchoBorde = anchoBorde;
        this.anchoLinea = anchoLinea;
    }

    public static ArbolRenderConfig defaultConfig() {
        return new ArbolRenderConfig(new Color(255, 210, 210), new Color(150, 20, 20), new Color(190, 40, 40), 30, 2);
    }

    public ArbolRenderConfig copy() {
        return new ArbolRenderConfig(colorNodo, colorNodoBorde, colorLinea, anchoBorde, anchoLinea);
    }

    public Color getColorNodo() {
        return colorNodo;
    }

    public void setColorNodo(Color colorNodo) {
        this.colorNodo = colorNodo;
    }

    public Color getColorNodoBorde() {
        return colorNodoBorde;
    }

    public void setColorNodoBorde(Color colorNodoBorde) {
        this.colorNodoBorde = colorNodoBorde;
    }

    public Color getColorLinea() {
        return colorLinea;
    }

    public void setColorLinea(Color colorLinea) {
        this.colorLinea = colorLinea;
    }

    public int getAnchoBorde() {
        return anchoBorde;
    }

    public void setAnchoBorde(int anchoBorde) {
        this.anchoBorde = anchoBorde;
    }

    public int getAnchoLinea() {
        return anchoLinea;
    }

    public void setAnchoLinea(int anchoLinea) {
        this.anchoLinea = anchoLinea;
    }
}