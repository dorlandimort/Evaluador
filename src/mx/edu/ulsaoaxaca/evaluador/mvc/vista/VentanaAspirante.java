package mx.edu.ulsaoaxaca.evaluador.mvc.vista;

import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class VentanaAspirante extends JFrame implements Serializable {
	
	private JTabbedPane panelPesta�as;
	
	private PanelRespuestasCliente panelRespuestas;
	private PanelReporte panelReporte;
	
	public VentanaAspirante() {
		this.setSize(1000, 600);
		
		panelPesta�as = new JTabbedPane();
		
		this.panelRespuestas = new PanelRespuestasCliente();
		this.panelReporte = new PanelReporte();
		
		this.panelPesta�as.addTab("Respuestas", this.panelRespuestas);
		this.panelPesta�as.addTab("Reporte", this.panelReporte);
		
		this.add(this.panelPesta�as);
		
		
	}

	public JTabbedPane getPanelPesta�as() {
		return panelPesta�as;
	}

	public void setPanelPesta�as(JTabbedPane panelPesta�as) {
		this.panelPesta�as = panelPesta�as;
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
