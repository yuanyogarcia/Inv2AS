/*
 Monopoly para cliente Inv2AS”
 Proyectos Informáticos II - 2019
 Grupo C (Juan Jose Garcia, Manuel Angel Mateos, Jaime Ojeda)
 */

import java.io.*;
import java.util.Scanner;

public class ListaTablero {
	
	private Casilla primera;
	private Casilla actual;
	
private Casilla primero;

  	public ListaTablero() {	
  		this.generarTablero(); 
  		primero = null;	
  	}
  
  	public void generarTablero(){
		try {
			File boardSpace = new File("casillas.txt");	
			Scanner boardScanner = new Scanner(boardSpace);
			
			for(int i = 1; i <= 40; i++){	
				//Cada case del switch se corresponde con un tipo de casilla, que a su vez tiene atributos diferentes (nombre, precio, alquiler...)
				switch(boardScanner.nextLine().charAt(0)){		
	  			case 's':		//Salida									
	  				String nombreSalida = boardScanner.nextLine();
	  				Casilla nPNodo = new SalidaCasilla('s', nombreSalida, i);
	  				anadirPrimera(nPNodo);
	  				break;
	  			case 'c': //Calle
	  				String nombreCalle = boardScanner.nextLine();	
	  				String colorCalle = boardScanner.nextLine();
	  				String precioCalle = boardScanner.nextLine();
	  				String costeConstruccionCalle = boardScanner.nextLine();
	  				String alquilerCalle = boardScanner.nextLine();
	  				String alquilerUnaCasaCalle = boardScanner.nextLine();
	  				Casilla nPNodo1 = new PropiedadCasilla('c', nombreCalle, colorCalle, precioCalle, costeConstruccionCalle, alquilerCalle, alquilerUnaCasaCalle, i);
	  				anadirUltima(nPNodo1);
	  				break;
	  			case 'a': //Comunidad
	  				String nombreComunidad = boardScanner.nextLine();
	  				Casilla nPNodo2 = new ComunidadCasilla('a', nombreComunidad, i);
	  				anadirUltima(nPNodo2);
	  				break;
	  			case 't': //Impuesto
	  				String nombreImpuesto = boardScanner.nextLine();
	  				String impuesto = boardScanner.nextLine();
	  				Casilla nPNodo3 = new ImpuestoCasilla('t', nombreImpuesto, impuesto, i);
	  				anadirUltima(nPNodo3);
	  				break;
	  			case 'f': //Ferrocarril
	  				String nombreFerrocarril = boardScanner.nextLine();
	  				String colorFerrocarril = boardScanner.nextLine();
	  				String precioFerrocarril = boardScanner.nextLine();
	  				String alquilerFerrocarril = boardScanner.nextLine();
	  				Casilla nPNodo4 = new FerrocarrilCasilla('r', nombreFerrocarril, colorFerrocarril, precioFerrocarril, null, alquilerFerrocarril, null, i);
	  				anadirUltima(nPNodo4);
	  				break;
	  			case 'h': //Suerte
	  				String suerteNombre = boardScanner.nextLine();
	  				Casilla nPNodo5 = new SuerteCasilla('h', suerteNombre, i);
	  				anadirUltima(nPNodo5);
	  				break;
//	  			case 'j': //Cárcel
//	  				String nombreCarcel = boardScanner.nextLine();
//	  				Casilla nPNodo6 = new CarcelCasilla('j', nombreCarcel, i);
//	  				anadirUltima(nPNodo6);
//	  				break;
//	  			case 'u': //Servicios
//	  				String nombreServicios = boardScanner.nextLine();
//	  				String colorServicios = boardScanner.nextLine();
//	  				String precioServicios = boardScanner.nextLine();
//	  				String alquilerServicios = boardScanner.nextLine();
//	  				Casilla nPNodo7 = new UtilitySpace('u', nombreServicios, colorServicios, precioServicios, null, alquilerServicios, null, i);
//	  				anadirUltima(nPNodo7);
//	  				break;
//	  			case 'k': //Parking Gratis
//	  				String nombreParking = boardScanner.nextLine();
//	  				Casilla nPNodo8 = new FreeSpace('f', nombreParking, i);
//	  				anadirUltima(nPNodo8);
//	  				break;
//	  			case 'w': //A la cárcel
//	  				String nombreAlaCarcel = boardScanner.nextLine();
//	  				Casilla nPNodo9 = new ToJailSpace('w', nombreAlaCarcel, i);
//	  				anadirUltima(nPNodo9);
//	  				break;
	  			default:
	  				break;
	  				}
			}
		} 
		catch (FileNotFoundException e) {e.printStackTrace();}
  	}
  	
	public void anadirPrimera(Casilla nPNodo) { 
		primera = nPNodo;
  		actual = primera;
  	}

  	public void anadirUltima(Casilla nPNodo) {
  		actual.ponerSiguiente(nPNodo);			
  		actual = nPNodo;
  		actual.ponerSiguiente(primera);
  	}
  	
  	public Casilla obtenerPrimera() {	//nos devuelve al principio
		return primera;
	}	
		

  	
}