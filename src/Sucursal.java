import java.util.ArrayList;
import java.util.List;
public class Sucursal {
    private String codigo;
    private String nombre;
    private String direccion;
    private String ciudad;
    private List<Inventario> listaInventarios;
    private List<Vendedor> listaVendedores;
    private List<Venta> listaVentas;

    public Sucursal(String codigo, String nombre, String direccion, String ciudad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.listaInventarios = new ArrayList<>();
        this.listaVendedores = new ArrayList<>();
        this.listaVentas = new ArrayList<>();
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Inventario> getListaInventarios() {
        return listaInventarios;
    }

    public void setListaInventarios(List<Inventario> listaInventarios) {
        this.listaInventarios = listaInventarios;
    }

    public List<Vendedor> getListaVendedores() {
        return listaVendedores;
    }

    public void setListaVendedores(List<Vendedor> listaVendedores) {
        this.listaVendedores = listaVendedores;
    }

    public List<Venta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }
    public boolean registrarInventario(Inventario inventario){
        return listaInventarios.add(inventario);
    }
    public boolean buscarInventario(Prenda prenda, String talla, String color){
        boolean encontrado = false;
        for (Inventario inventario : listaInventarios) {
            if (inventario.getPrenda().equals(prenda) && inventario.getTalla().equals(talla) && inventario.getColor().equals(color)) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }
    public void agregarVendedor(Vendedor vendedor){
        listaVendedores.add(vendedor);
    }
    public void agregarVenta(Venta venta){
        listaVentas.add(venta);
    }
    public ArrayList<Inventario> consultarPrendasDisponibles() {
        ArrayList<Inventario> prendasDisponibles = new ArrayList<>();
        for (Inventario inventario : listaInventarios) {
            if (inventario.getCantidadDisponible() > 0) {
                prendasDisponibles.add(inventario);
            }
        }
        return prendasDisponibles;
    }
    public ArrayList<Inventario> consultarPrendasAgotadas() {
        ArrayList<Inventario> prendasAgotadas = new ArrayList<>();
        for (Inventario inventario : listaInventarios) {
            if (inventario.getCantidadDisponible() == 0) {
                prendasAgotadas.add(inventario);
            }
        }
        return prendasAgotadas;
    }
    public double calcularIngresos(){
        double ingresos = 0;
        for (Venta venta : listaVentas) {
            if (venta.isConfirmada()) {
                ingresos += venta.calcularSubtotal();
            }
        }
        return ingresos;
}
}
