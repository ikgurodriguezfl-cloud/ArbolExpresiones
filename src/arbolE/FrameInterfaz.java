/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package arbolE;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author iker_
 */
public class FrameInterfaz extends javax.swing.JFrame {
    
    String nPolaca;
    int temp;
    private ArbolRenderConfig configArbol;
    String izquierdo;
    String derecho;
    String emuLocal = "";
    int Contador = 0;
    int i ;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrameInterfaz.class.getName());

    /**
     * Creates new form FrameInterfaz
     */
    
    
    
    public FrameInterfaz() {
        initComponents();
        nPolaca = "";
        temp=0;
        configArbol = ArbolRenderConfig.defaultConfig();
        izquierdo = "";
        derecho = "";
        i = 1;
    }
    
    public void generarEmutasm(String emu,int Contador){
        try{
            FileWriter escritor = new FileWriter("e"+Contador+".asm");
            escritor.write(emu);
            escritor.close();
            System.out.println("Archivo creado exitosamente");
        }catch (Exception e){
            System.out.println("Ha ocurrido un error al crear el archivo");
        }
    }
    
    public void sonido(){
        //sonido
                try {
                    File sonido = new File("C:\\Users\\iker_\\Desktop\\VERANO 2026\\LyA2\\ArbolExpresiones\\src\\arbolE\\new-notification-022-370046.wav");
                    if (sonido.exists()) {
                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(sonido);
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioStream);
                        clip.start(); 
                    } else {
                        showMessageDialog(null, "No se encontró el archivo de sonido.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showMessageDialog(null, "Error al reproducir el sonido.");
                }
    }
    
    public void abrirArchivo(){
        try{
            File archivo = new File("C:\\Users\\iker_\\Desktop\\VERANO 2026\\LyA2\\ArbolExpresiones\\e"+Contador+".asm");
            Desktop.getDesktop().open(archivo);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //============METODOS INORDDEN POSORDEN Y PREORDEN================9 de julio de 2026==
    public void inOrden(Nodo n){
        if(n!=null){
            inOrden(n.getIzquierdo());
            jTxtInOrden.append(n.getDato()+"\n");
            inOrden(n.getDerecho());
            // 15 julio
            
            switch(n.getDato()){
                case "+":
                    System.out.println("ADD RODRIGUEZ FLORES");
                    izquierdo = n.getIzquierdo().getDato();
                    derecho = n.getDerecho().getDato();
                    System.out.println("izq:"+izquierdo);
                    System.out.println("der:"+derecho);
                    emuLocal += "MOV AX, "+n.getIzquierdo().getDato()+"\n";
                    emuLocal += "MOV BX, "+n.getDerecho().getDato()+"\n";
                    emuLocal += "ADD AX, BX"+"\n\n";
                    break;
                case "-":
                    System.out.println("SUB");
                    izquierdo = n.getIzquierdo().getDato();
                    derecho = n.getDerecho().getDato();
                    break;    
                case "*":
                    System.out.println("MUL");
                    izquierdo = n.getIzquierdo().getDato();
                    derecho = n.getDerecho().getDato();
                    break;    
                case "/":
                    System.out.println("DIV");
                    izquierdo = n.getIzquierdo().getDato();
                    derecho = n.getDerecho().getDato();
                    break;
            }
        }
    }
    public void preOrden(Nodo n){
        if(n!=null){
            jTxtPreOrden.append(n.getDato()+"\n");
            nPolaca+=jNotacionPolaca.getText()+n.getDato()+" ";
            jNotacionPolaca.setText(jNotacionPolaca.getText()+
                    n.getDato()+" ");
            preOrden(n.getIzquierdo());
            preOrden(n.getDerecho());
        }
    }
    public void postOrden(Nodo n){
        if(n!=null){
            postOrden(n.getIzquierdo());
            postOrden(n.getDerecho());
            jTxtPostOrden.append(n.getDato()+"\n");
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxtPreOrden = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTxtInOrden = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTxtPostOrden = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTxtReglasSemanticas = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTxtTresDirecciones = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jNotacionPolaca = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Arbol de expresiones - LyA2");

        jLabel9.setText("jLabel9");

        jLabel11.setText("jLabel11");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tec resized.png"))); // NOI18N
        jLabel12.setText("jLabel12");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/foto historica resized.jpg"))); // NOI18N
        jLabel10.setText("jLabel10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(268, 268, 268)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Expresion");

        jTextField1.setBackground(new java.awt.Color(255, 255, 0));

        jButton1.setLabel("Compila");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jTxtPreOrden.setColumns(20);
        jTxtPreOrden.setRows(5);
        jTxtPreOrden.setMinimumSize(new java.awt.Dimension(150, 190));
        jTxtPreOrden.setPreferredSize(new java.awt.Dimension(150, 190));
        jScrollPane1.setViewportView(jTxtPreOrden);

        jTxtInOrden.setColumns(20);
        jTxtInOrden.setRows(5);
        jTxtInOrden.setMinimumSize(new java.awt.Dimension(150, 190));
        jTxtInOrden.setPreferredSize(new java.awt.Dimension(150, 190));
        jScrollPane2.setViewportView(jTxtInOrden);

        jTxtPostOrden.setColumns(20);
        jTxtPostOrden.setRows(5);
        jTxtPostOrden.setMinimumSize(new java.awt.Dimension(150, 190));
        jTxtPostOrden.setPreferredSize(new java.awt.Dimension(150, 190));
        jScrollPane3.setViewportView(jTxtPostOrden);

        jTxtReglasSemanticas.setColumns(20);
        jTxtReglasSemanticas.setRows(5);
        jTxtReglasSemanticas.setMinimumSize(new java.awt.Dimension(150, 190));
        jTxtReglasSemanticas.setPreferredSize(new java.awt.Dimension(150, 190));
        jScrollPane4.setViewportView(jTxtReglasSemanticas);

        jTxtTresDirecciones.setColumns(20);
        jTxtTresDirecciones.setRows(5);
        jTxtTresDirecciones.setMinimumSize(new java.awt.Dimension(150, 190));
        jTxtTresDirecciones.setPreferredSize(new java.awt.Dimension(150, 190));
        jScrollPane5.setViewportView(jTxtTresDirecciones);

        jButton5.setText("Agente IA");
        jButton5.addActionListener(this::jButton5ActionPerformed);

        jButton6.setText("Optimiza inter");
        jButton6.addActionListener(this::jButton6ActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addComponent(jScrollPane2)
                        .addComponent(jScrollPane3)
                        .addComponent(jScrollPane5))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 102));

        jLabel2.setText("Pre Orden");

        jLabel3.setText("In Orden");

        jLabel4.setText("Pos Orden");

        jLabel5.setText("Reglas Semanticas");

        jLabel6.setText("Pre Codigo 3 direcciones");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel2)
                .addGap(63, 63, 63)
                .addComponent(jLabel3)
                .addGap(88, 88, 88)
                .addComponent(jLabel4)
                .addGap(105, 105, 105)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Notacion Polaca");

        jButton2.setText("Codigo 3 direcciones");

        jButton3.setText("Clean");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton4.setText("Tabla de simbolos");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addGap(42, 42, 42)
                .addComponent(jNotacionPolaca, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jNotacionPolaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String datos = "";
        
        //Arbol a = new Arbol();
        ArbolIker a = new ArbolIker();
        datos = jTextField1.getText();
        
        Nodo arbolExpresion = a.crear(datos);
        jTxtReglasSemanticas.append(a.getReglasEjecutadas());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String datos = "";
        HashMap<String, String> hash;
        
        ArbolAgenteIA arbol = new ArbolAgenteIA();
        hash = arbol.getTablaSimbolos();
        
        datos = jTextField1.getText();
        Nodo arbolExpresion = arbol.crear(datos);
        arbolExpresion = arbol.convertirAGAD(arbolExpresion);
        jTxtReglasSemanticas.append(arbol.getReglasEjecutadas());

        JFrame ventana = new JFrame("Visualizador de Arboles - LyA2");
        PanelArbol panel = new PanelArbol(arbolExpresion, hash, configArbol);
        
        ventana.add(panel);
        ventana.setSize(600,400);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        DialogoConfigArbol dialogoConfig = new DialogoConfigArbol(this, configArbol, panel::repaint);
        dialogoConfig.setVisible(true);

        preOrden(arbolExpresion);
        inOrden(arbolExpresion);
        postOrden(arbolExpresion);
        
        
        intermedio(arbolExpresion);
        
        arbol.emu86+=".CODE \n"
                + "MOV AX, @DATA \n"
                + "MOV DS,AX \n";
        
        String finalEmu = arbol.emu86+this.emuLocal;
        
        finalEmu += "MOV AX, 4C00h \n"
                + "INT 21h \nEND";
        showMessageDialog(null,finalEmu);
        Contador++;
        generarEmutasm(finalEmu,Contador);
        sonido();
        abrirArchivo();
        jTxtTresDirecciones.append(arbolExpresion.getCodigoIntermedio());
        
        
        //Archivos
        try {
    
    String ruta = "reglasSemanticas.txt"; 
    
    String contenido = jTxtReglasSemanticas.getText();
    contenido = ("\n"+contenido+"\n---------------------------------------");
    
    FileWriter escritor = new FileWriter(ruta, true);
    
    escritor.write(contenido);
    escritor.close();
    
    JOptionPane.showMessageDialog(null, "Archivo guardado exitosamente.");
    
} catch (IOException e) {
    JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage());
}   
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jTextField1.setText("");
        jTxtReglasSemanticas.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // Abre el navegador al hacer clic. Reemplaza el placeholder por tu enlace de YouTube.
        try {
            // PON AQUI TU ENLACE DE YOUTUBE ENTRE LAS COMILLAS
            String link = "https://youtube.com/shorts/VtPL5rJqN24";
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(link));
            } else {
                JOptionPane.showMessageDialog(this, "gg gente");
            }
        } catch (IOException | URISyntaxException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo abrir el navegador: " + ex.getMessage());
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    public void intermedio(Nodo n){
        if(n!=null){
            intermedio(n.getIzquierdo());
            intermedio(n.getDerecho());
            if(n.getIzquierdo()==null && n.getDerecho()==null){
                n.setLugar(n.getDato()+"");
                n.setCodigoIntermedio(" ");
            }else{
                if(n.getDato().equals("+") || n.getDato().equals("*") || n.getDato().equals("-") || n.getDato().equals("/")){
                    temp++;
                    n.setLugar("T"+temp);
                    Nodo izquierdo = n.getIzquierdo();
                    Nodo derecho = n.getDerecho();
                    String codigoI = "";
                    codigoI = izquierdo.getCodigoIntermedio()+" "+derecho.getCodigoIntermedio()+" "+n.getLugar()+" "+"="+
                            izquierdo.getLugar()+" "+ n.getDato()+ " "+ derecho.getLugar();
                    n.setCodigoIntermedio(codigoI+"\n");
                }else{
                    if(n.getDato().equals("=")){
                        String codigoI = "";
                        Nodo izquierdo = n.getIzquierdo();
                        Nodo derecho = n.getDerecho();
                        codigoI = derecho.getDato()+" "+izquierdo.getLugar()+" "+ " = T"+temp+"\n";
                        n.setCodigoIntermedio(codigoI);
                    }//equals =
                }//equals +-*/
            }//getDerecho getIzquierdo
        }//n!=null
        
    }//intermedio
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        //java.awt.EventQueue.invokeLater(() -> new FrameInterfaz().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jNotacionPolaca;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea jTxtInOrden;
    private javax.swing.JTextArea jTxtPostOrden;
    private javax.swing.JTextArea jTxtPreOrden;
    private javax.swing.JTextArea jTxtReglasSemanticas;
    private javax.swing.JTextArea jTxtTresDirecciones;
    // End of variables declaration//GEN-END:variables
}
