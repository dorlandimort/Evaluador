package mx.edu.ulsaoaxaca.evaluador;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import mx.edu.ulsaoaxaca.evaluador.controlador.EvaluadorControlador;
import mx.edu.ulsaoaxaca.evaluador.vista.VentanaAspirante;
import mx.edu.ulsaoaxaca.evaluador.vista.VentanaEvaluador;
import mx.edu.ulsaoaxaca.evaluador.vista.VentanaRegistro;

public class Servidor {
	
	
	public static void main(String ... args) {
		VentanaEvaluador ventanaEvaluador = new VentanaEvaluador();
		EvaluadorControlador controlador = new EvaluadorControlador(ventanaEvaluador);
		
	}
	
	
	
}
