package businessLogic;

/*
And on the pedestal these words appear:
"My name is Ozymandias, king of kings:
Look on my works, ye Mighty, and despair!"

Nothing beside remains. Round the decay
Of that colossal wreck, boundless and bare
The lone and level sands stretch far away

Percy Bysshe Shelley, Ozymandias
 */

import data.Administrador;
import java.util.TreeMap;
import data.Cliente;
import ui.IU;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TestBanco {

    public static void main(String[] args) throws Exception {
        TreeMap<String, Cliente> clientes = new TreeMap <>();
        ArrayList <Cliente> listaClientes = new ArrayList<>();
        Administrador admin = new Administrador ("Miguel Salgado", "86239434", "admin");
        Scanner entrada = new Scanner (System.in);
        boolean correrPrograma = true, esCliente, clienteRegistrado, volverMenu, nuevaClave=false;
        String documento, clave, nombre;
        int eleccion, dineroInicial;
        
        Cliente cliente1 = new Cliente ("Pablo HernÃ¡ndez", "582052054", "11111", 200000000);
        Cliente cliente2 = new Cliente ("Jairo Ortega", "10330045", "22222", 800000);
        Cliente cliente3 = new Cliente ("Andrea Uribe", "40280402", "33333", 3400000);
        Cliente cliente4 = new Cliente ("Ana MarÃ­a PÃ©rez", "7938594", "44444", 16700000);
        Cliente cliente5 = new Cliente ("Carlos Bustos", "56080203", "55555", 150000);
        Cliente cliente; //Se pone este cliente genérico como referencia
        
        clientes.put(cliente1.getDocumento(), cliente1);
        clientes.put(cliente2.getDocumento(), cliente2);
        clientes.put(cliente3.getDocumento(), cliente3);
        clientes.put(cliente4.getDocumento(), cliente4);
        clientes.put(cliente5.getDocumento(), cliente5);
        ObjectInputStream ois = null;
        FileOutputStream os = new FileOutputStream("files/tabla.txt"); 
        
        while(correrPrograma) { //Mientras siga corriendo el programa
        	 
        	for(Cliente clienteGenerico : clientes.values()) { //Se refresca la lista de clientes
        		listaClientes.add(clienteGenerico);
        	}
        	admin.setClientes(listaClientes);
        	admin.guardarClientes();
        	
            IU.mostrarBienvenida();
            esCliente = IU.preguntarTipoUsuario(entrada);
            
            if(esCliente) { //Si es cliente
            	volverMenu=false;
                clienteRegistrado = IU.preguntarClienteNuevoORegistrado(entrada);
                documento = IU.preguntarUsuario(entrada);
                if (clienteRegistrado) { //Si está registrado
                	if (!(clientes.containsKey(documento))) { //Si no se encuentra el documento ingresado
                		documento = IU.mostrarDocumentoNoValido(false);
                	}
                	clave = IU.preguntarClave(entrada);
                	if (!(clave.equals(clientes.get(documento).getClave()))) { //Si la clave del usuario no coincide
                		while (!(clave.equals(clientes.get(documento).getClave()))) { //Mientras no sea así
                			nuevaClave = IU.preguntarNuevaClaveOInsertarOtraVez(entrada);
                			clave = IU.preguntarClave(entrada);
            				if (nuevaClave) { //Si se quiere ingresar una nueva clave
                				clientes.get(documento).setClave(clave);
                				cliente = clientes.get(documento);
                				break;
            				}
            				if (clave.equals(clientes.get(documento).getClave())) { //Si la clave ahora sí coincide
            					cliente = clientes.get(documento);
            					break;
            				}
                		}
                	}
                	
                } else { //Si no está registrado
                	if (clientes.containsKey(documento)) {//Si el documento ingresado está en la lista pese a decir que no está registrado
                		documento = IU.mostrarDocumentoNoValido(false);
                	} else { //Si el documento es nuevo
                		//Hacer que el usuario se registre, pidiendole nombre y clave
                		nombre = IU.preguntarNombre(entrada);
                		clave = IU.preguntarClave(entrada);
                		dineroInicial = IU.preguntarDineroInicial(entrada);
                		Cliente clienteNuevo = new Cliente(nombre, documento, clave, dineroInicial);
                		clientes.put(clienteNuevo.getDocumento(), clienteNuevo);
                		cliente = clienteNuevo;
                	}
                }
                
                cliente = clientes.get(documento);
                admin.guardarClientes(cliente);
                try {
                	FileInputStream fis = new FileInputStream("files/clientes.ser");
                    ois = new ObjectInputStream(fis);
                } catch (IOException e) {
                	System.out.println("Error al leer el archivo. ");
                	System.exit(1);
                }
                
                try {
                	while (true) {
                		cliente = (Cliente) ois.readObject();
                		System.out.println(cliente.toString());
                	}
                } catch (EOFException endOfFileException) {
                	System.out.printf("%No hay mas registros%n");
                } catch (ClassNotFoundException classNotFoundException) {
                    System.err.println("Tipo de objeto invalido. Terminando.");
                } catch (IOException ioException) {
                     System.out.print("\n");
                }
               
                
                while (!volverMenu) { //Se repetirá hasta que se quiera volver al menú principal
                	eleccion = IU.mostrarAccionesCliente(entrada, cliente);
                    switch(eleccion) {
                    	case 1: //Mostrar datos personales
                    		IU.mostrarDatosPersonales(clientes, documento);
                    		break;
                    	case 2: //Modificar clave
                    		clave = IU.preguntarClave(entrada);
                    		clientes.get(documento).setClave(clave);
                    		for(int i=0; i<1; i++) {
                            	IU.mostrarConfirmacionAccion(eleccion);
                            }
                    		break;
                    	case 3: //Añadir dinero a su cuenta
                    		clientes.get(documento).aumentarSaldo(clientes);
                    		break;
                    	case 4: //Retirar dinero de su cuenta
                    		clientes.get(documento).retirarSaldo(clientes);
                    		break;
                    	case 5: //Eliminar su usuario
                    		cliente.borrarCliente(clientes, entrada);
                    		for(int i=0; i<1; i++) {
                            	IU.mostrarConfirmacionAccion(eleccion);
                            }
                    		break;
                    	case 6: //Volver al menú principal
                    		listaClientes.clear();
                    		volverMenu = true;
                            break;
                    }
                } 
                
            } else { //Es decir, si es administrador
            	volverMenu = false;
                clave = IU.preguntarClave(entrada);
                if(!clave.equals(admin.getClave())) { //Si la clave no coincide
                    while (!clave.equals(admin.getClave())) { //Mientras no coincida
                        IU.mostrarClaveDesconocidaAdmin();
                        clave = IU.preguntarClave(entrada);
                    }
                }
                eleccion = IU.mostrarAccionesAdmin(entrada); //Se toma una decisión
                
                while(!volverMenu) { //Se repetirá hasta que se quiera volver al menú principal
                    switch(eleccion) {
                        case 1: //Mostrar datos de los clientes
                        	eleccion=admin.consultarDatosCliente(clientes, entrada, true);
                        	break;
                        case 2: //Eliminar cliente
                        	admin.borrarCliente(clientes, entrada);
                        	eleccion=admin.consultarDatosCliente(clientes, entrada, false);
                            break;
                        case 3: //Aumentar 3% del saldo a clientes
                            admin.aumentarSaldo(clientes);
                            for(int i=0; i<1; i++) {
                            	IU.mostrarConfirmacionAccion(eleccion);
                            }
                            eleccion=admin.consultarDatosCliente(clientes, entrada, false);
                            break;
                        case 4: //Descontar el 2.75% del saldo a clientes
                        	admin.generarRetencionFuente(clientes);
                            for(int i=0; i<1; i++) {
                            	IU.mostrarConfirmacionAccion(eleccion);
                            }
                            eleccion=admin.consultarDatosCliente(clientes, entrada, false);
                            break;
                        case 5: //Mostrar los 5 usuarios con mayor saldo
                        	Collections.sort(admin.getClientes(), admin); //Permite ordenar la lista por un atributo en especial
                        	IU.generarTablaReportes(admin.getClientes(), os);
                        	eleccion=admin.consultarDatosCliente(clientes, entrada, false);
                        	break;
                        case 6: //Volver al menú principal
                        	listaClientes.clear();
                        	volverMenu = true;
                            break;
                        case 7: //Detener programa
                        	correrPrograma = false;
                        	System.exit(0);
                        	break;
                    }
                    
                }
                
            }
                
            
        }
        
        
    }

}
