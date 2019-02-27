package data;

import java.util.Scanner;
import java.util.TreeMap;

public interface AccionGeneral {
	void borrarCliente(TreeMap<String, Cliente> clientes, Scanner entrada);
	void aumentarSaldo(TreeMap<String, Cliente> clientes);
}


