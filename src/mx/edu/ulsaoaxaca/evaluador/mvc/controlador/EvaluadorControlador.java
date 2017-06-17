package mx.edu.ulsaoaxaca.evaluador.mvc.controlador;

import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
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
import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ServidorRMI;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class EvaluadorControlador {
	
	private VentanaEvaluador ventanaEvaluador;
	private AspiranteDAO dao;
	private Sesion sesionActual;
	private ClienteListModel clienteSeleccionado;
	private ServidorRMI server;
	
	private Map<Integer, ClienteListModel> clientesConectados;
	
	private String[] titulos = {"Id", "Pregunta", "Respuesta", "Aspirante", "Evaluación"};
	
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
		
		// listener para generar el reporte de la evaluacion
		
		this.ventanaEvaluador.getPanelEvaluacion().getBtnReporte().addActionListener(e -> {
			generarReporte();
			
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
		
		this.ventanaEvaluador.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					server.desconectar();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
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
					datos[i][4] = p.isCorrecta();
							
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
	
	/**
	 * Método usado para generar el ReportViewer de Jasper Reports
	 */
	private void generarReporte() {
		System.out.println("Generar Reporte");
		Map<String, Object> params = new HashMap<>();
		try {
			if (this.clienteSeleccionado != null) {
				Aspirante aspirante = this.clienteSeleccionado.getCliente().getAspirante();
				int puntuacion = this.dao.obtenerPuntuacion(aspirante);
				params.put("evaluador", this.sesionActual.getEvaluador());
				params.put("aspirante", aspirante.getNombre());
				params.put("edad", aspirante.getEdad() + " años");
				params.put("escolaridad", aspirante.getEscolaridad());
				params.put("puesto", aspirante.getPuesto());
				if (puntuacion == -1)
					params.put("puntuacion", "0 puntos");
				else
					params.put("puntuacion", puntuacion + " puntos");
				params.put("fecha", sesionActual.getFecha().toString());
				
				List<Map<String, String>> listaPreguntas = new LinkedList<>();
				List<Pregunta> preguntas = this.dao.obtenerPreguntas(aspirante);
				int i = 0;
				for (Pregunta p : preguntas) {
					Map<String, String> campos = new HashMap<>();
					campos.put("numero", (++i) + "");
					campos.put("pregunta", p.getPregunta());
					if (p.getRespuesta() == null)
						campos.put("respuesta", "N/R");
					else
						campos.put("respuesta", p.getRespuesta());
					if (p.isCorrecta())
						campos.put("correcta", "Correcta");
					else
						campos.put("correcta", "Incorrecta");
					
					listaPreguntas.add(campos);
				}
				
				params.put("imagen", "images/evaluacion.png");
				
				// DataSource para el reporte
				JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaPreguntas);
				this.mostrarReporte("reports/evaluacion.jrxml", params, dataSource);
			} else
				this.mostrarMensajeDeError(this.ventanaEvaluador, "No se ha seleccionado un aspirante");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	public JasperPrint generarJasperPrint(String archivo, Map<String, Object> params, JRBeanCollectionDataSource dataSource) {
		// compilar el reporte
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(archivo);
		JasperReport report;
		try {
			report = JasperCompileManager.compileReport(is);
			return JasperFillManager.fillReport(report, params, dataSource);
		} catch (JRException e) {
			e.printStackTrace();
		}
		return null;
				
	}
	
	public Map<String, Object> generarParametrosAspirante(Aspirante aspirante) {
		Map<String, Object> params = new HashMap<>();
		int puntuacion = this.dao.obtenerPuntuacion(aspirante);
		params.put("evaluador", this.sesionActual.getEvaluador());
		params.put("aspirante", aspirante.getNombre());
		params.put("edad", aspirante.getEdad() + " años");
		params.put("escolaridad", aspirante.getEscolaridad());
		params.put("puesto", aspirante.getPuesto());
		if (puntuacion == -1)
			params.put("puntuacion", "0 puntos");
		else
			params.put("puntuacion", puntuacion + " puntos");
		params.put("fecha", sesionActual.getFecha().toString());
		params.put("imagen", "images/evaluacion.png");
		return params;
	}
	
	public List<Map<String, String>> generarListaPreguntasReporte(Aspirante aspirante) {
		List<Map<String, String>> listaPreguntas = new LinkedList<>();
		List<Pregunta> preguntas = this.dao.obtenerPreguntas(aspirante);
		int i = 0;
		for (Pregunta p : preguntas) {
			Map<String, String> campos = new HashMap<>();
			campos.put("numero", (++i) + "");
			campos.put("pregunta", p.getPregunta());
			if (p.getRespuesta() == null)
				campos.put("respuesta", "N/R");
			else
				campos.put("respuesta", p.getRespuesta());
			if (p.isCorrecta())
				campos.put("correcta", "Correcta");
			else
				campos.put("correcta", "Incorrecta");
			
			listaPreguntas.add(campos);
		}
		return listaPreguntas;
	}
	
	private void mostrarReporte(String archivo, Map<String, Object> params, JRBeanCollectionDataSource dataSource) throws JRException {
		// compilar el reporte
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(archivo);
		JasperReport report = JasperCompileManager.compileReport(is);
		// llenar el reporte con los datos
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
		// mostrar el reporte en el JasperViewer
		JasperViewer.viewReport(jasperPrint, false);
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
