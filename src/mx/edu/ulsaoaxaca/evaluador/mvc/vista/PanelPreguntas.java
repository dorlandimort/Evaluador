package mx.edu.ulsaoaxaca.evaluador.mvc.vista;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import mx.edu.ulsaoaxaca.evaluador.misc.ClienteListModel;
import mx.edu.ulsaoaxaca.evaluador.misc.ListaClientesCellRenderer;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Pregunta;
import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ClienteRMI;

public class PanelPreguntas extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1001812524611473507L;
	private TitledBorder bordeEscribirPregunta;
	private TitledBorder bordePreguntas;
	private TitledBorder bordeAspirantes;
	
	private JLabel lblEvaluador;
	private JTextArea txtPregunta;
	
	private JList<Pregunta> preguntasEnviadas;
	DefaultListModel<Pregunta> preguntasEnviadasModelo;
	
	private JList<ClienteListModel> listaAspirantes;
	private DefaultListModel<ClienteListModel> listModel;
	
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
		this.bordePreguntas = new TitledBorder("Preguntas enviadas");
		
		this.panelEscribirPregunta = new JPanel();
		this.panelEscribirPregunta.setBounds(50, 50, 500, 200);
		this.panelEscribirPregunta.setBorder(this.bordeEscribirPregunta);
		this.panelEscribirPregunta.setLayout(null);
		
		this.lblEvaluador = new JLabel();
		this.lblEvaluador.setBounds(50, 10, 200, 20);
		
		this.txtPregunta = new JTextArea();
		this.txtPregunta.setBounds(20, 20, 460, 120);
		
		this.btnAgregarPregunta = new JButton("Enviar");
		this.btnAgregarPregunta.setBounds(350, 160, 100, 20);
		
		this.panelEscribirPregunta.add(this.txtPregunta);
		this.panelEscribirPregunta.add(this.btnAgregarPregunta);
		
		
		this.panelPreguntas = new JPanel();
		this.panelPreguntas.setBounds(50, 300, 500, 200);
		this.panelPreguntas.setBorder(this.bordePreguntas);
		
		String[] preguntas = {"Pregunta 1", "Pregunta2", "Pregunta3" };
		
		preguntasEnviadasModelo = new DefaultListModel<>();
		this.preguntasEnviadas = new JList<>(preguntasEnviadasModelo);
		this.preguntasEnviadas.setBounds(20, 20, 470, 100);
		
		JScrollPane scrollPreguntasEnviadas = new JScrollPane(this.preguntasEnviadas);
		scrollPreguntasEnviadas.setPreferredSize(new Dimension(460, 150));
		
		this.panelPreguntas.add(scrollPreguntasEnviadas);
		
		
		
		this.panelAspirantes = new JPanel();
		this.panelAspirantes.setBounds(570, 50, 300, 450);
		this.panelAspirantes.setBorder(this.bordeAspirantes);
		
		// crear la lista de aspirantes
		
		this.listModel = new DefaultListModel<>();
		this.listaAspirantes = new JList<>(this.listModel);
		this.listaAspirantes.setBounds(10, 20, 250, 400);
		JScrollPane scroll = new JScrollPane(this.listaAspirantes);
		scroll.setPreferredSize(new Dimension(250, 400));
		this.panelAspirantes.add(scroll);
		
		this.btnSalir = new JButton("Salir");
		this.btnSalir.setBounds(850, 10, 100, 20);
		
		this.add(this.lblEvaluador);
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

	public JLabel getLblEvaluador() {
		return lblEvaluador;
	}

	public void setLblEvaluador(JLabel lblEvaluador) {
		this.lblEvaluador = lblEvaluador;
	}

	public DefaultListModel<ClienteListModel> getListModel() {
		return listModel;
	}

	public void setListModel(DefaultListModel<ClienteListModel> listModel) {
		this.listModel = listModel;
	}

	public void setListaAspirantes(JList<ClienteListModel> listaAspirantes) {
		this.listaAspirantes = listaAspirantes;
	}

	public JList<ClienteListModel> getListaAspirantes() {
		return listaAspirantes;
	}

	public JList<Pregunta> getPreguntasEnviadas() {
		return preguntasEnviadas;
	}

	public void setPreguntasEnviadas(JList<Pregunta> preguntasEnviadas) {
		this.preguntasEnviadas = preguntasEnviadas;
	}

	public DefaultListModel<Pregunta> getPreguntasEnviadasModelo() {
		return preguntasEnviadasModelo;
	}

	public void setPreguntasEnviadasModelo(DefaultListModel<Pregunta> preguntasEnviadasModelo) {
		this.preguntasEnviadasModelo = preguntasEnviadasModelo;
	}
	
	

}
