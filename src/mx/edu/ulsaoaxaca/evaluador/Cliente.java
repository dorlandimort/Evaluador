package mx.edu.ulsaoaxaca.evaluador;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

import mx.edu.ulsaoaxaca.evaluador.mvc.controlador.ClienteControlador;
import mx.edu.ulsaoaxaca.evaluador.mvc.vista.VentanaRegistro;
import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ServidorRMI;

public class Cliente {
	
	public static void main(String ... args) {
		ClienteControlador clienteControlador = new ClienteControlador();
		clienteControlador.mostrarVentanaRegistro();
	}

}
