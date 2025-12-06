package projectrest.Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import projectrest.Entidades.Pedido;
import projectrest.Entidades.DetallePedido;
import projectrest.Conexion.Conexion;
import projectrest.Datos.Interfaces.IPedido;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoDAO implements IPedido {

    private final Conexion CNX;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean confirmacion;

    public PedidoDAO() {
        this.CNX = Conexion.getInstancia();
    }

    @Override
    public List<Pedido> listar(String cliente) {
        List<Pedido> registros = new ArrayList<>();
        try {
            ps = CNX.conectar().prepareStatement("SELECT p.idPedido, p.idEmpleado, e.nombreCompleto, p.idCliente, c.nombreCompleto, p.idMesa, m.nroMesa, p.montoTotal, p.fechaPedido, p.estado, o.tipoComprobante, o.serie, o.correlativo FROM pedido p INNER JOIN empleado e ON p.idEmpleado = e.idEmpleado INNER JOIN cliente c ON p.idCliente = c.idCliente INNER JOIN mesa m ON p.idMesa = m.idMesa LEFT JOIN comprobante o ON p.idPedido = o.idPedido WHERE c.nombreCompleto LIKE ?");
            ps.setString(1, "%" + cliente + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(
                        new Pedido(
                                rs.getInt(1),
                                rs.getInt(2),
                                rs.getString(3),
                                rs.getInt(4),
                                rs.getString(5),
                                rs.getInt(6),
                                rs.getString(7),
                                rs.getFloat(8),
                                rs.getString(9),
                                rs.getBoolean(10),
                                rs.getString(11),
                                rs.getString(12),
                                rs.getInt(13)
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
        Connection conn = null;
        try {
            conn = CNX.conectar();
            conn.setAutoCommit(false);
            String sqlInsertPedido = "INSERT INTO pedido (idEmpleado,idCliente,idMesa,montoTotal,fechaPedido,estado) VALUES (?,?,?,?,now(),?)";
            ps = conn.prepareStatement(sqlInsertPedido, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pedido.getIdEmpleado());
            ps.setInt(2, pedido.getIdCliente());
            ps.setInt(3, pedido.getIdMesa());
            ps.setFloat(4, pedido.getMontoTotal());
            ps.setBoolean(5, pedido.isEstado());

            int filasAfectadas = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            int idGenerado = 0;
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }
            if (filasAfectadas == 1) {
                System.out.println("PEDIDO ID::: " + idGenerado);
                String sqlInsertDetalle = "INSERT INTO detallepedido (idPedido,idPlato,cantidad,descuento) VALUES (?,?,?,?)";
                PreparedStatement psDetalle = conn.prepareStatement(sqlInsertDetalle);
                int detallesAfectados = 0;

                for (DetallePedido item : pedido.getDetalles()) {
                    psDetalle.setInt(1, idGenerado);
                    psDetalle.setInt(2, item.getIdPlato());
                    psDetalle.setInt(3, item.getCantidad());
                    psDetalle.setDouble(4, item.getDescuento());

                    detallesAfectados += psDetalle.executeUpdate();
                    System.out.println("Detalle agreado: " + detallesAfectados);
                }

                System.out.println("Si paso detalles...!");

                if (detallesAfectados == pedido.getDetalles().size()) {
                    String sqlInsertComprobante = "INSERT INTO comprobante (serie, correlativo, tipoComprobante, idPedido) VALUES (?,?,?,?)";
                    ps = conn.prepareStatement(sqlInsertComprobante);
                    ps.setString(1, pedido.getSerie());
                    ps.setInt(2, pedido.getCorrelativo());
                    ps.setString(3, pedido.getTipoComprobante());
                    ps.setInt(4, idGenerado);
                    confirmacion = ps.executeUpdate() > 0;
                    System.out.println("Comprobante--> " + confirmacion);
                } else {
                    System.out.println("AQUI DETALLESS..----");
                    conn.rollback();
                }

                conn.commit();
            } else {
                System.out.println("AQUI VENTAS.....");
                conn.rollback();
            }
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return confirmacion;
    }

    @Override
    public boolean editarEstado(Pedido pedido) {
        confirmacion = false;
        try {
            ps = CNX.conectar().prepareStatement("UPDATE pedido SET estado=? WHERE idPedido=?");
            ps.setBoolean(1, pedido.isEstado());
            ps.setInt(2, pedido.getIdPedido());
            if (ps.executeUpdate() > 0) {
                confirmacion = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CNX.desconectar();
        }
        return confirmacion;
    }

    public boolean existePedido(String serie, int correlativo) {
        confirmacion = false;
        try {
            ps = CNX.conectar().prepareStatement("SELECT idPedido FROM comprobante WHERE serie=? AND correlativo=?");
            ps.setString(1, serie);
            ps.setInt(2, correlativo);
            rs = ps.executeQuery();
            if (rs.next()) {
                confirmacion = true;
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
        return confirmacion;
    }

}
