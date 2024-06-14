/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Conection_bd;
import static interfaces.loggin.icono;
import java.awt.Color;
import java.awt.Font;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
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
public class verDetalles_tabla extends javax.swing.JFrame implements ClipboardOwner {

    public static int tipo_consulta = 0;
    public static int id_equipo = 0;
    private int tipo_consulta_pdf = 0, columnas_tabla_inicial = 12;
    private String titulo = "";                              //12 campos
    private String consulta_inicial = "select "
            + "e.id_equipo, "
            + "e.tipo_equipo, "
            + "e.ubicacion_equipo, "
            + "e.marca_equipo, "
            + "e.modelo_equipo, "
            + "e.procesador_equipo, "
            + "e.ram_equipo, "
            + "concat(e.disco_equipo,' GB'), "
            + "e.estado_equipo, "
            + "r.nombre_responsable, "
            + "e.perifericos_equipo, "
            + "DATE_ADD(e.ultima_modificacion, interval 1 day) "
            + "from equipos e JOIN responsable r "
            + "ON (e.id_responsable = r.id_responsable) "
            + "where NOT e.estado_equipo = 'BAJA'";
    private String consulta_barra = "select "
            + "e.id_equipo, "
            + "e.tipo_equipo, "
            + "e.ubicacion_equipo, "
            + "e.marca_equipo, "
            + "e.modelo_equipo, "
            + "e.procesador_equipo, "
            + "e.ram_equipo, "
            + "concat(e.disco_equipo,' GB'), "
            + "e.estado_equipo, "
            + "r.nombre_responsable, "
            + "e.perifericos_equipo, "
            + "DATE_ADD(e.ultima_modificacion, interval 1 day) "
            + "from equipos e JOIN responsable r "
            + "ON (e.id_responsable = r.id_responsable) "
            + "where NOT e.estado_equipo = 'BAJA' and e.marca_equipo like '%";

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

    /**
     * Creates new form verDetalles_tabla
     */
    public verDetalles_tabla() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(icono)).getImage());
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));
        tipo_consulta_pdf = 1;
        titulo = "Equipos Informaticos Activos";
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
            modelo_tabla.addColumn("Disco");          //7
            modelo_tabla.addColumn("Estado");             //8
            modelo_tabla.addColumn("Empleado");             //9
            modelo_tabla.addColumn("Perifericos");          //10            
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
        datosTabla();

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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        boton_reiniciar = new javax.swing.JButton();
        boton_regresar = new javax.swing.JButton();
        barra_busqueda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        boton_buscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        barra_ubicacion = new javax.swing.JComboBox<>();
        boton_buscar_ubicacion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ver detalles equipos");

        jPanel1.setBackground(new java.awt.Color(191, 191, 191));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 960, 330));

        boton_reiniciar.setBackground(new java.awt.Color(0, 204, 255));
        boton_reiniciar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_reiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reiniciar_15.png"))); // NOI18N
        boton_reiniciar.setText("Reiniciar tabla");
        boton_reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_reiniciarActionPerformed(evt);
            }
        });
        jPanel1.add(boton_reiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, -1, -1));

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
        jPanel1.add(barra_busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 220, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Buscar por marca:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        boton_buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar_15.png"))); // NOI18N
        boton_buscar.setText("Buscar");
        boton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscarActionPerformed(evt);
            }
        });
        jPanel1.add(boton_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Buscar por ubicacion:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, -1, -1));

        barra_ubicacion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_ubicacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JM TOLUCA", "JM PALMILLAS", "JM ATLACOMULCO", "JM TENANGO", "JM VILLA VICTORIA", "JM TEQUIXQUIAC", "PLANTA SWECOMEX", "BODEGA ISB QUERETARO", "FARMTIRE IXTLAHUACA" }));
        jPanel1.add(barra_ubicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, 210, -1));

        boton_buscar_ubicacion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar_ubicacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar_15.png"))); // NOI18N
        boton_buscar_ubicacion.setText("Buscar");
        boton_buscar_ubicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscar_ubicacionActionPerformed(evt);
            }
        });
        jPanel1.add(boton_buscar_ubicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 60, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void boton_reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_reiniciarActionPerformed
        // TODO add your handling code here:
        modelo_tabla.setRowCount(0);
        modelo_tabla.setColumnCount(0);
        barra_ubicacion.setSelectedIndex(0);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));
        tipo_consulta_pdf = 1;
        titulo = "Equipos Informaticos Activos";
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
            modelo_tabla.addColumn("Disco");          //7
            modelo_tabla.addColumn("Estado");             //8
            modelo_tabla.addColumn("Empleado");             //9
            modelo_tabla.addColumn("Perifericos");          //10            
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
        datosTabla();
    }//GEN-LAST:event_boton_reiniciarActionPerformed

    private void boton_regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_regresarActionPerformed
        menuEquipos p = new menuEquipos();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_regresarActionPerformed

    private void boton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscarActionPerformed
        modelo_tabla.setRowCount(0);
        modelo_tabla.setColumnCount(0);
        barra_ubicacion.setSelectedIndex(0);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));
        tipo_consulta_pdf = 1;
        titulo = "Equipos Informaticos Activos";
        try {
            Connection cn = Conection_bd.conexion_bd();
            PreparedStatement pst = cn.prepareStatement(consulta_barra+ barra_busqueda.getText().toUpperCase().trim() + "%'");
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
            modelo_tabla.addColumn("Disco");          //7
            modelo_tabla.addColumn("Estado");             //8
            modelo_tabla.addColumn("Empleado");             //9
            modelo_tabla.addColumn("Perifericos");          //10            
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
        datosTabla();

    }//GEN-LAST:event_boton_buscarActionPerformed

    private void boton_buscar_ubicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscar_ubicacionActionPerformed

        modelo_tabla.setRowCount(0);
        modelo_tabla.setColumnCount(0);
        //barra_ubicacion.setSelectedIndex(0);
        int ubicacion = barra_ubicacion.getSelectedIndex();
        System.out.println(ubicacion);
        String consulta_ubicacionn = "";
        setIconImage(new ImageIcon(getClass().getResource(icono)).getImage());
        if (ubicacion == 0) {
            consulta_ubicacionn = consultasReportes_equipos.consulta_jmtoluca;
        } else if (ubicacion == 1) {
            consulta_ubicacionn = consultasReportes_equipos.consulta_jmpalmillas;
        } else if (ubicacion == 2) {
            consulta_ubicacionn = consultasReportes_equipos.consulta_jmatlacomulco;
        } else if (ubicacion == 3) {
            consulta_ubicacionn = consultasReportes_equipos.consulta_jmtenango;
        } else if (ubicacion == 4) {
            consulta_ubicacionn = consultasReportes_equipos.consulta_jmvillavictoria;
        } else if (ubicacion == 5) {
            consulta_ubicacionn = consultasReportes_equipos.consulta_jmtequixquiac;
        } else if (ubicacion == 6) {
            consulta_ubicacionn = consultasReportes_equipos.consulta_planta_swecomex;
        } else if (ubicacion == 7) {
            consulta_ubicacionn = consultasReportes_equipos.consulta_bodegaisbqueretaro;
        } else if (ubicacion == 8) {
            consulta_ubicacionn = consultasReportes_equipos.consulta_farmtire;
        }
        System.out.println(consulta_ubicacionn);
        modelo_tabla.setRowCount(0);
        modelo_tabla.setColumnCount(0);
        barra_busqueda.setText("");
        barra_ubicacion.setSelectedIndex(0);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));

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
            modelo_tabla.addColumn("Disco");          //7
            modelo_tabla.addColumn("Estado");             //8
            modelo_tabla.addColumn("Empleado");             //9
            modelo_tabla.addColumn("Perifericos");          //10
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
        datosTabla();
    }//GEN-LAST:event_boton_buscar_ubicacionActionPerformed

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
            java.util.logging.Logger.getLogger(verDetalles_tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(verDetalles_tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(verDetalles_tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(verDetalles_tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new verDetalles_tabla().setVisible(true);
            }
        });
    }

    public void datosTabla() {
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int num_fila = tabla.rowAtPoint(evt.getPoint());
                //aqui se saca el nombre el id porque es columna 0
                int num_columna = 0;
                if (num_fila > -1) {
                    //nombre = (String) modelo_tabla.getValueAt(num_fila, num_columna);
                    id_equipo = (int) modelo_tabla.getValueAt(num_fila, num_columna);
                    System.out.println(modelo_tabla.getValueAt(num_fila, num_columna));
                    //nombre = (int) modelo_tabla.getValueAt(num_fila, num_columna);
                    verDetalles_datos inf = new verDetalles_datos();
                    inf.setVisible(true);
                }
                dispose();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barra_busqueda;
    private javax.swing.JComboBox<String> barra_ubicacion;
    private javax.swing.JButton boton_buscar;
    private javax.swing.JButton boton_buscar_ubicacion;
    private javax.swing.JButton boton_regresar;
    private javax.swing.JButton boton_reiniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

    @Override
    public void lostOwnership(Clipboard clpbrd, Transferable t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
