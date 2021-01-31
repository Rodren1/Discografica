
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

    public int GestorConexion() {
        int aux = 1;
        try {
            String urlBBDD = "jdbc:mysql://localhost:3306/discograficaJavierBermejo?serverTimexone=UTC";
            String user = "root";
            String password = "";

            conn1 = DriverManager.getConnection(urlBBDD, user, password);

            if (conn1 != null) {
                aux = 0;
            }else
                aux = 1;
        } catch (SQLException ex) {
            aux = -1;
        }
        return aux;
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

    /*
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
     */
    
    public void createTable(){
        try {
            Statement sta = conn1.createStatement();
            sta.executeUpdate("CREATE TABLE reproducciones ("
                    + "`id_reproducciones` int(11) NOT NULL AUTO_INCREMENT,"
                    + "`album_r` int(11) NOT NULL,"
                    + "`reproducciones_locales` varchar(20) DEFAULT NULL,"
                    + "`reproducciones_globales` varchar(20) DEFAULT NULL,"
                    + "PRIMARY KEY (`id_reproducciones`),"
                    + "FOREIGN KEY (`album_r`) REFERENCES `albumes` (`id_album`) ON DELETE CASCADE ON UPDATE CASCADE"
                    + ")ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;");
            sta.close();
            System.out.println("Tabla creada correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void fillTable(){
        try {
            Statement sta = conn1.createStatement();
            sta.executeUpdate("INSERT INTO `reproducciones` (`album_r`, `reproducciones_locales`, `reproducciones_globales`) VALUES" 
                                + "(1, '10.340.026', '35.105.659')," 
                                + "(2, '191.026', '9.330.704')," 
                                + "(3, '256.026', '50.703.812')," 
                                + "(4, '3.340.026', '3.119.249');");
            sta.close();
            System.out.println("Datos insertados correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void dropTable() {
        try {
            Statement sta = conn1.createStatement();
            sta.executeUpdate("DROP TABLE IF EXISTS reproducciones");
            sta.close();
            System.out.println("Tabla borrada correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void insertarArtista(String nombre_artista) {

        try {
            Statement sta = conn1.createStatement();
            sta.executeUpdate("INSERT INTO `artistas` (`nombre_artista`) "
                    + "VALUES('" + nombre_artista + "');");
            sta.close();
            System.out.println("Datos añadidos correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void insertarAlbum(String nombre_album, String fech_publ, String duracion, int artista) {

        try {
            Statement sta = conn1.createStatement();
            sta.executeUpdate("INSERT INTO `albumes` (`nombre_album`, `fech_publ`, `duracion`, `artista_album`) "
                    + "VALUES('" + nombre_album + "', '" + fech_publ + "', '" + duracion + "', '" + artista + "');");
            sta.close();
            System.out.println("Datos añadidos correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void insertarCancion(String nombre_cancion, String duracion, int album, int artista) {

        try {
            Statement sta = conn1.createStatement();
            sta.executeUpdate("INSERT INTO canciones (`nombre_cancion`, `duracion`, `album`, `artista_cancion`) "
                    + "VALUES('" + nombre_cancion + "', '" + duracion + "', '" + album + "', '" + artista + "');");
            sta.close();
            System.out.println("Datos añadidos correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void borrar(String dato, int n) {
        try {
            Statement sta = conn1.createStatement();
            if (n == 0) {
                sta.executeUpdate("DELETE FROM artistas WHERE nombre_artista = '" + dato + "';");
                sta.close();
            } else if (n == 1) {
                sta.executeUpdate("DELETE FROM albumes WHERE nombre_album = '" + dato + "';");
                sta.close();
            } else if (n == 2) {
                sta.executeUpdate("DELETE FROM canciones WHERE nombre_cancion = '" + dato + "';");
                sta.close();
            }
            System.out.println("Datos borrados correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void modificar(String nombre_cancion, String duracion, int album, int artista, int datoMod) {
        try {
            Statement sta = conn1.createStatement();
            sta.executeUpdate("UPDATE canciones "
                    + "SET nombre_cancion = '" + nombre_cancion + "', duracion = '" + duracion + "', album = '" + album + "', artista_cancion = '" + artista + "' "
                    + "WHERE id_cancion = '" + datoMod + "';");
            sta.close();
            System.out.println("Datos modificados correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public String buscarCancion(String nombre) {
        String query = "SELECT * FROM `canciones` WHERE `nombre_cancion` = '" + nombre + "' ORDER BY `id_cancion`";
        String salida = "";
        try {
            PreparedStatement pst = conn1.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                salida = ("\n ID: " + rs.getInt("id_cancion")
                        + "\n Título: " + rs.getString("nombre_cancion")
                        + "\n Duración: " + rs.getString("duracion")
                        + "\n Album: " + rs.getInt("album")
                        + "\n Artista: " + rs.getInt("artista_cancion"));
            }
            rs.close();
            pst.close();
            return salida;
        } catch (SQLException ex) {
            return ex.toString();
        }
    }

    public String buscarAlbum(String nombre) {
        String query = "SELECT * FROM `albumes` WHERE `nombre_album` = '" + nombre + "' ORDER BY `id_album`";
        String salida = "";
        try {
            PreparedStatement pst = conn1.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                salida = ("\n ID: " + rs.getInt("id_album")
                        + "\n Título: " + rs.getString("nombre_album")
                        + "\n Fecha de publicación: " + rs.getString("fech_publ")
                        + "\n Duración: " + rs.getString("duracion")
                        + "\n Artista: " + rs.getInt("artista_album"));
            }
            rs.close();
            pst.close();
            return salida;
        } catch (SQLException ex) {
            return ex.toString();
        }
    }

    public String selectCanciones() {
        String query = "SELECT * FROM `canciones` ORDER BY `id_cancion`";
        String salida = "";
        String aux = "";
        try {
            Statement sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery(query);
            while (rs.next()) {

                aux = ("\n-------------------------------------------------" 
                        + "\n ID: " + rs.getInt("id_cancion")
                        + "\n Título: " + rs.getString("nombre_cancion")
                        + "\n Duración: " + rs.getString("duracion")
                        + "\n Album: " + rs.getInt("album")
                        + "\n Artista: " + rs.getInt("artista_cancion")
                        + "\n-------------------------------------------------");
                salida = salida + aux;
            }
            rs.close();
            sta.close();
            return salida;
        } catch (SQLException ex) {
            return ex.toString();
        }
    }
    
    public String selectAlbumes() {
        String query = "SELECT * FROM `albumes` ORDER BY `id_album`";
        String salida = "";
        String aux = "";
        try {
            Statement sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery(query);
            while (rs.next()) {

                aux = ("\n-------------------------------------------------" 
                        + "\n ID: " + rs.getInt("id_album")
                        + "\n Título: " + rs.getString("nombre_album")
                        + "\n Fecha de publicación: " + rs.getString("fech_publ")
                        + "\n Duración: " + rs.getString("duracion")
                        + "\n Artista: " + rs.getInt("artista_album")
                        + "\n-------------------------------------------------");
                salida = salida + aux;
            }
            rs.close();
            sta.close();
            return salida;
        } catch (SQLException ex) {
            return ex.toString();
        }
    }
    
    public String selectArtistas() {
        String query = "SELECT * FROM `artistas` ORDER BY `id_artista`";
        String salida = "";
        String aux = "";
        try {
            Statement sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery(query);
            while (rs.next()) {

                aux = ("\n-------------------------------------------------" 
                        + "\n ID: " + rs.getInt("id_artista")
                        + "\n Nombre: " + rs.getString("nombre_artista")
                        + "\n-------------------------------------------------");
                salida = salida + aux;
            }
            rs.close();
            sta.close();
            return salida;
        } catch (SQLException ex) {
            return ex.toString();
        }
    }
    
    public String selectAll() {
        String query = "SELECT * FROM `canciones`, `albumes`, `artistas` WHERE album = id_album AND artista_album = id_artista ORDER BY id_artista";
        String salida = "";
        String aux = "";
        try {
            Statement sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery(query);
            while (rs.next()) {

                aux = ("\n-------------------------------------------------" 
                        + "\n ID artista: " + rs.getInt("id_artista")
                        + "\n Nombre del artista: " + rs.getString("nombre_artista")
                        + "\n ID album: " + rs.getInt("id_album")
                        + "\n Título del album: " + rs.getString("nombre_album")
                        + "\n Fecha de publicación: " + rs.getString("fech_publ")
                        + "\n Duración del album: " + rs.getString("albumes.duracion")
                        + "\n Artista del album: " + rs.getInt("artista_album")
                        + "\n ID canción: " + rs.getInt("id_cancion")
                        + "\n Título de la canción: " + rs.getString("nombre_cancion")
                        + "\n Duración de la canción: " + rs.getString("duracion")
                        + "\n Album de la canción: " + rs.getInt("album")
                        + "\n Artista de la canción: " + rs.getInt("artista_cancion")
                        + "\n-------------------------------------------------");
                salida = salida + aux;
            }
            rs.close();
            sta.close();
            return salida;
        } catch (SQLException ex) {
            return ex.toString();
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
        String query = "SELECT * FROM albumes WHERE EXISTS (SELECT `nombre_album`, `id_album` FROM albumes ORDER BY `id_album`)";
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

    public ArrayList<String> comboBoxCanciones() {
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * FROM canciones WHERE EXISTS (SELECT * FROM canciones ORDER BY `id_cancion`)";
        try {
            Statement sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString("nombre_cancion"));
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
