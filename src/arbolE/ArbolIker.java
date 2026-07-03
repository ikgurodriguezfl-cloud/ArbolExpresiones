/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author iker_
 * CLASE PARA ARMAR EL ARBOL
 * Nombre : iker gustavo rodriguez flores
 * Parte 1. Analisis Sintactico
 * Parte 2. Analisis semantico
 * Parte 3. Codigo intermedio
 * Parte 4. Codigo objeto
 */
public class ArbolIker {
    Stack<Nodo> arbolNodo;
    Stack<String> caracter;
        //Identificar entre OPERADOR y OPERANDOS
    final String espacios = "\t";
    final String aritmeticos = "+-*/()^=";
    final String variables = "abcdefghijklmnopqrstuvwxyz";
    final String opMultiplica = "*";
    
    private Nodo raiz;
    // 30 dejunio de 2026
    String[] temporales = {"T1","T2","T3","T4","T5"};
    
    HashMap<String, String> tablaSimbolos;
    HashMap<String, String> erroresSemanticos;
    HashMap<String, String> producciones;
    int paso;
    //1o Julio
    ArrayList<String> reglasEjecutadas;
    //constructor
    public ArbolIker() {
        reglasEjecutadas = new ArrayList<String>();
        arbolNodo = new Stack<Nodo>();
        caracter = new Stack<String>();
        tablaSimbolos = new HashMap();
        erroresSemanticos = new HashMap();
        producciones = new HashMap();
        paso = 0;
    }
    //** RREGLAS EJECUTADAS === 1 JULIO
    public String getReglasEjecutadas(){
        String reglasE = "";
        for(int i=0; i< reglasEjecutadas.size();i++){
            System.out.println("Reglas ejecutadas "
                    + reglasEjecutadas.get(i));
            reglasE+= reglasEjecutadas.get(i) + "\n";
        }
        return reglasE;
    }
    
    public void agregaValex(String lexema, String valor){
        
    }//agrega valex - analisis semantico
    
    public String regresaValex(String lexema){return this.tablaSimbolos.get(lexema);}// regresaVales
    
    public void guardar(){//Permite construir el arbol
        paso++;
        Nodo izquierdo = (Nodo) arbolNodo.pop();
        Nodo derecho = (Nodo) arbolNodo.pop();
        
        String operador = caracter.peek();
        //Investigar qué hace el peek 
        /*
        ** En una pila obtiene el elemento de la cima sin eliminarlo, el pop lo elimina
        */
        arbolNodo.push(new Nodo(derecho,caracter.pop(),izquierdo));
        if(operador.equals("+")){//el operador es +
            String reglaE = "E.nodo = new Nodo(+,E1.nodo,T.nodo);";
            reglasEjecutadas.add("P"+paso+" " + reglaE);
            
        }
        if(operador.equals("-")){//el operador es -
            String reglaE = "E.nodo = new Nodo(-,E1.nodo,T.nodo);";
            reglasEjecutadas.add("P"+paso+" " + reglaE);
        }
        if(operador.equals("*")){//el operador es -
            String reglaE = "E.nodo = new Nodo(*,E1.nodo,T.nodo);";
            reglasEjecutadas.add("P"+paso+" " + reglaE);
        }
        if(operador.equals("/")){//el operador es -
            String reglaE = "E.nodo = new Nodo(/,E1.nodo,T.nodo);";
            reglasEjecutadas.add("P"+paso+" " + reglaE);
        }
        
    }//guardar
    
    public Nodo crear(String expresion){
        // 1. Considerar la expresion como un conjunto de tokens
        StringTokenizer tokenizer;
        paso = 0;
        String token;
        //investigar que es StringTokenizer
        /*
        ** Se utiliza para separar cadenas String en fragmentos mas pequeños,
        ** por defecto toma los espacios en blanco, tabuladores y saltos de linea.
        */
        //2. Separacion de tokens de la expresion
        tokenizer = new StringTokenizer(expresion, espacios+aritmeticos,true);
        //3. Mientras existan tokens
        while(tokenizer.hasMoreTokens()){
            //4. Omitir espacios en blanco
            token = tokenizer.nextToken();
            System.out.println(" Token " + token);
            if(espacios.contains(token))
                //5. Se trata de un identificador
                System.out.println("Omitiendo espacios...");
                //
            else if(!aritmeticos.contains(token)){
                //6. Extraer de la pila de terminos que estaban
                arbolNodo.push(new Nodo(token));
                paso++;
                String regla = "T.nodo = new Hoja(id<"+token+">,id.entrada_"+token+")";
                reglasEjecutadas.add("P"+paso+" "+regla);
            }else if(token.equals(")")){
                //7. Tratar tokens que no son parentesis
                
                  while(!caracter.empty() && !caracter.peek().equals("("))
                      guardar();
                      
                  //while  
                  caracter.pop();
            }else{
                if(!token.equals("(")&& !caracter.empty()){
                    String exa = (String) caracter.peek();
                    while(!exa.equals("(")&& !caracter.empty()&&
                            aritmeticos.indexOf(exa)>=aritmeticos.indexOf(token)){
                        guardar();
                        if(!caracter.empty())
                            exa = (String) caracter.peek();
                        //if caracter
                    }//while exa.equals
                }// if token
                caracter.push(token);
            }//else if
            //8. Guardar el token
        }//while
        while(!caracter.empty()){
            if(caracter.peek().equals("("))
                caracter.pop();
            else{
                guardar();
                raiz = (Nodo) arbolNodo.peek();
            }//if - else
        }//while !caracter
        return raiz;
    }//Nodo crear
    
}//Clase
