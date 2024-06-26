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
public class modificarInformacion_equipos_datos extends javax.swing.JFrame {

    public int id_equipo = 0, id = 0;
    private String actualizacion = "update equipos set "
            + "tipo_equipo=?, "
            + "ubicacion_equipo=?, "
            + "marca_equipo=?, "
            + "procesador_equipo=?, "
            + "ram_equipo=?, "
            + "pw_equipo=?, "
            + "disco_equipo=?, "
            + "estado_equipo=?, "
            + "modelo_equipo=?, "
            + "perifericos_equipo=?, "
            + "ultima_modificacion=? "
            + "where id_equipo = '";

    /**
     * Creates new form modificarInformacion_equipos
     */
    public modificarInformacion_equipos_datos() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(icono)).getImage());
        id_equipo = modificarInformacion_equipos_tabla.id_equipo;
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
                    + "e.pw_equipo, "
                    + "e.disco_equipo, "
                    + "e.estado_equipo, "
                    + "e.perifericos_equipo, "
                    + "e.ultima_modificacion "
                    + "from equipos e "
                    + "where e.id_equipo='" + id_equipo + "'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_equipo");
                barra_tipo.setSelectedItem(rs.getString("tipo_equipo"));
                barra_ubicacion.setSelectedItem(rs.getString("ubicacion_equipo"));
                barra_marca.setText(rs.getString("marca_equipo"));
                barra_modelo.setText(rs.getString("modelo_equipo"));
                barra_procesador.setText(rs.getString("procesador_equipo"));
                barra_ram.setSelectedItem(rs.getString("ram_equipo"));
                barra_discoduro.setText(rs.getString("disco_equipo"));
                barra_perifericos.setText(rs.getString("perifericos_equipo"));
                barra_fecha.setText(rs.getString("ultima_modificacion"));
                barra_pw.setText(rs.getString("pw_equipo"));
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
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        barra_discoduro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        barra_tipo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        barra_ram = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        barra_marca = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        boton_aceptar = new javax.swing.JButton();
        barra_procesador = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        boton_buscar = new javax.swing.JButton();
        barra_estado = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        barra_modelo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        barra_perifericos = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        barra_fecha = new javax.swing.JLabel();
        barra_ubicacion = new javax.swing.JComboBox<>();
        barra_pw = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Modificar informacion de equipos");

        jPanel1.setBackground(new java.awt.Color(191, 191, 191));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("*este campo puede dejarse vacio");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, -1, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Tipo de equipo:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 118, -1));

        barra_discoduro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_discoduro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(barra_discoduro, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 210, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Ubicacion:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 98, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Procesador:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 86, -1));

        barra_tipo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PC", "LAPTOP", "ALL IN ONE" }));
        jPanel1.add(barra_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 210, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Memoria ram:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, -1, -1));

        barra_ram.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_ram.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "4", "6", "8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30", "32" }));
        jPanel1.add(barra_ram, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 210, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Disco duro:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 98, -1));

        barra_marca.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_marca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(barra_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 210, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Estado Actual:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, 98, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Marca:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 98, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Ultima modificacion:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 50, 150, -1));

        boton_aceptar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editar_100.png"))); // NOI18N
        boton_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_aceptarActionPerformed(evt);
            }
        });
        jPanel1.add(boton_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(763, 300, 110, 120));

        barra_procesador.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_procesador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(barra_procesador, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 210, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Modificar información");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 270, 210, -1));

        boton_buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/regresar_30.png"))); // NOI18N
        boton_buscar.setText("Regresar");
        boton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscarActionPerformed(evt);
            }
        });
        jPanel1.add(boton_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 35));

        barra_estado.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BUENO", "REGULAR", "MALO" }));
        jPanel1.add(barra_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 430, 210, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Modelo:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 98, 20));

        barra_modelo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_modelo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(barra_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 210, 20));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("*este campo puede dejarse vacio");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Perifericos");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, 98, -1));

        barra_perifericos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_perifericos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(barra_perifericos, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, 210, -1));

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("*este campo puede dejarse vacio");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, -1, -1));

        barra_fecha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        barra_fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_fecha.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 80, 150, 40));

        barra_ubicacion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_ubicacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JM TOLUCA", "JM PALMILLAS", "JM ATLACOMULCO", "JM TENANGO", "JM VILLA VICTORIA", "JM TEQUIXQUIAC", "PLANTA SWECOMEX", "BODEGA ISB QUERETARO", "FARMTIRE IXTLAHUACA" }));
        jPanel1.add(barra_ubicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 210, -1));

        barra_pw.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_pw.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(barra_pw, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 210, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Contraseña:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 86, -1));

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

    private void boton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscarActionPerformed
        // TODO add your handling code here:
        modificarInformacion_equipos_tabla p = new modificarInformacion_equipos_tabla();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_buscarActionPerformed

    private void boton_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_aceptarActionPerformed
        // TODO add your handling code here:
        java.util.Date date = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha_1 = new Date();
        int validacion = 0;
        String tipo, ubicacion, marca, modelo, procesador, ram, disco_duro, estado_actual, perifericos, pw;
        String fecha = "";
        tipo = barra_tipo.getSelectedItem().toString().trim().toUpperCase();
        ubicacion = barra_ubicacion.getSelectedItem().toString().trim().toUpperCase();
        marca = barra_marca.getText().trim().toUpperCase();
        modelo = barra_modelo.getText().trim().toUpperCase();
        procesador = barra_procesador.getText().trim().toUpperCase();
        ram = barra_ram.getSelectedItem().toString().trim();
        disco_duro = barra_discoduro.getText().trim().toUpperCase();
        estado_actual = barra_estado.getSelectedItem().toString().trim().toUpperCase();
        perifericos = barra_perifericos.getText().trim();
        pw = barra_pw.getText().trim();

        fecha = f.format(fecha_1);

        if (ubicacion.equals("")) {
            barra_ubicacion.setBackground(Color.red);
            validacion++;
        }
        if (marca.equals("")) {
            barra_marca.setBackground(Color.cyan);
            marca = "NO APLICA";
        }
        if (modelo.equals("")) {
            barra_modelo.setBackground(Color.cyan);
            modelo = "NO APLICA";
        }
        if (procesador.equals("")) {
            barra_procesador.setBackground(Color.red);
            validacion++;
        }
        if (disco_duro.equals("")) {
            barra_discoduro.setBackground(Color.red);
            validacion++;
        }
        if (pw.equals("")) {
            barra_pw.setBackground(Color.red);
            validacion++;
        }
        if (perifericos.equals("")) {
            barra_perifericos.setBackground(Color.cyan);
            perifericos = "NO APLICA";
        }

        if (validacion == 0) {
            try {
                Connection cn = Conection_bd.conexion_bd();
                PreparedStatement pst = cn.prepareStatement(actualizacion + id + "'");
                pst.setString(1, tipo);
                pst.setString(2, ubicacion);
                pst.setString(3, marca);
                pst.setString(4, procesador);
                pst.setInt(5, Integer.parseInt(ram));
                pst.setString(6, pw);
                pst.setInt(7, Integer.parseInt(disco_duro));
                pst.setString(8, estado_actual);
                pst.setString(9, modelo);
                pst.setString(10, perifericos);
                pst.setString(11, fecha);
                pst.executeUpdate();
                cn.close();

            } catch (Exception e) {
                System.out.println("Error al guardar: " + e);
                //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
                JOptionPane.showMessageDialog(null, "Error al guardar\n"+e, "Error", JOptionPane.ERROR_MESSAGE);
            }
            limpiarCampos();
            this.barra_fecha.setText(fecha);
        } else {
            JOptionPane.showMessageDialog(null, "Debes llenar los campos requeridos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_boton_aceptarActionPerformed

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
            java.util.logging.Logger.getLogger(modificarInformacion_equipos_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modificarInformacion_equipos_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modificarInformacion_equipos_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modificarInformacion_equipos_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new modificarInformacion_equipos_datos().setVisible(true);
            }
        });
    }

    public void limpiarCampos() {
        barra_ubicacion.setBackground(Color.GREEN);
        barra_marca.setBackground(Color.GREEN);
        barra_modelo.setBackground(Color.GREEN);
        barra_procesador.setBackground(Color.GREEN);
        barra_discoduro.setBackground(Color.GREEN);
        barra_perifericos.setBackground(Color.GREEN);
        barra_pw.setBackground(Color.GREEN);

        Icon icono_correcto = new ImageIcon(getClass().getResource("/imagenes/correcto.png"));
        int valor = JOptionPane.showConfirmDialog(null, "Se ha actualizado la informacion correctamente", "Equipo actualizado correctamente", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, icono_correcto);
        //int valor = JOptionPane.showConfirmDialog(null, "Se ha guardado correctamente", "Equipo registrado correctamente", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, icono_correcto);
        if (valor == JOptionPane.YES_OPTION) {

        }
        barra_ubicacion.setBackground(Color.white);
        barra_marca.setBackground(Color.white);
        barra_modelo.setBackground(Color.white);
        barra_procesador.setBackground(Color.white);
        barra_discoduro.setBackground(Color.white);
        barra_perifericos.setBackground(Color.white);
        barra_pw.setBackground(Color.white);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barra_discoduro;
    private javax.swing.JComboBox<String> barra_estado;
    private javax.swing.JLabel barra_fecha;
    private javax.swing.JTextField barra_marca;
    private javax.swing.JTextField barra_modelo;
    private javax.swing.JTextField barra_perifericos;
    private javax.swing.JTextField barra_procesador;
    private javax.swing.JTextField barra_pw;
    private javax.swing.JComboBox<String> barra_ram;
    private javax.swing.JComboBox<String> barra_tipo;
    private javax.swing.JComboBox<String> barra_ubicacion;
    private javax.swing.JButton boton_aceptar;
    private javax.swing.JButton boton_buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
