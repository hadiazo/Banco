package data;

import java.io.Serializable;
import java.util.Scanner;
import java.util.TreeMap;
import ui.IU;

public class Cliente extends Usuario implements AccionGeneral, Serializable{
    private static final long serialVersionUID = 1L;
	private int dinero;

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public Cliente(String nombre, String documento, String clave, int dinero) {
        super(nombre, documento, clave);
        this.dinero = dinero;
    }
    
    @Override
    public void aumentarSaldo (TreeMap<String, Cliente> clientes) {
    	Scanner entrada = new Scanner(System.in);
    	String documento = this.getDocumento();
    	int dinero;
    	dinero = IU.agregarCantidadDinero(entrada);
    	dinero = clientes.get(documento).getDinero() + dinero;
    	clientes.get(documento).setDinero(dinero);
    }
    
    public void retirarSaldo (TreeMap<String, Cliente> clientes) {
    	Scanner entrada = new Scanner(System.in);
    	String documento = this.getDocumento();
    	int dinero;
    	dinero = IU.retirarCantidadDinero(entrada, this.getDinero());
    	dinero = clientes.get(documento).getDinero() - dinero;
    	clientes.get(documento).setDinero(dinero);
    }
    
    @Override
    public void borrarCliente (TreeMap<String, Cliente> clientes, Scanner entrada) {
    	IU.borrarCliente(clientes, entrada);
    	System.out.println("Ha sido borrado con éxito. ");
    }
    
    @Override
    public String toString () {
        String info = "Nombre: "+super.getNombre()+". Documento: "+
        		super.getDocumento()+". Dinero en la cuenta: $"+this.dinero+".";
        return info;
    }
    
}
