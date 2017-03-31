package mx.edu.ulsaoaxaca.evaluador.misc;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ClienteRMI;

public class ListaClientesCellRenderer extends JLabel implements ListCellRenderer<ClienteRMI> {

	
	@Override
	public Component getListCellRendererComponent(JList<? extends ClienteRMI> list, ClienteRMI value, int index,
			boolean isSelected, boolean cellHasFocus) {
		String nombre = value.toString();
		setText(nombre);
		return this;
	}

}
