package data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeMap;
import ui.IU;

public class Administrador extends Usuario implements AccionGeneral, Comparator<Cliente>{
    private ArrayList<Cliente> clientes;

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Administrador(String nombre, String documento, String clave) {
        super(nombre, documento, clave);
    }
    
    public int consultarDatosCliente(TreeMap<String, Cliente> clientes, Scanner entrada, boolean mostrarClientes) {
        int eleccion;
        eleccion = IU.mostrarClientes(clientes, entrada, mostrarClientes);
        return eleccion;
    }
    
    public void guardarClientes () throws FileNotFoundException, IOException {
    	ObjectOutputStream salida = new ObjectOutputStream (new FileOutputStream("files/clientes.ser"));
    	salida.writeObject(this.clientes);
    	salida.close();
    }
    
    public void guardarClientes (Cliente cliente) throws FileNotFoundException, IOException {
    	ObjectOutputStream salida = new ObjectOutputStream (new FileOutputStream("files/clientes.ser"));
    	salida.writeObject(cliente);
    	salida.close();
    }
    
    @Override
    public void borrarCliente (TreeMap<String, Cliente> clientes, Scanner entrada) {
    	IU.borrarCliente(clientes, entrada);
    	System.out.println("El cliente ha sido borrado. ");
    }
    
    @Override
    public void aumentarSaldo (TreeMap<String, Cliente> clientes) {
    	for (Cliente cliente : clientes.values()) {
    		int dinero;
    		if (cliente.getDinero() >= 1000000 && cliente.getDinero() < 50000000) {
    			dinero = (int) (cliente.getDinero()*1.03);
    			cliente.setDinero(dinero);
    		}
    	}
    }
    
    public void generarRetencionFuente(TreeMap<String, Cliente> clientes) {
    	for (Cliente cliente : clientes.values()) {
    		int dinero;
    		if (cliente.getDinero() >= 50000000) {
    			dinero = (int) (cliente.getDinero()*0.9725);
    			cliente.setDinero(dinero);
    		}
    	}
    }
    
    @Override
    public int compare(Cliente a, Cliente b) { 
        return a.getDinero() - b.getDinero(); 
    }
}
