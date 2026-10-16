import java.util.ArrayList;
import java.util.List;
public class Empresa {
    private String nombre;
    private String nit;
    private String direccion;

    //Listas
    private List<Sucursal> listaSucursales;
    private List<Prenda> listaPrendas;
    private List<Cliente> listaClientes;
    private List<Vendedor> listaVendedores;
    private List<Venta> listaVentas;
    //constructor
    public Empresa(String nombre, String nit, String direccion) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.listaSucursales = new ArrayList<>();
        this.listaPrendas = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
        this.listaVendedores = new ArrayList<>();
        this.listaVentas = new ArrayList<>();
    }
    //Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNit() {
        return nit;
    }
    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
    public void setListaClientes(List<Cliente> listaClientes){
        this.listaClientes = listaClientes;
    }

    public List<Prenda> getListaPrendas() {
        return listaPrendas;
    }
    public void setListaPrendas(List<Prenda> listaPrendas){
        this.listaPrendas = listaPrendas;
    }

    public List<Sucursal> getListaSucursales() {
        return listaSucursales;
    }
    public void setListaSucursales(List<Sucursal> listaSucursales){
        this.listaSucursales = listaSucursales;
    }

    public List<Vendedor> getListaVendedores() {
        return listaVendedores;
    }
    public void setListaVendedores(List<Vendedor> listaVendedores){
        this.listaVendedores = listaVendedores;
    }
    public List<Venta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }

    //toString
    @Override
    public String toString() {
        return "Empresa " +
                "nombre='" + nombre + '\'' +
                ", nit='" + nit + '\'' +
                ", direccion='" + direccion + '\'' +
                ", listaSucursales=" + listaSucursales +
                ", listaPrendas=" + listaPrendas +
                ", listaClientes=" + listaClientes +
                ", listaVendedores=" + listaVendedores +
                ", listaVentas=" + listaVentas;
    }

    //Registrar sucursal
    public boolean registrarSucursal( Sucursal sucursal) {
        boolean agregado = false;
        if (listaSucursales.contains(sucursal)) {
            System.out.println("Sucursal ya registrada");
        } else {
            listaSucursales.add(sucursal);
            agregado = true;
        }
        return agregado;
    }
    //Registrar prenda
    public boolean registrarPrenda( Prenda prenda) {
        boolean agregado = false;
        if (listaPrendas.contains(prenda)) {
            System.out.println("Prenda ya registrada");
        } else {
            listaPrendas.add(prenda);
            agregado = true;
        }
        return agregado;
    }
    //Registrar cliente
    public boolean registrarCliente( Cliente cliente){
        if (buscarCliente(cliente.getDocumento()) != null) {
            System.out.println("Cliente ya registrado");
            return false;
        }
        listaClientes.add(cliente);
        return false;
    }
    //Registrar vendedor
    public boolean registrarVendedor( Vendedor vendedor){
        if (buscarVendedor(vendedor.getDocumento()) !=null) {
            System.out.println("Vendedor ya registrado");
            return false;
        }
        listaVendedores.add(vendedor);
        return false;

    }
    //Registrar venta
    public boolean registrarVenta(Venta venta) {
        boolean agregado = false;
        if (listaVentas.contains(venta)) {
            System.out.println("Venta ya registrada");
        } else {
            listaVentas.add(venta);
        }
        return agregado;
    }
    //existe el documento
    public boolean existeDocumento(String documento) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getDocumento().equals(documento)) {
                return true;
            }
        }
        for (Vendedor vendedor : listaVendedores) {
            if (vendedor.getDocumento().equals(documento)) {
                return true;
            }}
        return false;
    }
    //Buscar sucursal
    public Sucursal buscarSucursal (String codigoSucursal){
         Sucursal encontrada = null;

         for (Sucursal sucursal1 : listaSucursales) {
             if (sucursal1.getCodigo().equals(codigoSucursal)) {
                 encontrada = sucursal1;
                 break;
             }
         }
         return encontrada;
    }
    //Buscar prenda
    public Prenda buscarPrenda (String codigoPrenda){
         Prenda encontrada = null;
         for (Prenda prenda1 : listaPrendas) {
             if (prenda1.getCodigo().equals(codigoPrenda)) {
                 encontrada = prenda1;
                 break;
             }
         }
         return encontrada;
    }
    //Buscar cliente
    public Cliente buscarCliente (String documento){
         Cliente encontrada = null;
         for (Cliente cliente1 : listaClientes) {
             if (cliente1.getDocumento().equals(documento)) {
                 encontrada = cliente1;
                 break;
             }
         }
         return encontrada;
    }
    //Buscar vendedor
    public Vendedor buscarVendedor (String documento){
         Vendedor encontrada = null;
         for (Vendedor vendedor1 : listaVendedores) {
             if (vendedor1.getDocumento().equals(documento)) {
                 encontrada = vendedor1;
                 break;
             }
         }
         return encontrada;
    }
    //Consultar Prendas Agotadas
    public ArrayList<Inventario> consultarPrendasAgotadas(){
        ArrayList<Inventario> prendasAgotadas = new ArrayList<>();
        for (Sucursal sucursal : listaSucursales) {
            for (Inventario inventario : sucursal.getListaInventarios()) {
                if (inventario.getCantidadDisponible() == 0) {
                    prendasAgotadas.add(inventario);
                }
            }
        }
        return prendasAgotadas;
    }
    //Consultar Ventas por Vendedor
    public ArrayList<Venta> VentasPorVendedor(String documentoVendedor){
        ArrayList<Venta> ventasPorVendedor = new ArrayList<>();
        for (Venta venta : listaVentas) {
            if (venta.getVendedor().getDocumento().equals(documentoVendedor)) {
                ventasPorVendedor.add(venta);
            }
        }
        return ventasPorVendedor;
    }
    //Consultar Compras por Cliente
    public ArrayList<Venta> ComprasPorCliente(String documentoCliente){
        ArrayList<Venta> comprasPorCliente = new ArrayList<>();
        for (Venta venta : listaVentas) {
            if (venta.getCliente().getDocumento().equals(documentoCliente)) {
                comprasPorCliente.add(venta);
            }
        }
        return comprasPorCliente;
    }
    //Calcular Ingresos por Sucursal
    public double calcularIngresosSucursal(String codigo){
        double ingresos = 0;
        for (Venta venta : listaVentas) {
            if (venta.getSucursal().getCodigo().equals(codigo)) {
                ingresos += venta.calcularSubtotal();
            }
        }
        return ingresos;
    }
    //Prenda más vendida
    public Prenda prendaMasVendida(){
        Prenda prendaMasVendida = null;
        int cantidadVendidas = 0;
        for (Prenda prenda : listaPrendas) {
            int cantidadVendidasPrenda = 0;
            for (Inventario inventario : prenda.getListaInventarios()) {
                cantidadVendidasPrenda += inventario.calcularUnidadesVendidas();
            }
            if (cantidadVendidasPrenda > cantidadVendidas) {
                cantidadVendidas = cantidadVendidasPrenda;
                prendaMasVendida = prenda;
            }
        }
        return prendaMasVendida;
    }

}
