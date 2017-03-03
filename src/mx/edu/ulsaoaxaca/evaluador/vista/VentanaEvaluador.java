package mx.edu.ulsaoaxaca.evaluador.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class VentanaEvaluador extends JFrame {
	
	private JTabbedPane panelPesta�as;
	private PanelPreguntas panelPreguntas;
	private PanelBitacora panelBitacora;
	private PanelEvaluacion panelEvaluacion;
	private PanelReporte panelReporte;
	
	public VentanaEvaluador() {
		this.setTitle("Servidor");
		this.setSize(1000, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		// Inicializar el panel de pesta�as
		this.panelPesta�as = new JTabbedPane();
		
		
		//Inicializar los paneles
		this.panelPreguntas = new PanelPreguntas();
		this.panelBitacora = new PanelBitacora();
		this.panelEvaluacion = new PanelEvaluacion();
		//this.panelReporte = new PanelReporte();
		
		//agregar los paneles al panel de pesta�as
		
		this.panelPesta�as.addTab("Preguntas", this.panelPreguntas);
		this.panelPesta�as.addTab("Bitacora", this.panelBitacora);
		this.panelPesta�as.addTab("Evaluaci�n", this.panelEvaluacion);
		//this.panelPesta�as.addTab("Reporte", this.panelReporte);
		
		this.add(panelPesta�as);
		
		
		this.setVisible(true);
		
	}
	

}
