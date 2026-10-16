import java.util.ArrayList;

public abstract class Persona {
public String documento;
public String nombreCompleto;
public String correo;
public String telefono;

    public Persona( String documento, String nombreCompleto, String correo, String telefono) {
       this.documento =documento;
       this.nombreCompleto= nombreCompleto;
       this.correo = correo;
       this.telefono = telefono;
    }

    public Persona() {
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Persona " +
                "documento='" + documento + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'';
    }

    public ArrayList<Venta> getVentas() {
        return null;
    }
}
