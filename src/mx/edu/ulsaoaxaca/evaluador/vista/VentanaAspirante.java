package mx.edu.ulsaoaxaca.evaluador.vista;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class VentanaAspirante extends JFrame {
	
	private JTabbedPane panelPestañas;
	
	private PanelRespuestasCliente panelRespuestas;
	private PanelReporteCliente panelReporte;
	
	public VentanaAspirante() {
		this.setSize(1000, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panelPestañas = new JTabbedPane();
		
		this.panelRespuestas = new PanelRespuestasCliente();
		this.panelReporte = new PanelReporteCliente();
		
		this.panelPestañas.addTab("Respuestas", this.panelRespuestas);
		this.panelPestañas.addTab("Reporte", this.panelReporte);
		
		this.add(this.panelPestañas);
		
		this.setVisible(true);
		
	}

}
