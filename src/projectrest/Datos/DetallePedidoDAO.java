package projectrest.Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projectrest.Conexion.Conexion;
import projectrest.Datos.Interfaces.IDetallePedido;
import projectrest.Entidades.DetallePedido;

public class DetallePedidoDAO implements IDetallePedido {

    private final Conexion CNX;
    private PreparedStatement ps;
    private ResultSet rs;

    public DetallePedidoDAO() {
        this.CNX = Conexion.getInstancia();
    }

    @Override
    public List<DetallePedido> listar(String texto) {
        List<DetallePedido> registros = new ArrayList<>();
        try {
            ps = CNX.conectar().prepareStatement(
                    "SELECT idDetalle, idPedido, idPlato, cantidad, descuento "
                    + "FROM DetallePedidos WHERE idPedido LIKE ? ORDER BY idDetalle DESC"
            );
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(new DetallePedido(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getFloat(5)
                ));
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
    public boolean insertar(DetallePedido detalle) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement(
                    "INSERT INTO DetallePedidos (idPedido, idPlato, cantidad, descuento) VALUES (?,?,?,?)"
            );
            ps.setInt(1, detalle.getIdPedido());
            ps.setInt(2, detalle.getIdPlato());
            ps.setInt(3, detalle.getCantidad());
            ps.setFloat(4, detalle.getDescuento());
            ok = ps.executeUpdate() > 0;
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CNX.desconectar();
        }
        return ok;
    }

    @Override
    public boolean editar(DetallePedido detalle) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement(
                    "UPDATE DetallePedidos SET idPedido=?, idPlato=?, cantidad=?, descuento=? WHERE idDetalle=?"
            );
            ps.setInt(1, detalle.getIdPedido());
            ps.setInt(2, detalle.getIdPlato());
            ps.setInt(3, detalle.getCantidad());
            ps.setFloat(4, detalle.getDescuento());
            ps.setInt(5, detalle.getIdDetalle());
            ok = ps.executeUpdate() > 0;
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CNX.desconectar();
        }
        return ok;
    }

    @Override
    public boolean eliminar(DetallePedido detalle) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement("DELETE FROM DetallePedidos WHERE idDetalle=?");
            ps.setInt(1, detalle.getIdDetalle());
            ok = ps.executeUpdate() > 0;
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CNX.desconectar();
        }
        return ok;
    }
}
