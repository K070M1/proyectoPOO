package projectrest.Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projectrest.Conexion.Conexion;
import projectrest.Datos.Interfaces.IEmpleado;
import projectrest.Entidades.Empleado;

public class EmpleadoDAO implements IEmpleado{

    private final Conexion CNX;
    private PreparedStatement ps;
    private ResultSet rs;

    public EmpleadoDAO() {
        this.CNX = Conexion.getInstancia();
    }

    @Override
    public List<Empleado> listar(String texto) {
        List<Empleado> registros = new ArrayList<>();
        try {
            ps = CNX.conectar().prepareStatement(
                    "SELECT idEmpleado, nombreCompleto, rol, tipoDocumento, documento, usuario, clave, turno, estado, fechaIngreso, fechaSalida "
                    + "FROM Empleado WHERE nombreCompleto LIKE ? OR usuario LIKE ? ORDER BY idEmpleado DESC"
            );
            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setIdEmpleado(rs.getInt(1));
                emp.setNombreCompleto(rs.getString(2));
                emp.setRol(rs.getString(3));
                emp.setTipoDocumento(rs.getString(4));
                emp.setDocumento(rs.getString(5));
                emp.setUsuario(rs.getString(6));
                emp.setClave(rs.getString(7));
                emp.setTurno(rs.getString(8));
                emp.setEstado(rs.getBoolean(9));
                emp.setFechaIngreso(rs.getString(10));
                emp.setFechaSalida(rs.getString(11));
                registros.add(emp);
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
    public boolean insertar(Empleado empleado) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement(
                    "INSERT INTO Empleados (nombreCompleto, rol, tipoDocumento, documento, usuario, clave, turno, estado, fechaIngreso) VALUES (?,?,?,?,?,?,?,?,?)"
            );
            ps.setString(1, empleado.getNombreCompleto());
            ps.setString(2, empleado.getRol());
            ps.setString(3, empleado.getTipoDocumento());
            ps.setString(4, empleado.getDocumento());
            ps.setString(5, empleado.getUsuario());
            ps.setString(6, empleado.getClave());
            ps.setString(7, empleado.getTurno());
            ps.setBoolean(8, empleado.isEstado());
            ps.setString(9, empleado.getFechaIngreso());
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
    public boolean editar(Empleado empleado) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement(
                    "UPDATE Empleados SET nombreCompleto=?, rol=?, tipoDocumento=?, documento=?, usuario=?, clave=?, turno=?, estado=?, fechaIngreso=?, fechaSalida=? WHERE idEmpleado=?"
            );
            ps.setString(1, empleado.getNombreCompleto());
            ps.setString(2, empleado.getRol());
            ps.setString(3, empleado.getTipoDocumento());
            ps.setString(4, empleado.getDocumento());
            ps.setString(5, empleado.getUsuario());
            ps.setString(6, empleado.getClave());
            ps.setString(7, empleado.getTurno());
            ps.setBoolean(8, empleado.isEstado());
            ps.setString(9, empleado.getFechaIngreso());
            ps.setString(10, empleado.getFechaSalida());
            ps.setInt(11, empleado.getIdEmpleado());
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
    public boolean eliminar(Empleado empleado) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement("DELETE FROM Empleados WHERE idEmpleado=?");
            ps.setInt(1, empleado.getIdEmpleado());
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
