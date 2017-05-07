package mx.edu.ulsaoaxaca.evaluador.misc;

import java.io.Serializable;
import java.rmi.RemoteException;

import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ClienteRMI;

public class ClienteListModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8395715832069228460L;
	
	private int id;
	private ClienteRMI cliente;
	
	public ClienteListModel(int id, ClienteRMI cliente) {
		this.id = id;
		this.cliente = cliente;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ClienteRMI getCliente() {
		return cliente;
	}
	public void setCliente(ClienteRMI cliente) {
		this.cliente = cliente;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String toString() {
		try {
			return this.cliente.getAspirante().getNombre();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
