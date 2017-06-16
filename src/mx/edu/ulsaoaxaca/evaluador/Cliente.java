package mx.edu.ulsaoaxaca.evaluador;

import mx.edu.ulsaoaxaca.evaluador.mvc.controlador.ClienteControlador;

public class Cliente {
	
	public static void main(String ... args) {
		ClienteControlador clienteControlador = new ClienteControlador();
		clienteControlador.mostrarVentanaRegistro();
	}

}
