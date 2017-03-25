package mx.edu.ulsaoaxaca.evaluador.mvc.vista;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelRespuestasCliente extends JPanel {

	private JLabel lblEvaluador;
	private JLabel lblEscribirRespuesta;
	private JLabel lblPreguntas;
	private JLabel lblRespuestasEnviadas;
	private JButton btnSiguiente;
	private JButton btnEliminar;
	private JButton btnSalir;
	private JTextField txtEscribirRespuesta;
	private JTextField txtRespuestasEnviadas;
	private JTextArea jtxEscribirRespuesta;
	private JTextArea jtxRespuestasEnviadas;


	public PanelRespuestasCliente(){
		
		this.setLayout(null);
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(10, 10, 965, 230);
		TitledBorder title = BorderFactory.createTitledBorder("");
		panel1.setBorder(title);
		
		this.lblEvaluador = new JLabel();
		this.lblEvaluador.setBounds(50, 10, 250, 20);
		
		this.lblEscribirRespuesta = new JLabel("Escribir respuesta");
		this.lblEscribirRespuesta.setBounds(15, 30, 150, 20);
		
		//this.jtxEscribirRespuesta =new JTextArea();
		//this.jtxEscribirRespuesta.setBounds(10,50,1200,150);
		this.lblPreguntas = new JLabel("Pregunta 1");
		this.lblPreguntas.setBounds(15, 60, 150, 20);
		
		this.txtEscribirRespuesta = new JTextField();
		this.txtEscribirRespuesta.setBounds(130, 60, 400, 20);
		
		
		
		this.lblRespuestasEnviadas = new JLabel("Respuestas enviadas");
		this.lblRespuestasEnviadas.setBounds(15, 200, 150, 20);
		
		this.jtxRespuestasEnviadas =new JTextArea();
		this.jtxRespuestasEnviadas.setBounds(10,220,1200,150);
		
		
		this.btnSiguiente = new JButton("Siguiente");
		this.btnSiguiente.setBounds(20, 450, 100, 20);
		
		this.btnEliminar = new JButton("Eliminar");
		this.btnEliminar.setBounds(140, 450, 100, 20);
		
		this.btnSalir = new JButton("Salir");
		this.btnSalir.setBounds(630, 450, 100, 20);
		
		this.add(btnSiguiente);
		this.add(btnEliminar);
		this.add(btnSalir);
		this.add(jtxRespuestasEnviadas);
		this.add(lblPreguntas);
		this.add(txtEscribirRespuesta);
		
		this.add(lblEvaluador);
		this.add(lblRespuestasEnviadas);
		//this.add(jtxEscribirRespuesta);
		this.add(lblEscribirRespuesta);
	}


	public JLabel getLblEscribirRespuesta() {
		return lblEscribirRespuesta;
	}


	public void setLblEscribirRespuesta(JLabel lblEscribirRespuesta) {
		this.lblEscribirRespuesta = lblEscribirRespuesta;
	}


	public JLabel getLblPreguntas() {
		return lblPreguntas;
	}


	public void setLblPreguntas(JLabel lblPreguntas) {
		this.lblPreguntas = lblPreguntas;
	}


	public JLabel getLblRespuestasEnviadas() {
		return lblRespuestasEnviadas;
	}


	public void setLblRespuestasEnviadas(JLabel lblRespuestasEnviadas) {
		this.lblRespuestasEnviadas = lblRespuestasEnviadas;
	}


	public JButton getBtnSiguiente() {
		return btnSiguiente;
	}


	public void setBtnSiguiente(JButton btnSiguiente) {
		this.btnSiguiente = btnSiguiente;
	}


	public JButton getBtnEliminar() {
		return btnEliminar;
	}


	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}


	public JButton getBtnSalir() {
		return btnSalir;
	}


	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}


	public JTextField getTxtEscribirRespuesta() {
		return txtEscribirRespuesta;
	}


	public void setTxtEscribirRespuesta(JTextField txtEscribirRespuesta) {
		this.txtEscribirRespuesta = txtEscribirRespuesta;
	}


	public JTextField getTxtRespuestasEnviadas() {
		return txtRespuestasEnviadas;
	}


	public void setTxtRespuestasEnviadas(JTextField txtRespuestasEnviadas) {
		this.txtRespuestasEnviadas = txtRespuestasEnviadas;
	}


	public JTextArea getJtxEscribirRespuesta() {
		return jtxEscribirRespuesta;
	}


	public void setJtxEscribirRespuesta(JTextArea jtxEscribirRespuesta) {
		this.jtxEscribirRespuesta = jtxEscribirRespuesta;
	}


	public JTextArea getJtxRespuestasEnviadas() {
		return jtxRespuestasEnviadas;
	}


	public void setJtxRespuestasEnviadas(JTextArea jtxRespuestasEnviadas) {
		this.jtxRespuestasEnviadas = jtxRespuestasEnviadas;
	}


	public JLabel getLblEvaluador() {
		return lblEvaluador;
	}


	public void setLblEvaluador(JLabel lblEvaluador) {
		this.lblEvaluador = lblEvaluador;
	}
	
	
}
