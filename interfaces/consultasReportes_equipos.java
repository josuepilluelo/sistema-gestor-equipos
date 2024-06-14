/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Conection_bd;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static interfaces.loggin.icono;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Josue Lopez
 */
public class consultasReportes_equipos extends javax.swing.JFrame {

    public static int tipo_consulta = 0;
    public static int tipo_consulta_pdf = 0, columnas_tabla_inicial = 13;
    private String titulo = "";                              //12 campos
    public static String consulta_inicial = "select "
            + "e.id_equipo, "
            + "e.tipo_equipo, "
            + "e.ubicacion_equipo, "
            + "e.marca_equipo, "
            + "e.modelo_equipo, "
            + "e.procesador_equipo, "
            + "e.ram_equipo, "
            + "e.pw_equipo, "
            + "concat(e.disco_equipo,' GB'), "
            + "e.estado_equipo, "
            + "r.nombre_responsable, "
            + "e.perifericos_equipo, "
            + "DATE_ADD(e.ultima_modificacion, interval 1 day) "
            + "from equipos e JOIN responsable r "
            + "ON (e.id_responsable = r.id_responsable) "
            + "where NOT e.estado_equipo = 'BAJA'";
    public static String consulta_barra = "select "
            + "e.id_equipo, "
            + "e.tipo_equipo, "
            + "e.ubicacion_equipo, "
            + "e.marca_equipo, "
            + "e.modelo_equipo, "
            + "e.procesador_equipo, "
            + "e.ram_equipo, "
            + "e.pw_equipo, "
            + "concat(e.disco_equipo,' GB'), "
            + "e.estado_equipo, "
            + "r.nombre_responsable, "
            + "e.perifericos_equipo, "
            + "DATE_ADD(e.ultima_modificacion, interval 1 day) "
            + "from equipos e JOIN responsable r "
            + "ON (e.id_responsable = r.id_responsable) "
            + "where NOT e.estado_equipo = 'BAJA' and e.marca_equipo like '%";
    public static String consulta_lugar = "select "
            + "e.id_equipo, "
            + "e.tipo_equipo, "
            + "e.ubicacion_equipo, "
            + "e.marca_equipo, "
            + "e.modelo_equipo, "
            + "e.procesador_equipo, "
            + "e.ram_equipo, "
            + "e.pw_equipo, "
            + "concat(e.disco_equipo,' GB'), "
            + "e.estado_equipo, "
            + "r.nombre_responsable, "
            + "e.perifericos_equipo, "
            + "DATE_ADD(e.ultima_modificacion, interval 1 day) "
            + "from equipos e JOIN responsable r "
            + "ON (e.id_responsable = r.id_responsable) "
            + "where NOT e.estado_equipo = 'BAJA' and e.ubicacion_equipo ='";
    public static String consulta_jmtoluca = consulta_lugar + "JM TOLUCA'";
    public static String consulta_jmpalmillas = consulta_lugar + "JM PALMILLAS'";
    public static String consulta_jmatlacomulco = consulta_lugar + "JM ATLACOMULCO'";
    public static String consulta_jmtenango = consulta_lugar + "JM TENANGO'";
    public static String consulta_jmvillavictoria = consulta_lugar + "JM VILLA VICTORIA'";
    public static String consulta_jmtequixquiac = consulta_lugar + "JM TEQUIXQUIAC'";
    public static String consulta_planta_swecomex = consulta_lugar + "PLANTA SWECOMEX'";
    public static String consulta_bodegaisbqueretaro = consulta_lugar + "BODEGA ISB QUERETARO'";
    public static String consulta_farmtire = consulta_lugar + "FARMTIRE IXTLAHUACA'";

    public static String consulta_pdf_todos = "select "
            + "e.id_equipo, "
            + "e.tipo_equipo, "
            + "e.ubicacion_equipo, "
            + "e.marca_equipo, "
            + "e.modelo_equipo, "
            + "e.procesador_equipo, "
            + "e.ram_equipo, "
            + "e.pw_equipo, "
            + "concat(e.disco_equipo,' GB'), "
            + "r.nombre_responsable "
            + "from equipos e JOIN responsable r "
            + "ON (e.id_responsable = r.id_responsable) "
            + "where NOT e.estado_equipo = 'BAJA'";

    public static String consulta_pdf_ubicacion = "select "
            + "e.id_equipo, "
            + "e.tipo_equipo, "
            + "e.ubicacion_equipo, "
            + "e.marca_equipo, "
            + "e.modelo_equipo, "
            + "e.procesador_equipo, "
            + "e.ram_equipo, "
            + "e.pw_equipo, "
            + "concat(e.disco_equipo,' GB'), "
            + "r.nombre_responsable "
            + "from equipos e JOIN responsable r "
            + "ON (e.id_responsable = r.id_responsable) "
            + "where NOT e.estado_equipo = 'BAJA' and e.ubicacion_equipo ='";

    DefaultTableModel modelo_tabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            //ya no seran editadas
            if (columnas > 0 && filas > 0) {
                return false;
            }
            return false;
        }
    };

    public consultasReportes_equipos() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(icono)).getImage());
        etiqueta_resporte.setText("Tipo de reporte activo: TODOS LOS EQUIPOS");
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));
        tipo_consulta_pdf = 1;//todos

        try {
            Connection cn = Conection_bd.conexion_bd();
            PreparedStatement pst = cn.prepareStatement(consulta_inicial);
            ResultSet rs = pst.executeQuery();
            tabla = new JTable(modelo_tabla);
            //para no poder mover las columnas
            tabla.getTableHeader().setReorderingAllowed(false);
            //tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));

            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            //tamaño de alto de la tabla 
            tabla.setRowHeight(30);
            //color de fondo
            tabla.setBackground(new Color(156, 197, 152));
            //genera la fuente para los datos de la tabla
            tabla.setFont(new Font("Arial", Font.PLAIN, 16));
            //color del borde de la tabla
            tabla.setGridColor(new Color(255, 255, 255));
            //tabla.setRowMargin(10);
            //poner bordes
            //tabla_equipos.setShowGrid(true);
            tabla.setShowHorizontalLines(true);
            //tabla_equipos.setShowVerticalLines(true);
            //para que la tabla tenga un scroll
            jScrollPane1.setViewportView(tabla);

            modelo_tabla.addColumn("Id");                   //0
            modelo_tabla.addColumn("Tipo de Equipo");      //1
            modelo_tabla.addColumn("Ubicacion");            //2
            modelo_tabla.addColumn("Marca");                 //3
            modelo_tabla.addColumn("Modelo");               //4
            modelo_tabla.addColumn("Procesador");            //5
            modelo_tabla.addColumn("Ram");                  //6
            modelo_tabla.addColumn("Contraseña");         //7
            modelo_tabla.addColumn("Disco");          //8
            modelo_tabla.addColumn("Estado");             //9
            modelo_tabla.addColumn("Empleado");             //10
            modelo_tabla.addColumn("Perifericos");          //11            
            modelo_tabla.addColumn("Ultima modificacion");   //12
            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(220);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(75);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(10).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(11).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(12).setPreferredWidth(150);

            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(5).setResizable(false);
            tabla.getColumnModel().getColumn(6).setResizable(false);
            tabla.getColumnModel().getColumn(7).setResizable(false);
            tabla.getColumnModel().getColumn(8).setResizable(false);
            tabla.getColumnModel().getColumn(9).setResizable(false);
            tabla.getColumnModel().getColumn(10).setResizable(false);
            tabla.getColumnModel().getColumn(11).setResizable(false);
            tabla.getColumnModel().getColumn(12).setResizable(false);

            //modelo_tabla.
            while (rs.next()) {
                Object[] fila = new Object[columnas_tabla_inicial];//por las columnas que tengo
                for (int i = 0; i < columnas_tabla_inicial; i++) {
                    fila[i] = rs.getObject(i + 1);

                    //centra el contenido de la tabla
                    tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
                    //cambiamos el color del emcabezado y centrado
                    TableColumn column = tabla.getTableHeader().getColumnModel().getColumn(i);
                    column.setHeaderRenderer(cellRenderer);

                    cellRenderer.setFont(new Font("Arial", Font.BOLD, 11));
                    cellRenderer.setHorizontalAlignment(cellRenderer.CENTER);
                    cellRenderer.setForeground(Color.black);
                }
                tabla.getTableHeader().setBackground(new Color(255, 0, 0));
                modelo_tabla.addRow(fila);
                ((DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Error al llenar tabla de equipos: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion sobre equipos\n"+e, "Error", JOptionPane.ERROR_MESSAGE);
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
        boton_resportePDF = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        boton_reiniciar = new javax.swing.JButton();
        boton_buscar_ubicacion = new javax.swing.JButton();
        boton_regresar = new javax.swing.JButton();
        barra_busqueda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        boton_buscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        etiqueta_resporte = new javax.swing.JLabel();
        barra_ubicacion = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consultas y reportes");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(191, 191, 191));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        boton_resportePDF.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_resportePDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pdf_25.png"))); // NOI18N
        boton_resportePDF.setText("Generar Reporte");
        boton_resportePDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_resportePDFActionPerformed(evt);
            }
        });
        jPanel1.add(boton_resportePDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 440, -1, 46));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 970, 330));

        boton_reiniciar.setBackground(new java.awt.Color(0, 204, 255));
        boton_reiniciar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_reiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reiniciar_15.png"))); // NOI18N
        boton_reiniciar.setText("Reiniciar tabla");
        boton_reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_reiniciarActionPerformed(evt);
            }
        });
        jPanel1.add(boton_reiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 150, -1));

        boton_buscar_ubicacion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar_ubicacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar_15.png"))); // NOI18N
        boton_buscar_ubicacion.setText("Buscar");
        boton_buscar_ubicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscar_ubicacionActionPerformed(evt);
            }
        });
        jPanel1.add(boton_buscar_ubicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 60, -1, -1));

        boton_regresar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/regresar_30.png"))); // NOI18N
        boton_regresar.setText("Regresar");
        boton_regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_regresarActionPerformed(evt);
            }
        });
        jPanel1.add(boton_regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 35));

        barra_busqueda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_busqueda.setToolTipText("ejemplo silla, escritorio");
        jPanel1.add(barra_busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 160, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Buscar por marca:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        boton_buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar_15.png"))); // NOI18N
        boton_buscar.setText("Buscar");
        boton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscarActionPerformed(evt);
            }
        });
        jPanel1.add(boton_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 100, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Buscar por ubicacion:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, -1, -1));

        jButton1.setBackground(new java.awt.Color(255, 204, 51));
        jButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reportes_30.png"))); // NOI18N
        jButton1.setText("Reporte de Bajas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 180, 48));

        etiqueta_resporte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etiqueta_resporte.setText("Tipo de reporte activo:");
        jPanel1.add(etiqueta_resporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 460, 440, -1));

        barra_ubicacion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_ubicacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JM TOLUCA", "JM PALMILLAS", "JM ATLACOMULCO", "JM TENANGO", "JM VILLA VICTORIA", "JM TEQUIXQUIAC", "PLANTA SWECOMEX", "BODEGA ISB QUERETARO", "FARMTIRE IXTLAHUACA" }));
        jPanel1.add(barra_ubicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 210, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        reporteBajas mbm = new reporteBajas();
        mbm.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void boton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscarActionPerformed
        modelo_tabla.setRowCount(0);
        modelo_tabla.setColumnCount(0);
        barra_ubicacion.setSelectedIndex(0);

        etiqueta_resporte.setText("Tipo de reporte activo: TODOS LOS EQUIPOS");
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));
        tipo_consulta_pdf = 1;//todos

        try {
            Connection cn = Conection_bd.conexion_bd();
            PreparedStatement pst = cn.prepareStatement(consulta_barra + barra_busqueda.getText().toUpperCase().trim() + "%'");
            ResultSet rs = pst.executeQuery();
            tabla = new JTable(modelo_tabla);
            //para no poder mover las columnas
            tabla.getTableHeader().setReorderingAllowed(false);
            //tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));

            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            //tamaño de alto de la tabla 
            tabla.setRowHeight(30);
            //color de fondo
            tabla.setBackground(new Color(156, 197, 152));
            //genera la fuente para los datos de la tabla
            tabla.setFont(new Font("Arial", Font.PLAIN, 16));
            //color del borde de la tabla
            tabla.setGridColor(new Color(255, 255, 255));
            //tabla.setRowMargin(10);
            //poner bordes
            //tabla_equipos.setShowGrid(true);
            tabla.setShowHorizontalLines(true);
            //tabla_equipos.setShowVerticalLines(true);
            //para que la tabla tenga un scroll
            jScrollPane1.setViewportView(tabla);

            modelo_tabla.addColumn("Id");                   //0
            modelo_tabla.addColumn("Tipo de Equipo");      //1
            modelo_tabla.addColumn("Ubicacion");            //2
            modelo_tabla.addColumn("Marca");                 //3
            modelo_tabla.addColumn("Modelo");               //4
            modelo_tabla.addColumn("Procesador");            //5
            modelo_tabla.addColumn("Ram");                  //6
            modelo_tabla.addColumn("Contraseña");         //7
            modelo_tabla.addColumn("Disco");          //8
            modelo_tabla.addColumn("Estado");             //9
            modelo_tabla.addColumn("Empleado");             //10
            modelo_tabla.addColumn("Perifericos");          //11            
            modelo_tabla.addColumn("Ultima modificacion");   //12
            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(220);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(75);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(10).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(11).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(12).setPreferredWidth(150);

            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(5).setResizable(false);
            tabla.getColumnModel().getColumn(6).setResizable(false);
            tabla.getColumnModel().getColumn(7).setResizable(false);
            tabla.getColumnModel().getColumn(8).setResizable(false);
            tabla.getColumnModel().getColumn(9).setResizable(false);
            tabla.getColumnModel().getColumn(10).setResizable(false);
            tabla.getColumnModel().getColumn(11).setResizable(false);
            tabla.getColumnModel().getColumn(12).setResizable(false);

            //modelo_tabla.
            while (rs.next()) {
                Object[] fila = new Object[columnas_tabla_inicial];//por las columnas que tengo
                for (int i = 0; i < columnas_tabla_inicial; i++) {
                    fila[i] = rs.getObject(i + 1);

                    //centra el contenido de la tabla
                    tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
                    //cambiamos el color del emcabezado y centrado
                    TableColumn column = tabla.getTableHeader().getColumnModel().getColumn(i);
                    column.setHeaderRenderer(cellRenderer);

                    cellRenderer.setFont(new Font("Arial", Font.BOLD, 11));
                    cellRenderer.setHorizontalAlignment(cellRenderer.CENTER);
                    cellRenderer.setForeground(Color.black);
                }
                tabla.getTableHeader().setBackground(new Color(255, 0, 0));
                modelo_tabla.addRow(fila);
                ((DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Error al llenar tabla de equipos: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion sobre equipos\n"+e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_boton_buscarActionPerformed

    private void boton_regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_regresarActionPerformed
        menuEquipos p = new menuEquipos();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_regresarActionPerformed

    private void boton_buscar_ubicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscar_ubicacionActionPerformed
        // TODO add your handling code here:
        barra_busqueda.setText("");
        int ubicacion = barra_ubicacion.getSelectedIndex();
        String consulta_ubicacionn = "";
        setIconImage(new ImageIcon(getClass().getResource(icono)).getImage());
        if (ubicacion == 0) {
            tipo_consulta_pdf = 2;//jmtoluca
            consulta_ubicacionn = consulta_jmtoluca;
            etiqueta_resporte.setText("Tipo de reporte activo: EQUIPOS JM TOLUCA");
        } else if (ubicacion == 1) {
            tipo_consulta_pdf = 3;//jmpalmillas
            consulta_ubicacionn = consulta_jmpalmillas;
            etiqueta_resporte.setText("Tipo de reporte activo: EQUIPOS JM PALMILLAS");
        } else if (ubicacion == 2) {
            tipo_consulta_pdf = 4;//JM ATLACOMULCO
            consulta_ubicacionn = consulta_jmatlacomulco;
            etiqueta_resporte.setText("Tipo de reporte activo: EQUIPOS JM ATLACOMULCO");
        } else if (ubicacion == 3) {
            tipo_consulta_pdf = 5;//jm TENANGO
            consulta_ubicacionn = consulta_jmtenango;
            etiqueta_resporte.setText("Tipo de reporte activo: EQUIPOS JM TENANGO");
        } else if (ubicacion == 4) {
            tipo_consulta_pdf = 6;//jm VILLA VICTORIA
            consulta_ubicacionn = consulta_jmvillavictoria;
            etiqueta_resporte.setText("Tipo de reporte activo: EQUIPOS JM VILLA VICTORIA");
        } else if (ubicacion == 5) {
            tipo_consulta_pdf = 7;//JM TEQUIXQUIAC
            consulta_ubicacionn = consulta_jmtequixquiac;
            etiqueta_resporte.setText("Tipo de reporte activo: EQUIPOS JM TEQUIXQUIAC");
        } else if (ubicacion == 6) {
            tipo_consulta_pdf = 8;// PLANTA SWECOMEX
            consulta_ubicacionn = consulta_planta_swecomex;
            etiqueta_resporte.setText("Tipo de reporte activo:  EQUIPOS PLANTA SWECOMEX");
        } else if (ubicacion == 7) {
            tipo_consulta_pdf = 9;//BODEGA ISB QUERETARO
            consulta_ubicacionn = consulta_bodegaisbqueretaro;
            etiqueta_resporte.setText("Tipo de reporte activo: EQUIPOS BODEGA ISB QUERETARO");
        } else if (ubicacion == 8) {
            tipo_consulta_pdf = 10;//FARMTIRE IXTLAHUCA
            consulta_ubicacionn = consulta_farmtire;
            etiqueta_resporte.setText("Tipo de reporte activo: TODOS LOS EQUIPOS FARMTIRE IXTLAHUACA");
        }
        modelo_tabla.setRowCount(0);
        modelo_tabla.setColumnCount(0);
        barra_busqueda.setText("");
        barra_ubicacion.setSelectedIndex(0);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));
        titulo = "Equipos de computo Distribuidora JM";
        try {
            Connection cn = Conection_bd.conexion_bd();
            PreparedStatement pst = cn.prepareStatement(consulta_ubicacionn);
            ResultSet rs = pst.executeQuery();
            tabla = new JTable(modelo_tabla);
            //para no poder mover las columnas
            tabla.getTableHeader().setReorderingAllowed(false);
            //tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));

            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            //tamaño de alto de la tabla 
            tabla.setRowHeight(30);
            //color de fondo
            tabla.setBackground(new Color(156, 197, 152));
            //genera la fuente para los datos de la tabla
            tabla.setFont(new Font("Arial", Font.PLAIN, 16));
            //color del borde de la tabla
            tabla.setGridColor(new Color(255, 255, 255));
            //tabla.setRowMargin(10);
            //poner bordes
            //tabla_equipos.setShowGrid(true);
            tabla.setShowHorizontalLines(true);
            //tabla_equipos.setShowVerticalLines(true);
            //para que la tabla tenga un scroll
            jScrollPane1.setViewportView(tabla);

            modelo_tabla.addColumn("Id");                   //0
            modelo_tabla.addColumn("Tipo de Equipo");      //1
            modelo_tabla.addColumn("Ubicacion");            //2
            modelo_tabla.addColumn("Marca");                 //3
            modelo_tabla.addColumn("Modelo");               //4
            modelo_tabla.addColumn("Procesador");            //5
            modelo_tabla.addColumn("Ram");                  //6
            modelo_tabla.addColumn("Contraseña");         //7
            modelo_tabla.addColumn("Disco");          //8
            modelo_tabla.addColumn("Estado");             //9
            modelo_tabla.addColumn("Empleado");             //10
            modelo_tabla.addColumn("Perifericos");          //11            
            modelo_tabla.addColumn("Ultima modificacion");   //12
            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(220);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(75);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(10).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(11).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(12).setPreferredWidth(150);

            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(5).setResizable(false);
            tabla.getColumnModel().getColumn(6).setResizable(false);
            tabla.getColumnModel().getColumn(7).setResizable(false);
            tabla.getColumnModel().getColumn(8).setResizable(false);
            tabla.getColumnModel().getColumn(9).setResizable(false);
            tabla.getColumnModel().getColumn(10).setResizable(false);
            tabla.getColumnModel().getColumn(11).setResizable(false);
            tabla.getColumnModel().getColumn(12).setResizable(false);

            //modelo_tabla.
            while (rs.next()) {
                Object[] fila = new Object[columnas_tabla_inicial];//por las columnas que tengo
                for (int i = 0; i < columnas_tabla_inicial; i++) {
                    fila[i] = rs.getObject(i + 1);

                    //centra el contenido de la tabla
                    tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
                    //cambiamos el color del emcabezado y centrado
                    TableColumn column = tabla.getTableHeader().getColumnModel().getColumn(i);
                    column.setHeaderRenderer(cellRenderer);

                    cellRenderer.setFont(new Font("Arial", Font.BOLD, 11));
                    cellRenderer.setHorizontalAlignment(cellRenderer.CENTER);
                    cellRenderer.setForeground(Color.black);
                }
                tabla.getTableHeader().setBackground(new Color(255, 0, 0));
                modelo_tabla.addRow(fila);
                ((DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Error al llenar tabla de equipos: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion sobre equipos\n"+e, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_boton_buscar_ubicacionActionPerformed

    private void boton_reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_reiniciarActionPerformed
        // TODO add your handling code here:
        modelo_tabla.setRowCount(0);
        modelo_tabla.setColumnCount(0);
        barra_busqueda.setText("");
        barra_ubicacion.setSelectedIndex(0);

        etiqueta_resporte.setText("Tipo de reporte activo: TODOS LOS EQUIPOS");
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));
        tipo_consulta_pdf = 1;//todos

        try {
            Connection cn = Conection_bd.conexion_bd();
            PreparedStatement pst = cn.prepareStatement(consulta_inicial);
            ResultSet rs = pst.executeQuery();
            tabla = new JTable(modelo_tabla);
            //para no poder mover las columnas
            tabla.getTableHeader().setReorderingAllowed(false);
            //tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));

            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            //tamaño de alto de la tabla 
            tabla.setRowHeight(30);
            //color de fondo
            tabla.setBackground(new Color(156, 197, 152));
            //genera la fuente para los datos de la tabla
            tabla.setFont(new Font("Arial", Font.PLAIN, 16));
            //color del borde de la tabla
            tabla.setGridColor(new Color(255, 255, 255));
            //tabla.setRowMargin(10);
            //poner bordes
            //tabla_equipos.setShowGrid(true);
            tabla.setShowHorizontalLines(true);
            //tabla_equipos.setShowVerticalLines(true);
            //para que la tabla tenga un scroll
            jScrollPane1.setViewportView(tabla);

            modelo_tabla.addColumn("Id");                   //0
            modelo_tabla.addColumn("Tipo de Equipo");      //1
            modelo_tabla.addColumn("Ubicacion");            //2
            modelo_tabla.addColumn("Marca");                 //3
            modelo_tabla.addColumn("Modelo");               //4
            modelo_tabla.addColumn("Procesador");            //5
            modelo_tabla.addColumn("Ram");                  //6
            modelo_tabla.addColumn("Contraseña");         //7
            modelo_tabla.addColumn("Disco");          //8
            modelo_tabla.addColumn("Estado");             //9
            modelo_tabla.addColumn("Empleado");             //10
            modelo_tabla.addColumn("Perifericos");          //11            
            modelo_tabla.addColumn("Ultima modificacion");   //12
            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(220);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(75);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(10).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(11).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(12).setPreferredWidth(150);

            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(5).setResizable(false);
            tabla.getColumnModel().getColumn(6).setResizable(false);
            tabla.getColumnModel().getColumn(7).setResizable(false);
            tabla.getColumnModel().getColumn(8).setResizable(false);
            tabla.getColumnModel().getColumn(9).setResizable(false);
            tabla.getColumnModel().getColumn(10).setResizable(false);
            tabla.getColumnModel().getColumn(11).setResizable(false);
            tabla.getColumnModel().getColumn(12).setResizable(false);

            //modelo_tabla.
            while (rs.next()) {
                Object[] fila = new Object[columnas_tabla_inicial];//por las columnas que tengo
                for (int i = 0; i < columnas_tabla_inicial; i++) {
                    fila[i] = rs.getObject(i + 1);

                    //centra el contenido de la tabla
                    tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
                    //cambiamos el color del emcabezado y centrado
                    TableColumn column = tabla.getTableHeader().getColumnModel().getColumn(i);
                    column.setHeaderRenderer(cellRenderer);

                    cellRenderer.setFont(new Font("Arial", Font.BOLD, 11));
                    cellRenderer.setHorizontalAlignment(cellRenderer.CENTER);
                    cellRenderer.setForeground(Color.black);
                }
                tabla.getTableHeader().setBackground(new Color(255, 0, 0));
                modelo_tabla.addRow(fila);
                ((DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Error al llenar tabla de equipos: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion sobre equipos\n"+e, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_boton_reiniciarActionPerformed

    private void boton_resportePDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_resportePDFActionPerformed
        // TODO add your handling code here:
        JFileChooser jfc = new JFileChooser();
        int Opcion = jfc.showSaveDialog(this);
        /*
            public static String consulta_jmtoluca = consulta_lugar + "JM TOLUCA'";
    public static String consulta_jmpalmillas = consulta_lugar + "JM PALMILLAS'";
    public static String consulta_jmatlacomulco = consulta_lugar + "JM ATLACOMULCO'";
    public static String consulta_jmtenango = consulta_lugar + "JM TENANGO'";
    public static String consulta_jmvillavictoria = consulta_lugar + "JM VILLA VICTORIA'";
    public static String consulta_jmtequixquiac = consulta_lugar + "JM TEQUIXQUIAC'";
    public static String consulta_planta_swecomex = consulta_lugar + "PLANTA SWECOMEX'";
    public static String consulta_bodegaisbqueretaro = consulta_lugar + "BODEGA ISB QUERETARO'";
    public static String consulta_farmtire = consulta_lugar + "FARMTIRE IXTLAHUACA'";
         */
        String consulta = "";
        if (tipo_consulta_pdf == 1) {//todos
            consulta = consulta_pdf_todos;
            titulo = "Equipos de computo";
        } else if (tipo_consulta_pdf == 2) {//jm toluca
            consulta = consulta_pdf_ubicacion + "JM TOLUCA'";
            titulo = "Equipos de computo JM TOLUCA";
        } else if (tipo_consulta_pdf == 3) {//jm palmillas
            consulta = consulta_pdf_ubicacion + "JM PALMILLAS'";
            titulo = "Equipos de computo JM PALMILLAS";
        } else if (tipo_consulta_pdf == 4) {//jm atlacomulco
            consulta = consulta_pdf_ubicacion + "JM ATLACOMULCO'";
            titulo = "Equipos de computo JM ATLACOMULCO";
        } else if (tipo_consulta_pdf == 5) {//jmtenango
            consulta = consulta_pdf_ubicacion + "JM TENANGO'";
            titulo = "Equipos de computo JM TENANGO";
        } else if (tipo_consulta_pdf == 6) {//jm villa victoria
            consulta = consulta_pdf_ubicacion + "JM VILLA VICTORIA'";
            titulo = "Equipos de computo JM VILLA VICTORIA";
        } else if (tipo_consulta_pdf == 7) {//jm tequixquiac
            consulta = consulta_pdf_ubicacion + "JM TEQUIXQUIAC'";
            titulo = "Equipos de computo JM TEQUIXQUIAC";
        } else if (tipo_consulta_pdf == 8) {//jmplanta swecomex
            consulta = consulta_pdf_ubicacion + "PLANTA SWECOMEX'";
            titulo = "Equipos de computo PLANTA SWECOMEX";
        } else if (tipo_consulta_pdf == 9) {//bodega isb
            consulta = consulta_pdf_ubicacion + "BODEGA ISB QUERETARO'";
            titulo = "Equipos de computo BODEGA ISB QUERETARO";
        } else if (tipo_consulta_pdf == 10) {//farm tire 
            consulta = consulta_pdf_ubicacion + "FARMTIRE IXTLAHUACA'";
            titulo = "Equipos de computo FARMTIRE IXTLAHUACA";
        } else {
            JOptionPane.showMessageDialog(null, "Error en consulta de informacion", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (Opcion == JFileChooser.APPROVE_OPTION) {
            File f = jfc.getSelectedFile();
            System.out.println(f);

            float[] margenCeldas = new float[]{3f, 8f, 8f, 8f, 8f, 8f, 3f, 8f, 6f, 15f};//este arreglo sirve para las medidas que tengran cada una de las columnas si son 10 columnas son 1o numero
            Document documento = new Document(PageSize.LETTER.rotate()) {
            }; //el parametro es el tamaño de la hoja del documento LETTER es tamaño carta. rotate esta en horizontal
            //Documento documento = nuevo documento (PageSize.A4);(PageSize.LETTER.rotate())
            documento.setMargins(10, 10, 10, 10);//margenes del documento

            try {
                //String ruta = System.getProperty("user.home");//ruta del usuario
                PdfWriter.getInstance(documento, new FileOutputStream(f + ".pdf"));
                //insertar la imagen en el documento
                //cabecera
                com.itextpdf.text.Image imagen_cabecera = com.itextpdf.text.Image.getInstance("src/imagenes/banner_arriba.png");//las imagenes se guardan en src
                //dimensiones imagens
                imagen_cabecera.scaleToFit(700, 100);
                imagen_cabecera.setAlignment(Chunk.ALIGN_CENTER);

                com.itextpdf.text.Image imagen_abajo = com.itextpdf.text.Image.getInstance("src/imagenes/baner_abjo_horizontal.jpg");//las imagenes se guardan en src
                //dimensiones imagens
                imagen_abajo.scaleToFit(750, 100);

                imagen_abajo.setAbsolutePosition(25, 15);
                imagen_abajo.setAlignment(Chunk.ALIGN_CENTER);
                //formato de texto
                Paragraph parrafo = new Paragraph();
                parrafo.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo.setFont(FontFactory.getFont("Arial", 24, Font.BOLD, BaseColor.DARK_GRAY));
                parrafo.add(titulo + "\n\n");
                //abre el documento
                documento.open();
                //agregamos imagen y parrfos
                documento.add(imagen_cabecera);
                documento.add(parrafo);
                //numero de columnas

                PdfPTable tabla = new PdfPTable(10);
                tabla.setWidthPercentage(95);//el porcentaje total de ancho del documento tomara
                tabla.setWidths(margenCeldas);//aqui va el arreglo            
                PdfPCell cell = new PdfPCell(new Phrase("ID"));//aqui podrimos modificar las celdas
                cell.setVerticalAlignment(Element.ALIGN_CENTER);//alineados horizontales y verticales
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new BaseColor(183, 183, 183));//color de fondo
                cell.setFixedHeight(20);//alto de la celda que se le aplica a todas de la fila
                tabla.addCell(cell);
                PdfPCell cell_1 = new PdfPCell(new Phrase("Tipo"));
                cell_1.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_1.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_1);
                PdfPCell cell_2 = new PdfPCell(new Phrase("Ubicacion"));
                cell_2.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_2.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_2);
                PdfPCell cell_3 = new PdfPCell(new Phrase("Marca"));
                cell_3.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_3.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_3);
                PdfPCell cell_4 = new PdfPCell(new Phrase("Modelo"));
                cell_4.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_4.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_4.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_4);
                PdfPCell cell_5 = new PdfPCell(new Phrase("CPU"));
                cell_5.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_5.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_5.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_5);
                tabla.setWidths(margenCeldas);//aqui va el arreglo            
                PdfPCell cell_6 = new PdfPCell(new Phrase("Ram"));//aqui podrimos modificar las celdas
                cell_6.setVerticalAlignment(Element.ALIGN_CENTER);//alineados horizontales y verticales
                cell_6.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_6.setBackgroundColor(new BaseColor(183, 183, 183));//color de fondo
                cell_6.setFixedHeight(20);//alto de la celda que se le aplica a todas de la fila
                tabla.addCell(cell_6);
                /**
                 * ********************************************
                 */
                tabla.setWidths(margenCeldas);//aqui va el arreglo            
                PdfPCell cell_contra = new PdfPCell(new Phrase("Contraseña"));//aqui podrimos modificar las celdas
                cell_contra.setVerticalAlignment(Element.ALIGN_CENTER);//alineados horizontales y verticales
                cell_contra.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_contra.setBackgroundColor(new BaseColor(183, 183, 183));//color de fondo
                cell_contra.setFixedHeight(20);//alto de la celda que se le aplica a todas de la fila
                tabla.addCell(cell_contra);
                PdfPCell cell_7 = new PdfPCell(new Phrase("Disco duro"));
                cell_7.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_7.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_7.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_7);
                PdfPCell cell_8 = new PdfPCell(new Phrase("Responsable"));
                cell_8.setVerticalAlignment(Element.ALIGN_CENTER);
                cell_8.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell_8.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(cell_8);

                Connection cn = Conection_bd.conexion_bd();
                PreparedStatement pst = cn.prepareStatement(consulta);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    do {                        //columna
                        PdfPCell colu_1 = new PdfPCell(new Phrase(rs.getString(1)));
                        colu_1.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_1);
                        PdfPCell colu_2 = new PdfPCell(new Phrase(rs.getString(2)));
                        colu_2.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_2);
                        PdfPCell colu_3 = new PdfPCell(new Phrase(rs.getString(3)));
                        colu_3.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_3.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_3);
                        PdfPCell colu_4 = new PdfPCell(new Phrase(rs.getString(4)));
                        colu_4.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_4.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_4);
                        PdfPCell colu_5 = new PdfPCell(new Phrase(rs.getString(5)));
                        colu_5.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_5.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_5);
                        PdfPCell colu_6 = new PdfPCell(new Phrase(rs.getString(6)));
                        colu_6.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_6.setHorizontalAlignment(Element.ALIGN_CENTER);
                        colu_6.setFixedHeight(35);
                        tabla.addCell(colu_6);
                        PdfPCell colu_7 = new PdfPCell(new Phrase(rs.getString(7)));
                        colu_7.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_7.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_7);
                        PdfPCell colu_8 = new PdfPCell(new Phrase(rs.getString(8)));
                        colu_8.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_8.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_8);
                        PdfPCell colu_9 = new PdfPCell(new Phrase(rs.getString(9)));
                        colu_9.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_9.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_9);
                        PdfPCell colu_10 = new PdfPCell(new Phrase(rs.getString(10)));
                        colu_10.setVerticalAlignment(Element.ALIGN_CENTER);
                        colu_10.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(colu_10);
                    } while (rs.next());
                    tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
                    documento.add(tabla);
                }
                //documento.
                //documento.add(imagen_abajo);
                documento.close();
                Icon icono_correcto = new ImageIcon(getClass().getResource("/imagenes/correcto.png"));
                JOptionPane.showMessageDialog(null, "Reporte Creado", "Reporte", JOptionPane.INFORMATION_MESSAGE, icono_correcto);
            } catch (Exception e) {
                System.out.println("Error: " + e);
                JOptionPane.showMessageDialog(null, "Error al generar PDF\n"+e, "Error", JOptionPane.ERROR_MESSAGE);
            }
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);

        }
    }//GEN-LAST:event_boton_resportePDFActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(consultasReportes_equipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consultasReportes_equipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consultasReportes_equipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consultasReportes_equipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new consultasReportes_equipos().setVisible(true);
            }
        });
    }

    public void busqueda_ubicaccion() {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barra_busqueda;
    private javax.swing.JComboBox<String> barra_ubicacion;
    private javax.swing.JButton boton_buscar;
    private javax.swing.JButton boton_buscar_ubicacion;
    private javax.swing.JButton boton_regresar;
    private javax.swing.JButton boton_reiniciar;
    private javax.swing.JButton boton_resportePDF;
    private javax.swing.JLabel etiqueta_resporte;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
