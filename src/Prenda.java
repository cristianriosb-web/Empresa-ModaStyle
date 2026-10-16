import java.util.ArrayList;

public class Prenda {
    private String codigo;
    private String nombre;
    private String marca;
    private double precio;
    private CategoriaPrenda categoria;
    private ArrayList<Inventario> listaInventarios;

    public Prenda(String codigo, String nombre, String marca, double precio, CategoriaPrenda categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.categoria = categoria;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public CategoriaPrenda getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaPrenda categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Inventario> getListaInventarios() {
        return listaInventarios;
    }

    public void setListaInventarios(ArrayList<Inventario> listaInventarios) {
        this.listaInventarios = listaInventarios;
    }
    public double calcularPrecioConRecargo(){
        return precio+precio*categoria.getRecargo();
    }
    public boolean esTallaValida(String talla){
        boolean esValida=false;
        if(talla.equalsIgnoreCase("S") || talla.equals("M") || talla.equals("L") || talla.equals("XL")){
            esValida=true;
        }
        return esValida;
    }
    public void agregarInventario(Inventario inventario){
        if(listaInventarios==null){
            listaInventarios=new ArrayList<>();
            listaInventarios.add(inventario);
        }
        else{
            listaInventarios.add(inventario);
        }
    }
    public int calcularUnidadesVendidas(){
        int totalUnidadesVendidas=0;
        for(Inventario inventario : listaInventarios){
            totalUnidadesVendidas=totalUnidadesVendidas+inventario.calcularUnidadesVendidas();
        }
        return totalUnidadesVendidas;
    }
}