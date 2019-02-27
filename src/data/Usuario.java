package data;

public abstract class Usuario {
    private String nombre;
    private String documento;
    private String clave;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Usuario(String nombre, String documento, String clave) {
        this.nombre = nombre;
        this.documento = documento;
        this.clave = clave;
    }
    
    
}
