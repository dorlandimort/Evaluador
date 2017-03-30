package mx.edu.ulsaoaxaca.evaluador.mvc.controlador;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Aspirante;
import mx.edu.ulsaoaxaca.evaluador.mvc.vista.VentanaAspirante;
import mx.edu.ulsaoaxaca.evaluador.mvc.vista.VentanaRegistro;
import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ServidorRMI;

public class ClienteControlador {
	
	private VentanaRegistro ventanaRegistro;
	private VentanaAspirante ventanaAspirante;
	
	private ServidorRMI server;
	
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
			this.iniciarRMI();
			String evaluador;
			try {
				
				/**
				 * Poner las propiedades al aspirante
				 */
				Aspirante aspirante = new Aspirante();
				
				/**
				 * mandar a llamar al metodo remoto para guardar un aspirante
				 */
				//aspirante = this.server.registrarAspirante(aspirante);
				/**
				 * Si el aspirante retornado es null o su id es -1 no se guardó
				 * Entonces debemos tirar un error
				 */
				evaluador = this.server.obtenerEvaluador();
				this.mostrarVentanaClientePrincipal();
				this.ventanaAspirante.getPanelRespuestas().getLblEvaluador().setText(evaluador);
			} catch (RemoteException e1) {
				JOptionPane.showMessageDialog(this.ventanaRegistro, "Ocurrió un error en la conexión al servidor");
				e1.printStackTrace();
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
	
	private void iniciarRMI() {
		String host = "172.16.12.148";
        try {
        	
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            ServidorRMI server = (ServidorRMI) registry.lookup("ServidorEvaluador");
            this.server = server;
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Error de conexión al servidor");
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
	}
	
	

}
