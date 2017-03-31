package mx.edu.ulsaoaxaca.evaluador.servicios.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

import mx.edu.ulsaoaxaca.evaluador.mvc.controlador.ClienteControlador;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Aspirante;

public class ClienteRMIImpl implements ClienteRMI, Serializable {
	
	ClienteControlador controlador;
	Aspirante aspirante;
	
	public ClienteRMIImpl(String host, int port, ClienteControlador c, Aspirante a) {
		this.controlador = c;
		this.aspirante = a;
		try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            ServidorRMI server = (ServidorRMI) registry.lookup("ServidorEvaluador");
           this.controlador.setServer(server);
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Error de conexión al servidor");
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
	}

	@Override
	public void enviarMensajeCliente(String mensaje) throws RemoteException {
		// TODO Auto-generated method stub
		
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
	

}
