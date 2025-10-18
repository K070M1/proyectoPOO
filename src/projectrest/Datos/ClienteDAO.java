package projectrest.Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import projectrest.Entidades.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projectrest.Conexion.Conexion;
import projectrest.Datos.Interfaces.ICliente;

public class ClienteDAO implements ICliente {

    private final Conexion CNX;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean confirmacion;

    public ClienteDAO() {
        this.CNX = Conexion.getInstancia();
    }

    public List<Cliente> listar(String texto) {
        List<Cliente> registros = new ArrayList<>();
        try {
            ps = CNX.conectar().prepareStatement("SELEC * FROM Clientes WHERE nombreCategoria LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(
                        new Cliente(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5)
                        )
                );
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CNX.desconectar();
        }
        return registros;
    }

    public boolean insertar(Cliente cliente) {
        confirmacion = false;
        try {
            ps = CNX.conectar().prepareStatement("INSERT INTO Clientes (nombreCompleto, categoriaCliente, correo, telefono) VALUES (?,?,?,?)");
            ps.setString(1, cliente.getNombreCompleto());
            ps.setString(2, cliente.getCategoriaCliente());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getTelefono());

            confirmacion = ps.executeUpdate() > 0 || false;
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CNX.desconectar();
        }
        return confirmacion;
    }

    public boolean editar(Cliente cliente) {
        confirmacion = false;
        try {
            ps = CNX.conectar().prepareStatement("UPDATE Clientes SET nombreCompleto = ?, categoriaCliente = ?, correo = ?, telefono = ? WHERE idCliente = ?");
            ps.setString(1, cliente.getNombreCompleto());
            ps.setString(2, cliente.getCategoriaCliente());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getTelefono());
            ps.setInt(5, cliente.getIdCliente());

            confirmacion = ps.executeUpdate() > 0 || false;
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CNX.desconectar();
        }
        return confirmacion;
    }

    public boolean eliminar(int idCliente) {
        confirmacion = false;
        try {
            ps = CNX.conectar().prepareStatement("DELETE FROM Cliente WHERE idCliente = ?");
            ps.setInt(1, idCliente);
            confirmacion = ps.executeUpdate() > 0 || false;
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CNX.desconectar();
        }
        return confirmacion;
    }

    public Cliente buscarPorId(int idCliente) {
        Cliente client = new Cliente();
        try {
            ps = CNX.conectar().prepareStatement("SELEC * FROM Clientes WHERE idCliente = ?");
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            client.setIdCliente(rs.getInt(1));
            client.setNombreCompleto(rs.getString(2));
            client.setCategoriaCliente(rs.getString(3));
            client.setCorreo(rs.getString(4));
            client.setTelefono(rs.getString(5));

            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CNX.desconectar();
        }
        return client;
    }
}
