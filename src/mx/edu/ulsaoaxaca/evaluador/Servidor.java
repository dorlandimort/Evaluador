package mx.edu.ulsaoaxaca.evaluador;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import mx.edu.ulsaoaxaca.evaluador.mvc.controlador.EvaluadorControlador;
import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ServidorRMI;
import mx.edu.ulsaoaxaca.evaluador.servicios.rmi.ServidorRMIImpl;


public class Servidor {
	
	
	public static void main(String ... args) {
		
		
		try {
			EvaluadorControlador controlador = new EvaluadorControlador();
			ServidorRMI obj = new ServidorRMIImpl(controlador);
			ServidorRMI server = (ServidorRMI) UnicastRemoteObject.exportObject(obj, 0);
			controlador.setServer(server);
			Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ServidorEvaluador", server);
            
            System.err.println("Server ready");
            
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
}
