package mx.edu.ulsaoaxaca.evaluador.servicios.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.edu.ulsaoaxaca.evaluador.misc.ClienteListModel;
import mx.edu.ulsaoaxaca.evaluador.mvc.controlador.EvaluadorControlador;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Aspirante;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Pregunta;
import mx.edu.ulsaoaxaca.evaluador.servicios.dao.AspiranteDAO;
import mx.edu.ulsaoaxaca.evaluador.servicios.dao.AspiranteDAOImpl;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ServidorRMIImpl implements ServidorRMI, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4973860656423562649L;
	private AspiranteDAO dao;
	private EvaluadorControlador controlador;
	private Map<Integer, ClienteListModel> clientes;
	
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
		ClienteListModel model = new ClienteListModel(++contadorId, cliente);
		this.clientes.put(contadorId, model);
		this.controlador.agregarCliente(model);
	}

	@Override
	public void enviarPregunta(ClienteRMI cliente, String pregunta) throws RemoteException {
		/**
		 * To-do: guardar la pregunta en la base de datos
		 */
		Pregunta preguntaModel = new Pregunta(pregunta);
		preguntaModel = dao.agregarPregunta(cliente.getAspirante(), preguntaModel);
		if (preguntaModel != null && preguntaModel.getId() != -1) {
			this.controlador.agregarPregunta(preguntaModel);
			cliente.recibirPregunta(preguntaModel);
		} else
			this.controlador.mostrarMensajeDeError(null, "Error en la conexión a la base de datos");
	}

	@Override
	public void recibirRespuesta(ClienteRMI cliente, Pregunta pregunta) throws RemoteException {
		this.dao.actualizarPregunta(pregunta);
		this.controlador.mostrarMensaje(cliente.getAspirante().getNombre() + " contestó: " + pregunta.getRespuesta());
		this.controlador.actualizarPreguntasEvaluacion();
	}

	@Override
	public JasperPrint obtenerReporte(Aspirante aspirante) throws RemoteException {
		Map<String, Object> params = this.controlador.generarParametrosAspirante(aspirante);
		List<Map<String, String>> campos = this.controlador.generarListaPreguntasReporte(aspirante);
		JRBeanCollectionDataSource dataSource =  new JRBeanCollectionDataSource(campos);
		JasperPrint jp = this.controlador.generarJasperPrint("reports/evaluacion.jrxml", params, dataSource);
		return jp;
	}

}
