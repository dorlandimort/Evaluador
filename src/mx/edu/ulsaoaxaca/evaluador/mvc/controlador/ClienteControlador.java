package mx.edu.ulsaoaxaca.evaluador.mvc.controlador;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Aspirante;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Pregunta;
import mx.edu.ulsaoaxaca.evaluador.mvc.vista.VentanaAspirante;
import mx.edu.ulsaoaxaca.evaluador.mvc.vista.VentanaRegistro;
import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ClienteRMI;
import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ClienteRMIImpl;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ClienteControlador implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3550497967787488932L;
	private VentanaRegistro ventanaRegistro;
	private VentanaAspirante ventanaAspirante;
	private ClienteRMI clienteRMI;
	private String nombre;
	private String puesto;
	private String escolaridad;
	private String ip;
	private int edad;
	
	private Pregunta preguntaSeleccionada;
	
	public ClienteControlador() {
		this.ventanaRegistro = new VentanaRegistro();
		this.mostrarVentanaRegistro();
		this.init();
	}
	
	public void init() {
		this.ventanaRegistro.getBtnIngresar().addActionListener(e -> {
			/**
			 * Tenemos que validar que los campos no estan vacios
			 * Si no estan vacios creamos un Aspirante
			 */
			if (validarCampos()) {
				String ip = this.ventanaRegistro.getTxtIp().getText();
				System.out.println(ip);
				try {
					
					/**
					 * Poner las propiedades al aspirante
					 */
					Aspirante aspirante = new Aspirante();
					aspirante.setNombre(this.nombre);
					aspirante.setEscolaridad(this.escolaridad);
					aspirante.setEdad(this.edad);
					aspirante.setPuesto(this.puesto);
					
					// Conectar al servidor RMI mediante la creación de un clienteRMI
					this.iniciarRMI(ip, aspirante);
					String evaluador;
					
					/**
					 * mandar a llamar al metodo remoto para guardar un aspirante
					 */
					aspirante = this.clienteRMI.registrarAspirante(aspirante);
					/**
					 * Si el aspirante retornado es null o su id es -1 no se guardó
					 * Entonces debemos tirar un error
					 */
					if (aspirante == null || aspirante.getId() == -1) {
						this.mostrarError("Error al intentar registrar, intente nuevamente o verifique sus datos.");
					} else {
						evaluador = this.clienteRMI.obtenerEvaluador();
						this.mostrarVentanaClientePrincipal();
						this.ventanaAspirante.getPanelRespuestas().getLblEvaluador().setText(evaluador);
					}
					
				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(this.ventanaRegistro, "Ocurrió un error en la conexión al servidor");
					e1.printStackTrace();
				}
			}
			
		});
		
		
		this.ventanaRegistro.getBtnSalir().addActionListener(e -> {
			this.ventanaRegistro.dispose();
			
		});
		
		
	}
	
	public void mostrarVentanaRegistro() {
		this.ventanaRegistro.setVisible(true);
	}
	
	public void cerrarVentanaRegistro()  {
		this.ventanaRegistro.setVisible(false);
	}
	
	public void limpiarVentanaRegistro() {
		this.ventanaRegistro.getTxtEscolaridad().setText("");
		this.ventanaRegistro.getTxtNombre().setText("");
		this.ventanaRegistro.getTxtPuesto().setText("");
	}
	
	public void mostrarVentanaClientePrincipal() {
		this.limpiarVentanaRegistro();
		this.ventanaAspirante = new VentanaAspirante();
		this.ventanaAspirante.getPanelRespuestas().getBtnSalir().addActionListener(e -> {
			int salir = JOptionPane.showConfirmDialog(this.ventanaAspirante, "Todo su progreso se perderá!", "Está seguro que desea salir?", JOptionPane.YES_NO_OPTION);
			if (salir == JOptionPane.YES_NO_OPTION) {
				this.mostrarVentanaRegistro();
				this.cerrarVentanaClientePrincipal();
			}
		});
		
		this.ventanaAspirante.getPanelRespuestas().getPanelPreguntasCliente().getPreguntasLista().addListSelectionListener(e -> {
			this.preguntaSeleccionada = (Pregunta)
					this.ventanaAspirante.getPanelRespuestas().getPanelPreguntasCliente().getPreguntasLista().getSelectedValue();
		});
		
		this.ventanaAspirante.getPanelRespuestas().getBtnEnviarRespuesta().addActionListener(e -> {
			if (this.preguntaSeleccionada != null) {
				int index = 
						this.ventanaAspirante.getPanelRespuestas().getPanelPreguntasCliente().getPreguntasLista().getSelectedIndex();
				String respuesta = this.ventanaAspirante.getPanelRespuestas().getTxtRespuesta().getText();
				if (! respuesta.isEmpty()) {
					try {
						this.preguntaSeleccionada.setRespuesta(respuesta);
						this.clienteRMI.enviarRespuesta(this.preguntaSeleccionada);
						this.ventanaAspirante.getPanelRespuestas().getPanelPreguntasCliente().getPreguntasModelo().removeElementAt(index);
						this.ventanaAspirante.getPanelRespuestas().getTxtRespuesta().setText("");
					} catch (RemoteException e1) {
						this.mostrarError("Error de conexión al servidor");
						e1.printStackTrace();
					}
				} else {
					this.mostrarError("La respuesta no debe estar vacía");
				}
					
			} else
				this.mostrarError("Seleccione una pregunta a responder");
			
		});
		this.ventanaAspirante.getPanelRespuestas().getBtnReporte().addActionListener(e -> {
			try {
				JasperPrint jasperPrint = this.clienteRMI.obtenerReporte(this.clienteRMI.getAspirante());
				JasperViewer.viewReport(jasperPrint, false);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		this.ventanaAspirante.setVisible(true);
		this.cerrarVentanaRegistro();
		
		this.ventanaAspirante.addWindowListener(new WindowListener() {

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
					clienteRMI.desconectar();
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
	
	public void cerrarVentanaClientePrincipal() {
		this.ventanaAspirante.dispose();
	}
	
	private void iniciarRMI(String host, Aspirante aspirante) {
		this.clienteRMI = new ClienteRMIImpl(host, 1099, this, aspirante);
		
	}
	
	
	public boolean validarCampos() {
		 this.nombre = this.ventanaRegistro.getTxtNombre().getText();
		 this.puesto = this.ventanaRegistro.getTxtPuesto().getText();
		 this.escolaridad = this.ventanaRegistro.getTxtEscolaridad().getText();
		 this.ip = this.ventanaRegistro.getTxtIp().getText();
		 this.edad = (int) this.ventanaRegistro.getTxtEdad().getSelectedItem();
		
		if ( ! nombre.isEmpty() && ! puesto.isEmpty() && ! escolaridad.isEmpty() && !ip.isEmpty())
			return true;
		this.mostrarError("Por favor complete todos los campos.");
		return false;
		
	}
	
	public void mostrarError(String error) {
		JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void mostrarMensaje(String mensaje) {
		 Thread t = new Thread(new Runnable(){
		        public void run(){
		        	JOptionPane.showMessageDialog(ventanaAspirante, mensaje, 
		        			"Pregunta", JOptionPane.INFORMATION_MESSAGE);
		        }
		    });
		  t.start();
	}
	
	public void agregarPregunta(Pregunta pregunta) {
		this.ventanaAspirante.getPanelRespuestas().getPanelPreguntasCliente().getPreguntasModelo().addElement(pregunta);
	}
	
	
	/**
	 * Getters y Setters
	 * 
	 */

	public VentanaRegistro getVentanaRegistro() {
		return ventanaRegistro;
	}

	public void setVentanaRegistro(VentanaRegistro ventanaRegistro) {
		this.ventanaRegistro = ventanaRegistro;
	}

	public VentanaAspirante getVentanaAspirante() {
		return ventanaAspirante;
	}

	public void setVentanaAspirante(VentanaAspirante ventanaAspirante) {
		this.ventanaAspirante = ventanaAspirante;
	}

}
