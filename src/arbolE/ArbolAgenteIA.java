/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author iker_
 */
public class ArbolAgenteIA {
    Stack<Nodo> arbolNodo;
    Stack<String> caracter;
        //Identificar entre OPERADOR y OPERANDOS
    final String espacios = "\t";
    final String aritmeticos = "+-*/()^=";
    final String variables = "abcdefghijklmnopqrstuvwxyz";
    final String opMultiplica = "*";
    String reglaSemantica, r;
    String emu86;
    
    private Nodo raiz;
    String[] temporales = {"T1", "T2", "T3", "T4", "T5"};
    
    HashMap<String, String> tablaSimbolos;
    String valor = "";
    HashMap<String, String> erroresSemanticos;
    HashMap<String, String> producciones;
    int paso;
    ArrayList<String> reglasEjecutadas;

    public ArbolAgenteIA() {
        emu86 = "; RODRIGUEZ FLORES IKER GUSTAVO \n"
                + ".MODEL SMALL \n"
                + ".STACK \n"
                + ".DATA \n";
        reglasEjecutadas = new ArrayList<String>();
        arbolNodo = new Stack<Nodo>();
        caracter = new Stack<String>();
        tablaSimbolos = new HashMap();
        erroresSemanticos = new HashMap();
        producciones = new HashMap();
        paso = 0;
        reglaSemantica = "";
        r = "";
    }

    public String getReglasEjecutadas() {
        String reglasE = "";
        for (int i = 0; i < reglasEjecutadas.size(); i++) {
            reglasE += reglasEjecutadas.get(i) + "\n";
        }
        return reglasE;
    }
    
    public void agregaValex(String lexema, String valor) {
        
    }//agrega valex - analisis semantico
    
    public String regresaValex(String lexema) {
        return this.tablaSimbolos.get(lexema);
    }// regresaVales
    
    public void guardar() {
        if (arbolNodo.size() < 2 || caracter.empty()) return;
        paso++;
        r = "r" + paso;

        Nodo derecho = arbolNodo.pop();
        Nodo izquierdo = arbolNodo.pop();
        String operador = caracter.pop();

        String valorSintetizado = sintetizarValor(operador,
                izquierdo != null ? izquierdo.getValor() : null,
                derecho != null ? derecho.getValor() : null);
        arbolNodo.push(new Nodo(derecho, operador, izquierdo, valorSintetizado));
        
        String reglaE = "E.nodo = new Nodo(" + operador + ", E1.nodo, T.nodo)";
        reglasEjecutadas.add("P" + paso + " " + reglaE);
    }//guardar
    
    public Nodo crear(String expresion) {
        StringTokenizer tokenizer;
        paso = 0;
        String token;

        tokenizer = new StringTokenizer(expresion, espacios + aritmeticos, true);
        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();

            if (espacios.contains(token)) {
                continue;
            } else if (!aritmeticos.contains(token)) {
                arbolNodo.push(new Nodo(token));
                
                paso++;
                String regla = "T.nodo = new Hoja(id<" + token + ">,id.entrada_" + token + ")";
                reglasEjecutadas.add("P" + paso + " " + regla);
                

                valor = solicitarValorEntero(token);
                tablaSimbolos.put(token, valor);
                arbolNodo.peek().setValor(valor);
                emu86+= token+" dw "+valor+"\n";
            } else if (token.equals("(")) {
                caracter.push(token);
            } else if (token.equals(")")) {
                while (!caracter.empty() && !caracter.peek().equals("(")) {
                    guardar();
                }
                if (!caracter.empty()) {
                    caracter.pop();
                }
            } else {
                while (!caracter.empty() && !caracter.peek().equals("(")
                        && obtenerPrioridad(caracter.peek()) >= obtenerPrioridad(token)) {
                    guardar();
                }
                caracter.push(token);
            }
        }

        while (!caracter.empty()) {
            if (caracter.peek().equals("(")) {
                caracter.pop();
            } else {
                guardar();
                raiz = arbolNodo.peek();
            }
        }
        return raiz;
    }//Nodo crear
    
    private int obtenerPrioridad(String operador) {
        switch (operador) {
            case "^":
                return 3;
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            case "=":
                return 0;
            default:
                return -1;
        }
    }

    public Nodo convertirAGAD(Nodo raizAST) {
        HashMap<String, Nodo> tabla = new HashMap<>();
        return convertir(raizAST, tabla);
    }

    private Nodo convertir(Nodo nodo, HashMap<String, Nodo> tabla) {
        if (nodo == null) {
            return null;
        }

        if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
            String clave = "HOJA#" + nodo.getDato() + "#" + nodo.getValor();
            Nodo existente = tabla.get(clave);
            if (existente != null) {
                return existente;
            }
            tabla.put(clave, nodo);
            return nodo;
        }

        Nodo izquierdo = convertir(nodo.getIzquierdo(), tabla);
        Nodo derecho = convertir(nodo.getDerecho(), tabla);
        nodo.setIzquierdo(izquierdo);
        nodo.setDerecho(derecho);

        String clave = nodo.getDato() + "#"
                + System.identityHashCode(izquierdo) + "#"
                + System.identityHashCode(derecho);
        Nodo existente = tabla.get(clave);
        if (existente != null) {
            return existente;
        }

        tabla.put(clave, nodo);
        return nodo;
    }

    public HashMap<String, String> getTablaSimbolos() {
        return tablaSimbolos;
    }

    public void setTablaSimbolos(HashMap<String, String> tablaSimbolos) {
        this.tablaSimbolos = tablaSimbolos;
    }

    private String solicitarValorEntero(String token) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(null, "Inserta el valor de: '" + token + "'.");
            if (entrada == null) {
                continue;
            }

            entrada = entrada.trim();
            if (entrada.isEmpty()) {
                continue;
            }

            try {
                Integer.parseInt(entrada);
                return entrada;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Entrada no válida.\nPor favor, inserta únicamente un número entero.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String sintetizarValor(String operador, String valorIzq, String valorDer) {
        Integer opIzq = convertirEntero(valorIzq);
        Integer opDer = convertirEntero(valorDer);

        if (opIzq == null || opDer == null) {
            return "";
        }

        int resultado;
        switch (operador) {
            case "+":
                resultado = opIzq + opDer;
                break;
            case "-":
                resultado = opIzq - opDer;
                break;
            case "*":
                resultado = opIzq * opDer;
                break;
            case "/":
                if (opDer == 0) {
                    return "Err";
                }
                resultado = opIzq / opDer;
                break;
            case "^":
                resultado = (int) Math.pow(opIzq, opDer);
                break;
            case "=":
                return valorDer;
            default:
                return "";
        }

        return String.valueOf(resultado);
    }

    private Integer convertirEntero(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return null;
        }

        try {
            return Integer.parseInt(valor.trim());
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
