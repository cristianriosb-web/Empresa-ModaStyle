import java.util.ArrayList;

public class Empresa {
    private String nombre;
    private String nit;
    private String direccion;

    private ArrayList<Sucursal> sucursales;
    private ArrayList<Prenda> prendas;
    private ArrayList<Cliente> clientes;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<Venta> ventas;

    public Empresa(String nombre, String nit, String direccion) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.sucursales = new ArrayList<>();
        this.prendas = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.vendedores = new ArrayList<>();
        this.ventas = new ArrayList<>();
    }


    public boolean registrarSucursal(Sucursal sucursal) {
        if (buscarSucursal(sucursal.getCodigo()) != null) {
            return false;
        }
        sucursales.add(sucursal);
        return true;
    }

    public boolean registrarPrenda(Prenda prenda) {
        if (buscarPrenda(prenda.getCodigo()) != null) {
            return false;
        }
        prendas.add(prenda);
        return true;
    }

    public boolean registrarCliente(Cliente cliente) {
        if (existeDocumento(cliente.getDocumento())) {
            return false;
        }
        clientes.add(cliente);
        return true;
    }

    public boolean registrarVendedor(Vendedor vendedor) {
        if (existeDocumento(vendedor.getDocumento())) {
            return false;
        }
        vendedores.add(vendedor);
        vendedor.getSucursal().agregarVendedor(vendedor);
        return true;
    }

    public boolean registrarVenta(Venta venta) {
        if (buscarVenta(venta.getCodigo()) != null) {
            return false;
        }
        if (!venta.confirmar()) {
            return false;
        }
        ventas.add(venta);
        return true;
    }


    public boolean existeDocumento(String documento) {
        if (documento == null || documento.isBlank()) {
            return false;
        }
        if (buscarCliente(documento) != null) {
            return true;
        }
        if (buscarVendedor(documento) != null) {
            return true;
        }
        return false;
    }

    public Sucursal buscarSucursal(String codigo) {
        for (Sucursal sucursal : sucursales) {
            if (sucursal.getCodigo().equalsIgnoreCase(codigo)) {
                return sucursal;
            }
        }
        return null;
    }

    public Prenda buscarPrenda(String codigo) {
        for (Prenda prenda : prendas) {
            if (prenda.getCodigo().equalsIgnoreCase(codigo)) {
                return prenda;
            }
        }
        return null;
    }

    public Cliente buscarCliente(String documentoBuscado) {
        if (documentoBuscado == null || documentoBuscado.isBlank()) {
            return null;
        }

        for (Cliente c : clientes) {
            if (c != null && c.getDocumento() != null) {
                // Compara quitando cualquier espacio en blanco en ambos lados
                if (c.getDocumento().replaceAll("\\s+", "").equalsIgnoreCase(documentoBuscado.replaceAll("\\s+", ""))) {
                    return c;
                }
            }
        }
        return null; // Si recorrió todo y no lo encontró
    }

    public Vendedor buscarVendedor(String documento) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getDocumento().equals(documento)) {
                return vendedor;
            }
        }
        return null;
    }

    public Venta buscarVenta(String codigo) {
        for (Venta venta : ventas) {
            if (venta.getCodigo().equalsIgnoreCase(codigo)) {
                return venta;
            }
        }
        return null;
    }

    public ArrayList<Inventario> consultarPrendasAgotadas() {
        ArrayList<Inventario> agotadas = new ArrayList<>();
        for (Sucursal sucursal : sucursales) {
            agotadas.addAll(sucursal.consultarPrendasAgotadas());
        }
        return agotadas;
    }

    public ArrayList<Venta> consultarVentasVendedor(String documento) {
        Vendedor vendedor = buscarVendedor(documento);
        if (vendedor == null) {
            return null;
        }
        return vendedor.getVentas();
    }

    public ArrayList<Venta> consultarComprasCliente(String documento) {
        Cliente cliente = buscarCliente(documento);
        if (cliente == null) {
            return null;
        }
        return cliente.getCompras();
    }

    public double calcularIngresosSucursal(String codigo) {
        Sucursal sucursal = buscarSucursal(codigo);
        if (sucursal == null) {
            return -1;
        }
        return sucursal.calcularIngresos();
    }

    public Prenda prendaMasVendida() {
        Prenda masVendida = null;
        int mayor = 0;
        for (Prenda prenda : prendas) {
            int unidades = prenda.calcularUnidadesVendidas();
            if (unidades > mayor) {
                mayor = unidades;
                masVendida = prenda;
            }
        }
        return masVendida;
    }


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

    public ArrayList<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(ArrayList<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public ArrayList<Prenda> getPrendas() {
        return prendas;
    }

    public void setPrendas(ArrayList<Prenda> prendas) {
        this.prendas = prendas;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(ArrayList<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }
}
