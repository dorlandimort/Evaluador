package mx.edu.ulsaoaxaca.evaluador.servicios.rmi;

import java.rmi.RemoteException;

import mx.edu.ulsaoaxaca.evaluador.mvc.controlador.EvaluadorControlador;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Aspirante;
import mx.edu.ulsaoaxaca.evaluador.servicios.dao.AspiranteDAO;
import mx.edu.ulsaoaxaca.evaluador.servicios.dao.AspiranteDAOImpl;

public class ServidorRMIImpl implements ServidorRMI {

	private AspiranteDAO dao;
	private EvaluadorControlador controlador;
	
	public ServidorRMIImpl(EvaluadorControlador controlador) {
		this.dao = AspiranteDAOImpl.getInstance();
		this.controlador = controlador;
	}
	
	@Override
	public Aspirante registrarAspirante(Aspirante aspirante) throws RemoteException {
		aspirante = dao.registrarAspirante(aspirante);
		return aspirante;
	}
	
	public String obtenerEvaluador() throws RemoteException {
		return this.controlador.obtenerEvaluador();
	}

}
