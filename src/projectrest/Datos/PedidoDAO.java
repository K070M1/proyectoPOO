package projectrest.Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import projectrest.Entidades.Pedido;
import projectrest.Conexion.Conexion;
import projectrest.Datos.Interfaces.IPedido;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PedidoDAO implements IPedido {

    private final Conexion CNX;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean confirmacion;

    public PedidoDAO() {
        this.CNX = Conexion.getInstancia();
    }

    @Override
    public List<Pedido> listar(int idCliente) {
        List<Pedido> registros = new ArrayList<>();
        try {
            ps = CNX.conectar().prepareStatement("SELEC * FROM Pedidos WHERE idCliente = ?");
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(
                        new Pedido(
                                rs.getInt(1),
                                rs.getInt(2),
                                rs.getInt(3),
                                rs.getInt(4),
                                rs.getFloat(5),
                                rs.getString(6)
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

    @Override
    public boolean insertar(Pedido pedido) {
        confirmacion = false;
        try {
            ps = CNX.conectar().prepareStatement("INSERT INTO Pedidos (idEmpleado, idCliente, idMesa, montoTotal, fechaPedido) VALUES (?,?,?,?,?)");
            ps.setInt(1, pedido.getIdEmpleado());
            ps.setInt(2, pedido.getIdCliente());
            ps.setInt(3, pedido.getIdMesa());
            ps.setFloat(4, pedido.getMontoTotal());
            ps.setString(5, pedido.getFechaPedido());

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

    @Override
    public boolean editar(Pedido pedido) {
        confirmacion = false;
        try {
            ps = CNX.conectar().prepareStatement("UPDATE Clientes SET idEmpleado = ?, idCliente = ?, idMesa = ?, montoTotal = ?, fechaPedido = ? WHERE idPedido = ?");
            ps.setInt(1, pedido.getIdEmpleado());
            ps.setInt(2, pedido.getIdCliente());
            ps.setInt(3, pedido.getIdMesa());
            ps.setFloat(4, pedido.getMontoTotal());
            ps.setString(5, pedido.getFechaPedido());
            ps.setInt(6, pedido.getIdPedido());

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

    @Override
    public boolean eliminar(int idPedido) {
        confirmacion = false;
        try {
            ps = CNX.conectar().prepareStatement("DELETE FROM Pedidos WHERE idPedido = ?");
            ps.setInt(1, idPedido);
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

}
