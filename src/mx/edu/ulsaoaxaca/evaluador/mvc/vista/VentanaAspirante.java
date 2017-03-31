package mx.edu.ulsaoaxaca.evaluador.mvc.vista;

import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class VentanaAspirante extends JFrame implements Serializable {
	
	private JTabbedPane panelPestañas;
	
	private PanelRespuestasCliente panelRespuestas;
	private PanelReporte panelReporte;
	
	public VentanaAspirante() {
		this.setSize(1000, 600);
		
		panelPestañas = new JTabbedPane();
		
		this.panelRespuestas = new PanelRespuestasCliente();
		this.panelReporte = new PanelReporte();
		
		this.panelPestañas.addTab("Respuestas", this.panelRespuestas);
		this.panelPestañas.addTab("Reporte", this.panelReporte);
		
		this.add(this.panelPestañas);
		
		
	}

	public JTabbedPane getPanelPestañas() {
		return panelPestañas;
	}

	public void setPanelPestañas(JTabbedPane panelPestañas) {
		this.panelPestañas = panelPestañas;
	}

	public PanelRespuestasCliente getPanelRespuestas() {
		return panelRespuestas;
	}

	public void setPanelRespuestas(PanelRespuestasCliente panelRespuestas) {
		this.panelRespuestas = panelRespuestas;
	}

	public PanelReporte getPanelReporte() {
		return panelReporte;
	}

	public void setPanelReporte(PanelReporte panelReporte) {
		this.panelReporte = panelReporte;
	}
	
	

}
