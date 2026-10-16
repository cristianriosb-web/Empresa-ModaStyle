import java.time.LocalDate;
import java.util.ArrayList;

public class Venta {
    private String codigo;
    private LocalDate fecha;
    private boolean confirmada;
    private Sucursal sucursal;
    private Cliente cliente;
    private Vendedor vendedor;
    private ArrayList<DetalleVenta> detalles;

    public Venta(Sucursal sucursal, Cliente cliente, Vendedor vendedor) {
        this.sucursal = sucursal;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.fecha = LocalDate.now();
        this.confirmada = false;
        this.detalles = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public ArrayList<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    public boolean agregarDetalle(DetalleVenta detalleVenta){
        return detalles.add(detalleVenta);
    }
    public boolean validarExistencias() {
        for (DetalleVenta detalle : detalles) {
            Inventario inventario = detalle.getInventario();

            if (inventario == null) {
                return false;
            }

            if (inventario.getCantidadDisponible() < detalle.getCantidad()) {
                return false;
            }
        }

        return true;
    }
    public double calcularSubtotal() {
        double subtotal = 0;

        for (DetalleVenta detalle : detalles) {
            subtotal += detalle.calcularSubtotal();
        }

        return subtotal;
    }

    public double calcularSobrecargo() {
        double sobrecargo = 0;

        for (DetalleVenta detalle : detalles) {
            Prenda prenda = detalle.getPrenda();
            double subtotalDetalle = detalle.calcularSubtotal();
            double porcentajeRecargo = prenda.getCategoria().getRecargo();

            sobrecargo += subtotalDetalle * porcentajeRecargo;
        }

        return sobrecargo;
    }

    public double calcularDescuento() {
        double valorAntesDescuento = calcularSubtotal() + calcularSobrecargo();

        return cliente.calcularDescuento(valorAntesDescuento);
    }
    public boolean confirmar() {
        if(!validarExistencias()){
            return false;
        }
        for (DetalleVenta detalle : detalles) {
            Inventario inventario = detalle.getInventario();

            inventario.descontarExistencias(detalle.getCantidad());
            inventario.agregarDetalle(detalle);
        }
        this.confirmada = true;
        sucursal.agregarVenta(this);
        return true;
    }

    public double calcularTotal() {
        double subtotal = calcularSubtotal();
        double sobrecargo = calcularSobrecargo();
        double descuento = calcularDescuento();

        return subtotal + sobrecargo - descuento;
    }

}
