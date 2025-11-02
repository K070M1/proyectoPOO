package projectrest.Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projectrest.Conexion.Conexion;
import projectrest.Datos.Interfaces.IPlato;
import projectrest.Entidades.Plato;

public class PlatoDAO implements IPlato {

    private final Conexion CNX;
    private PreparedStatement ps;
    private ResultSet rs;

    public PlatoDAO() {
        this.CNX = Conexion.getInstancia();
    }

    @Override
    public List<Plato> listar(String texto) {
        List<Plato> registros = new ArrayList<>();
        try {
            ps = CNX.conectar().prepareStatement(
                    "SELECT idPlato, codigo, nombre, descripcion, categoriaPlato, precio, estadoPlato, imagenReferencia "
                    + "FROM Plato WHERE nombre LIKE ? OR codigo LIKE ? OR categoriaPlato LIKE ? ORDER BY idPlato DESC"
            );
            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");
            ps.setString(3, "%" + texto + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Plato plato = new Plato();
                plato.setIdPlato(rs.getInt(1));
                plato.setCodigo(rs.getString(2));
                plato.setNombre(rs.getString(3));
                plato.setDescripcion(rs.getString(4));
                plato.setCategoriaPlato(rs.getString(5));
                plato.setPrecio(rs.getFloat(6));
                plato.setEstadoPlato(rs.getBoolean(7));
                plato.setImagenReferencia(rs.getString(8));
                registros.add(plato);
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
    public boolean insertar(Plato plato) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement(
                    "INSERT INTO Platos (codigo, nombre, descripcion, categoriaPlato, precio, estadoPlato, imagenReferencia) VALUES (?,?,?,?,?,?,?)"
            );
            ps.setString(1, plato.getCodigo());
            ps.setString(2, plato.getNombre());
            ps.setString(3, plato.getDescripcion());
            ps.setString(4, plato.getCategoriaPlato());
            ps.setFloat(5, plato.getPrecio());
            ps.setBoolean(6, plato.isEstadoPlato());
            ps.setString(7, plato.getImagenReferencia());
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
    public boolean editar(Plato plato) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement(
                    "UPDATE Platos SET codigo=?, nombre=?, descripcion=?, categoriaPlato=?, precio=?, estadoPlato=?, imagenReferencia=? WHERE idPlato=?"
            );
            ps.setString(1, plato.getCodigo());
            ps.setString(2, plato.getNombre());
            ps.setString(3, plato.getDescripcion());
            ps.setString(4, plato.getCategoriaPlato());
            ps.setFloat(5, plato.getPrecio());
            ps.setBoolean(6, plato.isEstadoPlato());
            ps.setString(7, plato.getImagenReferencia());
            ps.setInt(8, plato.getIdPlato());
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
    public boolean eliminar(Plato plato) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement("DELETE FROM Platos WHERE idPlato=?");
            ps.setInt(1, plato.getIdPlato());
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
