package mx.edu.ulsaoaxaca.evaluador.vista;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class VentanaAspirante extends JFrame {
	
	private JTabbedPane panelPesta�as;
	
	private PanelRespuestasCliente panelRespuestas;
	private PanelReporteCliente panelReporte;
	
	public VentanaAspirante() {
		this.setSize(1000, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panelPesta�as = new JTabbedPane();
		
		this.panelRespuestas = new PanelRespuestasCliente();
		this.panelReporte = new PanelReporteCliente();
		
		this.panelPesta�as.addTab("Respuestas", this.panelRespuestas);
		this.panelPesta�as.addTab("Reporte", this.panelReporte);
		
		this.add(this.panelPesta�as);
		
		this.setVisible(true);
		
	}

}
