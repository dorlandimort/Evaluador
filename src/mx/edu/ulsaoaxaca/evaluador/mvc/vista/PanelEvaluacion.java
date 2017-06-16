package mx.edu.ulsaoaxaca.evaluador.mvc.vista;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class PanelEvaluacion extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TitledBorder bordePreguntas;
	
	private JPanel panel;
	
	private JButton btnReporte;
	private JButton btnEvaluar;
	private JButton btnSalir;
	private JTable tablaPreguntas;
	private String[] titulos;
	private Object[][] datos;
	private DefaultTableModel model;
	
	
	public PanelEvaluacion(Object[][] data, String[] titulos) {
		this.setLayout(null);
		
		this.bordePreguntas = new TitledBorder("Preguntas Contestadas");
		
		this.panel = new JPanel();
		this.panel.setBorder(this.bordePreguntas);
		this.panel.setBounds(20, 20, 960, 500);
		this.panel.setLayout(null);
		this.titulos = titulos;
		
		this.btnEvaluar = new JButton("Evaluar y terminar");
		this.btnEvaluar.setBounds(50, 450, 200, 20 );
		
		this.btnReporte = new JButton("Generar reporte");
		this.btnReporte.setBounds(270, 450, 200, 20);
		
		this.agregarTabla(data);
		panel.add(this.btnEvaluar);
		panel.add(btnReporte);
		this.add(panel);
		
	}
	
	@SuppressWarnings("serial")
	private void agregarTabla(Object[][] data) {
		 this.model = new DefaultTableModel(data, this.titulos) {
            @SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int c) {
              switch (c) {
                case 4: return Boolean.class;
                default: return String.class;
              }   
            } };
		this.datos = data;
		this.tablaPreguntas = new JTable(model);
		
		JScrollPane scroll = new JScrollPane(this.tablaPreguntas);
		scroll.setBounds(50, 50, 900, 350);
		
		this.panel.add(scroll);
		this.repaint();
	}
	
	public void agregarDatosTabla(Object[][] datos) {
		this.model.setDataVector(datos, this.titulos);
		this.datos = datos;
	}

	public TitledBorder getBordePreguntas() {
		return bordePreguntas;
	}

	public void setBordePreguntas(TitledBorder bordePreguntas) {
		this.bordePreguntas = bordePreguntas;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JButton getBtnReporte() {
		return btnReporte;
	}

	public void setBtnReporte(JButton btnReporte) {
		this.btnReporte = btnReporte;
	}

	public JButton getBtnEvaluar() {
		return btnEvaluar;
	}

	public void setBtnEvaluar(JButton btnEnviar) {
		this.btnEvaluar = btnEnviar;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JTable getTablaPreguntas() {
		return tablaPreguntas;
	}

	public void setTablaPreguntas(JTable tablaPreguntas) {
		this.tablaPreguntas = tablaPreguntas;
	}

	public String[] getTitulos() {
		return titulos;
	}

	public void setTitulos(String[] titulos) {
		this.titulos = titulos;
	}

	public Object[][] getDatos() {
		return datos;
	}

	public void setDatos(Object[][] datos) {
		this.datos = datos;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	
	

}
