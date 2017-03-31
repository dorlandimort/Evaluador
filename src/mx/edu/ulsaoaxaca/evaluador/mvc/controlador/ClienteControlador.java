package mx.edu.ulsaoaxaca.evaluador.mvc.controlador;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Aspirante;
import mx.edu.ulsaoaxaca.evaluador.mvc.vista.VentanaAspirante;
import mx.edu.ulsaoaxaca.evaluador.mvc.vista.VentanaRegistro;
import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ClienteRMI;
import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ClienteRMIImpl;
import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ServidorRMI;

public class ClienteControlador implements Serializable {
	
	private VentanaRegistro ventanaRegistro;
	private VentanaAspirante ventanaAspirante;
	
	private ServidorRMI server;
	private ClienteRMI clienteRMI;
	
	private String nombre;
	private String puesto;
	private String escolaridad;
	private String ip;
	private int edad;
	
	public ClienteControlador() {
		this.server = server;
		this.ventanaRegistro = new VentanaRegistro();
		this.mostrarVentanaRegistro();
		this.init();
	}
	
	public void init() {
		this.ventanaRegistro.getBtnIngresar().addActionListener(e -> {
			/**
			 * Tenemos que validar que los campos no estan vacios
			 * Si no estan vacios creamos un Aspirante
			 */
			
			if (validarCampos()) {
				String ip = this.ventanaRegistro.getTxtIp().getText();
				
				try {
					
					/**
					 * Poner las propiedades al aspirante
					 */
					Aspirante aspirante = new Aspirante();
					aspirante.setNombre(this.nombre);
					aspirante.setEscolaridad(this.escolaridad);
					aspirante.setEdad(this.edad);
					aspirante.setPuesto(this.puesto);
					
					// Conectar al servidor RMI mediante la creación de un clienteRMI
					this.iniciarRMI(ip, aspirante);
					String evaluador;
					
					/**
					 * mandar a llamar al metodo remoto para guardar un aspirante
					 */
					aspirante = this.server.registrarAspirante(aspirante);
					/**
					 * Si el aspirante retornado es null o su id es -1 no se guardó
					 * Entonces debemos tirar un error
					 */
					if (aspirante == null || aspirante.getId() == -1) {
						this.mostrarError("Error al intentar registrar, intente nuevamente o verifique sus datos.");
					} else {
						this.server.registrarCliente(this.clienteRMI);
						evaluador = this.server.obtenerEvaluador();
						this.mostrarVentanaClientePrincipal();
						this.ventanaAspirante.getPanelRespuestas().getLblEvaluador().setText(evaluador);
					}
					
				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(this.ventanaRegistro, "Ocurrió un error en la conexión al servidor");
					e1.printStackTrace();
				}
			}
			
		});
		this.ventanaRegistro.getBtnSalir().addActionListener(e -> {
			this.ventanaRegistro.dispose();
			
		});
	}
	
	public void mostrarVentanaRegistro() {
		this.ventanaRegistro.setVisible(true);
	}
	
	public void cerrarVentanaRegistro()  {
		this.ventanaRegistro.setVisible(false);
	}
	
	public void mostrarVentanaClientePrincipal() {
		this.ventanaAspirante = new VentanaAspirante();
		
		this.ventanaAspirante.getPanelRespuestas().getBtnSalir().addActionListener(e -> {
			int salir = JOptionPane.showConfirmDialog(this.ventanaAspirante, "Todo su progreso se perderá!", "Está seguro que desea salir?", JOptionPane.YES_NO_OPTION);
			if (salir == JOptionPane.YES_NO_OPTION) {
				this.mostrarVentanaRegistro();
				this.cerrarVentanaClientePrincipal();
			}
		});
		this.ventanaAspirante.setVisible(true);
		this.cerrarVentanaRegistro();
		
	}
	
	public void cerrarVentanaClientePrincipal() {
		this.ventanaAspirante.dispose();
	}
	
	private void iniciarRMI(String host, Aspirante aspirante) {
		this.clienteRMI = new ClienteRMIImpl(host, 1099, this, aspirante);
		
	}
	
	
	public boolean validarCampos() {
		 this.nombre = this.ventanaRegistro.getTxtNombre().getText();
		 this.puesto = this.ventanaRegistro.getTxtPuesto().getText();
		 this.escolaridad = this.ventanaRegistro.getTxtEscolaridad().getText();
		 this.ip = this.ventanaRegistro.getTxtIp().getText();
		 this.edad = (int) this.ventanaRegistro.getTxtEdad().getSelectedItem();
		
		if ( ! nombre.isEmpty() && ! puesto.isEmpty() && ! escolaridad.isEmpty() && !ip.isEmpty())
			return true;
		this.mostrarError("Por favor complete todos los campos.");
		return false;
		
	}
	
	public void mostrarError(String error) {
		JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	
	/**
	 * Getters y Setters
	 * 
	 */

	public VentanaRegistro getVentanaRegistro() {
		return ventanaRegistro;
	}

	public void setVentanaRegistro(VentanaRegistro ventanaRegistro) {
		this.ventanaRegistro = ventanaRegistro;
	}

	public VentanaAspirante getVentanaAspirante() {
		return ventanaAspirante;
	}

	public void setVentanaAspirante(VentanaAspirante ventanaAspirante) {
		this.ventanaAspirante = ventanaAspirante;
	}

	public ServidorRMI getServer() {
		return server;
	}

	public void setServer(ServidorRMI server) {
		this.server = server;
	}
	
	

}
