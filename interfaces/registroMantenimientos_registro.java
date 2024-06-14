/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Conection_bd;
import static interfaces.loggin.icono;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;

/**
 *
 * @author Josue Lopez
 */
public class registroMantenimientos_registro extends javax.swing.JFrame {

    public int id_equipo = 0, id = 0;
    private String insert = "insert into mantenimiento_equipo values(?,?,?,?,?,?)";

    /**
     * Creates new form registroMantenimientos_registro
     */
    public registroMantenimientos_registro() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource(icono)).getImage());
        id_equipo = registroMantenimientos_tabla.id_equipo;
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
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        opcion_2 = new javax.swing.JCheckBox();
        opcion_3 = new javax.swing.JCheckBox();
        opcion_4 = new javax.swing.JCheckBox();
        opcion_5 = new javax.swing.JCheckBox();
        opcion_7 = new javax.swing.JCheckBox();
        opcion_8 = new javax.swing.JCheckBox();
        opcion_9 = new javax.swing.JCheckBox();
        opcion_10 = new javax.swing.JCheckBox();
        opcion_11 = new javax.swing.JCheckBox();
        opcion_12 = new javax.swing.JCheckBox();
        opcion_13 = new javax.swing.JCheckBox();
        opcion_14 = new javax.swing.JCheckBox();
        opcion_16 = new javax.swing.JCheckBox();
        opcion_17 = new javax.swing.JCheckBox();
        opcion_6 = new javax.swing.JCheckBox();
        opcion_1 = new javax.swing.JCheckBox();
        opcion_15 = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        barra_responsable = new javax.swing.JTextField();
        barra_tipo_mantenimiento = new javax.swing.JComboBox<>();
        barra_comentario = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        barra_fecha1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro de mantenimiento");

        jPanel1.setBackground(new java.awt.Color(191, 191, 191));
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

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Ultima modificacion:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 220, 150, -1));

        boton_aceptar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/alta_100.png"))); // NOI18N
        boton_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_aceptarActionPerformed(evt);
            }
        });
        jPanel1.add(boton_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 360, 90, 85));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Registrar");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 330, 130, -1));

        boton_buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        boton_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/regresar_30.png"))); // NOI18N
        boton_buscar.setText("Regresar");
        boton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscarActionPerformed(evt);
            }
        });
        jPanel1.add(boton_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 35));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Modelo:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 80, 60, 20));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Perifericos:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 98, -1));

        barra_fecha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        barra_fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_fecha.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 250, 150, 40));

        barra_perifericos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_perifericos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barra_perifericos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(barra_perifericos, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 210, 30));

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

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Especificacion de Servicios");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        opcion_2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        opcion_2.setText("Limpieza profunda PxP");
        opcion_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcion_2ActionPerformed(evt);
            }
        });
        jPanel2.add(opcion_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 170, -1));

        opcion_3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        opcion_3.setText("Cambio de pasta termica");
        jPanel2.add(opcion_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 190, -1));

        opcion_4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        opcion_4.setText("Cambio pad termico");
        jPanel2.add(opcion_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        opcion_5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        opcion_5.setText("Aflojar/Engrasar vizagras");
        jPanel2.add(opcion_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        opcion_7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        opcion_7.setText("Cambio disco duro");
        jPanel2.add(opcion_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, -1));

        opcion_8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        opcion_8.setText("Cambio fuente/bateria");
        jPanel2.add(opcion_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        opcion_9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        opcion_9.setText("Cambio RAM");
        jPanel2.add(opcion_9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        opcion_10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        opcion_10.setText("Cambio procesador");
        jPanel2.add(opcion_10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));

        opcion_11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        opcion_11.setText("Cambio tajeta grafica");
        jPanel2.add(opcion_11, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, -1, -1));

        opcion_12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        opcion_12.setText("Cambio gabinete");
        jPanel2.add(opcion_12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, -1, -1));

        opcion_13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        opcion_13.setText("Cambio tajeta madre");
        jPanel2.add(opcion_13, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, -1, -1));

        opcion_14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        opcion_14.setText("Clonacion dico duro");
        jPanel2.add(opcion_14, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, -1, -1));

        opcion_16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        opcion_16.setText("Mantenimiento sotware");
        jPanel2.add(opcion_16, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, -1, -1));

        opcion_17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        opcion_17.setText("MANDAR A GARANTIA");
        jPanel2.add(opcion_17, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, -1, -1));

        opcion_6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        opcion_6.setText("Revicion de equipo");
        opcion_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcion_6ActionPerformed(evt);
            }
        });
        jPanel2.add(opcion_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 160, -1));

        opcion_1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        opcion_1.setText("Limpieza superficial");
        opcion_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcion_1ActionPerformed(evt);
            }
        });
        jPanel2.add(opcion_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 160, -1));

        opcion_15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        opcion_15.setText("Cambio pantalla");
        jPanel2.add(opcion_15, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 580, 170));

        jLabel11.setText("Responsable del mantenimiento:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, -1, -1));

        jLabel12.setText("Comentarios extra:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, -1, -1));
        jPanel1.add(barra_responsable, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 210, 30));

        barra_tipo_mantenimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Correctivo", "Preventivo" }));
        jPanel1.add(barra_tipo_mantenimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 130, 30));
        jPanel1.add(barra_comentario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 450, 530, 30));

        jLabel13.setText("Tipo de mantenimiento:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Fecha en que se realizo:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 380, 170, -1));

        barra_fecha1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        barra_fecha1.setPreferredSize(new java.awt.Dimension(81, 23));
        jPanel1.add(barra_fecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 400, 160, -1));

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

    private void boton_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_aceptarActionPerformed
        // TODO add your handling code here:
        String servicios = servicios(), fecha = "", comentario = barra_comentario.getText();
        java.util.Date date = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha_1 = new Date();
        fecha = f.format(fecha_1);
        int validacion = 0;
        String tipo_m = barra_tipo_mantenimiento.getSelectedItem().toString(), responsable = barra_responsable.getText();
        if (barra_fecha1.getDate() == null) {
            fecha = f.format(fecha_1);
        } else {
            fecha = f.format(barra_fecha1.getDate());
        }
        if (barra_comentario.equals("")) {
            comentario = "Sin comentarios";
        }

        int validar = 0;
        if (!servicios.equals("") && !tipo_m.equals(" ") && !responsable.equals("")) {
            System.out.println(servicios);
            try {
                Connection cn = Conection_bd.conexion_bd();
                PreparedStatement pst = cn.prepareStatement(insert);
                pst.setInt(1, 0);
                pst.setInt(2, id_equipo);
                pst.setString(3, servicios);
                pst.setString(4, responsable);
                pst.setString(5, comentario);
                pst.setString(6, fecha);
                pst.executeUpdate();
                cn.close();

            } catch (Exception e) {
                System.out.println("Error al guardar: " + e);
                //JOptionPane.showMessageDialog(Componente, mensaje, título, tipo de mensaje, icono);
                JOptionPane.showMessageDialog(null, "Error al guardar\n"+e, "Error", JOptionPane.ERROR_MESSAGE);
            }
            limpiarCampos();

        } else {
            JOptionPane.showMessageDialog(null, "Debes llenar todos los camplos\n1. Tipo de mantenimiento\n\n2. Minimo un servicio\n\n3. Nombre del responsable");
        }
    }//GEN-LAST:event_boton_aceptarActionPerformed

    private void boton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscarActionPerformed
        // TODO add your handling code here:
        registroMantenimientos_tabla p = new registroMantenimientos_tabla();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_boton_buscarActionPerformed

    private void opcion_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcion_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcion_2ActionPerformed

    private void opcion_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcion_6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcion_6ActionPerformed

    private void opcion_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcion_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcion_1ActionPerformed

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
            java.util.logging.Logger.getLogger(registroMantenimientos_registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registroMantenimientos_registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registroMantenimientos_registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registroMantenimientos_registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registroMantenimientos_registro().setVisible(true);
            }
        });
    }

    public String servicios() {
        String total_servicios = "";
        if (opcion_1.isSelected()) {
            total_servicios += opcion_1.getText() + " ■ ";
        }
        if (opcion_2.isSelected()) {
            total_servicios += opcion_2.getText() + " ■ ";
        }
        if (opcion_3.isSelected()) {
            total_servicios += opcion_3.getText() + " ■ ";
        }
        if (opcion_4.isSelected()) {
            total_servicios += opcion_4.getText() + " ■ ";
        }
        if (opcion_5.isSelected()) {
            total_servicios += opcion_5.getText() + " ■ ";
        }
        if (opcion_6.isSelected()) {
            total_servicios += opcion_6.getText() + " ■ ";
        }
        if (opcion_7.isSelected()) {
            total_servicios += opcion_7.getText() + " ■ ";
        }
        if (opcion_8.isSelected()) {
            total_servicios += opcion_8.getText() + " ■ ";
        }
        if (opcion_9.isSelected()) {
            total_servicios += opcion_9.getText() + " ■ ";
        }
        if (opcion_10.isSelected()) {
            total_servicios += opcion_10.getText() + " ■ ";
        }
        if (opcion_11.isSelected()) {
            total_servicios += opcion_11.getText() + " ■ ";
        }
        if (opcion_12.isSelected()) {
            total_servicios += opcion_12.getText() + " ■ ";
        }
        if (opcion_13.isSelected()) {
            total_servicios += opcion_13.getText() + " ■ ";
        }
        if (opcion_14.isSelected()) {
            total_servicios += opcion_14.getText() + " ■ ";
        }
        if (opcion_15.isSelected()) {
            total_servicios += opcion_15.getText() + " ■ ";
        }
        if (opcion_16.isSelected()) {
            total_servicios += opcion_16.getText() + " ■ ";
        }
        if (opcion_17.isSelected()) {
            total_servicios += opcion_17.getText() + " ■ ";
        }
        return total_servicios;

    }

    public void limpiarCampos() {

        Icon icono_correcto = new ImageIcon(getClass().getResource("/imagenes/correcto.png"));
        int valor = JOptionPane.showConfirmDialog(null, "Se ha registrado la informacion correctamente", "Equipo actualizado correctamente", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, icono_correcto);
        //int valor = JOptionPane.showConfirmDialog(null, "Se ha guardado correctamente", "Equipo registrado correctamente", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, icono_correcto);
        if (valor == JOptionPane.YES_OPTION) {

        }
        registroMantenimientos_tabla mbm = new registroMantenimientos_tabla();
        mbm.setVisible(true);
        dispose();

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barra_comentario;
    private javax.swing.JLabel barra_disco;
    private javax.swing.JLabel barra_fecha;
    private com.toedter.calendar.JDateChooser barra_fecha1;
    private javax.swing.JLabel barra_marca;
    private javax.swing.JLabel barra_modelo;
    private javax.swing.JLabel barra_perifericos;
    private javax.swing.JLabel barra_procesador;
    private javax.swing.JLabel barra_ram;
    private javax.swing.JTextField barra_responsable;
    private javax.swing.JLabel barra_tipo;
    private javax.swing.JComboBox<String> barra_tipo_mantenimiento;
    private javax.swing.JLabel barra_ubicacion;
    private javax.swing.JButton boton_aceptar;
    private javax.swing.JButton boton_buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JCheckBox opcion_1;
    private javax.swing.JCheckBox opcion_10;
    private javax.swing.JCheckBox opcion_11;
    private javax.swing.JCheckBox opcion_12;
    private javax.swing.JCheckBox opcion_13;
    private javax.swing.JCheckBox opcion_14;
    private javax.swing.JCheckBox opcion_15;
    private javax.swing.JCheckBox opcion_16;
    private javax.swing.JCheckBox opcion_17;
    private javax.swing.JCheckBox opcion_2;
    private javax.swing.JCheckBox opcion_3;
    private javax.swing.JCheckBox opcion_4;
    private javax.swing.JCheckBox opcion_5;
    private javax.swing.JCheckBox opcion_6;
    private javax.swing.JCheckBox opcion_7;
    private javax.swing.JCheckBox opcion_8;
    private javax.swing.JCheckBox opcion_9;
    // End of variables declaration//GEN-END:variables
}
