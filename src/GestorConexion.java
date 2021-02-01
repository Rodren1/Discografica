
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    String urlBBDD = "jdbc:mysql://localhost:3306"; //creo la variable url con la dirección del servidor para poder crear la base de datos desde la interfaz

    public int GestorConexion() { //cambio el constructor por un método que retorna un int para poder conectar con la base de datos mediante un boton
        //creo dos variables para conectar con la bbdd, el usuario y la contraseña
        String user = "root";
        String password = "";
        int aux = 1; //esta variable auxiliar recibe 0 en caso de conectar a la bbdd, 1 en el caso de no poder conectar y -1 si ha habido algún error  
        try {
            conn1 = DriverManager.getConnection(urlBBDD, user, password);

            if (conn1 != null) {
                aux = 0;
            } else {
                aux = 1;
            }
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

    //este método crea una tabla en la bbdd para almacenar las reproducciones de los albumes en cada año, 
    //con una clave ajena de la tabla albumes y una clave única que sería el año de registro
    public void createTable() {
        try {
            Statement sta = conn1.createStatement();
            sta.executeUpdate("CREATE TABLE reproducciones ("
                    + "`id_reproducciones` int(11) NOT NULL AUTO_INCREMENT,"
                    + "`album_r` int(11) NOT NULL,"
                    + "`reproducciones_locales` varchar(20) DEFAULT NULL,"
                    + "`reproducciones_globales` varchar(20) DEFAULT NULL,"
                    + "`fecha_registro` varchar(20) NOT NULL,"
                    + "PRIMARY KEY (`id_reproducciones`),"
                    + "UNIQUE KEY (`fecha_registro`),"
                    + "FOREIGN KEY (`album_r`) REFERENCES `albumes` (`id_album`) ON DELETE CASCADE ON UPDATE CASCADE"
                    + ")ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;");
            sta.close();
            System.out.println("Tabla creada correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    //este método inserta unos cuantos valores en la tabla creada anteriormente
    public void fillTable() {
        try {
            Statement sta = conn1.createStatement();
            sta.executeUpdate("INSERT INTO `reproducciones` (`album_r`, `reproducciones_locales`, `reproducciones_globales`, `fecha_registro`) VALUES"
                    + "(1, '10.340.026', '35.105.659', '2019'),"
                    + "(2, '191.026', '9.330.704', '2020'),"
                    + "(3, '256.026', '50.703.812', '2018'),"
                    + "(4, '3.340.026', '3.119.249', '2015');");
            sta.close();
            System.out.println("Datos insertados correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    //este método borra la tabla creada anteriormente 
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

    //este método inserta un artista dado por el usuario en su respectiva tabla
    public void insertarArtista(String nombre_artista) {

        try {
            Statement sta = conn1.createStatement();
            sta.executeUpdate("INSERT INTO `artistas` (`nombre_artista`) "
                    + "VALUES('" + nombre_artista + "');"); //el valor de la columna es introducido por el usuario, +nombre_artista+ es una forma alternativa a la de la ?
            sta.close();
            System.out.println("Datos añadidos correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    //este método hace lo mismo que el anterior pero en la tabla albumes con sus datos pertinentes, todos ellos introduccidos por el usuario, excepto el id que al igual que en el resto
    //de tablas es auto incrementativo
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

    //este método hace lo mismo que los dos anteriores pero en la tabla canciones, en el caso anterior la clave ajena es mediante un combobox, en este caso hay 2
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

    //este método borra un dato de una de las tres tablas, los parametros de entrada son los datos a borrar, introduccidos por el usuario y
    //un int para diferenciar de que tabla se esta borrando el dato, este valor se introducce automaticamente por el botón que ha sido pulsado
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

    //este método modifica una cancion, la seleccion de la canción es elegida por el usuario mediante un combobox que le da valor al parámetro de entrada datoMod y
    //los datos nuevos son, también, introduccidos por el usuario
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

    //este método busca una canción por su nombre, introducido por el usuario y devuelve todos los datos de dicha canción
    public String buscarCancion(String nombre) {
        String query = "SELECT * FROM `canciones` WHERE `nombre_cancion` = '" + nombre + "' ORDER BY `id_cancion`";
        String salida = "";
        try {
            PreparedStatement pst = conn1.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                //mientras haya datos en el rs de la query los v sumando a la salida con un salto de linea
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

    //este método hace lo mismo que el anterior pero sobre la tabla albumes
    public String buscarAlbum(String nombre) {
        String query = "SELECT * FROM `albumes` WHERE `nombre_album` = '" + nombre + "' ORDER BY `id_album`";
        String salida = "";
        try {
            PreparedStatement pst = conn1.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                //mientras haya datos en el rs de la query los v sumando a la salida con un salto de linea
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

    //este método consulta los datos de la tabla canciones y los muestra todos por pantalla
    public String selectCanciones() {
        String query = "SELECT * FROM `canciones` ORDER BY `id_cancion`";
        String salida = "";
        String aux = ""; //esta variable va sumando todas las filas de la tabla en la variable salida que es la que retorna el método
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

    //este método hace lo mismo que el anterior pero sobre la tabla albumes
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

    //este método hace lo mismo que los dos anteriores pero muestra los datos de todos los artistas
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
    
    //este metodo consulta todos los datos de la bbdd con dos joins para evitar datos duplicados
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

    //este método devuelve un array list con el id y el nombre de todos los artistas para mas adelante llenar el combobox
    public ArrayList<String> comboBoxArtista() {
        ArrayList<String> list = new ArrayList<String>();
        //con la cláusula WHERE EXISTS evito errores de darse el caso de que no existiesen el nombre o el id  
        String query = "SELECT * FROM artistas WHERE EXISTS (SELECT `nombre_artista` FROM artistas ORDER BY `id_artista`)";
        try {
            Statement sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery(query);
            while (rs.next()) {
                //se van añadiendo a la lista el id, un guion con espacios y el nombre para usarlo mas adelante en la selección de uno de los datos del combobox
                list.add(rs.getInt("id_artista") + " - " + rs.getString("nombre_artista"));
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

    //este método hace lo mismo que el anterior pero retorna un array list de albumes
    public ArrayList<String> comboBoxAlbumes() {
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * FROM albumes WHERE EXISTS (SELECT `nombre_album`, `id_album` FROM albumes ORDER BY `id_album`)";
        try {
            Statement sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getInt("id_album") + " - " + rs.getString("nombre_album"));
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

    //este método hace lo mismo que los dos anteriores pero retorna un array list de las canciones
    public ArrayList<String> comboBoxCanciones() {
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * FROM canciones WHERE EXISTS (SELECT * FROM canciones ORDER BY `id_cancion`)";
        try {
            Statement sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getInt("id_cancion") + " - " + rs.getString("nombre_cancion"));
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

    //este método crea la base de datos y cambia la url para que se conecte con la base de datos creada
    public void crearBBDD() {
        urlBBDD = "jdbc:mysql://localhost:3306/discograficajavierbermejo?serverTimexone=UTC";
        try {
            //se van ejecutando los updates de forma individual para evitar posibles errores y/o localizarlos facilmente
            Statement sta = conn1.createStatement();
            sta.executeUpdate("CREATE DATABASE IF NOT EXISTS `discograficajavierbermejo`;");
            sta.executeUpdate("USE discograficajavierbermejo;");
            sta.executeUpdate("CREATE TABLE `artistas`( "
                    + "`id_artista` int(11) NOT NULL AUTO_INCREMENT, "
                    + "`nombre_artista` varchar(20) NOT NULL,   "
                    + "PRIMARY KEY (`id_artista`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;");
            sta.executeUpdate("CREATE TABLE `albumes` ( "
                    + "`id_album` int(11) NOT NULL AUTO_INCREMENT, "
                    + "`nombre_album` varchar(40) NOT NULL, "
                    + "`fech_publ` varchar(20) DEFAULT NULL, "
                    + "`duracion` varchar(20) DEFAULT NULL, "
                    + "`artista_album` int(11) NOT NULL, "
                    + "PRIMARY KEY (`id_album`), "
                    + "FOREIGN KEY (`artista_album`) REFERENCES `artistas` (`id_artista`) ON DELETE CASCADE ON UPDATE CASCADE) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;");
            sta.executeUpdate("CREATE TABLE `canciones` ( "
                    + "`id_cancion` int(11) NOT NULL AUTO_INCREMENT, "
                    + "`nombre_cancion` varchar(40) NOT NULL, "
                    + "`duracion` varchar(20) DEFAULT NULL, "
                    + "`album` int(11) NOT NULL, "
                    + "`artista_cancion` int(11) NOT NULL, "
                    + "PRIMARY KEY (`id_cancion`), "
                    + "FOREIGN KEY (`album`) REFERENCES `albumes` (`id_album`) ON DELETE CASCADE ON UPDATE CASCADE, "
                    + "FOREIGN KEY (`artista_cancion`) REFERENCES `artistas` (`id_artista`) ON DELETE CASCADE ON UPDATE CASCADE) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;");
            sta.executeUpdate("INSERT INTO `artistas` (`nombre_artista`) "
                    + "VALUES ('The HU'), ('The Score'), ('Wardruna'), ('Skáld');");
            sta.executeUpdate("INSERT INTO `albumes` (`nombre_album`, `fech_publ`, `duracion`, `artista_album`) "
                    + "VALUES ('Breathe You Got This', '2020', '00:20:53', 2), "
                    + "('The Gereg', '2019', '00:47:45', 1), "
                    + "('Skáld', '2018', '00:09:55', 4), "
                    + "('Yggdrasil', '2013', '01:06:00', 3); ");
            sta.executeUpdate("INSERT INTO `canciones` (`nombre_cancion`, `duracion`, `album`, `artista_cancion`) "
                    + "VALUES ('Fehu', '00:06:45', 4, 3), "
                    + "('Gibu', '00:05:30', 4, 3), "
                    + "('NaudiR', '00:06:31', 4, 3), "
                    + "('AnsuR', '00:06:31', 4, 3), "
                    + "('Yuve Yuve Yu', '00:04:14', 2, 1), "
                    + "('Wolf Totem', '00:05:38', 2, 1), "
                    + "('Sugaan Essena', '00:06:36', 2, 1), "
                    + "('The Legend of Mother Swan', '00:05:25', 2, 1), "
                    + "('Níu', '00:03:05', 3, 4), ('Flúga', '00:02:32', 3, 4), "
                    + "('Ó Valhalla', '00:03:35', 3, 4), "
                    + "('Comeback', '00:03:43', 1, 2), "
                    + "('Bulletproof', '00:03:10', 1, 2), "
                    + "('Golden', '00:03:06', 1, 2), "
                    + "('Revolution', '00:03:52', 1, 2);");
            sta.close();
            System.out.println("Base de datos creada correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    //este método borra la base de datos y cambia la url para que se conecte solo al servidor 
    //un posible uso de este método es usar el método anterior a modo de backup
    public void borrarBBDD() {
        urlBBDD = "jdbc:mysql://localhost:3306";
        try {
            Statement sta = conn1.createStatement();
            sta.executeUpdate("DROP DATABASE IF EXISTS `discograficajavierbermejo`");
            sta.close();
            System.out.println("Base de datos borrada correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

}
