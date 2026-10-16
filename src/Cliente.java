import java.util.ArrayList;

public class Cliente extends Persona {
    private String codigoAfiliacion;
    private CategoriaCliente categoria;

    private ArrayList<Venta> compras;

    public Cliente(String documento, String nombreCompleto, String correo, String telefono,
                   String codigoAfiliacion, CategoriaCliente categoria) {
        super(documento, nombreCompleto, correo, telefono);
        this.codigoAfiliacion = codigoAfiliacion;
        this.categoria = categoria;
        this.compras = new ArrayList<>();
    }

    public double calcularDescuento() {
        return categoria.getDescuento();
    }

    public void agregarCompra(Venta venta) {
        compras.add(venta);
    }

    public String getCodigoAfiliacion() {
        return codigoAfiliacion;
    }

    public void setCodigoAfiliacion(String codigoAfiliacion) {
        this.codigoAfiliacion = codigoAfiliacion;
    }

    public CategoriaCliente getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaCliente categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Venta> getCompras() {
        return compras;
    }

    public void setCompras(ArrayList<Venta> compras) {
        this.compras = compras;
    }

    @Override
    public String toString() {
        return "Cliente -> " + super.toString() + " | Afiliacion: " + codigoAfiliacion
                + " | Categoria: " + categoria;
    }
}
