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
    public List<DetallePedido> listar(int id) {
        List<DetallePedido> registros = new ArrayList();
        try {
            ps = CNX.conectar().prepareStatement("SELECT p.idPlato,p.codigo,p.nombre,p.precio,p.categoriaPlato,d.cantidad,d.descuento,((d.cantidad*p.precio)-d.descuento) as sub_total FROM detallepedido d INNER JOIN plato p ON d.idPlato=p.idPlato WHERE d.idPedido=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(
                        new DetallePedido(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getFloat(4),
                                rs.getString(5),
                                rs.getInt(6),
                                rs.getFloat(7),
                                rs.getFloat(8)
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
}
