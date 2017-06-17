package mx.edu.ulsaoaxaca.evaluador.mvc.vista;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTabbedPane;

import mx.edu.ulsaoaxaca.evaluador.misc.ClienteListModel;
import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ClienteRMI;

public class VentanaEvaluador extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane panelPesta�as;
	private PanelPreguntas panelPreguntas;
	private PanelEvaluacion panelEvaluacion;
	
	
	public VentanaEvaluador() {
		this.setTitle("Servidor");
		this.setSize(1000, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void addTabs() {
		System.out.println(this.panelEvaluacion);
		// Inicializar el panel de pesta�as
		this.panelPesta�as = new JTabbedPane();
		this.panelPesta�as.addTab("Preguntas", this.panelPreguntas);
		this.panelPesta�as.addTab("Evaluaci�n", this.panelEvaluacion);
		
		this.add(panelPesta�as);
	}
	
	public void agregarCliente(ClienteRMI cliente) {
		
	}
	
	/**
	 * M�todos proxy
	 * 
	 */
	
	public JButton botonEnviarPregunta() {
		return this.panelPreguntas.getBtnAgregarPregunta();
	}
	
	public JList<ClienteListModel> listaClientes() {
		return this.panelPreguntas.getListaAspirantes();
	}
	
	public String textoPregunta() {
		return this.panelPreguntas.getTxtPregunta().getText();
	}
	
	// getters y setters

	public JTabbedPane getPanelPesta�as() {
		return panelPesta�as;
	}

	public void setPanelPesta�as(JTabbedPane panelPesta�as) {
		this.panelPesta�as = panelPesta�as;
	}

	public PanelPreguntas getPanelPreguntas() {
		return panelPreguntas;
	}

	public void setPanelPreguntas(PanelPreguntas panelPreguntas) {
		this.panelPreguntas = panelPreguntas;
	}

	public PanelEvaluacion getPanelEvaluacion() {
		return panelEvaluacion;
	}

	public void setPanelEvaluacion(PanelEvaluacion panelEvaluacion) {
		this.panelEvaluacion = panelEvaluacion;
	}


	

}
