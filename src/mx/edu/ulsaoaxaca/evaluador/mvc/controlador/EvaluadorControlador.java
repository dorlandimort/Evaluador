package mx.edu.ulsaoaxaca.evaluador.mvc.controlador;

import java.awt.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import mx.edu.ulsaoaxaca.evaluador.misc.ClienteListModel;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Aspirante;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Pregunta;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Sesion;
import mx.edu.ulsaoaxaca.evaluador.mvc.vista.PanelEvaluacion;
import mx.edu.ulsaoaxaca.evaluador.mvc.vista.PanelPreguntas;
import mx.edu.ulsaoaxaca.evaluador.mvc.vista.VentanaEvaluador;
import mx.edu.ulsaoaxaca.evaluador.servicios.dao.AspiranteDAO;
import mx.edu.ulsaoaxaca.evaluador.servicios.dao.AspiranteDAOImpl;
import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ClienteRMI;
import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ServidorRMI;

import java.rmi.RemoteException;

public class EvaluadorControlador {
	
	private VentanaEvaluador ventanaEvaluador;
	private AspiranteDAO dao;
	private Sesion sesionActual;
	private ClienteListModel clienteSeleccionado;
	private ServidorRMI server;
	
	private Map<Integer, ClienteListModel> clientesConectados;
	
	private String[] titulos = {"Id", "Pregunta", "Respuesta", "Aspirante", "Correcto"};
	
	public EvaluadorControlador() {
		this.init();
		
	}
	
	public void init() {
		this.dao = AspiranteDAOImpl.getInstance();
		this.ventanaEvaluador = new VentanaEvaluador();
		this.clientesConectados = new HashMap<>();
		
		Object data[][] = {
		};
		
		this.ventanaEvaluador.setPanelEvaluacion(new PanelEvaluacion(data, this.titulos));
		this.ventanaEvaluador.setPanelPreguntas(new PanelPreguntas());
		this.ventanaEvaluador.addTabs();
		this.ventanaEvaluador.getPanelPreguntas().getBtnSalir().addActionListener(e -> {
			this.cerrarVentanaEvaluador();
		});
		
		// Listener para la lista de aspirantes
		this.ventanaEvaluador.listaClientes().addListSelectionListener(e -> {
			ClienteListModel cliente = (ClienteListModel) this.ventanaEvaluador.listaClientes().getSelectedValue();
			this.clienteSeleccionado = cliente;
			this.actualizarPreguntasEnviadas();
			this.actualizarPreguntasEvaluacion();
			
		});
		
		// Listener para boton de enviar pregunta
		
		this.ventanaEvaluador.botonEnviarPregunta().addActionListener(e -> {
			if (this.clienteSeleccionado != null) {
				String pregunta = this.ventanaEvaluador.textoPregunta();
				if (pregunta.isEmpty()) {
					this.mostrarMensajeDeError(this.ventanaEvaluador, "El campo de pregunta no puede estar vacío");
				} else {
					try {
						server.enviarPregunta(this.clienteSeleccionado.getCliente(), pregunta);
						this.ventanaEvaluador.getPanelPreguntas().getTxtPregunta().setText("");
						this.actualizarPreguntasEvaluacion();
					} catch (RemoteException ex) {
						this.mostrarMensajeDeError(this.ventanaEvaluador, "Error de conexión con el cliente");
					}
				}
			} else
				this.mostrarMensajeDeError(this.ventanaEvaluador, "Seleccione un aspirante antes de enviar una pregunta");
		});
		
		// Listener para evaluar al aspirante
		this.ventanaEvaluador.getPanelEvaluacion().getBtnEvaluar().addActionListener(e -> {
			try {
				Aspirante aspirante = this.clienteSeleccionado.getCliente().getAspirante();
				DefaultTableModel model = this.ventanaEvaluador.getPanelEvaluacion().getModel();
				int m = model.getRowCount();
				int n = model.getColumnCount();
				Object[][] datos = new Object[m][n];
				for (int i = 0; i < m; i++) {
					for (int j = 0; j < n; j++) {
						datos[i][j] = model.getValueAt(i, j);
					}
				}
				for (int i = 0; i < datos.length; i++) {
					int id = (int) datos[i][0];
					System.out.println("id de la pregunta: " + id);
					
					boolean correcta = (boolean) datos[i][4];
					System.out.println("correcta?: " + correcta);
					this.dao.calificarPregunta(id, correcta);
				}
				double total = this.dao.contarPreguntas(aspirante);
				double correctas = this.dao.contarPreguntasCorrectas(aspirante);
				System.out.println("total: " + total + " correctas: " + correctas);
				double puntuacion = correctas * 100 / total; 
				aspirante.setPuntuacion(puntuacion);
				this.dao.puntuarAspirante(aspirante);
				this.mostrarMensaje("Se ha evaluado correctamente, la calificación es de " + puntuacion 
				+ ". Para más información consulte el reporte.");
				
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
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
	
	public void agregarCliente(ClienteListModel cliente) {
		this.clientesConectados.put(cliente.getId(), cliente);
		this.ventanaEvaluador.getPanelPreguntas().getListModel().addElement(cliente);
		this.mostrarMensaje("Se ha conectado un nuevo aspirante");
	}
	
	public void mostrarMensaje(String mensaje) {
		Thread t = new Thread(new Runnable(){
	        public void run(){
	        	JOptionPane.showMessageDialog(ventanaEvaluador, 
	        			mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
	        }
	    });
	  t.start();
		
	}
	
	public void agregarPregunta(Pregunta pregunta) {
		this.ventanaEvaluador.getPanelPreguntas().getPreguntasEnviadasModelo().addElement(pregunta);
	}
	
	public void actualizarPreguntasEvaluacion() {
		try {
			List<Pregunta> preguntas = dao.obtenerPreguntas(this.clienteSeleccionado.getCliente().getAspirante());
			this.clienteSeleccionado.getCliente().getAspirante().setPreguntas(preguntas);
			if (preguntas.size() > 0) {
				Object[][] datos = new Object[preguntas.size()][5]; 
				for (int i = 0; i < preguntas.size(); i++) {
					Pregunta p = preguntas.get(i);
					datos[i][0] = p.getId();
					datos[i][1] = p.getPregunta();
					datos[i][2] = p.getRespuesta();
					datos[i][3] = this.clienteSeleccionado.getCliente().getAspirante().getNombre();
					datos[i][4] = false;
							
				}
				this.ventanaEvaluador.getPanelEvaluacion().agregarDatosTabla(datos);
			} else {
				Object[][] datos = new Object[0][4];
				this.ventanaEvaluador.getPanelEvaluacion().agregarDatosTabla(datos);
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void actualizarPreguntasEnviadas() {
		try {
			this.ventanaEvaluador.getPanelPreguntas().getPreguntasEnviadasModelo().clear();
			List<Pregunta> preguntas = dao.obtenerPreguntas(this.clienteSeleccionado.getCliente().getAspirante());
			this.clienteSeleccionado.getCliente().getAspirante().setPreguntas(preguntas);
			for (Pregunta p : preguntas) {
				this.ventanaEvaluador.getPanelPreguntas().getPreguntasEnviadasModelo().addElement(p);
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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

	public ClienteListModel getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public void setClienteSeleccionado(ClienteListModel clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}

	public Map<Integer, ClienteListModel> getClientesConectados() {
		return clientesConectados;
	}

	public void setClientesConectados(Map<Integer, ClienteListModel> clientesConectados) {
		this.clientesConectados = clientesConectados;
	}

	public ServidorRMI getServer() {
		return server;
	}

	public void setServer(ServidorRMI server) {
		this.server = server;
	}
	
	

}
