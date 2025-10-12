/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Entidades;

/**
 *
 * @author Rafael
 */
public class Plato {

    private int idPlato;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String categoriaPlato;
    private float precio;
    private boolean estadoPlato;
    private String imagenReferencia;

    public Plato() {
    }

    public Plato(int idPlato, String codigo, String nombre, String descripcion, String categoriaPlato, float precio, boolean estadoPlato, String imagenReferencia) {
        this.idPlato = idPlato;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaPlato = categoriaPlato;
        this.precio = precio;
        this.estadoPlato = estadoPlato;
        this.imagenReferencia = imagenReferencia;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoriaPlato() {
        return categoriaPlato;
    }

    public void setCategoriaPlato(String categoriaPlato) {
        this.categoriaPlato = categoriaPlato;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean isEstadoPlato() {
        return estadoPlato;
    }

    public void setEstadoPlato(boolean estadoPlato) {
        this.estadoPlato = estadoPlato;
    }

    public String getImagenReferencia() {
        return imagenReferencia;
    }

    public void setImagenReferencia(String imagenReferencia) {
        this.imagenReferencia = imagenReferencia;
    }

    @Override
    public String toString() {
        return "Plato{" + "idPlato=" + idPlato + ", codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", categoriaPlato=" + categoriaPlato + ", precio=" + precio + ", estadoPlato=" + estadoPlato + ", imagenReferencia=" + imagenReferencia + '}';
    }

}
