/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package projectrest.Presentacion;

import projectrest.Negocio.PedidoNegocio;
import projectrest.Negocio.DetallePedidoNegocio;
import projectrest.Entidades.Pedido;
import projectrest.Entidades.Cliente;
import projectrest.Entidades.Mesa;
import projectrest.Entidades.Plato;
import projectrest.Entidades.Empleado;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import projectrest.Entidades.Session.Session;

/**
 *
 * @author Rafael
 */
public class Frm_Pedido extends javax.swing.JInternalFrame {

    Session session = Session.getInstance();
    private final PedidoNegocio CONTROL;
    private final DetallePedidoNegocio CONTROL_DETALLE;
    private String action;
    private Pedido pedido;
    private Cliente cliente;
    private String resp;
    private int idRow;

    public DefaultTableModel modeloDetalles;
    public JFrame contenedor;

    public Frm_Pedido(JFrame frmP) {
        initComponents();
        this.contenedor = frmP;
        this.CONTROL = new PedidoNegocio();
        this.CONTROL_DETALLE = new DetallePedidoNegocio();
        this.listar("");
        this.cargarClientes();
        this.cargarEmpleados();
        this.cargarMesas();
        this.cargarPlatos();
        this.action = "guardar";
        this.cambiarTabb(0);
        this.crearDetalles();
    }

    private void listar(String texto) {
        TblPedido.setModel(this.CONTROL.listar(texto));
        TableRowSorter orden = new TableRowSorter(TblPedido.getModel());
        TblPedido.setRowSorter(orden);
        int[] columnas = {0, 1, 3, 5};
        this.ocultarColumnas(columnas);
    }

    private void ocultarColumnas(int[] columns) {
        for (int col : columns) {
            TblPedido.getColumnModel().getColumn(col).setMaxWidth(0);
            TblPedido.getColumnModel().getColumn(col).setMinWidth(0);
            TblPedido.getTableHeader().getColumnModel().getColumn(col).setMaxWidth(0);
            TblPedido.getTableHeader().getColumnModel().getColumn(col).setMinWidth(0);
        }
    }

    private void cargarClientes() {
        DefaultComboBoxModel<Cliente> items = this.CONTROL.seleccionarCliente();
        CmbCliente.setModel(items);
    }

    private void cargarEmpleados() {
        DefaultComboBoxModel<Empleado> items = this.CONTROL.seleccionarEmpleado();
        CmbEmpleado.setModel(items);
    }

    private void cargarMesas() {
        DefaultComboBoxModel<Mesa> items = this.CONTROL.seleccionarMesa();
        CmbMesa.setModel(items);
    }

    private void cargarPlatos() {
        DefaultComboBoxModel<Plato> items = this.CONTROL.seleccionarPlato();
        CmbPlato.setModel(items);
    }

    private void cambiarTabb(int nro) {
        TabPedido.setEnabledAt(nro, true);
        TabPedido.setEnabledAt(nro == 0 ? 1 : 0, false);
        TabPedido.setSelectedIndex(nro);
    }

    private void cambiarEstadoPedido(boolean estado) {
        if (TblPedido.getSelectedRowCount() == 1) {
            // ID
            String id = String.valueOf(TblPedido.getValueAt(TblPedido.getSelectedRow(), 0));
            String estadoPedido = String.valueOf(TblPedido.getValueAt(TblPedido.getSelectedRow(), 12));
            boolean estadoBoolean = estadoPedido.equals("Activa");
            if (estadoBoolean == estado) {
                JOptionPane.showMessageDialog(null, "Este pedido ya tiene ese estado..!", "Pedido", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int res = JOptionPane.showConfirmDialog(null, "¿Estas seguro de " + (estado ? "activar" : "anular") + " este pedido?", "Cambiar de estado al Pedido", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                pedido = new Pedido();
                pedido.setIdPedido(Integer.parseInt(id));
                pedido.setEstado(estado);
                resp = this.CONTROL.editarEstado(pedido);
                if (resp.equals("OK")) {
                    JOptionPane.showMessageDialog(null, "Registro guardado correctamente", "Pedido", JOptionPane.INFORMATION_MESSAGE);
                    this.limpiar(0);
                    this.listar("");
                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un problema con el proceso..!", "Pedido", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Necesitas seleccionar un registro de la tabla..!", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limpiar(int nro) {
        this.TxtBuscar.setText("");
        this.CmbCliente.setSelectedIndex(0);
        this.CmbComprobante.setSelectedIndex(0);
        this.CmbEmpleado.setSelectedIndex(0);
        this.CmbMesa.setSelectedIndex(0);
        this.CmbPlato.setSelectedIndex(0);
        this.TxtSerie.setText("");
        this.TxtCorrelativo.setText("");
        this.crearDetalles();
        this.ChkEmpleado.setSelected(false);
        this.TxtMonto.setText("");
        this.BtnGuardar.setVisible(true);
        this.BtnSeleccionarPlato.setEnabled(true);
        this.BtnCliente.setEnabled(true);
        this.BtnEliminarPlato.setEnabled(true);
        this.BtnPlato.setEnabled(true);
        this.ChkEmpleado.setEnabled(true);

        this.action = "guardar";
        this.idRow = -1;

        this.cambiarTabb(nro);
    }

    private void crearDetalles() {
        modeloDetalles = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                if (columna == 4) {
                    return columna == 4;
                }
                if (columna == 6) {
                    return columna == 6;
                }
                return columna == 4;
            }

            @Override
            public Object getValueAt(int row, int col) {
                if (col == 7) {
                    Double cantD;
                    try {
                        cantD = Double.parseDouble((String) getValueAt(row, 4));
                    } catch (Exception e) {
                        cantD = 1.0;
                    }
                    Double precioD = Double.parseDouble((String) getValueAt(row, 5));
                    Double descuentoD = Double.parseDouble((String) getValueAt(row, 6));
                    if (cantD != null && precioD != null && descuentoD != null) {
                        return String.format("%.2f", (cantD * precioD) - descuentoD);
                    } else {
                        return 0;
                    }
                }
                return super.getValueAt(row, col);
            }

            @Override
            public void setValueAt(Object aValue, int row, int col) {
                super.setValueAt(aValue, row, col);
                calcularTotales();
                fireTableDataChanged();
            }

        };

        modeloDetalles.setColumnIdentifiers(new Object[]{"Id", "CODIGO", "CATEGORIA", "PLATO", "CANTIDAD", "PRECIO", "DESCUENTO", "SUBTOTAL"});
        TblPlato.setModel(modeloDetalles);
    }

    private void calcularTotales() {
        double total = 0;
        int items = modeloDetalles.getRowCount();
        if (items == 0) {
            total = 0;
        } else {
            for (int i = 0; i < items; i++) {
                total = total + Double.parseDouble(String.valueOf(modeloDetalles.getValueAt(i, 7)));
            }
        }
        TxtMonto.setText(String.format("%.2f", total));
    }

    public void agregarDetalles(String id, String codigo, String nombre, String categoria, String precio, String descuento) {
        String idT;
        boolean existe = false;
        for (int i = 0; i < this.modeloDetalles.getRowCount(); i++) {
            idT = String.valueOf(this.modeloDetalles.getValueAt(i, 0));
            if (idT.equals(id)) {
                existe = true;
            }
        }
        if (existe) {
            JOptionPane.showMessageDialog(null, "La plata ya ha sido agregado en el pedido.");
        } else {
            this.modeloDetalles.addRow(new Object[]{id, codigo, categoria, nombre, "1", precio, descuento, precio});
            this.calcularTotales();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabPedido = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblPedido = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        TxtBuscar = new javax.swing.JTextField();
        BtnBuscar = new javax.swing.JButton();
        BtnNuevo = new javax.swing.JButton();
        BtnVer = new javax.swing.JButton();
        BtnAnular = new javax.swing.JButton();
        BtnReactivar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        CmbCliente = new javax.swing.JComboBox<>();
        BtnCliente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        CmbComprobante = new javax.swing.JComboBox<>();
        TxtCorrelativo = new javax.swing.JTextField();
        TxtSerie = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TxtMonto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        CmbPlato = new javax.swing.JComboBox<>();
        BtnPlato = new javax.swing.JButton();
        BtnEliminarPlato = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblPlato = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        BtnGuardar = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        CmbMesa = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        CmbEmpleado = new javax.swing.JComboBox<>();
        ChkEmpleado = new javax.swing.JCheckBox();
        BtnSeleccionarPlato = new javax.swing.JButton();

        setClosable(true);
        setTitle("Formulario de pedidos");

        TblPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TblPedido);

        jLabel1.setText("Buscar cliente:");

        BtnBuscar.setText("Buscar");
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        BtnNuevo.setText("Nuevo");
        BtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoActionPerformed(evt);
            }
        });

        BtnVer.setText("Ver");
        BtnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVerActionPerformed(evt);
            }
        });

        BtnAnular.setText("Anular");
        BtnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAnularActionPerformed(evt);
            }
        });

        BtnReactivar.setText("Reactivar");
        BtnReactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReactivarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                        .addComponent(BtnReactivar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnAnular)
                        .addGap(34, 34, 34)
                        .addComponent(BtnVer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnNuevo)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(BtnBuscar)
                    .addComponent(BtnNuevo)
                    .addComponent(BtnVer)
                    .addComponent(BtnAnular)
                    .addComponent(BtnReactivar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        TabPedido.addTab("Listado", jPanel1);

        jLabel2.setText("Cliente (*):");

        BtnCliente.setText("Buscar cliente");
        BtnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnClienteActionPerformed(evt);
            }
        });

        jLabel3.setText("Comprobante(*):");

        CmbComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nota", "Boleta", "Factura" }));

        jLabel4.setText("Serie (*):");

        jLabel5.setText("Correlativo (*):");

        jLabel6.setText("Monto total pedido:");

        TxtMonto.setEditable(false);

        jLabel7.setText("-- Detalles del Pedido --");

        jLabel8.setText("Platos:");

        BtnPlato.setText("Buscar platos");
        BtnPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPlatoActionPerformed(evt);
            }
        });

        BtnEliminarPlato.setText("Quitar plato");
        BtnEliminarPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarPlatoActionPerformed(evt);
            }
        });

        TblPlato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(TblPlato);

        jLabel9.setText("- El pedido viene con IGV incluido");

        jLabel10.setText("- Los campos marcados con (*) , son obligatorios");

        BtnGuardar.setText("Guardar");
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });

        BtnCancelar.setText("Cancelar");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        jLabel12.setText("- Debes tener mínimo 1 plato en el detalle para registrar el pedido");

        jLabel11.setText("Mesa (*):");

        jLabel13.setText("Empleado(*):");

        ChkEmpleado.setText("Empleado actual");
        ChkEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkEmpleadoActionPerformed(evt);
            }
        });

        BtnSeleccionarPlato.setText("Seleccionar plato");
        BtnSeleccionarPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeleccionarPlatoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel7))
                .addGap(384, 384, 384))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(CmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnCliente))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(TxtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtCorrelativo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CmbMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CmbComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(CmbEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ChkEmpleado))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(CmbPlato, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnSeleccionarPlato)
                        .addGap(27, 27, 27)
                        .addComponent(BtnPlato)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnEliminarPlato))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(402, 402, 402)
                                .addComponent(BtnCancelar)
                                .addGap(18, 18, 18)
                                .addComponent(BtnGuardar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCliente)
                    .addComponent(jLabel3)
                    .addComponent(CmbComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(CmbMesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(TxtCorrelativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(CmbEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChkEmpleado))
                .addGap(29, 29, 29)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(CmbPlato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPlato)
                    .addComponent(BtnEliminarPlato)
                    .addComponent(BtnSeleccionarPlato))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TxtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnGuardar)
                            .addComponent(BtnCancelar))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabPedido.addTab("Formulario", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabPedido)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabPedido)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnClienteActionPerformed
        Frm_Pedido_Cliente frm = new Frm_Pedido_Cliente(contenedor, this, true);
        frm.toFront();
    }//GEN-LAST:event_BtnClienteActionPerformed

    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed
        this.limpiar(1);
    }//GEN-LAST:event_BtnNuevoActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        this.limpiar(0);
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void ChkEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkEmpleadoActionPerformed
        boolean valueChk = this.ChkEmpleado.isSelected();
        if (valueChk) {
            CmbEmpleado.setSelectedIndex(0);
            CmbEmpleado.setEnabled(false);
        } else {
            int idEmpleado = session.getCurrentUser().getIdUser();
            for (int i = 0; i < CmbEmpleado.getItemCount(); i++) {
                Empleado e = CmbEmpleado.getItemAt(i);
                if (e.getIdEmpleado() == idEmpleado) {
                    CmbEmpleado.setSelectedIndex(i);
                    break;
                }
            }
            CmbEmpleado.setEnabled(true);
        }
    }//GEN-LAST:event_ChkEmpleadoActionPerformed

    private void BtnPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPlatoActionPerformed
        Frm_Pedido_Plato frm = new Frm_Pedido_Plato(contenedor, this, true);
        frm.toFront();
    }//GEN-LAST:event_BtnPlatoActionPerformed

    private void BtnSeleccionarPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeleccionarPlatoActionPerformed
        Plato select = (Plato) CmbPlato.getSelectedItem();
        this.agregarDetalles(Integer.toString(select.getIdPlato()), select.getCodigo(), select.getNombre(), select.getCategoriaPlato(), Float.toString(select.getPrecio()), "0");
    }//GEN-LAST:event_BtnSeleccionarPlatoActionPerformed

    private void BtnEliminarPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarPlatoActionPerformed
        if (TblPlato.getSelectedRowCount() == 1) {
            this.modeloDetalles.removeRow(TblPlato.getSelectedRow());
            this.calcularTotales();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un plato para quitar...!");
        }
    }//GEN-LAST:event_BtnEliminarPlatoActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        String title_s = this.action.equals("guardar") ? "Nuevo pedido" : "Editar pedido";

        Cliente client_slc = (Cliente) CmbCliente.getSelectedItem();
        Mesa mesa_slc = (Mesa) CmbMesa.getSelectedItem();
        String comprobante = CmbComprobante.getSelectedItem().toString();
        String serie = TxtSerie.getText();
        String correlativo = TxtCorrelativo.getText();
        Empleado empleado_slc = (Empleado) CmbEmpleado.getSelectedItem();
        String montoT = TxtMonto.getText();

        int idEmpleado = this.ChkEmpleado.isSelected() ? session.getCurrentUser().getIdUser() : empleado_slc.getIdEmpleado();
        System.out.println("EL ID EMPLEADO ES::: " + idEmpleado);

        if (serie.isEmpty() || correlativo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debes colocar la serie y correlativo de este Pedido..!", title_s, JOptionPane.WARNING_MESSAGE);
            return;
        }

        int res = JOptionPane.showConfirmDialog(null, "¿Estas seguro de registrar este Pedido?", title_s, JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            resp = this.CONTROL.insertar(client_slc.getIdCliente(), mesa_slc.getIdMesa(), comprobante.substring(0, 1), serie, Integer.parseInt(correlativo), idEmpleado, Float.parseFloat(montoT), modeloDetalles);
            if (resp.equals("OK")) {
                JOptionPane.showMessageDialog(null, "Registro guardado correctamente", title_s, JOptionPane.INFORMATION_MESSAGE);
                this.limpiar(0);
                this.listar("");
            } else {
                System.out.println("----> " + resp);
                JOptionPane.showMessageDialog(null, "Hubo un problema con el proceso..!", title_s, JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnReactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReactivarActionPerformed
        this.cambiarEstadoPedido(true);
    }//GEN-LAST:event_BtnReactivarActionPerformed

    private void BtnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAnularActionPerformed
        this.cambiarEstadoPedido(false);
    }//GEN-LAST:event_BtnAnularActionPerformed

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        this.listar(TxtBuscar.getText());
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void BtnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVerActionPerformed
        if (TblPedido.getSelectedRowCount() == 1) {
            String id = String.valueOf(TblPedido.getValueAt(TblPedido.getSelectedRow(), 0));
            String idMesa = String.valueOf(TblPedido.getValueAt(TblPedido.getSelectedRow(), 1));
            String idCLiente = String.valueOf(TblPedido.getValueAt(TblPedido.getSelectedRow(), 3));
            String idEmpleado = String.valueOf(TblPedido.getValueAt(TblPedido.getSelectedRow(), 5));
            String comprobante = String.valueOf(TblPedido.getValueAt(TblPedido.getSelectedRow(), 7));
            String serie = String.valueOf(TblPedido.getValueAt(TblPedido.getSelectedRow(), 8));
            String correlativo = String.valueOf(TblPedido.getValueAt(TblPedido.getSelectedRow(), 9));

            TxtSerie.setText(serie);
            TxtCorrelativo.setText(correlativo);
            CmbComprobante.setSelectedItem(comprobante);

            for (int i = 0; i < this.CmbCliente.getItemCount(); i++) {
                Cliente c = this.CmbCliente.getItemAt(i);
                if (c.getIdCliente() == Integer.parseInt(idCLiente)) {
                    CmbCliente.setSelectedIndex(i);
                    break;
                }
            }

            for (int i = 0; i < this.CmbMesa.getItemCount(); i++) {
                Mesa c = this.CmbMesa.getItemAt(i);
                if (c.getIdMesa()== Integer.parseInt(idMesa)) {
                    CmbMesa.setSelectedIndex(i);
                    break;
                }
            }
            
             for (int i = 0; i < this.CmbEmpleado.getItemCount(); i++) {
                Empleado c = this.CmbEmpleado.getItemAt(i);
                if (c.getIdEmpleado()== Integer.parseInt(idEmpleado)) {
                    CmbEmpleado.setSelectedIndex(i);
                    break;
                }
            }

            this.modeloDetalles = CONTROL_DETALLE.listar(Integer.parseInt(id));
            TblPlato.setModel(modeloDetalles);
            this.calcularTotales();

            this.cambiarTabb(1);
            this.BtnGuardar.setVisible(false);
            this.BtnSeleccionarPlato.setEnabled(false);
            this.BtnCliente.setEnabled(false);
            this.BtnEliminarPlato.setEnabled(false);
            this.BtnPlato.setEnabled(false);
            this.ChkEmpleado.setEnabled(false);

        } else {
            JOptionPane.showMessageDialog(null, "Necesitas seleccionar un registro de la tabla..!", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BtnVerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAnular;
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnCliente;
    private javax.swing.JButton BtnEliminarPlato;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnNuevo;
    private javax.swing.JButton BtnPlato;
    private javax.swing.JButton BtnReactivar;
    private javax.swing.JButton BtnSeleccionarPlato;
    private javax.swing.JButton BtnVer;
    private javax.swing.JCheckBox ChkEmpleado;
    public javax.swing.JComboBox<Cliente> CmbCliente;
    private javax.swing.JComboBox<String> CmbComprobante;
    private javax.swing.JComboBox<Empleado> CmbEmpleado;
    private javax.swing.JComboBox<Mesa> CmbMesa;
    public javax.swing.JComboBox<Plato> CmbPlato;
    private javax.swing.JTabbedPane TabPedido;
    private javax.swing.JTable TblPedido;
    private javax.swing.JTable TblPlato;
    private javax.swing.JTextField TxtBuscar;
    private javax.swing.JTextField TxtCorrelativo;
    private javax.swing.JTextField TxtMonto;
    private javax.swing.JTextField TxtSerie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
