package mx.edu.ulsaoaxaca.evaluador.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VentanaRegistro extends JFrame {
	
	private JLabel lblNombre;
	private JLabel lblPuesto;
	private JLabel lblEscolaridad;
	private JLabel lblFecha;
	private JLabel lblEdad;
	
	private JTextField txtNombre;
	private JTextField txtPuesto;
	private JTextField txtEscolaridad;
	private JTextField txtFecha;
	
	private JComboBox<Integer> txtEdad;
	
	private JButton btnIngresar;
	private JButton btnSalir;
	
	public VentanaRegistro() {
		this.setTitle("Registro");
		this.setSize(1000, 600);
		this.setLayout(null);
		
		
		this.lblNombre = new JLabel("Nombre");
		this.lblNombre.setBounds(50, 20, 100, 20);
		
		this.txtNombre = new JTextField();
		this.txtNombre.setBounds(160, 20, 400, 20);
		
		this.add(this.lblNombre);
		this.add(this.txtNombre);
		
		this.setVisible(true);
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
