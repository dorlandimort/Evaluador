package mx.edu.ulsaoaxaca.evaluador.servicios.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

import mx.edu.ulsaoaxaca.evaluador.mvc.controlador.ClienteControlador;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Aspirante;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Pregunta;
import net.sf.jasperreports.engine.JasperPrint;

public class ClienteRMIImpl implements ClienteRMI, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1423982717920875048L;
	private ClienteControlador controlador;
	private Aspirante aspirante;
	private ServidorRMI server;
	
	public ClienteRMIImpl(String host, int port, ClienteControlador c, Aspirante a) {
		this.controlador = c;
		this.aspirante = a;
		try {
			UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry(host, port);
            ServidorRMI server = (ServidorRMI) registry.lookup("ServidorEvaluador");
           this.server = server;
           server.registrarCliente(this);
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Error de conexión al servidor");
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
	}
	

	@Override
	public void recibirPregunta(Pregunta pregunta) throws RemoteException {
		this.controlador.mostrarMensaje("Se ha agregado una nueva pregunta a su lista de preguntas pendientes");
		this.controlador.agregarPregunta(pregunta);
		
	}
	
	@Override
	public void enviarRespuesta(Pregunta pregunta) throws RemoteException {
		this.server.recibirRespuesta(this, pregunta);
		
	}

	public ClienteControlador getControlador() {
		return controlador;
	}

	public void setControlador(ClienteControlador controlador) {
		this.controlador = controlador;
	}

	public Aspirante getAspirante() {
		return aspirante;
	}

	public void setAspirante(Aspirante aspirante) {
		this.aspirante = aspirante;
	}
	
	public String toString() {
		return this.aspirante.getNombre();
	}


	@Override
	public Aspirante registrarAspirante(Aspirante aspirante) throws RemoteException {
		this.aspirante = server.registrarAspirante(aspirante);
		return this.aspirante;
		
	}


	@Override
	public String obtenerEvaluador() throws RemoteException {
		return server.obtenerEvaluador();
	}


	@Override
	public void registrarCliente(ClienteRMI cliente) throws RemoteException {
		server.registrarCliente(this);
		
	}


	@Override
	public JasperPrint obtenerReporte(Aspirante aspirante) throws RemoteException {
		return this.server.obtenerReporte(aspirante);
	}
	
	

}
