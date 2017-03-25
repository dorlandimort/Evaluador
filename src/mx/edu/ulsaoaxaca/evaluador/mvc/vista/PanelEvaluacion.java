package mx.edu.ulsaoaxaca.evaluador.mvc.vista;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import mx.edu.ulsaoaxaca.evaluador.misc.PreguntasModel;

public class PanelEvaluacion extends JPanel {
	
	private TitledBorder bordePreguntas;
	
	private JPanel panel;
	
	private JButton btnReporte;
	private JButton btnEnviar;
	private JButton btnSalir;
	private JTable tablaPreguntas;
	
	
	public PanelEvaluacion(Object[][] data, String[] titulos) {
		this.setLayout(null);
		
		this.bordePreguntas = new TitledBorder("Preguntas Contestadas");
		
		this.panel = new JPanel();
		this.panel.setBorder(this.bordePreguntas);
		this.panel.setBounds(20, 20, 960, 500);
		this.panel.setLayout(null);
		
		this.btnEnviar = new JButton("Evaluar");
		this.btnEnviar.setBounds(50, 450, 100, 20 );
		
		this.agregarTabla(data, titulos);
		panel.add(this.btnEnviar);
		this.add(panel);
		
	}
	
	public void agregarTabla(Object[][] data, String[] titulos) {
		
		DefaultTableModel model = new DefaultTableModel(data, titulos) {
            public Class getColumnClass(int c) {
              switch (c) {
                case 0: return Integer.class;
                case 4: return Boolean.class;
                case 5: return Boolean.class;
                default: return String.class;
              }   
            } };
		
		this.tablaPreguntas = new JTable(model);
		
		JScrollPane scroll = new JScrollPane(this.tablaPreguntas);
		scroll.setBounds(50, 50, 900, 350);
		
		this.panel.add(scroll);
	}

}
