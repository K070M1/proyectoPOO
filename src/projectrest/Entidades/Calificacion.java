/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Entidades;

/**
 *
 * @author Rafael
 */
public class Calificacion {

    private int idCalificacion;
    private int idPedido;
    private int calificacion;
    private String comentarios;
    private String fechaCalificacion;

    public Calificacion() {
    }

    public Calificacion(int idCalificacion, int idPedido, int calificacion, String comentarios, String fechaCalificacion) {
        this.idCalificacion = idCalificacion;
        this.idPedido = idPedido;
        this.calificacion = calificacion;
        this.comentarios = comentarios;
        this.fechaCalificacion = fechaCalificacion;
    }

    public int getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getFechaCalificacion() {
        return fechaCalificacion;
    }

    public void setFechaCalificacion(String fechaCalificacion) {
        this.fechaCalificacion = fechaCalificacion;
    }

    @Override
    public String toString() {
        return "Calificacion{" + "idCalificacion=" + idCalificacion + ", idPedido=" + idPedido + ", calificacion=" + calificacion + ", comentarios=" + comentarios + ", fechaCalificacion=" + fechaCalificacion + '}';
    }

}
