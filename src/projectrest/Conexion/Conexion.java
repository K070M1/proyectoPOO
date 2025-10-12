/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class Conexion {

    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String DB = "db_rest";
    private final String USER_DB = "root";
    private final String PASSWORD_DB = "TU_CLAVE_AQUI";

    public Connection cnx;

    public static Conexion instancia;

    public Conexion() {
        this.cnx = null;
    }

    public Connection conectar() {
        try {
            Class.forName(DRIVER);
            this.cnx = DriverManager.getConnection(URL + DB, USER_DB, PASSWORD_DB);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        }
        System.out.println("Conexión establecida...!");
        return this.cnx;
    }

    public void desconectar() {
        try {
            this.cnx.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        }
    }

    // Patron singleton
    public synchronized static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }
}
