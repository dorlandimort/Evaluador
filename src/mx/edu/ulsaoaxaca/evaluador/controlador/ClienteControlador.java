package mx.edu.ulsaoaxaca.evaluador.controlador;

import mx.edu.ulsaoaxaca.evaluador.vista.VentanaAspirante;
import mx.edu.ulsaoaxaca.evaluador.vista.VentanaRegistro;

public class ClienteControlador {
	
	VentanaRegistro ventanaRegistro;
	VentanaAspirante ventanaAspirante;
	
	public ClienteControlador() {
		this.ventanaRegistro = new VentanaRegistro();
		this.mostrarVentanaRegistro();
		this.init();
	}
	
	public void init() {
		this.ventanaRegistro.getBtnIngresar().addActionListener(e -> {
			this.mostrarVentanaClientePrincipal();
		});
		this.ventanaRegistro.getBtnSalir().addActionListener(e -> {
			this.cerrarVentanaRegistro();
		});
	}
	
	public void mostrarVentanaRegistro() {
		this.ventanaRegistro.setVisible(true);
	}
	
	public void cerrarVentanaRegistro()  {
		this.ventanaRegistro.dispose();
	}
	
	public void mostrarVentanaClientePrincipal() {
		if (this.ventanaAspirante == null) {
			this.ventanaAspirante = new VentanaAspirante();
		}
		this.ventanaRegistro.setVisible(false);
		this.ventanaAspirante.setVisible(true);
		
		
	}
	
	

}
