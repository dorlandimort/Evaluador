package mx.edu.ulsaoaxaca.evaluador.servicios.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Aspirante;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Pregunta;
import net.sf.jasperreports.engine.JasperPrint;

public interface ClienteRMI extends Remote {
	
	public Aspirante registrarAspirante(Aspirante aspirante) throws RemoteException;
	public String obtenerEvaluador() throws RemoteException;
	public void registrarCliente(ClienteRMI cliente) throws RemoteException;
	
	public void recibirPregunta(Pregunta pregunta) throws RemoteException;
	public void enviarRespuesta(Pregunta pregunta) throws RemoteException;
	public Aspirante getAspirante() throws RemoteException;
	public JasperPrint obtenerReporte(Aspirante aspirante) throws RemoteException;
	public void desconectar() throws RemoteException;
	
}
