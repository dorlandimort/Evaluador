package mx.edu.ulsaoaxaca.evaluador.mvc.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ClienteRMI;

public class VentanaEvaluador extends JFrame {
	
	private JTabbedPane panelPestaņas;
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
		// Inicializar el panel de pestaņas
		this.panelPestaņas = new JTabbedPane();
		this.panelPestaņas.addTab("Preguntas", this.panelPreguntas);
		this.panelPestaņas.addTab("Evaluaciķn", this.panelEvaluacion);
		this.panelPestaņas.addTab("Reporte", this.panelReporte);
		
		this.add(panelPestaņas);
	}
	
	public void agregarCliente(ClienteRMI cliente) {
		
	}
	
	// getters y setters

	public JTabbedPane getPanelPestaņas() {
		return panelPestaņas;
	}

	public void setPanelPestaņas(JTabbedPane panelPestaņas) {
		this.panelPestaņas = panelPestaņas;
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
