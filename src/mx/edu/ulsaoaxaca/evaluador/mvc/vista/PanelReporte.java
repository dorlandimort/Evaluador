package mx.edu.ulsaoaxaca.evaluador.mvc.vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelReporte extends JPanel {

	private JLabel lblReporte;
	private JLabel lblEvaluador;
	
	private TitledBorder bordePregunta;
	private JPanel panelPregunta;
	
	private JButton btnExportar;
	
	
	public PanelReporte() {
		this.setLayout(null);
		
		this.lblReporte = new JLabel("Reporte de evaluación");
		this.lblReporte.setBounds(300, 20, 200, 20);
		
		this.lblEvaluador = new JLabel("Evaluador: Harim Castellanos");
		this.lblEvaluador.setBounds(20, 50, 200, 20);
		
		this.bordePregunta = new TitledBorder("Pregunta 1");
		
		this.panelPregunta = new JPanel();
		this.panelPregunta.setBorder(this.bordePregunta);
		this.panelPregunta.setBounds(30, 90, 750, 150);
		
		this.btnExportar = new JButton("Exportar");
		this.btnExportar.setBounds(750, 400, 100, 20);
		
		this.add(this.lblReporte);
		this.add(this.lblEvaluador);
		this.add(this.panelPregunta);
		this.add(this.btnExportar);
		
	}


	public JLabel getLblReporte() {
		return lblReporte;
	}


	public void setLblReporte(JLabel lblReporte) {
		this.lblReporte = lblReporte;
	}


	public JLabel getLblEvaluador() {
		return lblEvaluador;
	}


	public void setLblEvaluador(JLabel lblEvaluador) {
		this.lblEvaluador = lblEvaluador;
	}


	public TitledBorder getBordePregunta() {
		return bordePregunta;
	}


	public void setBordePregunta(TitledBorder bordePregunta) {
		this.bordePregunta = bordePregunta;
	}


	public JPanel getPanelPregunta() {
		return panelPregunta;
	}


	public void setPanelPregunta(JPanel panelPregunta) {
		this.panelPregunta = panelPregunta;
	}


	public JButton getBtnExportar() {
		return btnExportar;
	}


	public void setBtnExportar(JButton btnExportar) {
		this.btnExportar = btnExportar;
	}
	
	
}
