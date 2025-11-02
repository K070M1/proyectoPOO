package projectrest.Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projectrest.Conexion.Conexion;
import projectrest.Datos.Interfaces.ICalificacion;
import projectrest.Entidades.Calificacion;

public class CalificacionDAO implements ICalificacion {

    private final Conexion CNX;
    private PreparedStatement ps;
    private ResultSet rs;

    public CalificacionDAO() {
        this.CNX = Conexion.getInstancia();
    }

    @Override
    public List<Calificacion> listar(String texto) {
        List<Calificacion> registros = new ArrayList<>();
        try {
            ps = CNX.conectar().prepareStatement(
                    "SELECT idCalificacion, idPedido, calificacion, comentarios, fechaCalificacion "
                    + "FROM Calificaciones WHERE comentarios LIKE ? ORDER BY idCalificacion DESC"
            );
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(new Calificacion(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5)
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
    public boolean insertar(Calificacion calificacion) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement(
                    "INSERT INTO Calificaciones (idPedido, calificacion, comentarios, fechaCalificacion) VALUES (?,?,?,?)"
            );
            ps.setInt(1, calificacion.getIdPedido());
            ps.setInt(2, calificacion.getCalificacion());
            ps.setString(3, calificacion.getComentarios());
            ps.setString(4, calificacion.getFechaCalificacion());
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
    public boolean editar(Calificacion calificacion) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement(
                    "UPDATE Calificaciones SET idPedido=?, calificacion=?, comentarios=?, fechaCalificacion=? WHERE idCalificacion=?"
            );
            ps.setInt(1, calificacion.getIdPedido());
            ps.setInt(2, calificacion.getCalificacion());
            ps.setString(3, calificacion.getComentarios());
            ps.setString(4, calificacion.getFechaCalificacion());
            ps.setInt(5, calificacion.getIdCalificacion());
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
    public boolean eliminar(Calificacion calificacion) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement("DELETE FROM Calificaciones WHERE idCalificacion=?");
            ps.setInt(1, calificacion.getIdCalificacion());
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
