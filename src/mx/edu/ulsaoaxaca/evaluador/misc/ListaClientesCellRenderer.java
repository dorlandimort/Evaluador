package mx.edu.ulsaoaxaca.evaluador.misc;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ListaClientesCellRenderer extends JLabel implements ListCellRenderer<ClienteListModel> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4912781787312264999L;

	@Override
	public Component getListCellRendererComponent(JList<? extends ClienteListModel> list, ClienteListModel value, int index,
			boolean isSelected, boolean cellHasFocus) {
		String nombre = value.toString();
		setText(nombre);
		return this;
	}

}
