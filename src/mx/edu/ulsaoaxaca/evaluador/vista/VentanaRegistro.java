package mx.edu.ulsaoaxaca.evaluador.vista;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class VentanaRegistro extends JFrame {
	
	private JLabel lblNombre;
	private JLabel lblPuesto;
	private JLabel lblEscolaridad;
	private JLabel lblFecha;
	private JLabel lblEdad;
	private JLabel lblIp;
	
	private JTextField txtNombre;
	private JTextField txtPuesto;
	private JTextField txtEscolaridad;
	private JTextField txtFecha;
	private JTextField txtIp;
	
	private JComboBox<Integer> txtEdad;
	private Integer[] edades = { 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38 };
	
	private JButton btnIngresar;
	private JButton btnSalir;
	
	public VentanaRegistro() {
		this.setTitle("Registro");
		this.setSize(1000, 600);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(10, 10, 965, 230);
		TitledBorder title = BorderFactory.createTitledBorder("");
		panel1.setBorder(title);
		
		this.lblNombre = new JLabel("Nombre");
		this.lblNombre.setBounds(20, 70, 100, 20);
		
		this.txtNombre = new JTextField();
		this.txtNombre.setBounds(130, 70, 400, 20);
		
		this.lblPuesto = new JLabel("Puesto");
		this.lblPuesto.setBounds(20, 95, 100, 20);
		
		this.txtPuesto = new JTextField();
		this.txtPuesto.setBounds(130, 95, 400, 20);
		
		this.lblEscolaridad = new JLabel("Fecha");
		this.lblEscolaridad.setBounds(20, 120, 100, 20);
		
		this.txtEscolaridad = new JTextField();
		this.txtEscolaridad.setBounds(130, 120, 400, 20);
		
		this.lblFecha = new JLabel("Fecha");
		this.lblFecha.setBounds(540, 70, 100, 20);
		
		this.txtFecha = new JTextField();
		this.txtFecha.setBounds(650, 70, 100, 20);
		
		this.lblEdad = new JLabel("Edad");
		this.lblEdad.setBounds(540, 95, 100, 20);
		
		this.txtEdad = new JComboBox<Integer>(this.edades);
		this.txtEdad.setBounds(650, 95, 100, 20);
	
		
		panel1.add(this.lblNombre);
		panel1.add(this.txtNombre);
		panel1.add(this.lblPuesto);
		panel1.add(this.txtPuesto);
		panel1.add(this.lblEscolaridad);
		panel1.add(this.txtEscolaridad);
		panel1.add(this.lblFecha);
		panel1.add(this.txtFecha);
		panel1.add(this.lblEdad);
		panel1.add(this.txtEdad);
		
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(10, 250, 965, 200);
		panel2.setBorder(title);
		
		
		this.lblIp = new JLabel("Dirección IP");
		this.lblIp.setBounds(20, 20, 100, 20);
		
		this.txtIp = new JTextField();
		this.txtIp.setBounds(130, 20, 400, 20);
		
		this.btnIngresar = new JButton("Ingresar");
		this.btnIngresar.setBounds(520, 100, 100, 20);
		
		this.btnSalir = new JButton("Salir");
		this.btnSalir.setBounds(630, 100, 100, 20);
		
		panel2.add(this.lblIp);
		panel2.add(this.txtIp);
		panel2.add(this.btnIngresar);
		panel2.add(this.btnSalir);
		
		this.add(panel1);
		this.add(panel2);
		
		//this.setVisible(true);
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public JLabel getLblPuesto() {
		return lblPuesto;
	}

	public void setLblPuesto(JLabel lblPuesto) {
		this.lblPuesto = lblPuesto;
	}

	public JLabel getLblEscolaridad() {
		return lblEscolaridad;
	}

	public void setLblEscolaridad(JLabel lblEscolaridad) {
		this.lblEscolaridad = lblEscolaridad;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}

	public void setLblFecha(JLabel lblFecha) {
		this.lblFecha = lblFecha;
	}

	public JLabel getLblEdad() {
		return lblEdad;
	}

	public void setLblEdad(JLabel lblEdad) {
		this.lblEdad = lblEdad;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtPuesto() {
		return txtPuesto;
	}

	public void setTxtPuesto(JTextField txtPuesto) {
		this.txtPuesto = txtPuesto;
	}

	public JTextField getTxtEscolaridad() {
		return txtEscolaridad;
	}

	public void setTxtEscolaridad(JTextField txtEscolaridad) {
		this.txtEscolaridad = txtEscolaridad;
	}

	public JTextField getTxtFecha() {
		return txtFecha;
	}

	public void setTxtFecha(JTextField txtFecha) {
		this.txtFecha = txtFecha;
	}

	public JComboBox<Integer> getTxtEdad() {
		return txtEdad;
	}

	public void setTxtEdad(JComboBox<Integer> txtEdad) {
		this.txtEdad = txtEdad;
	}

	public JButton getBtnIngresar() {
		return btnIngresar;
	}

	public void setBtnIngresar(JButton btnIngresar) {
		this.btnIngresar = btnIngresar;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	
}
