package mx.edu.ulsaoaxaca.evaluador.servicios.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Aspirante;

public interface ServidorRMI extends Remote {
	
	public Aspirante registrarAspirante(Aspirante aspirante) throws RemoteException;
	public String obtenerEvaluador() throws RemoteException;

}
