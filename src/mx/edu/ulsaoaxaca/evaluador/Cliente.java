package mx.edu.ulsaoaxaca.evaluador;

import mx.edu.ulsaoaxaca.evaluador.controlador.ClienteControlador;
import mx.edu.ulsaoaxaca.evaluador.vista.VentanaRegistro;

public class Cliente {
	
	public static void main(String ... args) {
		
		VentanaRegistro ventanaRegistro = new VentanaRegistro();
		ClienteControlador clienteControlador = new ClienteControlador(ventanaRegistro);
		clienteControlador.mostrarVentanaRegistro();
		
	}

}
