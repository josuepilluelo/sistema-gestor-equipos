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
public class consultasMantenimientos_datos extends javax.swing.JFrame {

    public int id_equipo = 0, id = 0;
    public static int columnas_tabla_inicial = 5;
    private String consulta_mantemienientos = "select "
            + "e.id_matenimiento, "
            + "e.servicios, "
            + "e.responsable_servicio, "
            + "e.comentarios, "
            + "DATE_ADD(e.fecha_mantenimiento, interval 1 day) "
            + "from mantenimiento_equipo e "
            + "where e.id_equipo = '";

    /**
     * Creates new form consultasMantenimientos_datos
     */
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

    public consultasMantenimientos_datos() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(icono)).getImage());
        id_equipo = consultasMantenimientos_tabla.id_equipo;
        try {
            Connection cn = Conection_bd.conexion_bd();
            PreparedStatement pst = cn.prepareStatement("select "
                    + "e.id_equipo, "
                    + "e.tipo_equipo, "
                    + "e.ubicacion_equipo, "
                    + "e.marca_equipo, "
                    + "e.modelo_equipo, "
                    + "e.procesador_equipo, "
                    + "e.ram_equipo, "
                    + "e.disco_equipo, "
                    + "e.estado_equipo, "
                    + "e.ultima_modificacion "
                    + "from equipos e "
                    + "where e.id_equipo='" + id_equipo + "'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_equipo");
                barra_tipo.setText(rs.getString("tipo_equipo"));
                barra_ubicacion.setText(rs.getString("ubicacion_equipo"));
                barra_marca.setText(rs.getString("marca_equipo"));
                barra_modelo.setText(rs.getString("modelo_equipo"));
                barra_procesador.setText(rs.getString("procesador_equipo"));
                barra_ram.setText(rs.getString("ram_equipo"));
                barra_disco.setText(rs.getString("disco_equipo"));
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Error al llenar tabla de equipos: " + e);
            //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion sobre equipos\n"+e, "Error", JOptionPane.ERROR_MESSAGE);
        }

        ////////////////////////////////////mantenimientos///////////////////////////////////////////////////
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));

        try {
            Connection cn = Conection_bd.conexion_bd();
            PreparedStatement pst = cn.prepareStatement(consulta_mantemienientos + id_equipo + "'");
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
            modelo_tabla.addColumn("Servicios");      //1
            modelo_tabla.addColumn("Responsable");            //2
            modelo_tabla.addColumn("Comentarios");                 //3
            modelo_tabla.addColumn("Fecha");               //4
            tabla.setAutoResizeMode(AUTO_RESIZE_OFF);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(1000);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(400);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(150);

            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        boton_buscar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        barra_disco = new javax.swing.JLabel();
        barra_ram = new javax.swing.JLabel();
        barra_procesador = new javax.swing.JLabel();
        barra_modelo = new javax.swing.JLabel();
        barra_marca = new javax.swing.JLabel();
        barra_ubicacion = new javax.swing.JLabel();
        barra_tipo = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consulta mantenimientos");

        jPanel1.setBackground(new java.awt.Color(191, 191, 191));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 500));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Tipo de equipo:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 118, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Ubicacion:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 80, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Procesador:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 86, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Memoria ram:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 120, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Disco duro:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 90, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Marca:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 50, -1));

        boton_buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/regresar_30.png"))); // NOI18N
        boton_buscar.setText("Regresar");
        boton_buscar.setPreferredSize(new java.awt.Dimension(130, 25));
        boton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscarActionPerformed(evt);
            }
        });
        jPanel1.add(boton_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 35));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Modelo:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 80, 60, 20));

        barra_disco.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_disco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_disco.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_disco, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 210, 30));

        barra_ram.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_ram.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_ram.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_ram, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, 140, 30));

        barra_procesador.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_procesador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_procesador.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_procesador, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 210, 30));

        barra_modelo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_modelo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_modelo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, 210, 30));

        barra_marca.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_marca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_marca.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 210, 30));

        barra_ubicacion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_ubicacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_ubicacion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_ubicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 210, 30));

        barra_tipo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_tipo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_tipo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 210, 30));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Detalles del equipo");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 930, 260));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void boton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscarActionPerformed
        // TODO add your handling code here:
        consultasMantenimientos_tabla p = new consultasMantenimientos_tabla();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_buscarActionPerformed

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
            java.util.logging.Logger.getLogger(consultasMantenimientos_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consultasMantenimientos_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consultasMantenimientos_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consultasMantenimientos_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new consultasMantenimientos_datos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel barra_disco;
    private javax.swing.JLabel barra_marca;
    private javax.swing.JLabel barra_modelo;
    private javax.swing.JLabel barra_procesador;
    private javax.swing.JLabel barra_ram;
    private javax.swing.JLabel barra_tipo;
    private javax.swing.JLabel barra_ubicacion;
    private javax.swing.JButton boton_buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
