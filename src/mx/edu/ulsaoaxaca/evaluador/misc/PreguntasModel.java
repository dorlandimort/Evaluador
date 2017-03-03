package mx.edu.ulsaoaxaca.evaluador.misc;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class PreguntasModel extends DefaultTableModel {
	
	public PreguntasModel(Object[][] data, String title[]) {
	      super(data, title);
	    }
	
	@Override
    public Class<?> getColumnClass(int columnIndex) {
      Class clazz = String.class;
      switch (columnIndex) {
        case 0:
          clazz = Integer.class;
          break;
        case 2:
          clazz = Boolean.class;
          break;
      }
      return clazz;
    }
	
	
  

}
