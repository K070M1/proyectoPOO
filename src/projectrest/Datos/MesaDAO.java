package projectrest.Datos;

import projectrest.Entidades.Mesa;
import java.util.ArrayList;
import java.util.List;

public class MesaDAO {
    private List<Mesa> mesas = new ArrayList<>();
    private int nextId = 1;
    
    public MesaDAO() {
        mesas.add(new Mesa(1, "M01", 4));
        mesas.add(new Mesa(2, "M02", 6));
        mesas.add(new Mesa(3, "M03", 2));
    }
    
    public List<Mesa> listarTodos() {
        return new ArrayList<>(mesas);
    }
    
    public boolean insertar(Mesa mesa) {
        mesa.setIdMesa(nextId++);
        return mesas.add(mesa);
    }
    
    public Mesa buscarPorNumero(String nroMesa) {
        for (Mesa mesa : mesas) {
            if (mesa.getNroMesa().equals(nroMesa)) {
                return mesa;
            }
        }
        return null;
    }
    
    public Mesa buscarPorId(int idMesa) {
        for (Mesa mesa : mesas) {
            if (mesa.getIdMesa() == idMesa) {
                return mesa;
            }
        }
        return null;
    }
    
    public List<Mesa> buscarPorCapacidad(int capacidadMinima) {
        List<Mesa> resultado = new ArrayList<>();
        for (Mesa mesa : mesas) {
            if (mesa.getMaximoClientes() >= capacidadMinima) {
                resultado.add(mesa);
            }
        }
        return resultado;
    }
}