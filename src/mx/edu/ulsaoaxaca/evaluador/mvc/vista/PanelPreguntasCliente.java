package mx.edu.ulsaoaxaca.evaluador.mvc.vista;

import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Pregunta;

public class PanelPreguntasCliente extends JPanel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7518765511383113914L;
	
	private JList<Pregunta> preguntasLista;
	private DefaultListModel<Pregunta> preguntasModelo;
	
	public PanelPreguntasCliente() {
		this.setLayout(null);
		TitledBorder title = BorderFactory.createTitledBorder("Preguntas pendientes");
		this.setBorder(title);
		JPanel panelPreguntasLista = new JPanel();
		panelPreguntasLista.setBounds(10, 20, 900, 200);
		this.preguntasModelo = new DefaultListModel<>();
		this.preguntasLista = new JList<>(this.preguntasModelo);
		this.preguntasLista.setBounds(15, 15, 850, 170);
		JScrollPane scroll = new JScrollPane(this.preguntasLista);
		scroll.setPreferredSize(new Dimension(850, 170));
		panelPreguntasLista.add(scroll);
		this.add(panelPreguntasLista);
		this.setVisible(true);		
	}

	public JList<Pregunta> getPreguntasLista() {
		return preguntasLista;
	}

	public void setPreguntasLista(JList<Pregunta> preguntasLista) {
		this.preguntasLista = preguntasLista;
	}

	public DefaultListModel<Pregunta> getPreguntasModelo() {
		return preguntasModelo;
	}

	public void setPreguntasModelo(DefaultListModel<Pregunta> preguntasModelo) {
		this.preguntasModelo = preguntasModelo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
	