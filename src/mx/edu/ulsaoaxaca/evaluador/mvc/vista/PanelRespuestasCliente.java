package mx.edu.ulsaoaxaca.evaluador.mvc.vista;

import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class PanelRespuestasCliente extends JPanel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7090975003745217598L;
	private JLabel lblEvaluador;
	private JButton btnSalir;
	private JButton btnEnviarRespuesta;
	private JTextArea txtRespuesta;

	
	private PanelPreguntasCliente panelPreguntasCliente;

	public PanelRespuestasCliente(){
		
		this.setLayout(null);
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(10, 10, 965, 230);
		TitledBorder title = BorderFactory.createTitledBorder("");
		panel1.setBorder(title);
		
		this.lblEvaluador = new JLabel();
		this.lblEvaluador.setBounds(50, 10, 250, 20);
		this.btnSalir = new JButton("Salir");
		this.btnSalir.setBounds(800, 10, 100, 20);
		
		this.panelPreguntasCliente = new PanelPreguntasCliente();
		this.panelPreguntasCliente.setBounds(15, 30, 920, 230);
		
		JPanel panelRespuesta = new JPanel();
		panelRespuesta.setBounds(15, 270, 920, 230);
		TitledBorder title1 = BorderFactory.createTitledBorder("Escribir respuesta");
		panelRespuesta.setBorder(title1);
		panelRespuesta.setLayout(null);
		
		this.txtRespuesta = new JTextArea();
		this.txtRespuesta.setBounds(10, 20, 900, 160);
		panelRespuesta.add(this.txtRespuesta);
		
		this.btnEnviarRespuesta = new JButton("Enviar");
		this.btnEnviarRespuesta.setBounds(780, 200, 100, 20);
		panelRespuesta.add(this.btnEnviarRespuesta);
		
		
		this.add(btnSalir);
		this.add(lblEvaluador);
		this.add(panelPreguntasCliente);
		this.add(panelRespuesta);
	}

	public JLabel getLblEvaluador() {
		return lblEvaluador;
	}

	public void setLblEvaluador(JLabel lblEvaluador) {
		this.lblEvaluador = lblEvaluador;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JButton getBtnEnviarRespuesta() {
		return btnEnviarRespuesta;
	}

	public void setBtnEnviarRespuesta(JButton btnEnviarRespuesta) {
		this.btnEnviarRespuesta = btnEnviarRespuesta;
	}

	public JTextArea getTxtRespuesta() {
		return txtRespuesta;
	}

	public void setTxtRespuesta(JTextArea txtRespuesta) {
		this.txtRespuesta = txtRespuesta;
	}

	public PanelPreguntasCliente getPanelPreguntasCliente() {
		return panelPreguntasCliente;
	}

	public void setPanelPreguntasCliente(PanelPreguntasCliente panelPreguntasCliente) {
		this.panelPreguntasCliente = panelPreguntasCliente;
	}


	
	
}
