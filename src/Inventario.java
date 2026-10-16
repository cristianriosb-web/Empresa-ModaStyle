import java.util.ArrayList;
import java.util.List;
public class Inventario {
    private String talla;
    private String color;
    private int cantidadDisponible;
    private Sucursal sucursal;
    private Prenda prenda;
    private List<DetalleVenta> detallesVendidos;

    public Inventario(Sucursal sucursal,Prenda prenda, String talla, String color, int cantidadDisponible){
        this.sucursal = sucursal;
        this.prenda = prenda;
        this.talla = talla;
        this.color = color;
        this.cantidadDisponible = cantidadDisponible;
        this.detallesVendidos = new ArrayList<>();
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

    public List<DetalleVenta> getDetallesVendidos() {
        return detallesVendidos;
    }

    public void setDetallesVendidos(List<DetalleVenta> detallesVendidos) {
        this.detallesVendidos = detallesVendidos;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Inventario" +
                "talla='" + talla + '\'' +
                ", color='" + color + '\'' +
                ", cantidadDisponible=" + cantidadDisponible +
                ", sucursal=" + sucursal +
                ", prenda=" + prenda +
                ", detallesVendidos=" + detallesVendidos;
    }
    public boolean hayExistencias(int cantidadDisponible){
        boolean existencias=false;
        if(cantidadDisponible>0){
            existencias=true;
        }
        else {
            existencias=false;

        }
        return existencias;
    }
    public void descontarExistencias(int cantidadDisponible){
        this.cantidadDisponible=this.cantidadDisponible-cantidadDisponible;
        System.out.println("Existencias actualizadas");
    }
    public boolean estaAgotado(){
        boolean agotado=false;
        if(cantidadDisponible==0){
            agotado=true;
        }
        return agotado;
    }
    public void agregarDetalle( DetalleVenta detalleVenta){
        this.detallesVendidos.add(detalleVenta);
        System.out.println("Detalle agregado");
    }
    public int calcularUnidadesVendidas(){
    int unidadesVendidas=0;
    for(DetalleVenta detalleVenta:detallesVendidos){
        unidadesVendidas=unidadesVendidas+detalleVenta.getCantidad();
    }
    return unidadesVendidas;
    }
}

