package mx.edu.ulsaoaxaca.evaluador.vista;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelPreguntas extends JPanel {
	
	private TitledBorder bordeEscribirPregunta;
	private TitledBorder bordePreguntas;
	private TitledBorder bordeAspirantes;
	
	private JTextArea txtPregunta;
	
	private JList listaPreguntas;
	private JList listaAspirantes;
	
	private JButton btnAgregarPregunta;
	private JButton btnEnviarPregunta;
	private JButton btnEliminarPregunta;
	private JButton btnSalir;
	
	private JPanel panelEscribirPregunta;
	private JPanel panelPreguntas;
	private JPanel panelAspirantes;
	
	public PanelPreguntas() {
		this.setLayout(null);
		
		this.bordeAspirantes = new TitledBorder("Aspirantes conectados");
		this.bordeEscribirPregunta = new TitledBorder("Escribir Pregunta");
		this.bordePreguntas = new TitledBorder("Preguntas");
		
		this.panelEscribirPregunta = new JPanel();
		this.panelEscribirPregunta.setBounds(50, 50, 500, 200);
		this.panelEscribirPregunta.setBorder(this.bordeEscribirPregunta);
		this.panelEscribirPregunta.setLayout(null);
		
		this.txtPregunta = new JTextArea();
		this.txtPregunta.setBounds(20, 20, 460, 120);
		
		this.btnAgregarPregunta = new JButton("Agregar");
		this.btnAgregarPregunta.setBounds(350, 160, 100, 20);
		
		this.panelEscribirPregunta.add(this.txtPregunta);
		this.panelEscribirPregunta.add(this.btnAgregarPregunta);
		
		
		this.panelPreguntas = new JPanel();
		this.panelPreguntas.setBounds(50, 300, 500, 200);
		this.panelPreguntas.setBorder(this.bordePreguntas);
		this.panelPreguntas.setLayout(null);
		
		String[] preguntas = {"Pregunta 1", "Pregunta2", "Pregunta3" };
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>(preguntas);
		this.listaPreguntas = new JList(modelo);
		this.listaPreguntas.setBounds(20, 20, 470, 100);
		
		this.btnEnviarPregunta = new JButton("Enviar");
		this.btnEnviarPregunta.setBounds(200, 140, 100, 20);
		
		this.btnEliminarPregunta = new JButton("Eliminar");
		this.btnEliminarPregunta.setBounds(310, 140, 100, 20);
		
		this.panelPreguntas.add(this.listaPreguntas);
		this.panelPreguntas.add(this.btnEnviarPregunta);
		this.panelPreguntas.add(this.btnEliminarPregunta);
		
		this.panelAspirantes = new JPanel();
		this.panelAspirantes.setBounds(570, 50, 300, 450);
		this.panelAspirantes.setLayout(null);
		this.panelAspirantes.setBorder(this.bordeAspirantes);
		
		this.btnSalir = new JButton("Salir");
		this.btnSalir.setBounds(850, 10, 100, 20);
		
		this.add(this.panelEscribirPregunta);
		this.add(this.panelPreguntas);
		this.add(this.panelAspirantes);
		this.add(this.btnSalir);
		
	}

	public TitledBorder getBordeEscribirPregunta() {
		return bordeEscribirPregunta;
	}

	public void setBordeEscribirPregunta(TitledBorder bordeEscribirPregunta) {
		this.bordeEscribirPregunta = bordeEscribirPregunta;
	}

	public TitledBorder getBordePreguntas() {
		return bordePreguntas;
	}

	public void setBordePreguntas(TitledBorder bordePreguntas) {
		this.bordePreguntas = bordePreguntas;
	}

	public TitledBorder getBordeAspirantes() {
		return bordeAspirantes;
	}

	public void setBordeAspirantes(TitledBorder bordeAspirantes) {
		this.bordeAspirantes = bordeAspirantes;
	}

	public JTextArea getTxtPregunta() {
		return txtPregunta;
	}

	public void setTxtPregunta(JTextArea txtPregunta) {
		this.txtPregunta = txtPregunta;
	}

	public JList getListaPreguntas() {
		return listaPreguntas;
	}

	public void setListaPreguntas(JList listaPreguntas) {
		this.listaPreguntas = listaPreguntas;
	}

	public JList getListaAspirantes() {
		return listaAspirantes;
	}

	public void setListaAspirantes(JList listaAspirantes) {
		this.listaAspirantes = listaAspirantes;
	}

	public JButton getBtnAgregarPregunta() {
		return btnAgregarPregunta;
	}

	public void setBtnAgregarPregunta(JButton btnAgregarPregunta) {
		this.btnAgregarPregunta = btnAgregarPregunta;
	}

	public JButton getBtnEnviarPregunta() {
		return btnEnviarPregunta;
	}

	public void setBtnEnviarPregunta(JButton btnEnviarPregunta) {
		this.btnEnviarPregunta = btnEnviarPregunta;
	}

	public JButton getBtnEliminarPregunta() {
		return btnEliminarPregunta;
	}

	public void setBtnEliminarPregunta(JButton btnEliminarPregunta) {
		this.btnEliminarPregunta = btnEliminarPregunta;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JPanel getPanelEscribirPregunta() {
		return panelEscribirPregunta;
	}

	public void setPanelEscribirPregunta(JPanel panelEscribirPregunta) {
		this.panelEscribirPregunta = panelEscribirPregunta;
	}

	public JPanel getPanelPreguntas() {
		return panelPreguntas;
	}

	public void setPanelPreguntas(JPanel panelPreguntas) {
		this.panelPreguntas = panelPreguntas;
	}

	public JPanel getPanelAspirantes() {
		return panelAspirantes;
	}

	public void setPanelAspirantes(JPanel panelAspirantes) {
		this.panelAspirantes = panelAspirantes;
	}
	
	

}
