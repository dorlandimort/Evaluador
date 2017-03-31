package mx.edu.ulsaoaxaca.evaluador.servicios.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import mx.edu.ulsaoaxaca.evaluador.mvc.controlador.EvaluadorControlador;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Aspirante;
import mx.edu.ulsaoaxaca.evaluador.servicios.dao.AspiranteDAO;
import mx.edu.ulsaoaxaca.evaluador.servicios.dao.AspiranteDAOImpl;

public class ServidorRMIImpl implements ServidorRMI, Serializable {

	private AspiranteDAO dao;
	private EvaluadorControlador controlador;
	private Map<Integer, ClienteRMI> clientes;
	
	private int contadorId;
	
	public ServidorRMIImpl(EvaluadorControlador controlador) {
		this.dao = AspiranteDAOImpl.getInstance();
		this.controlador = controlador;
		this.clientes = new HashMap<>();
	}
	
	@Override
	public Aspirante registrarAspirante(Aspirante aspirante) throws RemoteException {
		aspirante.setSesion(this.controlador.getSesionActual());
		aspirante = dao.registrarAspirante(aspirante);
		return aspirante;
	}
	
	public String obtenerEvaluador() throws RemoteException {
		return this.controlador.obtenerEvaluador();
	}

	@Override
	public void registrarCliente(ClienteRMI cliente) throws RemoteException {
		this.clientes.put(contadorId, cliente);
		this.controlador.agregarCliente(contadorId, cliente);
		contadorId++;
	}

}
