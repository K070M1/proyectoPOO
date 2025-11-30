package projectrest.Datos;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projectrest.Conexion.Conexion;
import projectrest.Datos.Interfaces.IEmpleado;
import projectrest.Entidades.Empleado;

public class EmpleadoDAO implements IEmpleado {

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
                    "INSERT INTO Empleado (nombreCompleto, rol, tipoDocumento, documento, turno, estado, fechaIngreso) VALUES (?,?,?,?,?,?, NOW())"
            );
            ps.setString(1, empleado.getNombreCompleto());
            ps.setString(2, empleado.getRol());
            ps.setString(3, empleado.getTipoDocumento());
            ps.setString(4, empleado.getDocumento());
            ps.setString(5, empleado.getTurno());
            ps.setBoolean(6, empleado.isEstado());
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
                    "UPDATE Empleado SET nombreCompleto=?, rol=?, tipoDocumento=?, documento=?, turno=?, estado=? WHERE idEmpleado=?"
            );
            ps.setString(1, empleado.getNombreCompleto());
            ps.setString(2, empleado.getRol());
            ps.setString(3, empleado.getTipoDocumento());
            ps.setString(4, empleado.getDocumento());
            ps.setString(5, empleado.getTurno());
            ps.setBoolean(6, empleado.isEstado());
            ps.setInt(7, empleado.getIdEmpleado());
            // ps.setString(7, empleado.getFechaIngreso());
            // ps.setString(8, empleado.getFechaSalida());
            // ps.setInt(9, empleado.getIdEmpleado());
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
            ps = CNX.conectar().prepareStatement("DELETE FROM Empleado WHERE idEmpleado=?");
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

    public boolean editarCredenciales(Empleado empleado) {
        boolean ok = false;
        try {
            ps = CNX.conectar().prepareStatement(
                    "UPDATE Empleado SET usuario=?, clave=? WHERE idEmpleado=?"
            );
            ps.setString(1, empleado.getUsuario());
            ps.setString(2, this.hashing(empleado.getClave()));
            ps.setInt(3, empleado.getIdEmpleado());
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

    public String iniciarSesion(String usuario, String clave) {
        String message = "";
        try {
            ps = CNX.conectar().prepareStatement("SELECT idEmpleado, clave, estado, rol, usuario FROM Empleado WHERE usuario = ?");
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("idEmpleado");
                String pass = rs.getString("clave");
                boolean estado = rs.getBoolean("estado");
                String rol = rs.getString("rol");
                String user = rs.getString("usuario");

                if (estado) {
                    String clave_ing_hash = hashing(clave);
                    if (pass.equals(clave_ing_hash)) {
                        message = user;
                    } else {
                        message = "ERROR LOGIN: Clave no válida..!";
                    }
                } else {
                    message = "ERROR LOGIN: Estado no válido..!";
                }
            } else {
                message = "ERROR LOGIN: Usuario no encontrado o inválido..!";
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            message = "ERROR LOGIN: " + e.getMessage();
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CNX.desconectar();
        }
        System.out.println("===> " + message);
        return message;
    }

    private String hashing(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(pass.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
