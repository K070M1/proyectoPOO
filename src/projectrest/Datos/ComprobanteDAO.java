package projectrest.Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projectrest.Conexion.Conexion;
import projectrest.Datos.Interfaces.IComprobante;
import projectrest.Entidades.Comprobante;

public class ComprobanteDAO implements IComprobante {

    private final Conexion CNX;
    private PreparedStatement ps;
    private ResultSet rs;

    public ComprobanteDAO() {
        // Usa el mismo patrón que ClienteDAO
        this.CNX = Conexion.getInstancia();
    }

    @Override
    public List<Comprobante> listar(String texto) {
        List<Comprobante> registros = new ArrayList<>();
        try {
            ps = CNX.conectar().prepareStatement(
                    "SELECT idComprobante, idPedido, serie, correlativo, tipoComprobante "
                    + "FROM comprobantes "
                    + "WHERE serie LIKE ? OR tipoComprobante LIKE ? "
                    + "ORDER BY idComprobante DESC"
            );
            String like = "%" + (texto == null ? "" : texto.trim()) + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            rs = ps.executeQuery();
            while (rs.next()) {
                Comprobante c = new Comprobante();
                c.setIdComprobante(rs.getInt(1));
                c.setIdPedido(rs.getInt(2));
                c.setSerie(rs.getString(3));
                c.setCorrelativo(rs.getInt(4));
                c.setTipoComprobante(rs.getString(5));
                registros.add(c);
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
    public boolean insertar(Comprobante comprobante) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement(
                    "INSERT INTO comprobantes (idPedido, serie, correlativo, tipoComprobante) VALUES (?,?,?,?)"
            );
            ps.setInt(1, comprobante.getIdPedido());
            ps.setString(2, comprobante.getSerie());
            ps.setInt(3, comprobante.getCorrelativo());
            ps.setString(4, comprobante.getTipoComprobante());
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
    public boolean eliminar(Comprobante comprobante) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement("DELETE FROM comprobantes WHERE idComprobante = ?");
            ps.setInt(1, comprobante.getIdComprobante());
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
    public boolean editar(Comprobante comprobante) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement(
                    "UPDATE comprobantes SET idPedido = ?, serie = ?, correlativo = ?, tipoComprobante = ? "
                    + "WHERE idComprobante = ?"
            );
            ps.setInt(1, comprobante.getIdPedido());
            ps.setString(2, comprobante.getSerie());
            ps.setInt(3, comprobante.getCorrelativo());
            ps.setString(4, comprobante.getTipoComprobante());
            ps.setInt(5, comprobante.getIdComprobante());
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
