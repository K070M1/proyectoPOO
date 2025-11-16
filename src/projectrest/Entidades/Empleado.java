/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Entidades;

/**
 *
 * @author Rafael
 */
public class Empleado {

    private int idEmpleado;
    private String nombreCompleto;
    private String rol;
    private String tipoDocumento;
    private String documento;
    private String usuario;
    private String clave;
    private String turno;
    private boolean estado;
    private String fechaIngreso;
    private String fechaSalida;

    public Empleado() {
    }

    public Empleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleado(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public Empleado(int idEmpleado, String nombreCompleto, String rol, String tipoDocumento, String documento, String turno, boolean estado, String fechaIngreso, String fechaSalida) {
        this.idEmpleado = idEmpleado;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.turno = turno;
        this.estado = estado;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }

    public Empleado(int idEmpleado, String nombreCompleto, String rol, String tipoDocumento, String documento, String usuario, String clave, String turno, boolean estado, String fechaIngreso, String fechaSalida) {
        this.idEmpleado = idEmpleado;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.usuario = usuario;
        this.clave = clave;
        this.turno = turno;
        this.estado = estado;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", nombreCompleto=" + nombreCompleto + ", rol=" + rol + ", tipoDocumento=" + tipoDocumento + ", documento=" + documento + ", usuario=" + usuario + ", clave=" + clave + ", turno=" + turno + ", estado=" + estado + ", fechaIngreso=" + fechaIngreso + ", fechaSalida=" + fechaSalida + '}';
    }
}
