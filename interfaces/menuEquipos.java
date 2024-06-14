/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import static interfaces.loggin.icono;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Josue Lopez
 */
public class menuEquipos extends javax.swing.JFrame {

    /**
     * Creates new form menu_principal
     */
    public menuEquipos() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(icono)).getImage());
        boton_transparente(boton_altas_equipos);
        boton_transparente(boton_bajas_equipos);
        boton_transparente(boton_modificar_equipos);
        boton_transparente(boton_consulta);
        boton_transparente(boton_consultas_mantenimientos);
        boton_transparente(boton_detalles);
        boton_transparente(boton_asignacion);
        boton_transparente(boton_registro_mantenimientos);
        
        
        

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
        boton_modificar_equipos = new javax.swing.JButton();
        boton_altas_equipos = new javax.swing.JButton();
        boton_bajas_equipos = new javax.swing.JButton();
        boton_asignacion = new javax.swing.JButton();
        boton_consulta = new javax.swing.JButton();
        boton_detalles = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        boton_registro_mantenimientos = new javax.swing.JButton();
        boton_consultas_mantenimientos = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        boton_regresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Equipos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(191, 191, 191));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        boton_modificar_equipos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        boton_modificar_equipos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editar_125.png"))); // NOI18N
        boton_modificar_equipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_modificar_equiposActionPerformed(evt);
            }
        });
        jPanel1.add(boton_modificar_equipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 90, 125, 125));

        boton_altas_equipos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        boton_altas_equipos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/alta_e_125.png"))); // NOI18N
        boton_altas_equipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_altas_equiposActionPerformed(evt);
            }
        });
        jPanel1.add(boton_altas_equipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 125, 125));

        boton_bajas_equipos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        boton_bajas_equipos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/baja_ee_125.png"))); // NOI18N
        boton_bajas_equipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_bajas_equiposActionPerformed(evt);
            }
        });
        jPanel1.add(boton_bajas_equipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 125, 125));

        boton_asignacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        boton_asignacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/asignacion_125.png"))); // NOI18N
        boton_asignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_asignacionActionPerformed(evt);
            }
        });
        jPanel1.add(boton_asignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, 125, 125));

        boton_consulta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        boton_consulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/consultas_reportes_125.png"))); // NOI18N
        boton_consulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_consultaActionPerformed(evt);
            }
        });
        jPanel1.add(boton_consulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 125, 125));

        boton_detalles.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        boton_detalles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar_125.png"))); // NOI18N
        boton_detalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_detallesActionPerformed(evt);
            }
        });
        jPanel1.add(boton_detalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 125, 125));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Baja de equipos");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        boton_registro_mantenimientos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        boton_registro_mantenimientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mante_125.png"))); // NOI18N
        boton_registro_mantenimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_registro_mantenimientosActionPerformed(evt);
            }
        });
        jPanel1.add(boton_registro_mantenimientos, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 310, 125, 125));

        boton_consultas_mantenimientos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        boton_consultas_mantenimientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/consultas mantenimientos_125.png"))); // NOI18N
        boton_consultas_mantenimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_consultas_mantenimientosActionPerformed(evt);
            }
        });
        jPanel1.add(boton_consultas_mantenimientos, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 310, 125, 125));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Consultas y reportes");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("Modificar informacion");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Ver detalles");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Asignacion de equipos");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setText("Consultas Mantenimientos");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 270, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("Registro Mantenimientos");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 270, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel10.setText("Alta de equipos");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        boton_regresar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/regresar_30.png"))); // NOI18N
        boton_regresar.setText("Regresar");
        boton_regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_regresarActionPerformed(evt);
            }
        });
        jPanel1.add(boton_regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 35));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void boton_altas_equiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_altas_equiposActionPerformed
        // TODO add your handling code here:
        altaEquipos mbm = new altaEquipos();
        mbm.setVisible(true);
        dispose();
    }//GEN-LAST:event_boton_altas_equiposActionPerformed

    private void boton_modificar_equiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_modificar_equiposActionPerformed
        // TODO add your handling code here:
        modificarInformacion_equipos_tabla mbm = new modificarInformacion_equipos_tabla();
        mbm.setVisible(true);
        dispose();
    }//GEN-LAST:event_boton_modificar_equiposActionPerformed

    private void boton_bajas_equiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_bajas_equiposActionPerformed
        // TODO add your handling code here:
        bajaEquipos_tabla baj = new bajaEquipos_tabla();
        baj.setVisible(true);
        dispose();
    }//GEN-LAST:event_boton_bajas_equiposActionPerformed

    private void boton_consultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_consultaActionPerformed
        // TODO add your handling code here:
        consultasReportes_equipos con = new consultasReportes_equipos();
        con.setVisible(true);
        dispose();
    }//GEN-LAST:event_boton_consultaActionPerformed

    private void boton_asignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_asignacionActionPerformed
        // TODO add your handling code here:
        asignacionequipos_tabla1 con = new asignacionequipos_tabla1();
        con.setVisible(true);
        dispose();
    }//GEN-LAST:event_boton_asignacionActionPerformed

    private void boton_detallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_detallesActionPerformed
        // TODO add your handling code here:
        verDetalles_tabla con = new verDetalles_tabla();
        con.setVisible(true);
        dispose();
    }//GEN-LAST:event_boton_detallesActionPerformed

    private void boton_registro_mantenimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_registro_mantenimientosActionPerformed
        // TODO add your handling code here:
        registroMantenimientos_tabla re = new registroMantenimientos_tabla();
        re.setVisible(true);
        dispose();
    }//GEN-LAST:event_boton_registro_mantenimientosActionPerformed

    private void boton_consultas_mantenimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_consultas_mantenimientosActionPerformed
        // TODO add your handling code here:
        consultasMantenimientos_tabla re = new consultasMantenimientos_tabla();
        re.setVisible(true);
        dispose();
    }//GEN-LAST:event_boton_consultas_mantenimientosActionPerformed

    private void boton_regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_regresarActionPerformed

        menuPrincipal p = new menuPrincipal();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_regresarActionPerformed

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
            java.util.logging.Logger.getLogger(menuEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuEquipos().setVisible(true);
            }
        });
    }
    public static void boton_transparente(JButton boton) {
        boton.setOpaque(true);
        boton.setContentAreaFilled(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_altas_equipos;
    private javax.swing.JButton boton_asignacion;
    private javax.swing.JButton boton_bajas_equipos;
    private javax.swing.JButton boton_consulta;
    private javax.swing.JButton boton_consultas_mantenimientos;
    private javax.swing.JButton boton_detalles;
    private javax.swing.JButton boton_modificar_equipos;
    private javax.swing.JButton boton_registro_mantenimientos;
    private javax.swing.JButton boton_regresar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}