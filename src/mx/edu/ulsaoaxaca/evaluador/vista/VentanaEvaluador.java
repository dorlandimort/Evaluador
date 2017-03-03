package mx.edu.ulsaoaxaca.evaluador.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
		
		
		// Inicializar el panel de pestañas
		this.panelPestañas = new JTabbedPane();
		
		
		//Inicializar los paneles
		this.panelPreguntas = new PanelPreguntas();
		this.panelBitacora = new PanelBitacora();
		this.panelEvaluacion = new PanelEvaluacion();
		//this.panelReporte = new PanelReporte();
		
		//agregar los paneles al panel de pestañas
		
		this.panelPestañas.addTab("Preguntas", this.panelPreguntas);
		this.panelPestañas.addTab("Bitacora", this.panelBitacora);
		this.panelPestañas.addTab("Evaluación", this.panelEvaluacion);
		//this.panelPestañas.addTab("Reporte", this.panelReporte);
		
		this.add(panelPestañas);
		
		
		this.setVisible(true);
		
	}
	

}
