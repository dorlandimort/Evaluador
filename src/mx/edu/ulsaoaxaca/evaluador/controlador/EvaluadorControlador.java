package mx.edu.ulsaoaxaca.evaluador.controlador;

import mx.edu.ulsaoaxaca.evaluador.vista.PanelEvaluacion;
import mx.edu.ulsaoaxaca.evaluador.vista.PanelPreguntas;
import mx.edu.ulsaoaxaca.evaluador.vista.VentanaEvaluador;

public class EvaluadorControlador {
	
	private VentanaEvaluador ventanaEvaluador;
	
	private String[] titulos = {"No.", "Pregunta", "Respuesta", "Aspirante", "Correcto", "Enviar"};
	
	public EvaluadorControlador() {
		this.init();
	}
	
	public void init() {
		
		this.ventanaEvaluador = new VentanaEvaluador();
		
		Object data[][] = {
				{new Integer(1), "¿Qué te gusta hacer?", "Programar", "Alondra Soledad", new Boolean(true), new Boolean(false)},
				{new Integer(2), "¿En qué lenguaje?", "Java", "Alondra Soledad", new Boolean(true), new Boolean(false)}
		};
		
		this.ventanaEvaluador.setPanelEvaluacion(new PanelEvaluacion(data, this.titulos));
		this.ventanaEvaluador.setPanelPreguntas(new PanelPreguntas());
		this.ventanaEvaluador.addTabs();
		this.ventanaEvaluador.setVisible(true);
		this.ventanaEvaluador.getPanelPreguntas().getBtnSalir().addActionListener(e -> {
			this.cerrarVentanaEvaluador();
		});
		this.mostrarVentanaEvaluador();
	}
	
	public void mostrarVentanaEvaluador() {
		this.ventanaEvaluador.setVisible(true);
	}
	
	public void cerrarVentanaEvaluador() {
		this.ventanaEvaluador.dispose();
	}

}
