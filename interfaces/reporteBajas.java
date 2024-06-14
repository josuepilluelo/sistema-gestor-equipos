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
import static interfaces.consultasReportes_equipos.columnas_tabla_inicial;
import static interfaces.consultasReportes_equipos.consulta_inicial;
import static interfaces.consultasReportes_equipos.tipo_consulta_pdf;
import static interfaces.loggin.icono;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.JTable.*;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Josue Lopez
 */
public class reporteBajas extends javax.swing.JFrame {

    private String fecha = "", fecha_2 = "", tipo = "", fecha_pdf_1 = "", fecha_pdf_2 = "";
    public static int tipo_consulta_pdf = 0, columnas_tabla_inicial = 12, columnas_tabla_inicial_pdf = 10;
    private String titulo = "", consulta_pdf = "";                              //12 campos
    public static String consulta_inicial = "select "
            + "ROW_NUMBER() OVER( ORDER BY e.id_equipo) as NP, "
            + "e.tipo_equipo, "
            + "e.ubicacion_equipo, "
            + "e.marca_equipo, "
            + "e.modelo_equipo, "
            + "e.procesador_equipo, "
            + "e.ram_equipo, "
            + "concat(e.disco_equipo,' GB'), "
            + "e.estado_equipo, "
            + "r.nombre_responsable, "
            + "e.motivo_baja, "
            + "DATE_ADD(e.ultima_modificacion, interval 1 day) "
            + "from equipos e JOIN responsable r "
            + "ON (e.id_responsable = r.id_responsable) "
            + "where e.estado_equipo = 'BAJA'";
    public static String consulta_pdf_todos = "select "
            + "ROW_NUMBER() OVER( ORDER BY e.id_equipo) as NP, "
            + "e.tipo_equipo, "
            + "e.ubicacion_equipo, "
            + "e.marca_equipo, "
            + "e.modelo_equipo, "
            + "e.procesador_equipo, "
            + "e.ram_equipo, "
            + "concat(e.disco_equipo,' GB'), "
            + "e.motivo_baja, "
            + "e.ultima_modificacion "
            + "from equipos e JOIN responsable r "
            + "ON (e.id_responsable = r.id_responsable) "
            + "where e.estado_equipo = 'BAJA'";

    public static String consulta_pdf_fecha = "select "
            + "ROW_NUMBER() OVER( ORDER BY e.id_equipo) as NP, "
            + "e.tipo_equipo, "
            + "e.ubicacion_equipo, "
            + "e.marca_equipo, "
            + "e.modelo_equipo, "
            + "e.procesador_equipo, "
            + "e.ram_equipo, "
            + "concat(e.disco_equipo,' GB'), "
            + "e.motivo_baja, "
            + "e.ultima_modificacion "
            + "from equipos e JOIN responsable r "
            + "ON (e.id_responsable = r.id_responsable) ";
    /*
        ("select "
                        + "ROW_NUMBER() OVER( ORDER BY e.id_equipo) as NP, "
                        + "e.id_equipo, "
                        + "e.ubicacion_equipo, "
                        + "e.marca_equipo, "
                        + "e.modelo_equipo, "
                        + "e.procesador_equipo, "
                        + "e.ram_equipo, "
                        + "concat(e.disco_equipo,' GB'), "
                        + "e.estado_equipo, "
                        + "r.nombre_responsable, "
                        + "e.motivo_baja "
        + "DATE_ADD(e.ultima_modificacion, interval 1 day) "
                        + "from equipos e JOIN responsable r "
                        + "ON (e.id_responsable = r.id_responsable) "
                        + "where ultima_modificacion BETWEEN '" + fecha + "' AND '" + fecha_2 + "' "
                        + "and e.estado_equipo = 'BAJA' "
                        + "ORDER BY id_equipo");*/

    DefaultTableModel modelo_tabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            //ya no seran editadas
            if (columnas > 0 && filas > 0) {
                return true;
            }
            return true;

        }

    };

    /**
     * Creates new form reporteBajas
     */
    public reporteBajas() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(icono)).getImage());
        etiqueta_resporte.setText("Tipo de reporte activo: TODAS LAS BAJAS SOBRE EQUIPOS");
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

            modelo_tabla.addColumn("NP");                   //0
            modelo_tabla.addColumn("Tipo de Equipo");      //1
            modelo_tabla.addColumn("Ubicacion");            //2
            modelo_tabla.addColumn("Marca");                 //3
            modelo_tabla.addColumn("Modelo");               //4
            modelo_tabla.addColumn("Procesador");            //5
            modelo_tabla.addColumn("Ram");                  //6
            modelo_tabla.addColumn("Disco");          //7
            modelo_tabla.addColumn("Estado");             //8
            modelo_tabla.addColumn("Empleado");             //9
            modelo_tabla.addColumn("Motivo de baja");          //10            
            modelo_tabla.addColumn("Ultima modificacion");   //11
            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(220);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(75);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(10).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(11).setPreferredWidth(150);

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
        jLabel10 = new javax.swing.JLabel();
        boton_consultar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        barra_fecha_1 = new com.toedter.calendar.JDateChooser();
        barra_fecha_2 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        boton_regresar = new javax.swing.JButton();
        boton_reporte = new javax.swing.JButton();
        etiqueta_resporte = new javax.swing.JLabel();
        botn_reiniciar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Reporte de bajas");
        setPreferredSize(new java.awt.Dimension(1000, 500));

        jPanel1.setBackground(new java.awt.Color(191, 191, 191));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 500));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("*en caso de que la fecha se HOY no selecciones nada");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, -1, -1));

        boton_consultar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar_30.png"))); // NOI18N
        boton_consultar.setText("Consultar");
        boton_consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_consultarActionPerformed(evt);
            }
        });
        jPanel1.add(boton_consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(688, 41, 191, 43));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "N.P", "No. Inventario", "Descripcion", "Estado Actual", "Marca", "Valor", "Ultima Modificacion"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 930, 280));

        barra_fecha_1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel1.add(barra_fecha_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 64, 130, -1));

        barra_fecha_2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel1.add(barra_fecha_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 64, 130, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Fecha de Termino:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 41, 130, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Fecha Inicial:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 41, 130, -1));

        boton_regresar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/regresar_30.png"))); // NOI18N
        boton_regresar.setText("Regresar");
        boton_regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_regresarActionPerformed(evt);
            }
        });
        jPanel1.add(boton_regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 35));

        boton_reporte.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_reporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pdf_25.png"))); // NOI18N
        boton_reporte.setText("Generar Reporte");
        boton_reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_reporteActionPerformed(evt);
            }
        });
        jPanel1.add(boton_reporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, -1, 46));

        etiqueta_resporte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etiqueta_resporte.setText("Tipo de reporte activo:");
        jPanel1.add(etiqueta_resporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 420, 618, -1));

        botn_reiniciar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        botn_reiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reiniciar_15.png"))); // NOI18N
        botn_reiniciar.setText("Reiniciar tabla");
        botn_reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botn_reiniciarActionPerformed(evt);
            }
        });
        jPanel1.add(botn_reiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 52, 150, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void boton_consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_consultarActionPerformed
        // TODO add your handling code here:
        java.util.Date date = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha_1 = new Date();

        //--------------------------fecha para boton-------------------------------
        java.util.Date datee = new java.util.Date();
        SimpleDateFormat fechaa = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha_cambiada = new Date();

        fecha_pdf_1 = fechaa.format(fecha_cambiada);

        tipo_consulta_pdf = 2;

        if (barra_fecha_1.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Error debes elegir una fecha inicial ", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (barra_fecha_2.getDate() == null) {
                fecha_2 = f.format(fecha_1);
                fecha_pdf_2 = fechaa.format(fecha_1);
            } else {
                fecha_2 = f.format(barra_fecha_2.getDate());
                fecha_pdf_2 = fechaa.format(barra_fecha_2.getDate());
            }
            fecha = f.format(barra_fecha_1.getDate());
            fecha_pdf_1 = fechaa.format(barra_fecha_1.getDate());
//                titulo = "Reporte de Bajas Bienes Muebles";
//                tipo = "MUEBLE";
            System.out.println("Tipo de reporte activo: BAJAS de " + fecha_pdf_1 + " a " + fecha_pdf_2);
            etiqueta_resporte.setText("Tipo de reporte activo: BAJAS del ■■ " + fecha_pdf_1 + " ■■  al  ■■ " + fecha_pdf_2 + " ■■");
            modelo_tabla.setRowCount(0);
            modelo_tabla.setColumnCount(0);
            DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
            cellRenderer.setBackground(new Color(51, 102, 255));
            try {
                Connection cn1 = Conection_bd.conexion_bd();//bienes informaticos
                PreparedStatement pst1 = cn1.prepareStatement("select "
                        + "ROW_NUMBER() OVER( ORDER BY e.id_equipo) as NP, "
                        + "e.tipo_equipo, "
                        + "e.ubicacion_equipo, "
                        + "e.marca_equipo, "
                        + "e.modelo_equipo, "
                        + "e.procesador_equipo, "
                        + "e.ram_equipo, "
                        + "concat(e.disco_equipo,' GB'), "
                        + "e.estado_equipo, "
                        + "r.nombre_responsable, "
                        + "e.motivo_baja, "
                        + "DATE_ADD(e.ultima_modificacion, interval 1 day) "
                        + "from equipos e JOIN responsable r "
                        + "ON (e.id_responsable = r.id_responsable) "
                        + "where ultima_modificacion BETWEEN '" + fecha + "' AND '" + fecha_2 + "' "
                        + "and e.estado_equipo = 'BAJA' "
                        + "ORDER BY id_equipo");
                System.out.println(barra_fecha_1.getDate());
                System.out.println(barra_fecha_2.getDate());
                // + "where descripcion_bien like '%" + barra_busqueda.getText().trim() + "%' AND b.estatus = 'NO ACTIVO' "
                // + "ORDER BY b.no_inventario");
                ResultSet rs1 = pst1.executeQuery();
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

                modelo_tabla.addColumn("NP");                   //0
                modelo_tabla.addColumn("Tipo de Equipo");      //1
                modelo_tabla.addColumn("Ubicacion");            //2
                modelo_tabla.addColumn("Marca");                 //3
                modelo_tabla.addColumn("Modelo");               //4
                modelo_tabla.addColumn("Procesador");            //5
                modelo_tabla.addColumn("Ram");                  //6
                modelo_tabla.addColumn("Disco");          //7
                modelo_tabla.addColumn("Estado");             //8
                modelo_tabla.addColumn("Empleado");             //9
                modelo_tabla.addColumn("Motivo de baja");          //10            
                modelo_tabla.addColumn("Ultima modificacion");   //11 
                tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
                tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(300);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(220);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(7).setPreferredWidth(75);
                tabla.getColumnModel().getColumn(8).setPreferredWidth(120);
                tabla.getColumnModel().getColumn(9).setPreferredWidth(300);
                tabla.getColumnModel().getColumn(10).setPreferredWidth(150);
                tabla.getColumnModel().getColumn(11).setPreferredWidth(150);

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
                while (rs1.next()) {
                    Object[] fila = new Object[columnas_tabla_inicial];//por las columnas que tengo
                    for (int i = 0; i < columnas_tabla_inicial; i++) {
                        fila[i] = rs1.getObject(i + 1);

                        //centra el contenido de la tabla
                        tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
                        //cambiamos el color del emcabezado y centrado
                        TableColumn column = tabla.getTableHeader().getColumnModel().getColumn(i);
                        column.setHeaderRenderer(cellRenderer);
                        //formato encabezado
                        cellRenderer.setFont(new Font("Arial", Font.BOLD, 11));
                        cellRenderer.setHorizontalAlignment(cellRenderer.CENTER);
                        cellRenderer.setForeground(Color.white);
                    }
                    tabla.getTableHeader().setBackground(new Color(255, 0, 0));
                    modelo_tabla.addRow(fila);
                    ((DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
                }

                cn1.close();
            } catch (Exception e) {
                System.out.println("Error al llenar tabla: " + e);
                //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
                JOptionPane.showMessageDialog(null, "Error al mostrar informacion \n"+e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        /*
        select
        ROW_NUMBER() OVER( ORDER BY id_bien) as NP,
        no_inventario,
        descripcion_bien,
        estado_actual, marca,
        concat('$ ',valor_estimado)
        from bienes
        where ultima_modificacion BETWEEN '2021-02-01' AND '2022-02-10'
        and tipo_info_mueble = 'MUEBLE'
        and estatus = 'NO ACTIVO'
         */
        //boton_reporte.setEnabled(true);//desabilidatmos el botn de reporte
    }//GEN-LAST:event_boton_consultarActionPerformed

    private void boton_regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_regresarActionPerformed
        consultasReportes_equipos p = new consultasReportes_equipos();
        p.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_boton_regresarActionPerformed

    private void boton_reporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_reporteActionPerformed
        // TODO add your handling code here:
        JFileChooser jfc = new JFileChooser();
        int Opcion = jfc.showSaveDialog(this);
        if (tipo_consulta_pdf == 1) {
            titulo = "Reporte bajas";
            consulta_pdf = consulta_pdf_todos;
        } else if (tipo_consulta_pdf == 2) {
            titulo = "Reporte bajas de  " + fecha_pdf_1 + "  al  " + fecha_pdf_2;
            consulta_pdf = consulta_pdf_fecha
                    + "where ultima_modificacion BETWEEN '" + fecha + "' AND '" + fecha_2 + "' "
                    + "and e.estado_equipo = 'BAJA' "
                    + "ORDER BY id_equipo";
        }
        if (Opcion == JFileChooser.APPROVE_OPTION) {
            File f = jfc.getSelectedFile();
            System.out.println(f);

            float[] margenCeldas = new float[]{2f, 5f, 5f, 5f, 5f, 4f, 2f, 3f, 7f, 5f};//este arreglo sirve para las medidas que tengran cada una de las columnas si son 10 columnas son 1o numero
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
                parrafo.add(titulo + " " + "\n\n");
                //abre el documento
                documento.open();
                //agregamos imagen y parrfos
                documento.add(imagen_cabecera);
                documento.add(parrafo);
                //numero de columnas

                PdfPTable tabla = new PdfPTable(columnas_tabla_inicial_pdf);
                tabla.setWidthPercentage(95);//el porcentaje total de ancho del documento tomara
                tabla.setWidths(margenCeldas);//aqui va el arreglo
                /*
                            + "ROW_NUMBER() OVER( ORDER BY e.id_equipo) as NP, "
            + "e.tipo_equipo, "
            + "e.ubicacion_equipo, "
            + "e.marca_equipo, "
            + "e.modelo_equipo, "
            + "e.procesador_equipo, "
            + "e.ram_equipo, "
            + "concat(e.disco_equipo,' GB'), "
            + "e.motivo_baja, "
            + "DATE_ADD(e.ultima_modificacion, interval 1 day) "
                 */
                PdfPCell columna_1 = new PdfPCell(new Phrase("NP"));//aqui podrimos modificar las celdas
                columna_1.setVerticalAlignment(Element.ALIGN_CENTER);//alineados horizontales y verticales
                columna_1.setHorizontalAlignment(Element.ALIGN_CENTER);
                columna_1.setBackgroundColor(new BaseColor(183, 183, 183));//color de fondo
                columna_1.setFixedHeight(20);//alto de la celda que se le aplica a todas de la fila
                tabla.addCell(columna_1);
                PdfPCell columna_2 = new PdfPCell(new Phrase("Tipo"));
                columna_2.setVerticalAlignment(Element.ALIGN_CENTER);
                columna_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                columna_2.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(columna_2);
                PdfPCell columna_3 = new PdfPCell(new Phrase("Ubicacion"));
                columna_3.setVerticalAlignment(Element.ALIGN_CENTER);
                columna_3.setHorizontalAlignment(Element.ALIGN_CENTER);
                columna_3.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(columna_3);
                PdfPCell columna_4 = new PdfPCell(new Phrase("Marca"));
                columna_4.setVerticalAlignment(Element.ALIGN_CENTER);
                columna_4.setHorizontalAlignment(Element.ALIGN_CENTER);
                columna_4.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(columna_4);
                PdfPCell columna_5 = new PdfPCell(new Phrase("Modelo"));
                columna_5.setVerticalAlignment(Element.ALIGN_CENTER);
                columna_5.setHorizontalAlignment(Element.ALIGN_CENTER);
                columna_5.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(columna_5);
                PdfPCell columna_6 = new PdfPCell(new Phrase("CPU"));
                columna_6.setVerticalAlignment(Element.ALIGN_CENTER);
                columna_6.setHorizontalAlignment(Element.ALIGN_CENTER);
                columna_6.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(columna_6);
                //tabla.setWidths(margenCeldas);//aqui va el arreglo
                PdfPCell columna_7 = new PdfPCell(new Phrase("Ram"));//aqui podrimos modificar las celdas
                columna_7.setVerticalAlignment(Element.ALIGN_CENTER);//alineados horizontales y verticales
                columna_7.setHorizontalAlignment(Element.ALIGN_CENTER);
                columna_7.setBackgroundColor(new BaseColor(183, 183, 183));//color de fondo
                columna_7.setFixedHeight(20);//alto de la celda que se le aplica a todas de la fila
                tabla.addCell(columna_7);
                PdfPCell columna_8 = new PdfPCell(new Phrase("Disco duro"));
                columna_8.setVerticalAlignment(Element.ALIGN_CENTER);
                columna_8.setHorizontalAlignment(Element.ALIGN_CENTER);
                columna_8.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(columna_8);
                PdfPCell columna_9 = new PdfPCell(new Phrase("Motivo baja"));
                columna_9.setVerticalAlignment(Element.ALIGN_CENTER);
                columna_9.setHorizontalAlignment(Element.ALIGN_CENTER);
                columna_9.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(columna_9);
                PdfPCell columna_10 = new PdfPCell(new Phrase("Fecha baja"));
                columna_10.setVerticalAlignment(Element.ALIGN_CENTER);
                columna_10.setHorizontalAlignment(Element.ALIGN_CENTER);
                columna_10.setBackgroundColor(new BaseColor(183, 183, 183));
                tabla.addCell(columna_10);

                Connection cn = Conection_bd.conexion_bd();
                PreparedStatement pst = cn.prepareStatement(consulta_pdf);
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
    }//GEN-LAST:event_boton_reporteActionPerformed

    private void botn_reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botn_reiniciarActionPerformed
        // TODO add your handling code here:
        modelo_tabla.setRowCount(0);
        modelo_tabla.setColumnCount(0);
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

            modelo_tabla.addColumn("NP");                   //0
            modelo_tabla.addColumn("Tipo de Equipo");      //1
            modelo_tabla.addColumn("Ubicacion");            //2
            modelo_tabla.addColumn("Marca");                 //3
            modelo_tabla.addColumn("Modelo");               //4
            modelo_tabla.addColumn("Procesador");            //5
            modelo_tabla.addColumn("Ram");                  //6
            modelo_tabla.addColumn("Disco");          //7
            modelo_tabla.addColumn("Estado");             //8
            modelo_tabla.addColumn("Empleado");             //9
            modelo_tabla.addColumn("Motivo de baja");          //10            
            modelo_tabla.addColumn("Ultima modificacion");   //11
            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(220);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(75);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(10).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(11).setPreferredWidth(150);

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
    }//GEN-LAST:event_botn_reiniciarActionPerformed

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
            java.util.logging.Logger.getLogger(reporteBajas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reporteBajas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reporteBajas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reporteBajas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reporteBajas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser barra_fecha_1;
    private com.toedter.calendar.JDateChooser barra_fecha_2;
    private javax.swing.JButton botn_reiniciar;
    private javax.swing.JButton boton_consultar;
    private javax.swing.JButton boton_regresar;
    private javax.swing.JButton boton_reporte;
    private javax.swing.JLabel etiqueta_resporte;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
