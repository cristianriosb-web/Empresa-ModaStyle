public class DetalleVenta {
    private int cantidad;
    private double precioUnitarioFinal;
    private double subtotal;
    private Venta venta;
    private Inventario inventario;

    public DetalleVenta(Venta venta, Inventario inventario, int cantidad) {
        this.venta = venta;
        this.inventario = inventario;
        this.cantidad = cantidad;
        this.precioUnitarioFinal = calcularPrecioUnitarioFinal();
        this.subtotal = calcularSubtotal();
    }

    public double calcularPrecioUnitarioFinal() {
        double precioConRecargo = inventario.getPrenda().calcularPrecioConRecargo();
        double descuento = venta.getCliente().calcularDescuento();
        return precioConRecargo * (1 - descuento);
    }

    public double calcularSubtotal() {
        return precioUnitarioFinal * cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }

    public double getPrecioUnitarioFinal() {
        return precioUnitarioFinal;
    }

    public void setPrecioUnitarioFinal(double precioUnitarioFinal) {
        this.precioUnitarioFinal = precioUnitarioFinal;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    @Override
    public String toString() {
        Prenda prenda = inventario.getPrenda();
        return prenda.getCodigo() + " - " + prenda.getNombre() + " | Talla: " + inventario.getTalla()
                + " | Color: " + inventario.getColor() + " | Cantidad: " + cantidad
                + " | Precio unitario final: " + String.format("%.2f", precioUnitarioFinal)
                + " | Subtotal: " + String.format("%.2f", subtotal);
    }
}