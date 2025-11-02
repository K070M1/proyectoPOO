package projectrest.Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projectrest.Conexion.Conexion;
import projectrest.Datos.Interfaces.IMesa;
import projectrest.Entidades.Mesa;

public class MesaDAO implements IMesa {

    private final Conexion CNX;
    private PreparedStatement ps;
    private ResultSet rs;

    public MesaDAO() {
        this.CNX = Conexion.getInstancia();
    }

    @Override
    public List<Mesa> listar(String texto) {
        List<Mesa> registros = new ArrayList<>();
        try {
            ps = CNX.conectar().prepareStatement(
                    "SELECT idMesa, nroMesa, maximoClientes "
                    + "FROM Mesa "
                    + "WHERE nroMesa LIKE ? "
                    + "ORDER BY idMesa DESC"
            );
            String like = "%" + (texto == null ? "" : texto.trim()) + "%";
            ps.setString(1, like);
            rs = ps.executeQuery();
            while (rs.next()) {
                Mesa m = new Mesa();
                m.setIdMesa(rs.getInt(1));
                m.setNroMesa(rs.getString(2));
                m.setMaximoClientes(rs.getInt(3));
                registros.add(m);
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
    public boolean insertar(Mesa mesa) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement(
                    "INSERT INTO Mesa (nroMesa, maximoClientes) VALUES (?, ?)"
            );
            ps.setString(1, mesa.getNroMesa());
            ps.setInt(2, mesa.getMaximoClientes());
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
    public boolean eliminar(Mesa mesa) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement("DELETE FROM Mesa WHERE idMesa = ?");
            ps.setInt(1, mesa.getIdMesa());
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
    public boolean editar(Mesa mesa) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement(
                    "UPDATE Mesa SET nroMesa = ?, maximoClientes = ? WHERE idMesa = ?"
            );
            ps.setString(1, mesa.getNroMesa());
            ps.setInt(2, mesa.getMaximoClientes());
            ps.setInt(3, mesa.getIdMesa());
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
