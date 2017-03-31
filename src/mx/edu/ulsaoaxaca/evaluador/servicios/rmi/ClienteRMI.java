package mx.edu.ulsaoaxaca.evaluador.servicios.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClienteRMI extends Remote {
	
	public void enviarMensajeCliente(String mensaje) throws RemoteException;

}
