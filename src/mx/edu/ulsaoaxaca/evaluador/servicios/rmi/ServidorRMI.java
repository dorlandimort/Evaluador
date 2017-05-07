package mx.edu.ulsaoaxaca.evaluador.servicios.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Aspirante;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Pregunta;

public interface ServidorRMI extends Remote {
	
	public Aspirante registrarAspirante(Aspirante aspirante) throws RemoteException;
	public void registrarCliente(ClienteRMI cliente) throws RemoteException;
	public String obtenerEvaluador() throws RemoteException;
	
	public void enviarPregunta(ClienteRMI cliente, String pregunta) throws RemoteException;
	public void recibirRespuesta(ClienteRMI cliente, Pregunta pregunta) throws RemoteException;

}
