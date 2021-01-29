
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Javi
 */
public class GestorConexion {

    Connection conn1 = null;

    public GestorConexion() {
        try {
            String urlBBDD = "jdbc:mysql://localhost:3306/discograficaJavierBermejo?serverTimexone=UTC";
            String user = "root";
            String password = "";

            conn1 = DriverManager.getConnection(urlBBDD, user, password);

            if (conn1 != null) {
                System.out.println("Conectado a discográfica");
            }
        } catch (SQLException ex) {
            System.out.println("Error en la conexion a discográfica");
            ex.printStackTrace();
        }
    }

    public void cerrar_conexion() {
        try {
            conn1.close();
            if (conn1.isClosed()) {
                System.out.println("Desconectado de discográfica");
            }

        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexion a discográfica");
        }
    }

    public void createTable() {
        try {
            Statement sta = conn1.createStatement();
            sta.executeUpdate("CREATE TABLE IF NOT EXISTS `artistas`(`nombre_artista` varchar(20) NOT NULL, "
                    + "`id_artista` int(11) NOT NULL AUTO_INCREMENT, PRIMARY KEY (`id_artista`))ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;");
            sta.close();
            System.out.println("Tabla creada correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void alterAlbumes() {
        try {
            Statement sta = conn1.createStatement();
            sta.executeUpdate("ALTER TABLE albumes ADD IF NOT EXISTS artista INT(11), ADD CONSTRAINT `albumes_ibfk_1` FOREIGN KEY (`artista`)"
                    + " REFERENCES `artistas` (`id_artista`) ON DELETE CASCADE ON UPDATE CASCADE;");
            sta.close();
            System.out.println("Tabla modificada correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void alterCanciones() {
        try {
            Statement sta = conn1.createStatement();
            sta.executeUpdate("ALTER TABLE canciones ADD IF NOT EXISTS artista INT(11), ADD CONSTRAINT `canciones_ibfk_2` FOREIGN KEY (`artista`) "
                    + "REFERENCES `artistas` (`id_artista`) ON DELETE CASCADE ON UPDATE CASCADE;");
            sta.close();
            System.out.println("Tabla modificada correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void dropTable() {
        try {
            Statement sta = conn1.createStatement();
            sta.executeUpdate("ALTER TABLE albumes DROP CONSTRAINT IF EXISTS albumes_ibfk_1");
            sta.executeUpdate("ALTER TABLE albumes DROP IF EXISTS artista");
            sta.executeUpdate("ALTER TABLE canciones DROP CONSTRAINT IF EXISTS canciones_ibfk_2");
            sta.executeUpdate("ALTER TABLE canciones DROP IF EXISTS artista ");
            sta.executeUpdate("DROP TABLE IF EXISTS artistas");
            sta.close();
            System.out.println("Tabla borrada correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void insertarArtista(String nombre_artista) {

        String insert = "INSERT INTO `artistas` (`nombre_artista`) VALUES (?)";
        try {
            PreparedStatement pst = conn1.prepareStatement(insert);
            pst.setString(1, nombre_artista);
            pst.executeUpdate();
            pst.close();
            System.out.println("Datos añadidos correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void insertarAlbum(String nombre_album, String fech_publ, String duracion) {

        String insert = "INSERT INTO `albumes` (`nombre_album`, `fech_publ`, `duracion`) VALUES (?, ?, ?)";
        try {
            PreparedStatement pst = conn1.prepareStatement(insert);
            pst.setString(1, nombre_album);
            pst.setString(2, fech_publ);
            pst.setString(3, duracion);
            pst.executeUpdate();
            pst.close();
            System.out.println("Datos añadidos correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void insertarCancion(String nombre_cancion, String duracion, String album, int artista) {

        String insert = "INSERT INTO `canciones` (`nombre_cancion`, `duracion`, `album`) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pst = conn1.prepareStatement(insert);
            pst.setString(1, nombre_cancion);
            pst.setString(2, duracion);
            pst.setString(3, album);
            pst.setInt(4, artista);
            pst.executeUpdate();
            pst.close();
            System.out.println("Datos añadidos correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public ArrayList<String> comboBoxArtista() {
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * FROM artistas WHERE EXISTS (SELECT `nombre_artista` FROM artistas ORDER BY `id_artista`)";
        try {
            Statement sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString("nombre_artista"));
            }
            rs.close();
            sta.close();
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<String> comboBoxAlbumes() {
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * FROM albumes WHERE EXISTS (SELECT `nombre_album` FROM albumes ORDER BY `id_album`)";
        try {
            Statement sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString("nombre_album"));
            }
            rs.close();
            sta.close();
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}
