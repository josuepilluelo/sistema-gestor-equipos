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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Josue Lopez
 */
public class bajaEquipos_datos extends javax.swing.JFrame {

    public int id_equipo = 0, id = 0;
    private String actualizacion = "update equipos set "
            + "estado_equipo=?, "
            + "motivo_baja=?, "
            + "ultima_modificacion=? "
            + "where id_equipo = '";

    /**
     * Creates new form bajaEquipos_datos
     */
    public bajaEquipos_datos() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(icono)).getImage());
        id_equipo = bajaEquipos_tabla.id_equipo;
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(new Color(51, 102, 255));
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
                    + "disco_equipo, "
                    + "e.estado_equipo, "
                    + "e.id_responsable, "
                    + "e.perifericos_equipo, "
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
                barra_perifericos.setText(rs.getString("perifericos_equipo"));
                barra_fecha.setText(rs.getString("ultima_modificacion"));

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
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        boton_aceptar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        boton_buscar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        barra_fecha = new javax.swing.JLabel();
        barra_perifericos = new javax.swing.JLabel();
        barra_disco = new javax.swing.JLabel();
        barra_ram = new javax.swing.JLabel();
        barra_procesador = new javax.swing.JLabel();
        barra_modelo = new javax.swing.JLabel();
        barra_marca = new javax.swing.JLabel();
        barra_ubicacion = new javax.swing.JLabel();
        barra_tipo = new javax.swing.JLabel();
        barra_estado = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Baja equipos");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 500));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Tipo de equipo:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 118, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Ubicacion:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 98, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Procesador:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 86, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Memoria ram:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Disco duro:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 98, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Motivo de baja:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, 98, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Marca:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 98, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Ultima modificacion:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 40, 150, -1));

        boton_aceptar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/baja_100.png"))); // NOI18N
        boton_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_aceptarActionPerformed(evt);
            }
        });
        jPanel1.add(boton_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 290, 100, 100));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Dar de baja");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 260, 110, -1));

        boton_buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar.setText("Regresar");
        boton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscarActionPerformed(evt);
            }
        });
        jPanel1.add(boton_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 102, 35));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Modelo:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 98, 20));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Perifericos:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 98, -1));

        barra_fecha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        barra_fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_fecha.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 70, 150, 40));

        barra_perifericos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_perifericos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_perifericos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_perifericos, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 210, 30));

        barra_disco.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_disco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_disco.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_disco, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 210, 30));

        barra_ram.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_ram.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_ram.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_ram, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 210, 30));

        barra_procesador.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_procesador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_procesador.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_procesador, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 210, 30));

        barra_modelo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_modelo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_modelo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 210, 30));

        barra_marca.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_marca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_marca.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 210, 30));

        barra_ubicacion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_ubicacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_ubicacion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_ubicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 210, 30));

        barra_tipo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_tipo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_tipo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 210, 30));

        barra_estado.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "OBSOLESCENCIA", "DAÑO IRREPARABLE", "ROBO O PERDIDA" }));
        jPanel1.add(barra_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 210, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void boton_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_aceptarActionPerformed
        // TODO add your handling code here:
        java.util.Date date = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha_1 = new Date();
        int validacion = 0;
        String fecha = "", motivo = barra_estado.getSelectedItem().toString().trim().toUpperCase();
        if (motivo.equals("OBSOLESCENCIA") || motivo.equals("DAÑO IRREPARABLE") || motivo.equals("ROBO O PERDIDA")) {
            fecha = f.format(fecha_1);
            System.out.println("entro");
            try {

                Connection cn = Conection_bd.conexion_bd();
                PreparedStatement pst = cn.prepareStatement(actualizacion + id + "'");
                pst.setString(1, "BAJA");
                pst.setString(2, motivo);
                pst.setString(3, fecha);
                pst.executeUpdate();
                cn.close();
            } catch (Exception e) {
                System.out.println("Error al guardar: " + e);
                //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
                JOptionPane.showMessageDialog(null, "Error al dar de baja \n"+e, "Error", JOptionPane.ERROR_MESSAGE);
            }
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Debes elegir un motivo de baja", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_boton_aceptarActionPerformed

    private void boton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscarActionPerformed
        // TODO add your handling code here:
        bajaEquipos_tabla p = new bajaEquipos_tabla();
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
            java.util.logging.Logger.getLogger(bajaEquipos_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bajaEquipos_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bajaEquipos_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bajaEquipos_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bajaEquipos_datos().setVisible(true);
            }
        });
    }

    public void limpiarCampos() {
        Icon icono_correcto = new ImageIcon(getClass().getResource("/imagenes/correcto.png"));
        int valor = JOptionPane.showConfirmDialog(null, "Se ha actualizado la informacion correctamente", "Baja de equipo", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, icono_correcto);
        if (valor == JOptionPane.YES_OPTION) {

        }

        bajaEquipos_tabla p = new bajaEquipos_tabla();
        p.setVisible(true);
        this.dispose();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel barra_disco;
    private javax.swing.JComboBox<String> barra_estado;
    private javax.swing.JLabel barra_fecha;
    private javax.swing.JLabel barra_marca;
    private javax.swing.JLabel barra_modelo;
    private javax.swing.JLabel barra_perifericos;
    private javax.swing.JLabel barra_procesador;
    private javax.swing.JLabel barra_ram;
    private javax.swing.JLabel barra_tipo;
    private javax.swing.JLabel barra_ubicacion;
    private javax.swing.JButton boton_aceptar;
    private javax.swing.JButton boton_buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}