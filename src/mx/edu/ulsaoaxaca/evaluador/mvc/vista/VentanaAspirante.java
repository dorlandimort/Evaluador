package mx.edu.ulsaoaxaca.evaluador.mvc.vista;

import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class VentanaAspirante extends JFrame implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8268929114607318521L;

	private JTabbedPane panelPestaņas;
	
	private PanelRespuestasCliente panelRespuestas;
	
	public VentanaAspirante() {
		this.setSize(1000, 600);
		
		panelPestaņas = new JTabbedPane();
		
		this.panelRespuestas = new PanelRespuestasCliente();
		
		this.panelPestaņas.addTab("Respuestas", this.panelRespuestas);
		
		this.add(this.panelPestaņas);
		
		
	}

	public JTabbedPane getPanelPestaņas() {
		return panelPestaņas;
	}

	public void setPanelPestaņas(JTabbedPane panelPestaņas) {
		this.panelPestaņas = panelPestaņas;
	}

	public PanelRespuestasCliente getPanelRespuestas() {
		return panelRespuestas;
	}

	public void setPanelRespuestas(PanelRespuestasCliente panelRespuestas) {
		this.panelRespuestas = panelRespuestas;
	}


}
