package mx.edu.ulsaoaxaca.evaluador.controlador;

import mx.edu.ulsaoaxaca.evaluador.vista.VentanaEvaluador;

public class EvaluadorControlador {
	
	private VentanaEvaluador ventanaEvaluador;
	
	public EvaluadorControlador(VentanaEvaluador ventana) {
		this.ventanaEvaluador = ventana;
		this.init();
	}
	
	public void init() {
		this.mostrarVentanaEvaluador();
	}
	
	public void mostrarVentanaEvaluador() {
		this.ventanaEvaluador.setVisible(true);
	}
	
	public void cerrarVentanaEvaluador() {
		this.ventanaEvaluador.dispose();
	}

}
