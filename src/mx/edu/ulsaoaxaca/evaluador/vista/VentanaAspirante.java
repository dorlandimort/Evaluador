package mx.edu.ulsaoaxaca.evaluador.vista;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class VentanaAspirante extends JFrame {
	
	private JTabbedPane panelPestaņas;
	
	private PanelRespuestasCliente panelRespuestas;
	private PanelReporteCliente panelReporte;
	
	public VentanaAspirante() {
		this.setSize(1000, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panelPestaņas = new JTabbedPane();
		
		this.panelRespuestas = new PanelRespuestasCliente();
		this.panelReporte = new PanelReporteCliente();
		
		this.panelPestaņas.addTab("Respuestas", this.panelRespuestas);
		this.panelPestaņas.addTab("Reporte", this.panelReporte);
		
		this.add(this.panelPestaņas);
		
		this.setVisible(true);
		
	}

}
