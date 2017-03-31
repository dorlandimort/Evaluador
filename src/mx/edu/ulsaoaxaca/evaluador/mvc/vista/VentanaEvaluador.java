package mx.edu.ulsaoaxaca.evaluador.mvc.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ClienteRMI;

public class VentanaEvaluador extends JFrame {
	
	private JTabbedPane panelPestañas;
	private PanelPreguntas panelPreguntas;
	private PanelBitacora panelBitacora;
	private PanelEvaluacion panelEvaluacion;
	private PanelReporte panelReporte;
	
	
	public VentanaEvaluador() {
		this.setTitle("Servidor");
		this.setSize(1000, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.panelBitacora = new PanelBitacora();
		this.panelReporte = new PanelReporte();
	}
	
	public void addTabs() {
		System.out.println(this.panelEvaluacion);
		// Inicializar el panel de pestañas
		this.panelPestañas = new JTabbedPane();
		this.panelPestañas.addTab("Preguntas", this.panelPreguntas);
		this.panelPestañas.addTab("Evaluación", this.panelEvaluacion);
		this.panelPestañas.addTab("Reporte", this.panelReporte);
		
		this.add(panelPestañas);
	}
	
	public void agregarCliente(ClienteRMI cliente) {
		
	}
	
	// getters y setters

	public JTabbedPane getPanelPestañas() {
		return panelPestañas;
	}

	public void setPanelPestañas(JTabbedPane panelPestañas) {
		this.panelPestañas = panelPestañas;
	}

	public PanelPreguntas getPanelPreguntas() {
		return panelPreguntas;
	}

	public void setPanelPreguntas(PanelPreguntas panelPreguntas) {
		this.panelPreguntas = panelPreguntas;
	}

	public PanelBitacora getPanelBitacora() {
		return panelBitacora;
	}

	public void setPanelBitacora(PanelBitacora panelBitacora) {
		this.panelBitacora = panelBitacora;
	}

	public PanelEvaluacion getPanelEvaluacion() {
		return panelEvaluacion;
	}

	public void setPanelEvaluacion(PanelEvaluacion panelEvaluacion) {
		this.panelEvaluacion = panelEvaluacion;
	}

	public PanelReporte getPanelReporte() {
		return panelReporte;
	}

	public void setPanelReporte(PanelReporte panelReporte) {
		this.panelReporte = panelReporte;
	}
	
	
	

}
