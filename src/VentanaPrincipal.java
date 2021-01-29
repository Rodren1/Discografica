
import java.awt.Color;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Javi
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    GestorConexion ges = new GestorConexion();

    public VentanaPrincipal() {
        initComponents();
        
        jComboBoxAlbumes.removeAllItems();
        jComboBoxArtistasAlbum.removeAllItems();
        jComboBoxArtistasCancion.removeAllItems();
        
        

        ArrayList<String> listaAlbumes = new ArrayList<String>();//para la foreign key de album en canciones
        listaAlbumes = ges.comboBoxAlbumes();
        for (int i = 0; i < listaAlbumes.size(); i++) {
            jComboBoxAlbumes.addItem(listaAlbumes.get(i));
        }
        
        /*jButtonDesconectar.setEnabled(false);
        jButtonAlterAlbumes.setEnabled(false);
        jButtonAlterCanciones.setEnabled(false);
        jButtonDropTable.setEnabled(false);
         */
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelDesconectar = new javax.swing.JLabel();
        jLabelALTER_CREATE = new javax.swing.JLabel();
        jButtonDesconectar = new javax.swing.JButton();
        jButtonCreateTable = new javax.swing.JButton();
        jButtonAlterAlbumes = new javax.swing.JButton();
        jButtonAlterCanciones = new javax.swing.JButton();
        jButtonDropTable = new javax.swing.JButton();
        jLabelINSERTS = new javax.swing.JLabel();
        jTextField_Nombre_artista = new javax.swing.JTextField();
        jTextField_Nombre_album = new javax.swing.JTextField();
        jTextField_Fech_publ_album = new javax.swing.JTextField();
        jTextField_Duracion_album = new javax.swing.JTextField();
        jTextField_Nombre_cancion = new javax.swing.JTextField();
        jTextField_Duracion_cancion = new javax.swing.JTextField();
        jButton_Añadir_Artista = new javax.swing.JButton();
        jButton_Añadir_Album = new javax.swing.JButton();
        jButton_Añadir_Cancion = new javax.swing.JButton();
        jComboBoxArtistasAlbum = new javax.swing.JComboBox<>();
        jComboBoxAlbumes = new javax.swing.JComboBox<>();
        jComboBoxArtistasCancion = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 153));
        setResizable(false);

        jLabelDesconectar.setText("Cerrar conexion con la BBDD");

        jLabelALTER_CREATE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelALTER_CREATE.setText("EDITAR BASE DE DATOS");
        jLabelALTER_CREATE.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelALTER_CREATE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButtonDesconectar.setText("Desconectar");
        jButtonDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDesconectarActionPerformed(evt);
            }
        });

        jButtonCreateTable.setText("Añadir tabla artistas");
        jButtonCreateTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateTableActionPerformed(evt);
            }
        });

        jButtonAlterAlbumes.setText("Añadir artista a Albumes");
        jButtonAlterAlbumes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterAlbumesActionPerformed(evt);
            }
        });

        jButtonAlterCanciones.setText("Añadir artista a Canciones");
        jButtonAlterCanciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterCancionesActionPerformed(evt);
            }
        });

        jButtonDropTable.setText("Borrar tabla artistas");
        jButtonDropTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDropTableActionPerformed(evt);
            }
        });

        jLabelINSERTS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelINSERTS.setText("INSERTAR/ BORRAR DATOS");
        jLabelINSERTS.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelINSERTS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextField_Nombre_artista.setToolTipText("Nombre");

        jTextField_Nombre_album.setToolTipText("Nombre");

        jTextField_Fech_publ_album.setToolTipText("Fecha_Publicacion");

        jTextField_Duracion_album.setToolTipText("Duracion");

        jTextField_Nombre_cancion.setToolTipText("Nombre");

        jTextField_Duracion_cancion.setToolTipText("Duracion");

        jButton_Añadir_Artista.setText("AÑADIR ARTISTA");
        jButton_Añadir_Artista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Añadir_ArtistaActionPerformed(evt);
            }
        });

        jButton_Añadir_Album.setText("AÑADIR ALBUM");
        jButton_Añadir_Album.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Añadir_AlbumActionPerformed(evt);
            }
        });

        jButton_Añadir_Cancion.setText("AÑADIR CANCIÓN");
        jButton_Añadir_Cancion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Añadir_CancionActionPerformed(evt);
            }
        });

        jComboBoxArtistasAlbum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxAlbumes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxArtistasCancion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelDesconectar, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonDesconectar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelINSERTS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelALTER_CREATE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_Añadir_Artista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_Nombre_artista))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_Nombre_album, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addComponent(jTextField_Fech_publ_album, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addComponent(jTextField_Duracion_album, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addComponent(jButton_Añadir_Album, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxArtistasAlbum, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField_Nombre_cancion, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addComponent(jTextField_Duracion_cancion, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addComponent(jButton_Añadir_Cancion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxAlbumes, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxArtistasCancion, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonCreateTable, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAlterAlbumes, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAlterCanciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonDropTable, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonDesconectar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelDesconectar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jLabelALTER_CREATE, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCreateTable, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAlterAlbumes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAlterCanciones, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDropTable, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabelINSERTS, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Añadir_Artista)
                            .addComponent(jButton_Añadir_Album)
                            .addComponent(jButton_Añadir_Cancion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_Nombre_album, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Fech_publ_album, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Duracion_album, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxAlbumes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField_Nombre_artista, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField_Nombre_cancion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_Duracion_cancion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBoxArtistasAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxArtistasCancion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(203, Short.MAX_VALUE))
        );

        jTextField_Nombre_artista.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDesconectarActionPerformed
        ges.cerrar_conexion();
    }//GEN-LAST:event_jButtonDesconectarActionPerformed

    private void jButtonCreateTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateTableActionPerformed
        ges.createTable();
        ArrayList<String> listaArtistas = new ArrayList<String>();//para las foreign key de artista en albumes y canciones
        listaArtistas = ges.comboBoxArtista();
        for (int i = 0; i < listaArtistas.size(); i++) {
            jComboBoxArtistasAlbum.addItem(listaArtistas.get(i));
            jComboBoxArtistasCancion.addItem(listaArtistas.get(i));
        }
        jButtonAlterAlbumes.setEnabled(true);
        jButtonAlterCanciones.setEnabled(true);
    }//GEN-LAST:event_jButtonCreateTableActionPerformed

    private void jButtonAlterAlbumesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterAlbumesActionPerformed
        ges.alterAlbumes();
    }//GEN-LAST:event_jButtonAlterAlbumesActionPerformed

    private void jButtonAlterCancionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterCancionesActionPerformed
        ges.alterCanciones();
    }//GEN-LAST:event_jButtonAlterCancionesActionPerformed

    private void jButtonDropTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDropTableActionPerformed
        ges.dropTable();
    }//GEN-LAST:event_jButtonDropTableActionPerformed

    private void jButton_Añadir_ArtistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Añadir_ArtistaActionPerformed
        ges.insertarArtista(jTextField_Nombre_artista.getText());
    }//GEN-LAST:event_jButton_Añadir_ArtistaActionPerformed

    private void jButton_Añadir_AlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Añadir_AlbumActionPerformed
        //ges.insertarAlbum(jTextField_Nombre_album.getText(), jTextField_Fech_publ_album.getText(), jTextField_Duracion_album.getText(), jComboBoxArtistasAlbum.getActionListeners());
    }//GEN-LAST:event_jButton_Añadir_AlbumActionPerformed

    private void jButton_Añadir_CancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Añadir_CancionActionPerformed
        //ges.insertarCancion(jTextField_Nombre_cancion.getText(), jTextField_Duracion_cancion.getText(), jTextField_album_cancion.getText());
    }//GEN-LAST:event_jButton_Añadir_CancionActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterAlbumes;
    private javax.swing.JButton jButtonAlterCanciones;
    private javax.swing.JButton jButtonCreateTable;
    private javax.swing.JButton jButtonDesconectar;
    private javax.swing.JButton jButtonDropTable;
    private javax.swing.JButton jButton_Añadir_Album;
    private javax.swing.JButton jButton_Añadir_Artista;
    private javax.swing.JButton jButton_Añadir_Cancion;
    private javax.swing.JComboBox<String> jComboBoxAlbumes;
    private javax.swing.JComboBox<String> jComboBoxArtistasAlbum;
    private javax.swing.JComboBox<String> jComboBoxArtistasCancion;
    private javax.swing.JLabel jLabelALTER_CREATE;
    private javax.swing.JLabel jLabelDesconectar;
    private javax.swing.JLabel jLabelINSERTS;
    private javax.swing.JTextField jTextField_Duracion_album;
    private javax.swing.JTextField jTextField_Duracion_cancion;
    private javax.swing.JTextField jTextField_Fech_publ_album;
    private javax.swing.JTextField jTextField_Nombre_album;
    private javax.swing.JTextField jTextField_Nombre_artista;
    private javax.swing.JTextField jTextField_Nombre_cancion;
    // End of variables declaration//GEN-END:variables
}