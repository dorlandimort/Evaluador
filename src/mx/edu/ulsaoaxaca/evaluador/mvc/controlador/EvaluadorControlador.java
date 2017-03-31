package mx.edu.ulsaoaxaca.evaluador.mvc.controlador;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Sesion;
import mx.edu.ulsaoaxaca.evaluador.mvc.vista.PanelEvaluacion;
import mx.edu.ulsaoaxaca.evaluador.mvc.vista.PanelPreguntas;
import mx.edu.ulsaoaxaca.evaluador.mvc.vista.VentanaEvaluador;
import mx.edu.ulsaoaxaca.evaluador.servicios.dao.AspiranteDAO;
import mx.edu.ulsaoaxaca.evaluador.servicios.dao.AspiranteDAOImpl;
import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ClienteRMI;

public class EvaluadorControlador {
	
	private VentanaEvaluador ventanaEvaluador;
	private AspiranteDAO dao;
	private Sesion sesionActual;
	
	private Map<Integer, ClienteRMI> clientesConectados;
	
	private String[] titulos = {"No.", "Pregunta", "Respuesta", "Aspirante", "Correcto", "Enviar"};
	
	public EvaluadorControlador() {
		this.init();
	}
	
	public void init() {
		
		this.dao = AspiranteDAOImpl.getInstance();
		this.ventanaEvaluador = new VentanaEvaluador();
		this.clientesConectados = new HashMap<>();
		
		Object data[][] = {
				{new Integer(1), "¿Qué te gusta hacer?", "Programar", "Alondra Soledad", new Boolean(true), new Boolean(false)},
				{new Integer(2), "¿En qué lenguaje?", "Java", "Alondra Soledad", new Boolean(true), new Boolean(false)}
		};
		
		this.ventanaEvaluador.setPanelEvaluacion(new PanelEvaluacion(data, this.titulos));
		this.ventanaEvaluador.setPanelPreguntas(new PanelPreguntas());
		this.ventanaEvaluador.addTabs();
		this.ventanaEvaluador.getPanelPreguntas().getBtnSalir().addActionListener(e -> {
			this.cerrarVentanaEvaluador();
		});
		
		// Listener para la lista de aspirantes
		this.ventanaEvaluador.getPanelPreguntas().getListaAspirantes().addListSelectionListener(e -> {
			System.out.println(this.ventanaEvaluador.getPanelPreguntas().getListaAspirantes().getSelectedValue());
		});
		
		this.mostrarVentanaEvaluador();
		
		String nombre = JOptionPane.showInputDialog(this.ventanaEvaluador, "Ingrese su nombre");
		if (nombre != null && nombre.length() > 0) {
			Sesion sesion  = new Sesion();
			sesion.setEvaluador(nombre);
			sesion = this.dao.registrarSesion(sesion);
			if (sesion == null || sesion.getId() == -1) {
				this.mostrarMensajeDeError(this.ventanaEvaluador, "Ocurrió en error de comunicación al servidor de base de datos");
				this.cerrarVentanaEvaluador();
			} else {
				this.sesionActual = sesion;
				this.ventanaEvaluador.getPanelPreguntas().getLblEvaluador().setText("Evaluador: " + nombre);
			}
		} else {
			this.cerrarVentanaEvaluador();
		}
	}
	
	public void mostrarVentanaEvaluador() {
		this.ventanaEvaluador.setVisible(true);
	}
	
	public void cerrarVentanaEvaluador() {
		this.ventanaEvaluador.dispose();
	}
	
	public String obtenerEvaluador() {
		return this.ventanaEvaluador.getPanelPreguntas().getLblEvaluador().getText();
	}
	
	public void mostrarMensajeDeError(Component parent, String mensaje) {
		JOptionPane.showMessageDialog(parent, mensaje, "Ha ocurrido un error", JOptionPane.OK_OPTION);
	}
	
	public void agregarCliente(int id, ClienteRMI cliente) {
		
		this.clientesConectados.put(id, cliente);
		this.ventanaEvaluador.getPanelPreguntas().getListModel().addElement(cliente);
		this.mostrarMensaje("Se ha conectado un nuevo aspirante");
	}
	
	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this.ventanaEvaluador, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
	}
	

	// Setters y getters
	
	public VentanaEvaluador getVentanaEvaluador() {
		return ventanaEvaluador;
	}

	public void setVentanaEvaluador(VentanaEvaluador ventanaEvaluador) {
		this.ventanaEvaluador = ventanaEvaluador;
	}

	public AspiranteDAO getDao() {
		return dao;
	}

	public void setDao(AspiranteDAO dao) {
		this.dao = dao;
	}

	public Sesion getSesionActual() {
		return sesionActual;
	}

	public void setSesionActual(Sesion sesionActual) {
		this.sesionActual = sesionActual;
	}

	public String[] getTitulos() {
		return titulos;
	}

	public void setTitulos(String[] titulos) {
		this.titulos = titulos;
	}
	
	

}
