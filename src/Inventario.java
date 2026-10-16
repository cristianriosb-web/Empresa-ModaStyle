import java.util.ArrayList;

public class Inventario {
    private String talla;
    private String color;
    private int cantidadDisponible;
    private Sucursal sucursal;
    private Prenda prenda;
    private ArrayList<DetalleVenta> detallesVendidos;

    public Inventario(Sucursal sucursal, Prenda prenda, String talla, String color, int cantidadDisponible) {
        this.sucursal = sucursal;
        this.prenda = prenda;
        this.talla = talla.toUpperCase();
        this.color = color;
        this.cantidadDisponible = cantidadDisponible;
        this.detallesVendidos = new ArrayList<>();
    }

    public boolean hayExistencias(int cantidad) {
        return cantidad <= cantidadDisponible;
    }

    public void descontar(int cantidad) {
        cantidadDisponible -= cantidad;
    }

    public boolean estaAgotado() {
        return cantidadDisponible == 0;
    }

    public void agregarDetalle(DetalleVenta detalle) {
        detallesVendidos.add(detalle);
    }

    public int calcularUnidadesVendidas() {
        int total = 0;
        for (DetalleVenta detalle : detallesVendidos) {
            total += detalle.getCantidad();
        }
        return total;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }

    public ArrayList<DetalleVenta> getDetallesVendidos() {
        return detallesVendidos;
    }

    public void setDetallesVendidos(ArrayList<DetalleVenta> detallesVendidos) {
        this.detallesVendidos = detallesVendidos;
    }

    @Override
    public String toString() {
        return "Sucursal: " + sucursal.getNombre() + " | Prenda: " + prenda.getCodigo() + " - " + prenda.getNombre()
                + " | Talla: " + talla + " | Color: " + color + " | Disponibles: " + cantidadDisponible;
    }
}
