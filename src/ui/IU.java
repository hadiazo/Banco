package ui;

import data.Cliente;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class IU {
    public static void mostrarBienvenida() {
        System.out.println("Bienvenido al Banco JavaLimos");
        System.out.println("         _._._                       _._._\r\n" + 
        		"        _|   |_                     _|   |_\r\n" + 
        		"        | ... |_._._._._._._._._._._| ... |\r\n" + 
        		"        | ||| |  o   JAVALIMOS   o  | ||| |\r\n" + 
        		"        | \"\"\" |  \"\"\"    \"\"\"    \"\"\"  | \"\"\" |\r\n" + 
        		"   ())  |[-|-]| [-|-]  [-|-]  [-|-] |[-|-]|  ())\r\n" + 
        		"  (())) |     |---------------------|     | (()))\r\n" + 
        		" (())())| \"\"\" |  \"\"\"    \"\"\"    \"\"\"  | \"\"\" |(())())\r\n" + 
        		" (()))()|[-|-]|  :::   .-\"-.   :::  |[-|-]|(()))()\r\n" + 
        		" ()))(()|     | |~|~|  |_|_|  |~|~| |     |()))(()\r\n" + 
        		"    ||  |_____|_|_|_|__|_|_|__|_|_|_|_____|  ||\r\n" + 
        		" ~ ~^^ @@@@@@@@@@@@@@/=======\\@@@@@@@@@@@@@@ ^^~ ~\r\n" + 
        		"      ^~^~                                ~^~^\n");
    }
    
    public static boolean ingresarCondicionNumero(String entradaIngresada, int numero) {
        boolean condicion;
        String num = Integer.toString(numero);
        condicion = (entradaIngresada.startsWith(num) && entradaIngresada.endsWith(num) && (entradaIngresada.length()==1));
        return condicion;
    }
    
    public static int convertirAEntero (String entradaIngresada) {
    	int numero;
        try {
        	numero = Integer.parseInt(entradaIngresada);
		} catch (NumberFormatException e) {
			numero = -1;
		}
        return numero;
    }
    
    public static String verificarCondicionNumero(String entradaIngresada, Scanner entrada, int limite) {
    	int numero;
    	numero = IU.convertirAEntero(entradaIngresada);
        if(numero<1||numero>limite) {
            while (numero<1||numero>limite) {
                System.out.print("Entrada no vÃ¡lida. Intente de nuevo: ");
                entradaIngresada = entrada.nextLine();
                numero = IU.convertirAEntero(entradaIngresada);
            }
        }
        return entradaIngresada;
    }
    
    public static int verificarUnicoValorVerdadero(boolean[] arreglo) {
    	int i=0; 
    	while (i<arreglo.length) {
            if (arreglo[i]==true) {break;}
            i++;
        }
    	return i;	
    }
    
    public static boolean preguntarTipoUsuario(Scanner entrada) {
        String entradaIngresada;
        boolean condicion, condicion1, condicion2, condicion3, esCliente=false;
        System.out.println("1. Administrador");
        System.out.println("2. Cliente");
        System.out.print("Â¿QuÃ© tipo de usuario es usted?: ");
        entradaIngresada = entrada.nextLine();
        condicion = "cliente".equals(entradaIngresada) || "Cliente".equals(entradaIngresada);
        condicion1 = "administrador".equals(entradaIngresada) || "Administrador".equals(entradaIngresada);
        condicion2 = IU.ingresarCondicionNumero(entradaIngresada, 1);
        condicion3 = IU.ingresarCondicionNumero(entradaIngresada, 2);
        
        if (!(condicion || condicion1 || condicion2 || condicion3)) {
            while (!(condicion || condicion1 || condicion2 || condicion3)) {
                System.out.print("Entrada no vÃ¡lida. Intente de nuevo: ");
                entradaIngresada = entrada.nextLine();
                condicion = "cliente".equals(entradaIngresada) || "Cliente".equals(entradaIngresada);
                condicion1 = "administrador".equals(entradaIngresada) || "Administrador".equals(entradaIngresada);
                condicion2 = IU.ingresarCondicionNumero(entradaIngresada, 1);
                condicion3 = IU.ingresarCondicionNumero(entradaIngresada, 2);
            }
        }
        if (condicion || condicion3) {
            esCliente = true;
        } System.out.println("");
        return esCliente;
    }
    
    public static String mostrarDocumentoNoValido (boolean documentoRegistrado) {
    	Scanner entrada = new Scanner (System.in);
    	String documento = "";
    	if (documentoRegistrado) {
    		while (documentoRegistrado) {
    			System.out.println("Ese documento ya está registrado. Intente con otro documento. ");
    			documento = IU.preguntarUsuario(entrada);
    		}
    	} else {
    		while (!documentoRegistrado) {
    			System.out.println("Ese documento no está registrado. Intente con otro documento. ");
    			documento = IU.preguntarUsuario(entrada);
    		}
    	}
    	return documento;
    }
    
    public static boolean preguntarNuevaClaveOInsertarOtraVez (Scanner entrada) {
    	int eleccion;
    	String entradaIngresada;
    	boolean[] condiciones = new boolean[2];
    	boolean nuevaClave = false;
    	System.out.println("Clave incorrecta. ¿Desea ingresar una clave otra vez (1) o cambiar su clave (2)?");
    	System.out.print("Ingrese sólo el número con la opción correspondiente: ");
    	entradaIngresada = entrada.nextLine();
        entradaIngresada = IU.verificarCondicionNumero(entradaIngresada, entrada, condiciones.length);
        for(int i=0; i<condiciones.length; i++) {
            condiciones[i] = IU.ingresarCondicionNumero(entradaIngresada, (i+1));
        }
        eleccion = IU.verificarUnicoValorVerdadero(condiciones);
        if (eleccion == 1) {
        	nuevaClave = true;
        }
        return nuevaClave;
    }
    
    public static boolean preguntarClienteNuevoORegistrado (Scanner entrada) {
        String entradaIngresada;
        int eleccion;
        boolean[] condiciones = new boolean[2];
        boolean clienteRegistrado = false;
        System.out.println("Â¿Desea ingresar sus datos (1) o registrarse como nuevo usuario (2)?");
        System.out.print("Ingrese sÃ³lo el nÃºmero con la opciÃ³n correspondiente: ");
        entradaIngresada = entrada.nextLine();
        entradaIngresada = IU.verificarCondicionNumero(entradaIngresada, entrada, condiciones.length);
        for(int i=0; i<condiciones.length; i++) {
            condiciones[i] = IU.ingresarCondicionNumero(entradaIngresada, (i+1));
        }
        eleccion = IU.verificarUnicoValorVerdadero(condiciones);
        if (eleccion == 0) {
            clienteRegistrado = true;
        }
        return clienteRegistrado;
    }
    
    public static String preguntarUsuario (Scanner entrada) {
        String documento;
        System.out.print("Usuario (nÃºmero de documento): ");
        documento = entrada.nextLine();
        return documento;
    }
    
    public static String preguntarClave (Scanner entrada) {
        String clave;
        System.out.print("ContraseÃ±a: ");
        clave = entrada.nextLine();
        return clave;
    }
    
    public static String preguntarNombre (Scanner entrada) {
        String nombre;
        System.out.print("¿Cuál es su nombre?: ");
        nombre = entrada.nextLine();
        return nombre;
    }
    
    public static int preguntarDineroInicial (Scanner entrada) {
    	String entradaIngresada;
    	int dineroInicial;
    	System.out.print("\n¿Cuánto dinero va a guardar dentro de su cuenta inicial?"
    			+ "\nNo puede ser menor a $50000 y sólo ingrese el número sin puntos ni comas.\n$");
    	entradaIngresada = entrada.nextLine();
		dineroInicial = IU.convertirAEntero(entradaIngresada);
		if (dineroInicial < 50000) {
    		while (dineroInicial < 50000) {
    			System.out.print("Cantidad insuficiente. Intente de nuevo");
    			dineroInicial = entrada.nextInt();
    		}
    	}
    	return dineroInicial;
    }
    
    public static int agregarCantidadDinero (Scanner entrada) {
    	String entradaIngresada;
    	int dinero;
    	System.out.print("\n¿Cuanto dinero quiere agregar? \nCantidad mínima: $10000. $");
		entradaIngresada = entrada.nextLine();
		dinero = IU.convertirAEntero(entradaIngresada);
		if (dinero<=10000) {
			while (dinero<=10000) {
				System.out.print("Cantidad insuficiente. Intente de nuevo");
				entradaIngresada = entrada.nextLine();
	    		dinero = IU.convertirAEntero(entradaIngresada);
			}
		}
    	return dinero;
    }
    
    public static int retirarCantidadDinero (Scanner entrada, int limite) {
    	String entradaIngresada;
    	int dinero;
    	limite -= 50000;
    	System.out.print("\n¿Cuanto dinero quiere retirar? \nCantidad máxima: $"+limite+". $");
		entradaIngresada = entrada.nextLine();
		dinero = IU.convertirAEntero(entradaIngresada);
		if (dinero>limite) {
			while (dinero>limite) {
				System.out.print("Cantidad insuficiente. Intente de nuevo");
				entradaIngresada = entrada.nextLine();
	    		dinero = IU.convertirAEntero(entradaIngresada);
			}
		}
		return dinero;
    }
    
    public static void mostrarClaveDesconocidaAdmin () {
        System.out.println("\nLa contraseÃ±a no es correcta. Intente de nuevo. ");
    }
    
    public static void mostrarListaClientes (TreeMap<String, Cliente> clientes) {
        int pos=1;
        System.out.println("\nLos clientes registrados en la base de datos son: ");
        for(Cliente cliente : clientes.values()) {
            System.out.println(pos+". "+cliente.toString());
            pos++;
        }
    }
    
    public static void mostrarListaClientes (ArrayList <Cliente> clientes) {
    	int pos=1;
    	System.out.println("\nLos clientes registrados en la base de datos son: ");
    	for (Cliente cliente : clientes) {
    		System.out.println(pos+". "+cliente.toString());
            pos++;
    	}
    }
    
    public static int mostrarAccionesAdmin (Scanner entrada) {
        String entradaIngresada;
        int eleccion;
        boolean[] condiciones = new boolean[7];
        System.out.println("\nBienvenido Administrador");
        System.out.println("1. Consultar datos de cliente."); //READ
        System.out.println("2. Eliminar cliente.");
        System.out.println("3. Aumentar 3% del saldo a clientes.");
        System.out.println("4. Descontar el 2.75% del saldo a clientes.");
        System.out.println("5. Mostrar los 5 usuarios con mayor saldo.");
        System.out.println("6. Volver al menú principal.");
        System.out.println("7. Detener programa.");
        System.out.print("Ingrese el nÃºmero (1-"+condiciones.length+") con la opciÃ³n correspondiente a la acciÃ³n que desea realizar: ");
        entradaIngresada = entrada.nextLine();
        entradaIngresada = IU.verificarCondicionNumero(entradaIngresada, entrada, condiciones.length);
        for(int i=0; i<condiciones.length; i++) {
            condiciones[i] = IU.ingresarCondicionNumero(entradaIngresada, (i+1));
        }
        eleccion = IU.verificarUnicoValorVerdadero(condiciones);
        return (eleccion+1);
    }
    
    public static int mostrarClientes (TreeMap<String, Cliente> clientes, Scanner entrada, boolean mostrarClientes) {
        String entradaIngresada;
        int eleccion;
        boolean[] condiciones = new boolean[6];
        if(mostrarClientes) {
        	IU.mostrarListaClientes(clientes);
        }
        System.out.println("\nÂ¿QuÃ© desea hacer con los clientes?: ");
        System.out.println("1. Eliminar cliente.");
        System.out.println("2. Aumentar 3% del saldo a clientes.");
        System.out.println("3. Descontar el 2.75% del saldo a clientes.");
        System.out.println("4. Mostrar los 5 usuarios con mayor saldo.");
        System.out.println("5. Volver al menú principal.");
        System.out.println("6. Detener programa.");
        System.out.print("Ingrese el nÃºmero (1-"+condiciones.length+") con la opciÃ³n correspondiente a la acciÃ³n que desea realizar: ");
        entradaIngresada = entrada.nextLine();
        entradaIngresada = IU.verificarCondicionNumero(entradaIngresada, entrada, condiciones.length);
        for(int i=0; i<condiciones.length; i++) {
            condiciones[i] = IU.ingresarCondicionNumero(entradaIngresada, (i+1));
        }
        eleccion = IU.verificarUnicoValorVerdadero(condiciones);
        return (eleccion+2);
    }
    
    public static void mostrarDatosPersonales (TreeMap<String, Cliente> clientes, String documento) {
    	Cliente cliente = clientes.get(documento);
    	System.out.println(cliente.toString());
    }
    
    public static int mostrarAccionesCliente (Scanner entrada, Cliente cliente) {
    	String entradaIngresada;
        int eleccion;
        boolean[] condiciones = new boolean[6];
        System.out.println("\nBienvenido, "+cliente.getNombre()+". ¿Qué quiere hacer?");
        System.out.println("1. Mostrar sus datos personales.");
        System.out.println("2. Modificar clave."); //UPDATE
        System.out.println("3. AÃ±adir dinero a su cuenta."); //UPDATE
        System.out.println("4. Retirar dinero."); //UPDATE
        System.out.println("5. Eliminar su usuario."); //DELETE
        System.out.println("6. Volver al menú principal. ");
        System.out.print("Ingrese sólo el número (1-"+condiciones.length+") con la opción correspondiente a la acción que desea realizar: ");
        entradaIngresada = entrada.nextLine();
        entradaIngresada = IU.verificarCondicionNumero(entradaIngresada, entrada, condiciones.length);
        for(int i=0; i<condiciones.length; i++) {
            condiciones[i] = IU.ingresarCondicionNumero(entradaIngresada, (i+1));
        }
        eleccion = IU.verificarUnicoValorVerdadero(condiciones);
        return (eleccion+1);
    }
    
    public static void borrarCliente(TreeMap<String, Cliente> clientes, Scanner entrada) {
    	String documento;
    	System.out.print("Introduzca el documento del cliente que quiere borrar: ");
    	documento = entrada.nextLine();
    	if(!(clientes.containsKey(documento))) {
    		while(!(clientes.containsKey(documento))) {
    			System.out.print("Entrada no vÃ¡lida. Intente de nuevo: ");
        		documento = entrada.nextLine();
    		}
    	}
    	clientes.remove(documento);
    }
    
    public static void generarTablaReportes(ArrayList <Cliente> listaClientes, FileOutputStream os) {
    	PrintStream ps = new PrintStream(os); 
    	int tamanioTabla = 5;
    	String nombre = "";
    	if (listaClientes.size() < tamanioTabla) {
    		tamanioTabla = listaClientes.size();
    	}
    	ps.println("|NOMBRE\t\t\t|DOCUMENTO\t|DINERO EN LA CUENTA");
    	ps.println("|-----------------------|---------------|-------------------");
    	for(int i=(listaClientes.size()-1); i>=(listaClientes.size()-tamanioTabla); i--) {
    		if(listaClientes.get(i).getNombre().length() > 7 && listaClientes.get(i).getNombre().length() <= 15) {
    			nombre = listaClientes.get(i).getNombre()+"\t\t";
    		} else if(listaClientes.get(i).getNombre().length() <= 7) {
    			nombre = listaClientes.get(i).getNombre()+"\t\t\t";
    		} else if(listaClientes.get(i).getNombre().length() > 15 && listaClientes.get(i).getNombre().length() <= 23) {
    			nombre = listaClientes.get(i).getNombre()+"\t";
    		} else {
    			nombre = listaClientes.get(i).getNombre();
    		}
    		ps.println("|"+nombre+"|"+listaClientes.get(i).getDocumento()+"\t|$"+listaClientes.get(i).getDinero());
    		//ps.println(listaClientes.get(i).toString());
    	}
    	System.out.println("Tabla generada exitosamente. Revise el archivo \"tabla.txt\" generado en la carpeta files.");
    }
    
    public static void mostrarConfirmacionAccion(int eleccion) {
    	switch(eleccion) {
    		case 2: //Para el cliente
    			System.out.println("Se ha modificado la clave. ");
    			break;
    		case 3: //Para el administrador
    			System.out.println("Se ha aumentado el saldo de los clientes con éxito. ");
    			break;
    		case 4: //Para el administrador
    			System.out.println("Se ha generado retención de fuente con éxito. ");
    			break;
    		case 5: //Para el cliente
    			System.out.println("Se ha eliminado su usuario. ");
    			break;
    	}
    }
    
}
